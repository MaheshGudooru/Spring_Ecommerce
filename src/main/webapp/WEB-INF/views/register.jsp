<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>

        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Create Account - Lumina Store</title>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/register.css">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/global.css">
        </head>

        <body>
            <div id="toast-container"></div>
            <header class="site-header">
                <div class="container">
                    <a href="${pageContext.request.contextPath}/home" class="logo">LUMINA</a>
                    <a href="${pageContext.request.contextPath}/product" class="back-link">Back to Store</a>
                </div>
            </header>

            <main class="auth-wrapper">
                <div class="auth-card">
                    <h1>Create Account</h1>

                    <p>Join Lumina to unlock exclusive premium benefits.</p>

                    <form class="user-register-form" action="${pageContext.request.contextPath}/register" method="post">

                        <!-- FULL NAME -->
                        <div class="form-group">
                            <label for="fullname">Full Name</label>

                            <input type="text" id="fullname" name="name" placeholder="Jane Doe" required>
                            <c:if test="${not empty requestScope['org.springframework.validation.BindingResult.user']}">
                                <c:forEach var="error"
                                    items="${requestScope['org.springframework.validation.BindingResult.user'].fieldErrors}">

                                    <c:if test="${error.field == 'name'}">
                                        <span class="error">${error.defaultMessage}</span>
                                    </c:if>

                                </c:forEach>
                            </c:if>
                        </div>


                        <!-- EMAIL -->
                        <div class="form-group">
                            <label for="email">Email Address</label>

                            <input type="email" id="email" name="email" placeholder="you@example.com" required>
                            <c:if test="${not empty requestScope['org.springframework.validation.BindingResult.user']}">

                                <c:forEach var="error"
                                    items="${requestScope['org.springframework.validation.BindingResult.user'].fieldErrors}">

                                    <c:if test="${error.field == 'email'}">
                                        <span class="error">${error.defaultMessage}</span>
                                    </c:if>

                                </c:forEach>
                            </c:if>
                        </div>


                        <!-- PASSWORD -->
                        <div class="form-group">
                            <label for="password">Password</label>

                            <input type="password" id="password" name="password" placeholder="Create a strong password"
                                required>
                            <c:if test="${not empty requestScope['org.springframework.validation.BindingResult.user']}">

                                <c:forEach var="error"
                                    items="${requestScope['org.springframework.validation.BindingResult.user'].fieldErrors}">

                                    <c:if test="${error.field == 'password'}">
                                        <span class="error">${error.defaultMessage}</span>
                                    </c:if>

                                </c:forEach>
                            </c:if>
                        </div>


                        <button class="btn-primary" type="submit">
                            Create Account
                        </button>

                    </form>

                    <a href="${pageContext.request.contextPath}/login" class="switch-auth-link">

                        Already have an account?
                        <span>Sign in</span>

                    </a>
                </div>
            </main>

            <c:if test="${emailExists}">
            <script src="${pageContext.request.contextPath}/static/scripts/toast.js"></script>
            <script>
                showToast("Email already in user!", "warning");
            </script>
            </c:if>

        </body>

        </html>