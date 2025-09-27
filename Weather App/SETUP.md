# 🌤️ Weather App Setup Instructions

## Important: Get Your Free OpenWeather API Key

Your weather app is ready, but you need to get a free API key from OpenWeather to see live weather data.

### Step 1: Get API Key (Free - 2 minutes)

1. **Visit**: https://openweathermap.org/api
2. **Click**: "Sign Up" (top right)
3. **Create Account**: Use your email
4. **Verify Email**: Check your inbox and click the verification link
5. **Get API Key**: 
   - Login to your account
   - Go to "API keys" tab
   - Copy the "Default" API key

### Step 2: Configure Your App

1. **Open** the `.env` file in your project
2. **Replace** `your_openweather_api_key_here` with your actual API key:
   ```
   OPENWEATHER_API_KEY=your_actual_api_key_here
   PORT=3000
   ```
3. **Save** the file

### Step 3: Restart Server

1. **Stop** the current server (Ctrl+C in terminal)
2. **Start** again: `npm start`
3. **Open**: http://localhost:3000

## ✅ What's Already Set Up

- **100 Major Indian Cities** pre-loaded
- **Complete CRUD Operations** (Add, Edit, Delete cities)
- **Export Functionality** (XML and JSON)
- **Responsive Design** for all devices
- **Error Handling** for API issues

## 🏙️ Pre-loaded Indian Cities Include:

Major cities: Mumbai, Delhi, Bangalore, Hyderabad, Chennai, Kolkata, Pune, Ahmedabad, Jaipur, Surat, Lucknow, Kanpur, Nagpur, Indore, Bhopal, Visakhapatnam, Patna, Vadodara, Ghaziabad, Ludhiana, Agra, Nashik, Faridabad, Meerut, Rajkot, Varanasi, Srinagar, Aurangabad, Amritsar, Navi Mumbai, Coimbatore, Mysore, Chandigarh, Dehradun, Kochi, Thiruvananthapuram, Noida, Guwahati, Udaipur, and many more!

## 🔧 Troubleshooting

**Problem**: Cities show "API key not configured"
**Solution**: Add your OpenWeather API key to `.env` file

**Problem**: "City not found" error
**Solution**: Check spelling, or try alternative names (e.g., "Bengaluru" instead of "Bangalore")

**Problem**: Can't add new cities
**Solution**: Make sure your API key is working and has free quota remaining

## 🚀 Features You Can Use Right Now

1. **View All Cities**: See the 100 pre-loaded Indian cities
2. **Add New Cities**: Add any city worldwide
3. **Edit Cities**: Change city names
4. **Delete Cities**: Remove cities you don't need
5. **Export Data**: Download your city list as XML or JSON
6. **Refresh Weather**: Click refresh button on any city card

## 📊 API Limits (Free Plan)

- **1,000 calls per day**
- **60 calls per minute**
- This is more than enough for personal use!

---

**Need Help?** Check the README.md file for detailed documentation.

**Ready to Start?** Just get your API key and refresh the page!