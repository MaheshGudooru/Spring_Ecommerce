<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
        <%@ taglib prefix="c" uri="jakarta.tags.core" %>

            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>My Account - Lumina Store</title>
                <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/account.css">
                <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/global.css">
            </head>

            <body>

                <div id="toast-container"></div>

                <header class="site-header">
                    <div class="container header-content">
                        <a href="${pageContext.request.contextPath}/home" class="logo">LUMINA</a>

                        <div class="search-bar">
                            <input type="text" placeholder="Search for anything...">
                            <button type="submit">Search</button>
                        </div>

                        <nav class="user-nav">
                            <c:choose>


                                <c:when test="${pageContext.request.userPrincipal == null}">
                                    <a href="${pageContext.request.contextPath}/login">Login</a>
                                </c:when>

                                <c:otherwise>
                                    <a href="${pageContext.request.contextPath}/product">product</a>
                                    <a href="${pageContext.request.contextPath}/account">Account</a>
                                    <a href="${pageContext.request.contextPath}/order">Orders</a>
                                    <a href="${pageContext.request.contextPath}/cart">Cart</a>
                                </c:otherwise>

                            </c:choose>
                        </nav>
                    </div>
                </header>

                <main class="profile-section">
                    <div class="profile-header">
                        <h1>My Account</h1>
                    </div>

                    <div class="profile-grid">

                        <aside class="profile-sidebar">
                            <div class="avatar">
                                ${fn:substring(user.name, 0, 1)}
                            </div>
                            <h2>${user.name}</h2>
                            <p>Member since ${user.formattedJoinedDate}</p>

                            <a href="${pageContext.request.contextPath}/logout" class="btn-primary">
                                Log Out
                            </a>
                        </aside>

                        <section class="profile-details-card">
                            <h3>Personal Information</h3>

                            <form class="update-user-details">

                                <div class="detail-grid">
                                    <div class="detail-item">
                                        <label for="fullName">Full Name</label>
                                        <input type="text" id="fullName" name="fullName"
                                            class="detail-input track-change" value="${user.name}" required>
                                    </div>

                                    <div class="detail-item">
                                        <label for="emailAddress">Email Address</label>
                                        <input type="email" id="emailAddress" name="emailAddress"
                                            class="detail-input track-change" value="${user.email}" required>
                                    </div>
                                </div>

                                <div class="form-actions" id="saveActions">
                                    <button type="submit" class="btn-primary">Save Changes</button>
                                </div>

                            </form>

                        </section>

                    </div>
                </main>

                <footer>
                    <div class="footer-bottom">
                        <p>&copy; 2026 Lumina Store. All rights reserved.</p>
                    </div>
                </footer>

                <script src="${pageContext.request.contextPath}/static/scripts/account.js"></script>
                <script src="${pageContext.request.contextPath}/static/scripts/toast.js"></script>

            </body>

            </html>