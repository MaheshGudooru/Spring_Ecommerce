function showToast(message, type = 'info') {
    const container = document.getElementById('toast-container');
    if (!container) return;


    const toast = document.createElement('div');
    toast.className = `lumina-toast ${type}`;


    const textSpan = document.createElement('span');
    textSpan.className = 'toast-text';
    textSpan.innerText = message;


    const closeBtn = document.createElement('button');
    closeBtn.className = 'lumina-toast-close';
    closeBtn.innerHTML = '&times;'; // HTML entity for the "X" symbol
    closeBtn.setAttribute('aria-label', 'Close notification');


    toast.appendChild(textSpan);
    toast.appendChild(closeBtn);
    container.appendChild(toast);


    const removeToast = () => {
        if (toast.classList.contains('fade-out')) return;
        toast.classList.add('fade-out');
        toast.addEventListener('animationend', () => {
            toast.remove();
        });
    };


    closeBtn.addEventListener('click', removeToast);
    setTimeout(removeToast, 3000);
}
