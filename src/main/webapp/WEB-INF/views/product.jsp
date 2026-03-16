<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
        <%@ taglib prefix="c" uri="jakarta.tags.core" %>


            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Lumina Pro Laptop - Lumina Store</title>
                <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/product.css">
                <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/global.css">

            </head>

            <body>
            <div id="toast-container"></div>
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

                <main class="product-section">

                    <div class="product-grid">

                        <div class="image-stack">
                            <img src="${productDetail.productImage}" alt="${productDetail.name}">
                        </div>

                        <div class="product-details">

                            <c:choose>
                                <c:when test="${productDetail.stock <= 0}">
                                    <span class="stock-badge .out-of-stock">Out of Stock</span>
                                </c:when>

                                <c:otherwise>
                                    <span class="stock-badge in-stock">In Stock</span>
                                </c:otherwise>
                            </c:choose>

                            <h1 class="product-title">${productDetail.name}</h1>
                            <span class="product-price">
                                <fmt:formatNumber value="${productDetail.price}" type="number" minFractionDigits="2"
                                    maxFractionDigits="2" />
                            </span>

                            <p class="product-description">${productDetail.productDescription}</p>

                            <form class="add-to-cart-form">

                                <div class="form-group">
                                    <label for="quantity">Quantity</label>
                                    <input type="number" id="quantity" name="quantity" value="1" min="1" max="10">
                                </div>

                                <div class="action-buttons">
                                    <input type="hidden" name="productId" value="${productDetail.id}">
                                    <button type="submit" class="btn-primary" onclick="">Add to
                                        Cart</button>
                                </div>

                            </form>

                        </div>
                    </div>
                </main>

                <script>
                    const contextPath = "${pageContext.request.contextPath}";
                </script>
                <script src="${pageContext.request.contextPath}/static/scripts/cart.js"></script>
                <script src="${pageContext.request.contextPath}/static/scripts/toast.js"></script>

            </body>

            </html>