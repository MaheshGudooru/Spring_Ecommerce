<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin: Manage Products - Lumina Store</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/allproducts.css">

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
        <h1>Manage Products</h1>
        <a href="${pageContext.request.contextPath}/admin/add">
            <button type="submit" class="btn-primary">+ Add New Product</button>
        </a>
    </div>

    <div class="table-container">
        <table class="product-table">
            <thead>
            <tr>
                <th>Product ID</th>
                <th class="thumb-cell">Image</th>
                <th>Name</th>
                <th>Category</th>
                <th>Price</th>
                <th>Stock</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="product" items="${productList}">

                <tr>
                    <td><span class="id-badge">LUM-${product.id}</span></td>
                    <td class="thumb-cell">
                        <img src="${product.productImage}" alt="Sneakers" class="product-thumb">
                    </td>
                    <td class="product-name">${product.name}</td>
                    <td><span class="category-pill">${product.category}</span></td>
                    <td class="product-price">$
                        <fmt:formatNumber value="${product.price}" type="number"
                                          minFractionDigits="2" maxFractionDigits="2"/>
                    </td>
                    <c:choose>
                        <c:when test="${product.stock == 0}">
                            <td class="product-stock out-of-stock">0</td>
                        </c:when>

                        <c:otherwise>
                            <td class="product-stock">${product.stock}</td>
                        </c:otherwise>
                    </c:choose>
                    <td>
                        <div class="action-links">
                            <a href="${pageContext.request.contextPath}/admin/update/${product.id}"
                               class="btn-action btn-edit">Edit</a>
                            <form class="action-links-delete">
                                <input type="hidden" name="productId" value="${product.id}">
                                <button type="submit" class="btn-action btn-delete">Delete</button>
                            </form>
                        </div>
                    </td>
                </tr>

            </c:forEach>

            </tbody>
        </table>
    </div>

</main>


<!-- Pagination component -->

<c:if test="${totalPages > 1}">
    <div class="pagination-container">
        <div class="pagination">
            <c:if test="${currentPage > 1}">
                <a href="?page=${currentPage - 1}" class="page-nav">&laquo; Prev</a>
            </c:if>

            <c:forEach begin="1" end="${totalPages}" var="i">
                <a href="?page=${i}" class="page-link ${i == currentPage ? 'active' : ''}">
                        ${i}
                </a>
            </c:forEach>

            <c:if test="${currentPage < totalPages}">
                <a href="?page=${currentPage + 1}" class="page-nav">Next &raquo;</a>
            </c:if>
        </div>
    </div>
</c:if>

<footer>
    <div class="footer-bottom">
        <p>&copy; 2026 Lumina Store. All rights reserved.</p>
    </div>
</footer>

<script>
    const contextPath = "${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath}/static/scripts/admin.js"></script>

</body>

</html>