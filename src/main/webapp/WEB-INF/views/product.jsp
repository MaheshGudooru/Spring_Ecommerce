<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
        <%@ taglib prefix="c" uri="jakarta.tags.core" %>

            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>All Products - Lumina Store</title>
                <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/product.css">
                <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/global.css">
            </head>

            <body>

                <header class="site-header">
                    <div class="container header-content">
                        <a href="#" class="logo">LUMINA</a>

                        <div class="search-bar">
                            <input type="text" placeholder="Search for anything...">
                            <button type="submit">Search</button>
                        </div>

                        <nav class="user-nav">
                            <c:choose>


                                <c:when test="${pageContext.request.userPrincipal == null}">
                                    <a href="/login">Login</a>
                                </c:when>

                                <c:otherwise>
                                    <a href="/account">Account</a>
                                    <a href="/orders">Orders</a>
                                    <a href="/cart">Cart</a>
                                    <a href="/logout">Logout</a>
                                </c:otherwise>

                            </c:choose>
                        </nav>
                    </div>
                </header>

                <section class="page-header">
                    <h1>All Products</h1>
                    <p>Discover our complete collection of minimalist essentials, meticulously crafted for your everyday
                        life.</p>

                    <div class="category-filters">
                        <button class="filter-btn active">All</button>
                        <button class="filter-btn">Electronics</button>
                        <button class="filter-btn">Footwear</button>
                        <button class="filter-btn">Apparel</button>
                        <button class="filter-btn">Home</button>
                    </div>
                </section>

                <div class="toolbar">
                    <span class="item-count">Showing ${fn:length(productsList)} products</span>
                    <div class="sort-options">
                        <label for="sort">Sort by: </label>
                        <select id="sort" class="sort-dropdown">
                            <option value="featured">Featured</option>
                            <option value="price-low">Price: Low to High</option>
                            <option value="price-high">Price: High to Low</option>
                            <option value="newest">Newest Arrivals</option>
                        </select>
                    </div>
                </div>

                <main class="products-container">
                    <div class="product-grid">

                        <div class="product-card">
                            <div class="image-wrapper">
                                <img src="https://images.unsplash.com/photo-1496181133206-80ce9b88a853?w=500&q=80"
                                    alt="Minimalist Laptop">
                            </div>
                            <div class="product-info">
                                <span class="category">Electronics</span>
                                <h3>Lumina Pro Laptop</h3>
                                <span class="price">$1,299.00</span>
                                <button class="btn-secondary">Add to Cart</button>
                            </div>
                        </div>

                        <div class="product-card">
                            <div class="image-wrapper">
                                <img src="https://images.unsplash.com/photo-1549298916-b41d501d3772?w=500&q=80"
                                    alt="Sneakers">
                            </div>
                            <div class="product-info">
                                <span class="category">Footwear</span>
                                <h3>Classic White Sneakers</h3>
                                <span class="price">$120.00</span>
                                <button class="btn-secondary">Add to Cart</button>
                            </div>
                        </div>
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
                                                    href="${pageContext.request.contextPath}/product/${product.id}">${product.name}</a>
                                            </h3>
                                            <span class="price">$${product.price}</span>
                                            <form class="add-item-to-cart">
                                                <input type="hidden" name="productId" value="${product.id}">
                                                <button type="submit" class="btn-secondary">Add to Cart</button>
                                            </form>
                                        </div>
                                    </div>
                                </c:forEach>

                            </c:otherwise>

                        </c:choose>
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

            </body>

            </html>