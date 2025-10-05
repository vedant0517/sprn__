require('dotenv').config();
const express = require('express');
const cors = require('cors');
const path = require('path');
const citiesRoutes = require('./routes/cities');
const exportRoutes = require('./routes/export');

const app = express();
const PORT = process.env.PORT || 3000;

// Middleware
app.use(cors());
app.use(express.json());
app.use(express.static('public'));

// Routes
app.use('/api/cities', citiesRoutes);
app.use('/api/export', exportRoutes);

// Serve the main HTML file
app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'public', 'index.html'));
});

// Test endpoint
app.get('/test', (req, res) => {
    res.json({
        success: true,
        message: 'Weather App Server is running!',
        timestamp: new Date().toISOString(),
        endpoints: {
            cities: '/api/cities',
            export: '/api/export/xml'
        }
    });
});

// Error handling middleware with detailed logging
app.use((error, req, res, next) => {
    const timestamp = new Date().toISOString();
    const errorLog = {
        timestamp,
        method: req.method,
        url: req.url,
        error: error.message,
        stack: error.stack,
        ip: req.ip
    };
    
    console.error('Server Error:', JSON.stringify(errorLog, null, 2));
    
    res.status(500).json({ 
        success: false, 
        message: 'Internal server error',
        error: process.env.NODE_ENV === 'development' ? error.message : 'Something went wrong'
    });
});

// 404 handler
app.use('*', (req, res) => {
    res.status(404).json({ 
        success: false, 
        message: 'Route not found' 
    });
});

app.listen(PORT, () => {
    console.log(`🌤️  Weather App server running on http://localhost:${PORT}`);
    console.log(`📁 Serving static files from ./public`);
    console.log(`🔗 API endpoints:`);
    console.log(`   GET    /api/cities     - Get all cities`);
    console.log(`   POST   /api/cities     - Add new city`);
    console.log(`   PUT    /api/cities/:id - Update city`);
    console.log(`   DELETE /api/cities/:id - Delete city`);
    console.log(`   GET    /api/export/xml - Export cities as XML`);
});