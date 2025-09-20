@echo off
echo Starting SushrutAI Health Assistant...
echo.

REM Check if Java is installed
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo Error: Java is not installed or not in PATH
    echo Please install Java 17 or higher
    pause
    exit /b 1
)

REM Check if Maven wrapper exists
if exist "mvnw.cmd" (
    echo Using Maven wrapper...
    call mvnw.cmd clean spring-boot:run
) else (
    echo Maven wrapper not found. Trying global Maven...
    mvn clean spring-boot:run
)

echo.
echo SushrutAI Health Assistant started!
echo API available at: http://localhost:8080/api/dialog/health
echo H2 Console at: http://localhost:8080/h2-console
echo.
pause