document.addEventListener("DOMContentLoaded", function () {
    const inputs = document.querySelectorAll('.track-change');
    const saveActionsDiv = document.getElementById('saveActions');

    // Store original values to check if they actually changed
    const originalValues = {};
    inputs.forEach(input => {
        originalValues[input.id] = input.value;
    });

    // Listen for any input changes
    inputs.forEach(input => {
        input.addEventListener('input', function () {
            let hasChanges = false;

            // Check if ANY input currently differs from its original value
            inputs.forEach(inp => {
                if (inp.value !== originalValues[inp.id]) {
                    hasChanges = true;
                }
            });

            // Show or hide the submit button based on changes
            if (hasChanges) {
                saveActionsDiv.style.display = 'block';
            } else {
                saveActionsDiv.style.display = 'none';
            }
        });
    });
});

document.querySelectorAll('.update-user-details').addEventListener('submit', function (e) {
    e.preventDefault();

    const cartItemId = this.querySelector("input[name='cartItemId']").value;

    const params = new URLSearchParams();
    params.append("cartItemId", cartItemId);

    fetch(contextPath + '/account', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: params
    }).then(response => response.text())
        .then(status => {
            if (status === "success") {
                location.reload();
            } else {
                showToast(status, "warning");
            }
        })
        .catch(err => console.error(err));
});
