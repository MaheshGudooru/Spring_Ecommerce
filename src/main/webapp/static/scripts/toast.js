
/**
 * Displays a themed notification on the screen.
 * @param {string} message - The text to display.
 * @param {string} type - 'success', 'error', 'warning', or 'info' (default).
 */
function showToast(message, type = 'info') {
    const container = document.getElementById('toast-container');
    if (!container) return;

    // Anti-spam: Check if this exact message is already on screen
    // const existingMessages = container.querySelectorAll('.toast-text');
    // for (let span of existingMessages) {
    //     if (span.innerText === message) return;
    // }

    // 1. Create the main toast wrapper
    const toast = document.createElement('div');
    toast.className = `lumina-toast ${type}`;

    // 2. Create the text element
    const textSpan = document.createElement('span');
    textSpan.className = 'toast-text';
    textSpan.innerText = message;

    // 3. Create the close button
    const closeBtn = document.createElement('button');
    closeBtn.className = 'lumina-toast-close';
    closeBtn.innerHTML = '&times;'; // HTML entity for the "X" symbol
    closeBtn.setAttribute('aria-label', 'Close notification');

    // 4. Assemble the toast and add to screen
    toast.appendChild(textSpan);
    toast.appendChild(closeBtn);
    container.appendChild(toast);

    // 5. Shared removal function (handles the fade-out logic)
    const removeToast = () => {
        if (toast.classList.contains('fade-out')) return; // Prevent triggering twice
        toast.classList.add('fade-out');
        toast.addEventListener('animationend', () => {
            toast.remove();
        });
    };

    // 6. Event Listeners for removal
    closeBtn.addEventListener('click', removeToast); // Manual close
    setTimeout(removeToast, 3000);                   // Auto close after 3 seconds
}
