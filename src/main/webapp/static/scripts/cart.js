document.querySelectorAll(".add-to-cart-form").forEach(form => {
    form.addEventListener("submit", function (e) {
        e.preventDefault();

        const productId = this.querySelector("input[name='productId']").value;

        const params = new URLSearchParams();
        params.append("productId", productId);

        fetch(contextPath + "/cart/add", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: params
        })
            .then(response => response.text())
            .then(status => {

                const productName = status.split("::")[1];


                if (status.indexOf("success") !== -1) {
                    showToast(productName + " added to cart", "success");
                } else {
                    showToast("Failed to add item", "error");
                }
            })
            .catch(() => {
                showToast("Something went wrong", "error");
            });
    });
});

document.querySelectorAll('.remove-item-from-cart').forEach(form => {
    form.addEventListener('submit', function (e) {
        e.preventDefault();

        const cartItemId = this.querySelector("input[name='cartItemId']").value;

        const params = new URLSearchParams();
        params.append("cartItemId", cartItemId);

        fetch(contextPath + '/cart/remove', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
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

document.querySelectorAll('.reduce-item-from-cart').forEach(form => {
    form.addEventListener('submit', function (e) {
        e.preventDefault();

        const cartItemId = this.querySelector("input[name='cartItemId']").value;

        const params = new URLSearchParams();
        params.append("cartItemId", cartItemId);

        fetch(contextPath + '/cart/decreasecnt', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: params
        })
            .then(response => response.text())
            .then(status => {
                if (status === "success") {
                    location.reload();
                    console.log("cnt reduced");
                } else {
                    console.log("cnt failed to be reduced");
                }
            })
            .catch(err => console.error(err));
    });
});

document.querySelectorAll('.increase-item-from-cart').forEach(form => {
    form.addEventListener('submit', function (e) {
        e.preventDefault();

        const cartItemId = this.querySelector("input[name='cartItemId']").value;

        const params = new URLSearchParams();
        params.append("cartItemId", cartItemId);

        fetch(contextPath + '/cart/increasecnt', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: params
        }).then(response => response.text())
            .then(status => {
                if (status === "success") {
                    location.reload();
                    console.log("cnt increased");
                } else {
                    console.log("cnt failed to be increased");
                }
            })
            .catch(err => console.error(err));
    });
});

// document.querySelectorAll('.send-user-address').forEach(form => {
//     console.log("post request sent!!")
//     form.addEventListener('submit', function (e) {
//         e.preventDefault();

//         const formData = new FormData(this);
//         const params = new URLSearchParams(formData).toString();

//         fetch(contextPath + '/order/checkout', {
//             method: 'POST',
//             headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
//             body: params
//         })
//             .then(response => response.text())
//             .then(status => {
//                 if (status === "success") {
//                     showToast("Item added to cart", "success");
//                 } else {
//                     showToast("Failed to add item", "error");
//                 }
//             })
//             .catch(err => console.error(err));
//     });
// });