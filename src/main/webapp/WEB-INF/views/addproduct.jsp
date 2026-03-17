<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin: Add Product - Lumina Store</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/addproduct.css">
</head>

<body>

    <header class="site-header">
        <div class="container">
            <a href="#" class="logo">LUMINA <span>Admin</span></a>
            <nav class="user-nav">
                <a href="${pageContext.request.contextPath}/logout">Logout</a>
            </nav>
        </div>
    </header>

    <div id="toast-container"></div>

    <main class="admin-section">

        <div class="admin-header">
            <h1>Add New Product</h1>
            <a href="${pageContext.request.contextPath}/admin" class="back-link">Back to Product List</a>
        </div>

        <div class="form-card">
            <form class="add-product-form">

                <div class="form-grid">

                    <div class="form-group full-width">
                        <label for="productName">Product Name</label>
                        <input type="text" id="productName" name="productName" placeholder="e.g. Lumina Pro Laptop"
                            required>
                    </div>

                    <div class="form-group">
                        <label for="productCategory">Category</label>
                        <select id="productCategory" name="category" required>
                            <option value="ELECTRONICS">Electronics</option>
                            <option value="HOME APPLIANCES">Home Appliances</option>
                            <option value="FOOTWEAR">Footwear</option>
                            <option value="FURNITURE">Apparel</option>
                            <option value="BOOKS">Books</option>
                            <option value="ACCESSORIES">Accessories</option>
                            <option value="FITNESS">Fitness</option>
                            <option value="BAGS">Bags</option>
                            <option value="FASHION">Fashion</option>
                            <option value="OTHERS">Others</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="productPrice">Price</label>
                        <div class="price-wrapper">
                            <span>$</span>
                            <input type="number" id="productPrice" name="productPrice" placeholder="0.00" step="0.01"
                                min="0" required>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="productStock">Stock Available</label>
                        <input type="number" id="productStock" name="productStock" placeholder="0" min="0" required>
                    </div>

                    <div class="form-group full-width">
                        <label for="productDescription">Product Description</label>
                        <textarea id="productDescription" name="productDescription"
                            placeholder="Write a detailed description of the product..." required></textarea>
                    </div>
                    <div class="form-group full-width">
                        <label for="productImage">Product Image URL</label>
                        <input type="text" id="productImage" name="productImage" required
                            placeholder="https://example.com/image.jpg">
                    </div>

                </div>

                <div class="form-actions">
                    <button type="reset" class="btn-outline">Cancel</button>
                    <button type="submit" class="btn-primary">Save Product</button>
                </div>

            </form>
        </div>

    </main>

    <script>
        const contextPath = "${pageContext.request.contextPath}";
    </script>
    <script src="${pageContext.request.contextPath}/static/scripts/admin.js"></script>
    <script src="${pageContext.request.contextPath}/static/scripts/toast.js"></script>

</body>

</html>