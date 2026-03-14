document.querySelectorAll('.order-cancellation').forEach(form => {
    form.addEventListener('submit', function (e) {
        e.preventDefault();

        const formData = new FormData(this);
        const params = new URLSearchParams(formData).toString();

        fetch(contextPath + '/order/cancel', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: params
        })
            .then(response => {
                if (response.ok) {
                    location.reload();
                } else {
                    showToast("Failed to cancel order", "error");
                }
            })
            .catch(err => console.error(err));
    });
});