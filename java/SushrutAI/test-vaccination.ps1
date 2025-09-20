# SushrutAI Vaccination System Test Script

# Test 1: Create a user first
Write-Host "=== Testing SushrutAI Vaccination System ===" -ForegroundColor Green

# Test User Registration
$userResponse = Invoke-RestMethod -Uri "http://localhost:8080/api/dialog/health" -Method POST -Body @{
    phoneNumber = "+918765432101"
    message = "हैलो, मैं बच्चे का टीकाकरण शेड्यूल जानना चाहती हूं"
    language = "HINDI"
    district = "Bolangir"
    state = "Odisha"
    ageGroup = "ADULT_18_30"
} -ContentType "application/x-www-form-urlencoded"

Write-Host "User Registration Response:" -ForegroundColor Yellow
$userResponse | ConvertTo-Json -Depth 3

# Test UIP Information
Write-Host "`n=== UIP Information ===" -ForegroundColor Green
try {
    $uipResponse = Invoke-RestMethod -Uri "http://localhost:8080/api/vaccination/uip-info" -Method GET
    Write-Host "UIP Schedule Response:" -ForegroundColor Yellow
    $uipResponse | ConvertTo-Json -Depth 4
} catch {
    Write-Host "Error accessing UIP info: $_" -ForegroundColor Red
}

# Test Vaccination Schedule Creation
Write-Host "`n=== Vaccination Schedule Creation ===" -ForegroundColor Green
try {
    $scheduleResponse = Invoke-RestMethod -Uri "http://localhost:8080/api/vaccination/schedule" -Method POST -Body @{
        anonymousId = "anon_8765432101_1726427409"
        birthDate = "2024-01-15"  # Baby born in January 2024
    } -ContentType "application/x-www-form-urlencoded"
    
    Write-Host "Schedule Creation Response:" -ForegroundColor Yellow
    $scheduleResponse | ConvertTo-Json -Depth 3
} catch {
    Write-Host "Error creating schedule: $_" -ForegroundColor Red
}

# Test Getting Vaccination Schedule
Write-Host "`n=== Getting Vaccination Schedule ===" -ForegroundColor Green
try {
    $getScheduleResponse = Invoke-RestMethod -Uri "http://localhost:8080/api/vaccination/schedule/anon_8765432101_1726427409" -Method GET
    
    Write-Host "Current Schedule Response:" -ForegroundColor Yellow
    $getScheduleResponse | ConvertTo-Json -Depth 3
} catch {
    Write-Host "Error getting schedule: $_" -ForegroundColor Red
}

# Test Overdue Vaccinations
Write-Host "`n=== Overdue Vaccinations ===" -ForegroundColor Green
try {
    $overdueResponse = Invoke-RestMethod -Uri "http://localhost:8080/api/vaccination/overdue/anon_8765432101_1726427409" -Method GET
    
    Write-Host "Overdue Vaccinations Response:" -ForegroundColor Yellow
    $overdueResponse | ConvertTo-Json -Depth 3
} catch {
    Write-Host "Error getting overdue vaccinations: $_" -ForegroundColor Red
}

# Test Vaccination Reminders
Write-Host "`n=== Vaccination Reminders ===" -ForegroundColor Green
try {
    $reminderResponse = Invoke-RestMethod -Uri "http://localhost:8080/api/vaccination/reminders/anon_8765432101_1726427409" -Method GET
    
    Write-Host "Reminders Response:" -ForegroundColor Yellow
    $reminderResponse | ConvertTo-Json -Depth 3
} catch {
    Write-Host "Error getting reminders: $_" -ForegroundColor Red
}

Write-Host "`n=== Testing Complete ===" -ForegroundColor Green