<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin: Add Product - Lumina Store</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/updateform.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/global.css">
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


<main class="admin-section">

    <div class="admin-header">
        <h1>Edit Product</h1>
        <a href="admin-products.html" class="back-link">← Back to Product List</a>
    </div>

    <div class="form-card">
        <form action="${pageContext.request.contextPath}/admin/update" method="post">

            <div class="form-grid">

                <div class="form-group full-width">
                    <label for="productId">Product ID</label>
                    <input type="text" id="productId" name="productId" value="${product.id}" readonly>
                </div>

                <div class="form-group full-width">
                    <label for="productName">Product Name</label>
                    <input type="text" id="productName" name="productName" value="${product.name}" required>
                </div>

                <div class="form-group">
                    <label for="productCategory">Category</label>
                    <select id="productCategory" name="category" required>
                        <option value="electronics"
                        ${product.category == 'electronics' ? 'selected' : ''}>Electronics
                        </option>
                        <option value="footwear"
                        ${product.category == 'footwear' ? 'selected' : ''}>Footwear
                        </option>
                        <option value="apparel"
                        ${product.category == 'apparel' ? 'selected' : ''}>Apparel
                        </option>
                        <option value="home"
                        ${product.category == 'home' ? 'selected' : ''}>Home Essentials
                        </option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="productPrice">Price</label>
                    <div class="price-wrapper">
                        <span>$</span>
                        <input type="number" id="productPrice" name="price"
                               value="${product.price}" step="0.01" min="0" required>
                    </div>
                </div>

                <div class="form-group">
                    <label for="productStock">Stock Available</label>
                    <input type="number" id="productStock" name="stock"
                           value="${product.stock}" min="0" required>
                </div>

                <div class="form-group hide-mobile"></div>

                <div class="form-group full-width">
                    <label for="productDescription">Product Description</label>
                    <textarea id="productDescription" name="productDescription"
                              required>${product.productDescription}</textarea>
                </div>

                <div class="form-group full-width">
                    <label for="productImage">Product Image URL</label>
                    <input type="text" id="productImage" name="productImage"
                           value="${product.productImage}" required
                           placeholder="https://example.com/image.jpg">
                </div>

            </div>

            <div class="form-actions">
                <button type="submit" class="btn-primary">Save Changes</button>
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
