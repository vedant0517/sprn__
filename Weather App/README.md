# Weather App - Full Stack CRUD Application

A modern, responsive weather application built with Node.js, Express, and vanilla JavaScript. Features full CRUD operations for managing cities, real-time weather data from OpenWeather API, and data export functionality.

## 🌟 Features

- **Full CRUD Operations**: Add, view, update, and delete cities
- **Real-time Weather Data**: Integration with OpenWeather API
- **Data Export**: Export your cities as XML or JSON
- **Responsive Design**: Works on desktop, tablet, and mobile
- **Local Data Storage**: Uses JSON file for persistent storage
- **Form Validation**: Client and server-side validation
- **Toast Notifications**: User-friendly feedback system
- **Modern UI**: Clean, intuitive interface with smooth animations

## 🛠️ Tech Stack

### Backend
- **Node.js** - Runtime environment
- **Express.js** - Web framework
- **File System (fs)** - Local JSON storage
- **Axios** - HTTP client for API requests
- **UUID** - Unique ID generation
- **xml-js** - XML conversion
- **CORS** - Cross-origin resource sharing

### Frontend
- **HTML5** - Structure
- **CSS3** - Styling with CSS Grid and Flexbox
- **Vanilla JavaScript** - Interactivity
- **Font Awesome** - Icons

## 📁 Project Structure

```
weather-app/
├── public/                 # Frontend files
│   ├── index.html         # Main HTML file
│   ├── style.css          # Styles
│   └── script.js          # JavaScript logic
├── routes/                # API routes
│   ├── cities.js          # Cities CRUD operations
│   └── export.js          # Data export functionality
├── data/                  # Local data storage
│   └── cities.json        # Cities database
├── .github/               # GitHub configuration
│   └── copilot-instructions.md
├── server.js              # Main server file
├── package.json           # Dependencies
├── .env.example           # Environment variables template
└── README.md              # Documentation
```

## 🚀 Quick Start

### Prerequisites

- Node.js (v14 or higher)
- npm or yarn
- OpenWeather API key (free at [openweathermap.org](https://openweathermap.org/api))

### Installation

1. **Clone or download the project**
   ```bash
   cd weather-app
   ```

2. **Install dependencies**
   ```bash
   npm install
   ```

3. **Set up environment variables**
   ```bash
   # Copy the example environment file
   cp .env.example .env
   
   # Edit .env and add your OpenWeather API key
   OPENWEATHER_API_KEY=your_api_key_here
   PORT=3000
   ```

4. **Start the development server**
   ```bash
   npm run dev
   ```
   
   Or for production:
   ```bash
   npm start
   ```

5. **Open your browser**
   Navigate to `http://localhost:3000`

## 🔧 Configuration

### Environment Variables

Create a `.env` file in the root directory:

```env
# OpenWeather API Configuration
OPENWEATHER_API_KEY=your_openweather_api_key_here

# Server Configuration
PORT=3000
NODE_ENV=development
```

### Getting OpenWeather API Key

1. Visit [OpenWeather API](https://openweathermap.org/api)
2. Sign up for a free account
3. Generate an API key
4. Add it to your `.env` file

## 🧪 Testing

The project includes comprehensive testing:

```bash
# Run unit tests (no server required)
npm test

# Run API integration tests (server must be running)
npm run test:api
```

See [TESTING.md](TESTING.md) for detailed testing information.

## 📚 API Documentation

### Cities Endpoints

#### GET /api/cities
Get all cities with weather data
```json
{
  "success": true,
  "data": [
    {
      "id": "uuid",
      "name": "London",
      "createdAt": "2025-01-01T00:00:00.000Z",
      "weather": {
        "temperature": 15,
        "description": "partly cloudy",
        "icon": "02d",
        "humidity": 65,
        "windSpeed": 3.5,
        "country": "GB"
      }
    }
  ],
  "count": 1
}
```

#### POST /api/cities
Add a new city
```json
{
  "name": "Tokyo"
}
```

#### PUT /api/cities/:id
Update a city
```json
{
  "name": "New Tokyo"
}
```

#### DELETE /api/cities/:id
Delete a city

### Export Endpoints

#### GET /api/export/xml
Export cities as XML file

#### GET /api/export/json
Export cities as JSON file

## 🎨 Features Overview

### CRUD Operations

1. **Create**: Add new cities with validation
2. **Read**: Display all cities with real-time weather data
3. **Update**: Edit city names with duplicate checking
4. **Delete**: Remove cities with confirmation

### Weather Integration

- Real-time weather data from OpenWeather API
- Temperature, humidity, wind speed, and weather conditions
- Weather icons and descriptions
- Error handling for unavailable data

### Data Export

- Export saved cities as XML or JSON
- Timestamped filenames
- Automatic file download

### User Experience

- Responsive design for all devices
- Loading states and error handling
- Toast notifications for user feedback
- Modal dialogs for editing and deletion
- Form validation with helpful error messages
- Keyboard shortcuts (Escape to close modals)

## 🔄 Data Flow

1. **Frontend** sends requests to Express.js API
2. **Backend** processes requests and interacts with local JSON file
3. **Weather data** fetched from OpenWeather API
4. **Response** sent back to frontend with combined data
5. **UI updates** dynamically based on the response

## 🧪 Development

### Available Scripts

```bash
# Start development server with auto-reload
npm run dev

# Start production server
npm start

# Install dependencies
npm install
```

### File Structure Details

- **server.js**: Main Express server with middleware and route setup
- **routes/cities.js**: CRUD operations for cities with weather integration
- **routes/export.js**: Data export functionality (XML/JSON)
- **public/**: Static frontend files served by Express
- **data/cities.json**: Local database file (created automatically)

## 🌍 Browser Support

- ✅ Chrome (latest)
- ✅ Firefox (latest)
- ✅ Safari (latest)
- ✅ Edge (latest)
- ✅ Mobile browsers

## 🔒 Security Considerations

- Input validation on both client and server
- CORS protection
- Error handling without exposing sensitive information
- Environment variables for API keys

## 🐛 Troubleshooting

### Common Issues

1. **Weather data not loading**
   - Check your OpenWeather API key
   - Ensure you have internet connection
   - Verify the city name spelling

2. **Cities not saving**
   - Check file permissions in the `data/` directory
   - Ensure the server has write access

3. **Export not working**
   - Check browser's download settings
   - Ensure pop-ups are allowed

### Error Messages

- **"City not found"**: Invalid city name or API issue
- **"City already exists"**: Duplicate city detection
- **"Network error"**: Connection or server issues

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License. See the LICENSE file for details.

## 🙏 Acknowledgments

- [OpenWeather API](https://openweathermap.org/) for weather data
- [Font Awesome](https://fontawesome.com/) for icons
- [Express.js](https://expressjs.com/) for the web framework

---

**Built with ❤️ using Node.js, Express, and modern web technologies**