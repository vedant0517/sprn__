// ===== WEATHER APP - MAIN JAVASCRIPT FILE =====
// Global variables and configuration
const API_BASE_URL = '/api';
const WEATHER_ICONS = {
    '01d': '☀️', '01n': '🌙', '02d': '⛅', '02n': '☁️',
    '03d': '☁️', '03n': '☁️', '04d': '☁️', '04n': '☁️',
    '09d': '🌧️', '09n': '🌧️', '10d': '🌦️', '10n': '🌧️',
    '11d': '⛈️', '11n': '⛈️', '13d': '❄️', '13n': '❄️',
    '50d': '🌫️', '50n': '🌫️'
};

let cities = [];
let editingCityId = null;
let deletingCityId = null;

// DOM Elements
const elements = {
    // Forms
    addCityForm: document.getElementById('addCityForm'),
    cityInput: document.getElementById('cityInput'),
    formError: document.getElementById('formError'),
    editCityForm: document.getElementById('editCityForm'),
    editCityInput: document.getElementById('editCityInput'),
    editFormError: document.getElementById('editFormError'),

    // Buttons
    exportXmlBtn: document.getElementById('exportXmlBtn'),
    exportJsonBtn: document.getElementById('exportJsonBtn'),

    // Display elements
    cityCount: document.getElementById('cityCount'),
    loadingState: document.getElementById('loadingState'),
    emptyState: document.getElementById('emptyState'),
    citiesGrid: document.getElementById('citiesGrid'),

    // Modals
    editModal: document.getElementById('editModal'),
    deleteModal: document.getElementById('deleteModal'),
    deleteCityName: document.getElementById('deleteCityName'),

    // Modal controls
    closeModal: document.getElementById('closeModal'),
    cancelEdit: document.getElementById('cancelEdit'),
    closeDeleteModal: document.getElementById('closeDeleteModal'),
    cancelDelete: document.getElementById('cancelDelete'),
    confirmDelete: document.getElementById('confirmDelete'),

    // Toast container
    toastContainer: document.getElementById('toastContainer')
};

// ===== UTILITY FUNCTIONS =====

/**
 * Show toast notification
 */
function showToast(title, message, type = 'success') {
    const toast = document.createElement('div');
    toast.className = `toast ${type}`;
    
    const iconMap = {
        success: 'fas fa-check-circle',
        error: 'fas fa-exclamation-circle',
        warning: 'fas fa-exclamation-triangle',
        info: 'fas fa-info-circle'
    };

    toast.innerHTML = `
        <i class="${iconMap[type]} toast-icon"></i>
        <div class="toast-content">
            <div class="toast-title">${title}</div>
            <div class="toast-message">${message}</div>
        </div>
        <button class="toast-close" onclick="removeToast(this)">
            <i class="fas fa-times"></i>
        </button>
    `;

    elements.toastContainer.appendChild(toast);

    // Auto remove after 5 seconds
    setTimeout(() => {
        if (toast.parentNode) {
            removeToast(toast.querySelector('.toast-close'));
        }
    }, 5000);
}

/**
 * Remove toast notification
 */
function removeToast(closeBtn) {
    const toast = closeBtn.closest('.toast');
    toast.style.animation = 'slideOutRight 0.3s ease-in';
    setTimeout(() => {
        if (toast.parentNode) {
            toast.parentNode.removeChild(toast);
        }
    }, 300);
}

/**
 * Show error message in form
 */
function showFormError(errorElement, message) {
    errorElement.textContent = message;
    errorElement.style.display = 'flex';
}

/**
 * Clear error message from form
 */
function clearFormError(errorElement) {
    errorElement.textContent = '';
    errorElement.style.display = 'none';
}

/**
 * Toggle loading state
 */
function setLoadingState(isLoading) {
    if (isLoading) {
        elements.loadingState.classList.add('active');
        elements.emptyState.classList.remove('active');
        elements.citiesGrid.style.display = 'none';
    } else {
        elements.loadingState.classList.remove('active');
        if (cities.length === 0) {
            elements.emptyState.classList.add('active');
            elements.citiesGrid.style.display = 'none';
        } else {
            elements.emptyState.classList.remove('active');
            elements.citiesGrid.style.display = 'grid';
        }
    }
}

/**
 * Update city count display
 */
function updateCityCount() {
    elements.cityCount.textContent = cities.length;
}

/**
 * Validate city name input
 */
function validateCityName(name) {
    if (!name || name.trim().length === 0) {
        return { valid: false, message: 'City name is required' };
    }
    
    if (name.trim().length < 2) {
        return { valid: false, message: 'City name must be at least 2 characters long' };
    }
    
    if (name.trim().length > 50) {
        return { valid: false, message: 'City name must be less than 50 characters' };
    }
    
    // Check for invalid characters
    const validNameRegex = /^[a-zA-Z\s\-',.]+$/;
    if (!validNameRegex.test(name.trim())) {
        return { valid: false, message: 'City name contains invalid characters' };
    }
    
    return { valid: true };
}

// ===== API FUNCTIONS =====

/**
 * Fetch all cities from the server
 */
async function fetchCities() {
    try {
        setLoadingState(true);
        const response = await fetch(`${API_BASE_URL}/cities`);
        const data = await response.json();
        
        if (data.success) {
            cities = data.data;
            renderCities();
            updateCityCount();
            checkApiKeyStatus();
        } else {
            throw new Error(data.message || 'Failed to fetch cities');
        }
    } catch (error) {
        console.error('Error fetching cities:', error);
        showToast('Error', 'Failed to load cities. Please try again.', 'error');
        cities = [];
    } finally {
        setLoadingState(false);
    }
}

/**
 * Add a new city
 */
async function addCity(cityName) {
    try {
        const validation = validateCityName(cityName);
        if (!validation.valid) {
            showFormError(elements.formError, validation.message);
            return false;
        }

        // Check for duplicates on client side
        const existingCity = cities.find(city => 
            city.name.toLowerCase() === cityName.trim().toLowerCase()
        );
        
        if (existingCity) {
            showFormError(elements.formError, 'This city is already in your list');
            return false;
        }

        const response = await fetch(`${API_BASE_URL}/cities`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ name: cityName.trim() })
        });
        
        const data = await response.json();
        
        if (data.success) {
            cities.push(data.data);
            renderCities();
            updateCityCount();
            showToast('Success', `${data.data.name} has been added to your list`);
            elements.cityInput.value = '';
            clearFormError(elements.formError);
            return true;
        } else {
            showFormError(elements.formError, data.message || 'Failed to add city');
            return false;
        }
    } catch (error) {
        console.error('Error adding city:', error);
        showFormError(elements.formError, 'Network error. Please try again.');
        return false;
    }
}

/**
 * Update an existing city
 */
async function updateCity(cityId, newName) {
    try {
        const validation = validateCityName(newName);
        if (!validation.valid) {
            showFormError(elements.editFormError, validation.message);
            return false;
        }

        // Check for duplicates (excluding current city)
        const existingCity = cities.find(city => 
            city.id !== cityId && 
            city.name.toLowerCase() === newName.trim().toLowerCase()
        );
        
        if (existingCity) {
            showFormError(elements.editFormError, 'Another city with this name already exists');
            return false;
        }

        const response = await fetch(`${API_BASE_URL}/cities/${cityId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ name: newName.trim() })
        });
        
        const data = await response.json();
        
        if (data.success) {
            const cityIndex = cities.findIndex(city => city.id === cityId);
            if (cityIndex !== -1) {
                cities[cityIndex] = data.data;
                renderCities();
            }
            showToast('Success', `City updated to ${data.data.name}`);
            closeEditModal();
            return true;
        } else {
            showFormError(elements.editFormError, data.message || 'Failed to update city');
            return false;
        }
    } catch (error) {
        console.error('Error updating city:', error);
        showFormError(elements.editFormError, 'Network error. Please try again.');
        return false;
    }
}

/**
 * Delete a city
 */
async function deleteCity(cityId) {
    try {
        const response = await fetch(`${API_BASE_URL}/cities/${cityId}`, {
            method: 'DELETE'
        });
        
        const data = await response.json();
        
        if (data.success) {
            cities = cities.filter(city => city.id !== cityId);
            renderCities();
            updateCityCount();
            showToast('Success', `${data.data.name} has been removed from your list`);
            closeDeleteModal();
            return true;
        } else {
            showToast('Error', data.message || 'Failed to delete city', 'error');
            return false;
        }
    } catch (error) {
        console.error('Error deleting city:', error);
        showToast('Error', 'Network error. Please try again.', 'error');
        return false;
    }
}

/**
 * Refresh weather data for a specific city
 */
async function refreshCityWeather(cityId) {
    try {
        const city = cities.find(c => c.id === cityId);
        if (!city) return;

        // Add loading state to the specific card
        const card = document.querySelector(`[data-city-id="${cityId}"]`);
        if (card) {
            card.classList.add('pulse');
        }

        const response = await fetch(`${API_BASE_URL}/cities`);
        const data = await response.json();
        
        if (data.success) {
            const updatedCity = data.data.find(c => c.id === cityId);
            if (updatedCity) {
                const cityIndex = cities.findIndex(c => c.id === cityId);
                if (cityIndex !== -1) {
                    cities[cityIndex] = updatedCity;
                    renderCities();
                    showToast('Success', `Weather data refreshed for ${updatedCity.name}`);
                }
            }
        } else {
            throw new Error(data.message || 'Failed to refresh weather data');
        }
    } catch (error) {
        console.error('Error refreshing weather:', error);
        showToast('Error', 'Failed to refresh weather data', 'error');
    } finally {
        // Remove loading state
        const card = document.querySelector(`[data-city-id="${cityId}"]`);
        if (card) {
            card.classList.remove('pulse');
        }
    }
}

/**
 * Export cities as XML
 */
async function exportAsXML() {
    try {
        if (cities.length === 0) {
            showToast('Warning', 'No cities to export', 'warning');
            return;
        }

        const response = await fetch(`${API_BASE_URL}/export/xml`);
        
        if (response.ok) {
            const blob = await response.blob();
            const url = window.URL.createObjectURL(blob);
            const a = document.createElement('a');
            a.href = url;
            
            // Get filename from Content-Disposition header
            const contentDisposition = response.headers.get('Content-Disposition');
            let filename = 'weather-app-cities.xml';
            if (contentDisposition) {
                const filenameMatch = contentDisposition.match(/filename="(.+)"/);
                if (filenameMatch) {
                    filename = filenameMatch[1];
                }
            }
            
            a.download = filename;
            document.body.appendChild(a);
            a.click();
            window.URL.revokeObjectURL(url);
            document.body.removeChild(a);
            
            showToast('Success', 'Cities exported as XML successfully');
        } else {
            const errorData = await response.json();
            throw new Error(errorData.message || 'Export failed');
        }
    } catch (error) {
        console.error('Error exporting XML:', error);
        showToast('Error', 'Failed to export cities as XML', 'error');
    }
}

/**
 * Export cities as JSON
 */
async function exportAsJSON() {
    try {
        if (cities.length === 0) {
            showToast('Warning', 'No cities to export', 'warning');
            return;
        }

        const response = await fetch(`${API_BASE_URL}/export/json`);
        
        if (response.ok) {
            const blob = await response.blob();
            const url = window.URL.createObjectURL(blob);
            const a = document.createElement('a');
            a.href = url;
            
            // Get filename from Content-Disposition header
            const contentDisposition = response.headers.get('Content-Disposition');
            let filename = 'weather-app-cities.json';
            if (contentDisposition) {
                const filenameMatch = contentDisposition.match(/filename="(.+)"/);
                if (filenameMatch) {
                    filename = filenameMatch[1];
                }
            }
            
            a.download = filename;
            document.body.appendChild(a);
            a.click();
            window.URL.revokeObjectURL(url);
            document.body.removeChild(a);
            
            showToast('Success', 'Cities exported as JSON successfully');
        } else {
            const errorData = await response.json();
            throw new Error(errorData.message || 'Export failed');
        }
    } catch (error) {
        console.error('Error exporting JSON:', error);
        showToast('Error', 'Failed to export cities as JSON', 'error');
    }
}

// ===== RENDERING FUNCTIONS =====

/**
 * Render all cities in the grid
 */
function renderCities() {
    if (cities.length === 0) {
        elements.citiesGrid.innerHTML = '';
        setLoadingState(false);
        return;
    }

    const citiesHTML = cities.map(city => createCityCard(city)).join('');
    elements.citiesGrid.innerHTML = citiesHTML;
    setLoadingState(false);
}

/**
 * Create HTML for a single city card
 */
function createCityCard(city) {
    const hasWeather = city.weather && !city.weatherError;
    const weatherIcon = hasWeather ? WEATHER_ICONS[city.weather.icon] || '🌤️' : '❌';
    const cardClass = hasWeather ? 'weather-card' : 'weather-card error';
    
    return `
        <div class="${cardClass}" data-city-id="${city.id}">
            <div class="card-header">
                <div class="city-info">
                    <h3>${city.name}</h3>
                    ${hasWeather ? `<span class="country">${city.weather.country}</span>` : ''}
                </div>
                <div class="card-actions">
                    <button 
                        class="action-btn refresh-btn" 
                        onclick="refreshCityWeather('${city.id}')"
                        title="Refresh weather data"
                    >
                        <i class="fas fa-sync-alt"></i>
                    </button>
                    <button 
                        class="action-btn edit-btn" 
                        onclick="openEditModal('${city.id}', '${city.name.replace(/'/g, "\\'")}')"
                        title="Edit city"
                    >
                        <i class="fas fa-edit"></i>
                    </button>
                    <button 
                        class="action-btn delete-btn" 
                        onclick="openDeleteModal('${city.id}', '${city.name.replace(/'/g, "\\'")}')"
                        title="Delete city"
                    >
                        <i class="fas fa-trash"></i>
                    </button>
                </div>
            </div>
            
            ${hasWeather ? `
                <div class="weather-main">
                    <div class="weather-icon">${weatherIcon}</div>
                    <div class="temperature">${city.weather.temperature}</div>
                </div>
                
                <div class="weather-details">
                    <div class="detail-item">
                        <i class="fas fa-tint"></i>
                        <span>${city.weather.humidity}% humidity</span>
                    </div>
                    <div class="detail-item">
                        <i class="fas fa-wind"></i>
                        <span>${city.weather.windSpeed} m/s wind</span>
                    </div>
                </div>
                
                <div class="description">${city.weather.description}</div>
            ` : `
                <div class="weather-main error-card">
                    <div class="weather-icon">❌</div>
                    <div class="error-message">
                        <i class="fas fa-exclamation-triangle"></i>
                        <span>${city.weatherError || 'Weather data unavailable'}</span>
                    </div>
                </div>
            `}
            
            <div class="card-footer">
                <small class="text-muted">
                    Added: ${new Date(city.createdAt).toLocaleDateString()}
                    ${city.updatedAt ? ` • Updated: ${new Date(city.updatedAt).toLocaleDateString()}` : ''}
                </small>
            </div>
        </div>
    `;
}

// ===== MODAL FUNCTIONS =====

/**
 * Open edit modal
 */
function openEditModal(cityId, cityName) {
    editingCityId = cityId;
    elements.editCityInput.value = cityName;
    elements.editModal.classList.add('active');
    clearFormError(elements.editFormError);
    elements.editCityInput.focus();
}

/**
 * Close edit modal
 */
function closeEditModal() {
    elements.editModal.classList.remove('active');
    editingCityId = null;
    elements.editCityInput.value = '';
    clearFormError(elements.editFormError);
}

/**
 * Open delete confirmation modal
 */
function openDeleteModal(cityId, cityName) {
    deletingCityId = cityId;
    elements.deleteCityName.textContent = cityName;
    elements.deleteModal.classList.add('active');
}

/**
 * Close delete confirmation modal
 */
function closeDeleteModal() {
    elements.deleteModal.classList.remove('active');
    deletingCityId = null;
}

// ===== EVENT LISTENERS =====

// Add city form submission
elements.addCityForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const cityName = elements.cityInput.value.trim();
    
    if (cityName) {
        const submitBtn = elements.addCityForm.querySelector('button[type="submit"]');
        const originalText = submitBtn.innerHTML;
        submitBtn.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Adding...';
        submitBtn.disabled = true;
        
        await addCity(cityName);
        
        submitBtn.innerHTML = originalText;
        submitBtn.disabled = false;
    }
});

// Edit city form submission
elements.editCityForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const newName = elements.editCityInput.value.trim();
    
    if (newName && editingCityId) {
        const submitBtn = elements.editCityForm.querySelector('button[type="submit"]');
        const originalText = submitBtn.innerHTML;
        submitBtn.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Saving...';
        submitBtn.disabled = true;
        
        await updateCity(editingCityId, newName);
        
        submitBtn.innerHTML = originalText;
        submitBtn.disabled = false;
    }
});

// Export buttons
elements.exportXmlBtn.addEventListener('click', exportAsXML);
elements.exportJsonBtn.addEventListener('click', exportAsJSON);

// Modal controls
elements.closeModal.addEventListener('click', closeEditModal);
elements.cancelEdit.addEventListener('click', closeEditModal);
elements.closeDeleteModal.addEventListener('click', closeDeleteModal);
elements.cancelDelete.addEventListener('click', closeDeleteModal);

// Confirm delete
elements.confirmDelete.addEventListener('click', async () => {
    if (deletingCityId) {
        const originalText = elements.confirmDelete.innerHTML;
        elements.confirmDelete.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Deleting...';
        elements.confirmDelete.disabled = true;
        
        await deleteCity(deletingCityId);
        
        elements.confirmDelete.innerHTML = originalText;
        elements.confirmDelete.disabled = false;
    }
});

// Close modals when clicking outside
elements.editModal.addEventListener('click', (e) => {
    if (e.target === elements.editModal) {
        closeEditModal();
    }
});

elements.deleteModal.addEventListener('click', (e) => {
    if (e.target === elements.deleteModal) {
        closeDeleteModal();
    }
});

// Keyboard shortcuts
document.addEventListener('keydown', (e) => {
    // Escape key closes modals
    if (e.key === 'Escape') {
        if (elements.editModal.classList.contains('active')) {
            closeEditModal();
        }
        if (elements.deleteModal.classList.contains('active')) {
            closeDeleteModal();
        }
    }
    
    // Enter key in city input focuses submit button
    if (e.key === 'Enter' && e.target === elements.cityInput) {
        e.preventDefault();
        elements.addCityForm.querySelector('button[type="submit"]').click();
    }
});

// Input validation on keyup
elements.cityInput.addEventListener('input', () => {
    clearFormError(elements.formError);
});

elements.editCityInput.addEventListener('input', () => {
    clearFormError(elements.editFormError);
});

// ===== INITIALIZATION =====

/**
 * Check if API key notice should be shown
 */
function checkApiKeyStatus() {
    const hasApiKeyError = cities.some(city => 
        city.weatherError && 
        (city.weatherError.includes('API key') || city.weatherError.includes('401'))
    );
    
    const apiNotice = document.getElementById('apiKeyNotice');
    if (hasApiKeyError && cities.length > 0) {
        apiNotice.classList.remove('hidden');
    } else {
        apiNotice.classList.add('hidden');
    }
}

/**
 * Initialize the application
 */
function initApp() {
    console.log('🌤️ Weather App initialized');
    console.log('📡 Fetching cities...');
    
    // Add keyboard event listeners
    setupKeyboardHandlers();
    
    // Load cities on page load
    fetchCities();
    
    // Set up periodic refresh (every 10 minutes)
    setInterval(() => {
        if (cities.length > 0) {
            console.log('🔄 Auto-refreshing weather data...');
            fetchCities();
        }
    }, 10 * 60 * 1000);
}

/**
 * Set up keyboard event handlers for better UX
 */
function setupKeyboardHandlers() {
    document.addEventListener('keydown', (event) => {
        // Close modals with Escape key
        if (event.key === 'Escape') {
            const editModal = document.getElementById('editModal');
            const deleteModal = document.getElementById('deleteModal');
            
            if (editModal && editModal.style.display === 'flex') {
                editModal.style.display = 'none';
            }
            if (deleteModal && deleteModal.style.display === 'flex') {
                deleteModal.style.display = 'none';
            }
        }
        
        // Handle Enter key in input fields
        if (event.key === 'Enter' && event.target.tagName === 'INPUT') {
            if (event.target.id === 'cityInput') {
                const addButton = document.querySelector('.add-city-btn');
                if (addButton) addButton.click();
            } else if (event.target.id === 'editCityName') {
                const updateButton = document.querySelector('.modal-btn.primary');
                if (updateButton && updateButton.textContent === 'Update') {
                    updateButton.click();
                }
            }
        }
    });
}

// ===== GLOBAL FUNCTIONS (called from HTML) =====
// These functions are called from onclick attributes in the HTML

window.refreshCityWeather = refreshCityWeather;
window.openEditModal = openEditModal;
window.openDeleteModal = openDeleteModal;
window.removeToast = removeToast;

// Start the application when DOM is loaded
if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', initApp);
} else {
    initApp();
}