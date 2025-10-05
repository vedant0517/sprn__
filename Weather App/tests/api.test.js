/**
 * Simple API Tests for Weather App
 * Tests the main endpoints to ensure functionality
 */

const http = require('http');
const fs = require('fs');

// Test configuration
const HOST = 'localhost';
const PORT = 3000;
const BASE_URL = `http://${HOST}:${PORT}`;

// Test results
let tests = [];
let passed = 0;
let failed = 0;

/**
 * Make HTTP request
 */
function makeRequest(method, path, data = null) {
    return new Promise((resolve, reject) => {
        const options = {
            hostname: HOST,
            port: PORT,
            path: path,
            method: method,
            headers: {
                'Content-Type': 'application/json'
            }
        };

        const req = http.request(options, (res) => {
            let body = '';
            res.on('data', (chunk) => {
                body += chunk;
            });
            res.on('end', () => {
                try {
                    const jsonData = JSON.parse(body);
                    resolve({
                        statusCode: res.statusCode,
                        data: jsonData,
                        headers: res.headers
                    });
                } catch (error) {
                    resolve({
                        statusCode: res.statusCode,
                        data: body,
                        headers: res.headers
                    });
                }
            });
        });

        req.on('error', (error) => {
            reject(error);
        });

        if (data) {
            req.write(JSON.stringify(data));
        }

        req.end();
    });
}

/**
 * Test helper function
 */
function test(name, fn) {
    tests.push({ name, fn });
}

/**
 * Assert function
 */
function assert(condition, message) {
    if (condition) {
        console.log(`✓ ${message}`);
        passed++;
    } else {
        console.log(`✗ ${message}`);
        failed++;
    }
}

// Test Cases
test('Server Health Check', async () => {
    try {
        const response = await makeRequest('GET', '/test');
        assert(response.statusCode === 200, 'Server responds with 200');
        assert(response.data.success === true, 'Health check returns success');
        assert(response.data.message === 'Weather App Server is running!', 'Health check message is correct');
    } catch (error) {
        assert(false, `Health check failed: ${error.message}`);
    }
});

test('Get Cities Endpoint', async () => {
    try {
        const response = await makeRequest('GET', '/api/cities');
        assert(response.statusCode === 200, 'Cities endpoint responds with 200');
        assert(response.data.success === true, 'Cities response is successful');
        assert(Array.isArray(response.data.data), 'Cities data is an array');
    } catch (error) {
        assert(false, `Get cities failed: ${error.message}`);
    }
});

test('Add New City', async () => {
    try {
        const testCity = { name: 'Test City API' };
        const response = await makeRequest('POST', '/api/cities', testCity);
        
        // Should succeed or fail with appropriate message
        if (response.statusCode === 200 || response.statusCode === 201) {
            assert(true, 'Add city endpoint accessible');
        } else if (response.statusCode === 400) {
            assert(response.data.message.includes('already exists') || response.data.message.includes('invalid'), 'Add city validation working');
        } else {
            assert(false, `Unexpected response: ${response.statusCode}`);
        }
    } catch (error) {
        assert(false, `Add city failed: ${error.message}`);
    }
});

test('Export Endpoints', async () => {
    try {
        const xmlResponse = await makeRequest('GET', '/api/export/xml');
        assert(xmlResponse.statusCode === 200, 'XML export endpoint accessible');
        
        const jsonResponse = await makeRequest('GET', '/api/export/json');
        assert(jsonResponse.statusCode === 200, 'JSON export endpoint accessible');
    } catch (error) {
        assert(false, `Export test failed: ${error.message}`);
    }
});

test('404 Handler', async () => {
    try {
        const response = await makeRequest('GET', '/api/nonexistent');
        assert(response.statusCode === 404, '404 handler works correctly');
        assert(response.data.success === false, '404 response format is correct');
    } catch (error) {
        assert(false, `404 test failed: ${error.message}`);
    }
});

// Run all tests
async function runTests() {
    console.log('🧪 Starting API Tests for Weather App\n');
    console.log(`Testing server at ${BASE_URL}\n`);

    // Check if server is running
    try {
        await makeRequest('GET', '/test');
        console.log('✓ Server is running, proceeding with tests...\n');
    } catch (error) {
        console.log('✗ Server is not running. Please start the server first:');
        console.log('  npm start\n');
        process.exit(1);
    }

    // Run each test
    for (const test of tests) {
        console.log(`📋 ${test.name}:`);
        try {
            await test.fn();
        } catch (error) {
            console.log(`✗ Test failed with error: ${error.message}`);
            failed++;
        }
        console.log('');
    }

    // Results
    console.log('📊 Test Results:');
    console.log(`✓ Passed: ${passed}`);
    console.log(`✗ Failed: ${failed}`);
    console.log(`📈 Success Rate: ${Math.round((passed / (passed + failed)) * 100)}%\n`);

    if (failed === 0) {
        console.log('🎉 All tests passed! Your API is working correctly.');
    } else {
        console.log('⚠️ Some tests failed. Please check the implementation.');
    }

    process.exit(failed > 0 ? 1 : 0);
}

// Run tests if this file is executed directly
if (require.main === module) {
    runTests().catch(console.error);
}

module.exports = { runTests, makeRequest, test, assert };