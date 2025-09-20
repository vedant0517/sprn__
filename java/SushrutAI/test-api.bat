@echo off
echo Testing SushrutAI Health Assistant APIs...
echo.

REM Check if curl is available
curl --version >nul 2>&1
if %errorlevel% neq 0 (
    echo Warning: curl not found. Install curl or use Postman to test APIs
    echo Available endpoints:
    echo - GET  http://localhost:8080/api/dialog/health
    echo - POST http://localhost:8080/api/dialog/symptom
    echo - GET  http://localhost:8080/api/dialog/vaccination/INFANT_0_1
    echo - GET  http://localhost:8080/api/dialog/emergency
    pause
    exit /b 0
)

echo Testing Health Check endpoint...
curl -X GET http://localhost:8080/api/dialog/health
echo.
echo.

echo Testing Symptom Report endpoint...
curl -X POST http://localhost:8080/api/dialog/symptom ^
  -H "Content-Type: application/json" ^
  -d "{\"symptoms\": \"fever and cough\", \"language\": \"HINDI\", \"severity\": \"MODERATE\"}"
echo.
echo.

echo Testing Vaccination Schedule endpoint...
curl -X GET http://localhost:8080/api/dialog/vaccination/INFANT_0_1
echo.
echo.

echo Testing Emergency Contacts endpoint...
curl -X GET http://localhost:8080/api/dialog/emergency
echo.
echo.

echo API testing completed!
echo.
pause