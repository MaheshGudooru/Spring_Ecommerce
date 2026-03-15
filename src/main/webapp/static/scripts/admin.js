document.querySelectorAll('.action-links-delete').forEach(form => {
    form.addEventListener('submit', function (e) {
        e.preventDefault();

        const formData = new FormData(this);
        const params = new URLSearchParams(formData).toString();

        fetch(contextPath + '/admin/delete', {
            method: 'POST',
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            body: params
        })

            .then(response => response.text())
            .then(status => {
                if (status === "success") {
                    location.reload();
                    showToast("Item removed from cart", "success");
                } else {
                    showToast("Failed to remove item", "error");
                }
            })
            .catch(err => console.error(err));
    });
});

document.querySelector(".add-product-form").addEventListener("submit", function (e) {
    e.preventDefault();

    const formData = new FormData(this);
    const params = new URLSearchParams(formData).toString();

    fetch(contextPath + "/admin/add", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: params
    })
        .then(response => response.text())
        .then(status => {

            const insertionStatus = status.split("::");

            if (insertionStatus.length >= 1) {
                showToast(insertionStatus[1], insertionStatus[0]);
            } else {
                showToast("Failed to add item", "error");
            }

        })
        .catch(() => {
            showToast("Something went wrong", "error");
        });
});
