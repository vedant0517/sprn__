const express = require('express');
const fs = require('fs').promises;
const path = require('path');
const axios = require('axios');
const { v4: uuidv4 } = require('uuid');

const router = express.Router();
const CITIES_FILE = path.join(__dirname, '../data/cities.json');
const OPENWEATHER_API_KEY = process.env.OPENWEATHER_API_KEY || 'your_api_key_here';

// Check if API key is configured
const isApiKeyConfigured = () => {
    return OPENWEATHER_API_KEY && OPENWEATHER_API_KEY !== 'your_api_key_here' && OPENWEATHER_API_KEY.length > 10;
};

// Helper function to read cities from JSON file
async function readCitiesFromFile() {
    try {
        const data = await fs.readFile(CITIES_FILE, 'utf8');
        return JSON.parse(data);
    } catch (error) {
        console.error('Error reading cities file:', error);
        return [];
    }
}

// Helper function to write cities to JSON file
async function writeCitiesToFile(cities) {
    try {
        await fs.writeFile(CITIES_FILE, JSON.stringify(cities, null, 2));
        return true;
    } catch (error) {
        console.error('Error writing cities file:', error);
        return false;
    }
}

// Helper function to get weather data from OpenWeather API
async function getWeatherData(cityName) {
    // Check if API key is configured
    if (!isApiKeyConfigured()) {
        return {
            success: false,
            error: 'Weather API key not configured. Please add your OpenWeather API key to .env file'
        };
    }

    try {
        const response = await axios.get(
            `https://api.openweathermap.org/data/2.5/weather?q=${cityName}&appid=${OPENWEATHER_API_KEY}&units=metric`
        );
        return {
            success: true,
            data: {
                temperature: Math.round(response.data.main.temp),
                description: response.data.weather[0].description,
                icon: response.data.weather[0].icon,
                humidity: response.data.main.humidity,
                windSpeed: response.data.wind.speed,
                country: response.data.sys.country
            }
        };
    } catch (error) {
        console.error('Weather API error:', error.message);
        
        // More specific error messages
        if (error.response) {
            switch (error.response.status) {
                case 401:
                    return {
                        success: false,
                        error: 'Invalid API key. Please check your OpenWeather API key'
                    };
                case 404:
                    return {
                        success: false,
                        error: 'City not found. Please check the spelling'
                    };
                case 429:
                    return {
                        success: false,
                        error: 'API rate limit exceeded. Please try again later'
                    };
                default:
                    return {
                        success: false,
                        error: `Weather API error: ${error.response.status}`
                    };
            }
        }
        
        return {
            success: false,
            error: 'Unable to fetch weather data. Please check your internet connection'
        };
    }
}

// GET /api/cities - Get all cities with weather data
router.get('/', async (req, res) => {
    try {
        const cities = await readCitiesFromFile();
        
        // Fetch weather data for each city
        const citiesWithWeather = await Promise.all(
            cities.map(async (city) => {
                const weatherData = await getWeatherData(city.name);
                return {
                    ...city,
                    weather: weatherData.success ? weatherData.data : null,
                    weatherError: weatherData.success ? null : weatherData.error
                };
            })
        );

        res.json({
            success: true,
            data: citiesWithWeather,
            count: citiesWithWeather.length
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Failed to fetch cities',
            error: error.message
        });
    }
});

// POST /api/cities - Add a new city
router.post('/', async (req, res) => {
    try {
        const { name } = req.body;

        if (!name || typeof name !== 'string' || name.trim().length === 0) {
            return res.status(400).json({
                success: false,
                message: 'City name is required and must be a non-empty string'
            });
        }

        const cityName = name.trim();
        const cities = await readCitiesFromFile();

        // Check for duplicates (case-insensitive)
        const existingCity = cities.find(
            city => city.name.toLowerCase() === cityName.toLowerCase()
        );

        if (existingCity) {
            return res.status(409).json({
                success: false,
                message: 'City already exists in your list'
            });
        }

        // Verify city exists by calling weather API (only if API key is configured)
        if (isApiKeyConfigured()) {
            const weatherData = await getWeatherData(cityName);
            if (!weatherData.success && weatherData.error.includes('City not found')) {
                return res.status(404).json({
                    success: false,
                    message: 'City not found. Please check the spelling and try again.'
                });
            }
        }

        // Create new city object
        const newCity = {
            id: uuidv4(),
            name: cityName,
            createdAt: new Date().toISOString()
        };

        cities.push(newCity);
        await writeCitiesToFile(cities);

        // Get weather data for response
        const weatherData = await getWeatherData(cityName);

        res.status(201).json({
            success: true,
            message: 'City added successfully',
            data: {
                ...newCity,
                weather: weatherData.success ? weatherData.data : null,
                weatherError: weatherData.success ? null : weatherData.error
            }
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Failed to add city',
            error: error.message
        });
    }
});

// PUT /api/cities/:id - Update a city
router.put('/:id', async (req, res) => {
    try {
        const { id } = req.params;
        const { name } = req.body;

        if (!name || typeof name !== 'string' || name.trim().length === 0) {
            return res.status(400).json({
                success: false,
                message: 'City name is required and must be a non-empty string'
            });
        }

        const cityName = name.trim();
        const cities = await readCitiesFromFile();
        const cityIndex = cities.findIndex(city => city.id === id);

        if (cityIndex === -1) {
            return res.status(404).json({
                success: false,
                message: 'City not found'
            });
        }

        // Check for duplicates (excluding current city)
        const existingCity = cities.find(
            (city, index) => 
                index !== cityIndex && 
                city.name.toLowerCase() === cityName.toLowerCase()
        );

        if (existingCity) {
            return res.status(409).json({
                success: false,
                message: 'Another city with this name already exists'
            });
        }

        // Verify new city name exists by calling weather API (only if API key is configured)
        if (isApiKeyConfigured()) {
            const weatherData = await getWeatherData(cityName);
            if (!weatherData.success && weatherData.error.includes('City not found')) {
                return res.status(404).json({
                    success: false,
                    message: 'City not found. Please check the spelling and try again.'
                });
            }
        }

        // Update city
        cities[cityIndex] = {
            ...cities[cityIndex],
            name: cityName,
            updatedAt: new Date().toISOString()
        };

        await writeCitiesToFile(cities);

        // Get weather data for response
        const weatherData = await getWeatherData(cityName);

        res.json({
            success: true,
            message: 'City updated successfully',
            data: {
                ...cities[cityIndex],
                weather: weatherData.success ? weatherData.data : null,
                weatherError: weatherData.success ? null : weatherData.error
            }
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Failed to update city',
            error: error.message
        });
    }
});

// DELETE /api/cities/:id - Delete a city
router.delete('/:id', async (req, res) => {
    try {
        const { id } = req.params;
        const cities = await readCitiesFromFile();
        const cityIndex = cities.findIndex(city => city.id === id);

        if (cityIndex === -1) {
            return res.status(404).json({
                success: false,
                message: 'City not found'
            });
        }

        const deletedCity = cities[cityIndex];
        cities.splice(cityIndex, 1);
        await writeCitiesToFile(cities);

        res.json({
            success: true,
            message: 'City deleted successfully',
            data: deletedCity
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Failed to delete city',
            error: error.message
        });
    }
});

module.exports = router;