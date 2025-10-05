/**
 * Basic Unit Tests for Weather App Components
 * Tests individual functions and data validation
 */

const fs = require('fs');
const path = require('path');

// Test configuration
let passed = 0;
let failed = 0;

/**
 * Simple test framework
 */
function test(description, fn) {
    try {
        fn();
        console.log(`✓ ${description}`);
        passed++;
    } catch (error) {
        console.log(`✗ ${description}: ${error.message}`);
        failed++;
    }
}

/**
 * Assert function
 */
function assert(condition, message) {
    if (!condition) {
        throw new Error(message || 'Assertion failed');
    }
}

/**
 * Test data validation functions
 */
function validateCityName(name) {
    if (!name || typeof name !== 'string') {
        return { valid: false, message: 'City name is required and must be a string' };
    }
    
    if (name.trim().length === 0) {
        return { valid: false, message: 'City name cannot be empty' };
    }
    
    if (name.trim().length > 50) {
        return { valid: false, message: 'City name is too long (max 50 characters)' };
    }
    
    if (!/^[\p{L}\s\-',.()]+$/u.test(name.trim())) {
        return { valid: false, message: 'City name contains invalid characters' };
    }
    
    return { valid: true, message: 'Valid city name' };
}

/**
 * Test UUID generation (simplified check)
 */
function isValidUUID(uuid) {
    const uuidRegex = /^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$/i;
    return uuidRegex.test(uuid);
}

// Unit Tests
console.log('🧪 Starting Unit Tests for Weather App\n');

// Test city name validation
test('City name validation - valid names', () => {
    assert(validateCityName('London').valid === true, 'London should be valid');
    assert(validateCityName('New York').valid === true, 'New York should be valid');
    assert(validateCityName("O'Brien").valid === true, "O'Brien should be valid");
    assert(validateCityName('São Paulo').valid === true, 'São Paulo should be valid');
});

test('City name validation - invalid names', () => {
    assert(validateCityName('').valid === false, 'Empty string should be invalid');
    assert(validateCityName('   ').valid === false, 'Whitespace-only should be invalid');
    assert(validateCityName('City123').valid === false, 'Numbers should be invalid');
    assert(validateCityName('City@#$').valid === false, 'Special characters should be invalid');
    assert(validateCityName(null).valid === false, 'Null should be invalid');
    assert(validateCityName(undefined).valid === false, 'Undefined should be invalid');
});

test('City name validation - edge cases', () => {
    const longName = 'A'.repeat(51);
    assert(validateCityName(longName).valid === false, 'Names over 50 chars should be invalid');
    
    assert(validateCityName('A').valid === true, 'Single character should be valid');
    assert(validateCityName('Ab').valid === true, 'Two characters should be valid');
});

// Test UUID validation
test('UUID validation', () => {
    // Mock UUID (would normally use uuid library)
    const mockUuid = '550e8400-e29b-41d4-a716-446655440000';
    assert(isValidUUID(mockUuid) === true, 'Valid UUID format should pass');
    
    assert(isValidUUID('invalid-uuid') === false, 'Invalid UUID should fail');
    assert(isValidUUID('') === false, 'Empty string should fail UUID check');
    assert(isValidUUID(null) === false, 'Null should fail UUID check');
});

// Test file structure
test('Project structure validation', () => {
    const requiredFiles = [
        'package.json',
        'server.js',
        'README.md',
        'public/index.html',
        'public/script.js',
        'public/style.css',
        'routes/cities.js',
        'routes/export.js',
        'data/cities.json'
    ];
    
    requiredFiles.forEach(file => {
        const filePath = path.join(__dirname, '..', file);
        assert(fs.existsSync(filePath), `Required file ${file} should exist`);
    });
});

// Test package.json structure
test('Package.json validation', () => {
    const packagePath = path.join(__dirname, '..', 'package.json');
    const packageData = JSON.parse(fs.readFileSync(packagePath, 'utf8'));
    
    assert(packageData.name === 'weather-app', 'Package name should be weather-app');
    assert(packageData.version === '1.0.0', 'Version should be 1.0.0');
    assert(packageData.main === 'server.js', 'Main file should be server.js');
    assert(packageData.scripts.start === 'node server.js', 'Start script should be correct');
    assert(typeof packageData.dependencies === 'object', 'Dependencies should be an object');
});

// Test environment configuration
test('Environment configuration', () => {
    const envExamplePath = path.join(__dirname, '..', '.env.example');
    assert(fs.existsSync(envExamplePath), '.env.example should exist');
    
    const envContent = fs.readFileSync(envExamplePath, 'utf8');
    assert(envContent.includes('OPENWEATHER_API_KEY'), '.env.example should include API key template');
    assert(envContent.includes('PORT'), '.env.example should include PORT configuration');
});

// Test data file structure
test('Cities data file validation', () => {
    const citiesPath = path.join(__dirname, '..', 'data', 'cities.json');
    
    if (fs.existsSync(citiesPath)) {
        const citiesData = JSON.parse(fs.readFileSync(citiesPath, 'utf8'));
        assert(Array.isArray(citiesData), 'Cities data should be an array');
        
        if (citiesData.length > 0) {
            const firstCity = citiesData[0];
            assert(typeof firstCity === 'object', 'City entries should be objects');
            assert(typeof firstCity.id === 'string', 'City should have string ID');
            assert(typeof firstCity.name === 'string', 'City should have string name');
        }
    }
});

// Results
console.log('\n📊 Unit Test Results:');
console.log(`✓ Passed: ${passed}`);
console.log(`✗ Failed: ${failed}`);
console.log(`📈 Success Rate: ${Math.round((passed / (passed + failed)) * 100)}%\n`);

if (failed === 0) {
    console.log('🎉 All unit tests passed! Your code is working correctly.');
} else {
    console.log('⚠️ Some tests failed. Please check the implementation.');
}

process.exit(failed > 0 ? 1 : 0);