<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login - Lumina Store</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/login.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/global.css">
    </head>

    <body>

        <header class="site-header">
            <div class="container">
                <a href="${pageContext.request.contextPath}/home" class="logo">LUMINA</a>
                <a href="${pageContext.request.contextPath}/products" class="back-link">Back to Store</a>
            </div>
        </header>

        <main class="auth-main">
            <div class="login-card">
                <h1 class="login-title">Account Login</h1>
                <c:choose>
                    <c:when test="${param.failed != null}">
                        <p class="login-subtitle" style="color: red;">Invalid credentials</p>
                    </c:when>
                    <c:when test="${param.logout != null}">
                        <p class="login-subtitle" style="color: green;">Successfully logged out</p>
                    </c:when>
                    <c:when test="${param.expired != null}">
                        <p class="login-subtitle" style="color: orange;">Your session has expired. Please login again.</p>
                    </c:when>
                    <c:otherwise>
                        <p class="login-subtitle">Enter your credentials to access your orders.</p>
                    </c:otherwise>
                </c:choose>

                <form action="${pageContext.request.contextPath}/login" method="POST">
                    <div class="form-group">
                        <label for="email">Email Address</label>
                        <input type="email" id="email" placeholder="name@example.com" name="email" required>
                    </div>

                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" id="password" placeholder="••••••••" name="password" required>
                    </div>

                    <button type="submit" class="btn-login">Sign In</button>
                </form>

                <div class="create-account">
                    New to Lumina? <a href="${pageContext.request.contextPath}/register">Create an Account</a>
                </div>
            </div>
        </main>

    </body>

    </html>