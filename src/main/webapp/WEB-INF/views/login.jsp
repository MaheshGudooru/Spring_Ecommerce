<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
            <a href="${pageContext.request.contextPath}/product" class="back-link">Back to Store</a>
        </div>
    </header>

        <main class="auth-main">
            <div class="login-card">
                <h1 class="login-title">Account Login</h1>
                <p class="login-subtitle">${empty loginMessage ? 'Enter your credentials to access your orders.' : loginMessage}</p>

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