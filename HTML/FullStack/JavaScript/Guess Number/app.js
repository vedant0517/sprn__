// Improved Number Guessing Game
let gameState = {
    max: 0,
    randomNumber: 0,
    attempts: 0,
    guesses: [],
    gameActive: false
};

function startNewGame() {
    const maxInput = document.getElementById('maxInput');
    const maxValue = parseInt(maxInput.value);
    
    // Input validation
    if (!maxValue || maxValue < 2) {
        showFeedback('Please enter a valid number greater than 1!', 'error');
        maxInput.focus();
        return;
    }
    
    // Initialize game state
    gameState.max = maxValue;
    gameState.randomNumber = Math.floor(Math.random() * maxValue) + 1;
    gameState.attempts = 0;
    gameState.guesses = [];
    gameState.gameActive = true;
    
    // Update UI
    document.getElementById('setupSection').classList.add('hidden');
    document.getElementById('gameSection').classList.remove('hidden');
    document.getElementById('rangeInfo').textContent = `Guess a number between 1 and ${maxValue}`;
    document.getElementById('attemptInfo').textContent = 'Attempts: 0';
    document.getElementById('feedback').textContent = 'Make your first guess!';
    document.getElementById('feedback').className = 'feedback';
    document.getElementById('guessHistory').innerHTML = '';
    document.getElementById('newGameBtn').classList.add('hidden');
    document.getElementById('guessInput').focus();
    
    console.log('🎯 Secret number:', gameState.randomNumber); // For testing
}

function makeGuess() {
    if (!gameState.gameActive) return;
    
    const guessInput = document.getElementById('guessInput');
    const guess = parseInt(guessInput.value);
    
    // Input validation
    if (!guess || guess < 1 || guess > gameState.max) {
        showFeedback(`Please enter a valid number between 1 and ${gameState.max}!`, 'error');
        guessInput.focus();
        return;
    }
    
    // Check for duplicate guesses
    if (gameState.guesses.includes(guess)) {
        showFeedback(`You already guessed ${guess}! Try a different number.`, 'error');
        guessInput.value = '';
        guessInput.focus();
        return;
    }
    
    // Process the guess
    gameState.attempts++;
    gameState.guesses.push(guess);
    
    // Update attempts counter
    document.getElementById('attemptInfo').textContent = `Attempts: ${gameState.attempts}`;
    
    // Add guess to history
    addGuessToHistory(guess);
    
    // Check the guess
    if (guess === gameState.randomNumber) {
        // Correct guess!
        showFeedback(`🎉 Congratulations! You guessed it right!\nThe number was ${gameState.randomNumber}\nYou took ${gameState.attempts} attempts!`, 'success');
        endGame(true);
    } else if (guess < gameState.randomNumber) {
        // Too low
        const difference = gameState.randomNumber - guess;
        let hint = '';
        if (difference <= 5) hint = ' (Very close!)';
        else if (difference <= 10) hint = ' (Close!)';
        showFeedback(`📈 Too low! Try a higher number.${hint}`, 'too-low');
    } else {
        // Too high
        const difference = guess - gameState.randomNumber;
        let hint = '';
        if (difference <= 5) hint = ' (Very close!)';
        else if (difference <= 10) hint = ' (Close!)';
        showFeedback(`📉 Too high! Try a lower number.${hint}`, 'too-high');
    }
    
    // Clear input and focus
    guessInput.value = '';
    guessInput.focus();
}

function showFeedback(message, type) {
    const feedback = document.getElementById('feedback');
    feedback.textContent = message;
    feedback.className = `feedback ${type}`;
}

function addGuessToHistory(guess) {
    const historyDiv = document.getElementById('guessHistory');
    const guessElement = document.createElement('div');
    guessElement.className = guess === gameState.randomNumber ? 'guess-item correct' : 'guess-item';
    guessElement.textContent = guess;
    historyDiv.appendChild(guessElement);
}

function endGame(won) {
    gameState.gameActive = false;
    
    if (won) {
        // Mark the correct guess in history
        const lastGuess = document.querySelector('.guess-item:last-child');
        if (lastGuess) lastGuess.classList.add('correct');
    }
    
    document.getElementById('newGameBtn').classList.remove('hidden');
    document.getElementById('guessInput').disabled = true;
    
    // Add some celebration for good performance
    if (won && gameState.attempts <= 5) {
        setTimeout(() => {
            showFeedback(`🏆 Excellent! You're a guessing master!\nOnly ${gameState.attempts} attempts!`, 'success');
        }, 1500);
    }
}

function quitGame() {
    if (confirm('Are you sure you want to quit the current game?')) {
        showFeedback(`Game ended. The number was ${gameState.randomNumber}.`, 'error');
        endGame(false);
    }
}

function resetToSetup() {
    gameState.gameActive = false;
    document.getElementById('setupSection').classList.remove('hidden');
    document.getElementById('gameSection').classList.add('hidden');
    document.getElementById('guessInput').disabled = false;
    document.getElementById('maxInput').focus();
}

// Enhanced keyboard support
document.addEventListener('DOMContentLoaded', function() {
    // Enter key support for max number input
    document.getElementById('maxInput').addEventListener('keypress', function(e) {
        if (e.key === 'Enter') {
            startNewGame();
        }
    });
    
    // Enter key support for guess input
    document.getElementById('guessInput').addEventListener('keypress', function(e) {
        if (e.key === 'Enter') {
            makeGuess();
        }
    });
    
    // Override the start new game button to reset to setup when game is active
    const originalStartNewGame = window.startNewGame;
    window.startNewGame = function() {
        if (gameState.gameActive || !document.getElementById('gameSection').classList.contains('hidden')) {
            resetToSetup();
        } else {
            originalStartNewGame();
        }
    };
});






