<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
        <%@ taglib prefix="c" uri="jakarta.tags.core" %>

            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>All Products - Lumina Store</title>
                <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/products.css">
                <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/global.css">
            </head>

            <body>

                <header class="site-header">
                    <div class="container header-content">
                        <a href="${pageContext.request.contextPath}/home" class="logo">LUMINA</a>

                        <nav class="user-nav">
                            <c:choose>


                                <c:when test="${pageContext.request.userPrincipal == null}">
                                    <a href="${pageContext.request.contextPath}/products">product</a>
                                    <a href="${pageContext.request.contextPath}/login">Login</a>
                                </c:when>

                                <c:otherwise>
                                    <c:if test="${pageContext.request.userPrincipal.name == 'admin@gmail.com'}">
                                        <a href="${pageContext.request.contextPath}/admin">admin</a>
                                    </c:if>
                                    <a href="${pageContext.request.contextPath}/products">product</a>
                                    <a href="${pageContext.request.contextPath}/account">Account</a>
                                    <a href="${pageContext.request.contextPath}/order">Orders</a>
                                    <a href="${pageContext.request.contextPath}/cart">Cart</a>
                                </c:otherwise>

                            </c:choose>
                        </nav>
                    </div>
                </header>

                <div id="toast-container"></div>

                <section class="page-header">
                    <h1>All Products</h1>
                    <p>Discover our complete collection of minimalist essentials, meticulously crafted for your everyday
                        life.</p>
                </section>

                <main class="products-container">
                    <div class="product-grid">

                        <c:choose>

                            <c:when test="${empty products}">
                                <p>No products available.</p>
                            </c:when>

                            <c:otherwise>

                                <c:forEach var="product" items="${products}">
                                    <div class="product-card">
                                        <div class="image-wrapper">
                                            <img src="${product.productImage}" alt="${product.category}">
                                        </div>
                                        <div class="product-info">
                                            <span class="category">${product.category}</span>
                                            <h3>
                                                <a
                                                    href="${pageContext.request.contextPath}/products/${product.id}">${product.name}</a>
                                            </h3>
                                            <span class="price">$${product.price}</span>
                                            <form class="add-to-cart-form">
                                                <input type="hidden" name="productId" value="${product.id}">
                                                <button type="submit" class="btn-secondary">Add to Cart</button>
                                            </form>
                                        </div>
                                    </div>
                                </c:forEach>

                            </c:otherwise>

                        </c:choose>
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
                <script src="${pageContext.request.contextPath}/static/scripts/cart.js"></script>
                <script src="${pageContext.request.contextPath}/static/scripts/toast.js"></script>

            </body>

            </html>