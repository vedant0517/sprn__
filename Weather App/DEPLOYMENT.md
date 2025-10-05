# Weather App Deployment Guide

## Production Deployment

### Prerequisites
- Node.js 14+ installed on server
- OpenWeather API key
- Domain name (optional)
- SSL certificate (recommended)

### Environment Setup

1. **Server Configuration**
   ```bash
   # Update system packages
   sudo apt update && sudo apt upgrade -y
   
   # Install Node.js and npm
   curl -fsSL https://deb.nodesource.com/setup_18.x | sudo -E bash -
   sudo apt-get install -y nodejs
   ```

2. **Application Deployment**
   ```bash
   # Clone or upload project files
   git clone <your-repo-url>
   cd weather-app
   
   # Install dependencies
   npm install --production
   
   # Set up environment variables
   cp .env.example .env
   nano .env  # Add your API key
   ```

3. **Process Management**
   ```bash
   # Install PM2 for process management
   npm install -g pm2
   
   # Start application with PM2
   pm2 start server.js --name "weather-app"
   pm2 startup
   pm2 save
   ```

### Nginx Configuration (Optional)

```nginx
server {
    listen 80;
    server_name your-domain.com;
    
    location / {
        proxy_pass http://localhost:3000;
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection 'upgrade';
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
        proxy_cache_bypass $http_upgrade;
    }
}
```

## Performance Optimization

### Caching Strategy
- Enable API response caching (10 minutes for weather data)
- Implement client-side caching for static assets
- Use gzip compression for responses

### Security Measures
- Rate limiting for API endpoints
- Input sanitization and validation
- CORS policy configuration
- Environment variable protection

### Monitoring
- Set up application logging
- Monitor API usage and errors
- Track response times and performance
- Set up health checks

## Backup and Recovery

### Data Backup
```bash
# Backup cities data
cp data/cities.json backups/cities-$(date +%Y%m%d).json

# Automated daily backup
crontab -e
0 2 * * * /path/to/backup-script.sh
```

### Recovery Procedures
1. Restore from backup files
2. Restart application services
3. Verify data integrity
4. Test all functionality

---
*Last Updated: October 3, 2025*