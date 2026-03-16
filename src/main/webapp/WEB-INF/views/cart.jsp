<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Your Cart - Lumina Store</title>
                <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/cart.css">
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

                <c:if test="${empty cartItemsList}">
                    <section class="empty-cart-container">
                        <div class="empty-cart-content">
                            <div class="empty-cart-icon">
                                <svg width="64" height="64" viewBox="0 0 24 24" fill="none" stroke="black"
                                    stroke-width="1" stroke-linecap="square">
                                    <path d="M6 2 3 6v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2V6l-3-4Z" />
                                    <line x1="3" y1="6" x2="21" y2="6" />
                                    <path d="M16 10a4 4 0 0 1-8 0" />
                                </svg>
                            </div>

                            <h1 class="empty-cart-title">Your Bag is Empty</h1>
                            <p class="empty-cart-subtitle">The pieces you love are waiting for you. Discover our latest
                                arrivals and tech essentials.</p>

                            <div class="empty-cart-actions">
                                <a href="${pageContext.request.contextPath}/products" class="cart-btn-primary">Shop
                                    Collection</a>
                            </div>
                        </div>
                    </section>
                </c:if>

                <c:if test="${!empty cartItemsList}">
                    <main class="cart-section">
                        <div class="cart-header">
                            <h1>Your Cart</h1>
                        </div>

                        <div class="cart-grid">

                            <div class="cart-items">

                                <c:forEach var="cartItem" items="${cartItemsList}">
                                    <div class="cart-item">
                                        <img src="${cartItem.productId.productImage}" alt="${cartItem.productId.name}"
                                            class="item-image">
                                        <div class="item-details">
                                            <h3>${cartItem.productId.name}</h3>
                                            <p>${cartItem.productId.productDescription}</p>
                                            <div class="item-actions">

                                                <div class="quantity-wrapper">

                                                    <form class="reduce-item-from-cart">
                                                        <input type="hidden" name="cartItemId" value="${cartItem.id}">
                                                        <button type="submit" class="qty-btn minus">-</button>
                                                    </form>
                                                    <input type="text" class="qty-input" value="${cartItem.quantity}"
                                                        readonly>
                                                    <form class="increase-item-from-cart">
                                                        <input type="hidden" name="cartItemId" value="${cartItem.id}">
                                                        <button type="submit" class="qty-btn plus">+</button>
                                                    </form>

                                                </div>
                                                <form class="remove-item-from-cart">
                                                    <input type="hidden" name="cartItemId" value="${cartItem.id}">
                                                    <button type="submit" class="remove-btn">Remove</button>
                                                </form>
                                            </div>
                                        </div>
                                        <div class="item-price">$
                                            <fmt:formatNumber value="${cartItem.productId.price * cartItem.quantity}"
                                                type="number" minFractionDigits="2" maxFractionDigits="2" />
                                        </div>
                                    </div>
                                </c:forEach>

                            </div>

                            <div class="order-summary">
                                <h2>Order Summary</h2>
                                <div class="summary-row"><span>Subtotal</span><span>$
                                        <fmt:formatNumber value="${cartTotalPrice}" type="number" minFractionDigits="2"
                                            maxFractionDigits="2" />
                                    </span></div>
                                <div class="summary-row"><span>Shipping</span><span>Free</span></div>
                                <div class="summary-row"><span>Estimated Tax</span><span>$108.36</span></div>
                                <div class="summary-total"><span>Total</span><span>$
                                        <fmt:formatNumber value="${cartTotalPrice + 108.36}" type="number"
                                            minFractionDigits="2" maxFractionDigits="2" />
                                    </span></div>

                                <form class="send-user-address" action="${pageContext.request.contextPath}/order"
                                    method="post">
                                    <div class="address-group">
                                        <label for="deliveryAddress">Delivery Address</label>
                                        <input type="text" id="deliveryAddress" name="address"
                                            placeholder="Street, City, Zip Code" required>
                                    </div>

                                    <input type="number" name="cartTotalPrice" value="${cartTotalPrice}" hidden />

                                    <div class="payment-group">
                                        <label for="paymentMethod">Payment Method</label>
                                        <select id="paymentMethod" name="paymentMethod" required>
                                            <option value="" disabled selected>Select Payment</option>
                                            <option value="credit_card">Credit Card / Debit Card</option>
                                            <option value="UPI">UPI</option>
                                            <option value="online_banking">Online Banking</option>
                                            <option value="cod">Cash on Delivery</option>
                                        </select>
                                    </div>

                                    <button type="submit" class="btn-primary">Proceed to Checkout</button>
                                </form>

                                <a href="${pageContext.request.contextPath}/product" class="continue-shopping">Continue
                                    Shopping</a>
                            </div>

                        </div>
                    </main>
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
                <c:if test="${not empty message}">
                    <script>
                        showToast("${message}", "warning");
                    </script>
                </c:if>

            </body>

            </html>