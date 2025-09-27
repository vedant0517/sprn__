const express = require('express');
const fs = require('fs').promises;
const path = require('path');
const { js2xml } = require('xml-js');

const router = express.Router();
const CITIES_FILE = path.join(__dirname, '../data/cities.json');

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

// GET /api/export/xml - Export cities as XML
router.get('/xml', async (req, res) => {
    try {
        const cities = await readCitiesFromFile();

        if (cities.length === 0) {
            return res.status(404).json({
                success: false,
                message: 'No cities found to export'
            });
        }

        // Prepare data for XML conversion
        const xmlData = {
            _declaration: {
                _attributes: {
                    version: '1.0',
                    encoding: 'UTF-8'
                }
            },
            cities: {
                _attributes: {
                    count: cities.length,
                    exported: new Date().toISOString()
                },
                city: cities.map(city => ({
                    _attributes: {
                        id: city.id
                    },
                    name: { _text: city.name },
                    createdAt: { _text: city.createdAt },
                    ...(city.updatedAt && { updatedAt: { _text: city.updatedAt } })
                }))
            }
        };

        // Convert to XML
        const xmlString = js2xml(xmlData, { 
            compact: true, 
            ignoreComment: true, 
            spaces: 2 
        });

        // Set headers for file download
        const timestamp = new Date().toISOString().replace(/[:.]/g, '-');
        const filename = `weather-app-cities-${timestamp}.xml`;

        res.setHeader('Content-Type', 'application/xml');
        res.setHeader('Content-Disposition', `attachment; filename="${filename}"`);
        res.setHeader('Access-Control-Expose-Headers', 'Content-Disposition');

        res.send(xmlString);
    } catch (error) {
        console.error('XML export error:', error);
        res.status(500).json({
            success: false,
            message: 'Failed to export cities as XML',
            error: error.message
        });
    }
});

// GET /api/export/json - Export cities as JSON (bonus endpoint)
router.get('/json', async (req, res) => {
    try {
        const cities = await readCitiesFromFile();

        if (cities.length === 0) {
            return res.status(404).json({
                success: false,
                message: 'No cities found to export'
            });
        }

        const exportData = {
            exportInfo: {
                count: cities.length,
                exported: new Date().toISOString(),
                version: '1.0'
            },
            cities: cities
        };

        // Set headers for file download
        const timestamp = new Date().toISOString().replace(/[:.]/g, '-');
        const filename = `weather-app-cities-${timestamp}.json`;

        res.setHeader('Content-Type', 'application/json');
        res.setHeader('Content-Disposition', `attachment; filename="${filename}"`);
        res.setHeader('Access-Control-Expose-Headers', 'Content-Disposition');

        res.json(exportData);
    } catch (error) {
        console.error('JSON export error:', error);
        res.status(500).json({
            success: false,
            message: 'Failed to export cities as JSON',
            error: error.message
        });
    }
});

module.exports = router;