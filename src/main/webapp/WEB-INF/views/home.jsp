<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>

        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Lumina Store - Premium E-Commerce</title>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/home.css">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/global.css">
        </head>

        <body>

            <!-- HEADER -->
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
                                <a href="${pageContext.request.contextPath}/account">Account</a>
                                <a href="${pageContext.request.contextPath}/orders">Orders</a>
                                <a href="${pageContext.request.contextPath}/cart">Cart</a>
                                <a href="${pageContext.request.contextPath}/logout">Logout</a>
                            </c:otherwise>

                        </c:choose>
                    </nav>
                </div>
            </header>


            <!-- MARQUEE BANNER -->
            <div class="marquee-container">
                <div class="marquee-track">
                    <!-- Set 1 -->
                    <span class="marquee-item">Free Worldwide Shipping on Orders Over $150</span>
                    <span class="marquee-sep">•</span>
                    <span class="marquee-item">New Minimalist Collection Drops Friday</span>
                    <span class="marquee-sep">•</span>
                    <span class="marquee-item">Subscribe for 15% Off Your First Order</span>
                    <span class="marquee-sep">•</span>

                    <!-- Set 2 -->
                    <span class="marquee-item">Free Worldwide Shipping on Orders Over $150</span>
                    <span class="marquee-sep">•</span>
                    <span class="marquee-item">New Minimalist Collection Drops Friday</span>
                    <span class="marquee-sep">•</span>
                    <span class="marquee-item">Subscribe for 15% Off Your First Order</span>
                    <span class="marquee-sep">•</span>
                </div>
            </div>

            <!-- HERO SECTION -->
            <section class="hero">
                <h1 class="hero-title">Elevate Your<br>Everyday</h1>

                <p class="hero-subtitle">
                    Discover thoughtfully curated pieces designed for modern living.
                    Minimalist aesthetics meets uncompromising quality.
                </p>

                <div class="hero-actions">
                    <a href="${pageContext.request.contextPath}/product" class="btn btn-primary">Browse Products</a>
                </div>
            </section>

        </body>

        </html>