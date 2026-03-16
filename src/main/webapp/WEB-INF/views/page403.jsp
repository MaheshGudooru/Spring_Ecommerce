<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Access Denied - Lumina Store</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/page403.css">
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

<main class="empty-state-container">
    <div class="empty-state-content">

        <div class="error-code">403</div>

        <h1 class="empty-state-title">ACCESS DENIED</h1>
        <p class="empty-state-subtitle">You do not have the necessary permissions to view this page. Please log in with
            an authorized account or return to the homepage.</p>

        <div class="empty-state-actions">
            <a href="${pageContext.request.contextPath}/login" class="btn-solid">Log In</a>
            <a href="${pageContext.request.contextPath}/home" class="btn-outline">Back to Home</a>
        </div>

    </div>
</main>

<footer>
    <div class="footer-bottom">
        <p>&copy; 2026 Lumina Store. All rights reserved.</p>
    </div>
</footer>

</body>

</html>
