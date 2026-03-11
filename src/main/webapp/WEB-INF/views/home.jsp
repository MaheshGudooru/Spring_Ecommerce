<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lumina Store - Premium E-Commerce</title>
    <style>
        /* =========================================
           RESET & GLOBAL STYLES
           ========================================= */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif;
        }

        :root {
            --color-bg: #ffffff;
            --color-text: #111111;
            --color-text-light: #666666;
            --color-border: #eaeaec;
            --color-accent: #111111;
            --font-main: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif;
        }

        body {
            background-color: #fafafa;
            color: var(--color-text);
            overflow-x: hidden;
            line-height: 1.6;
        }

        /* =========================================
           HEADER
           ========================================= */
        .site-header {
            border-bottom: 1px solid var(--color-border);
            padding: 20px 0;
            position: sticky;
            top: 0;
            background: var(--color-bg);
            z-index: 100;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.02);
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 0 20px;
        }

        .header-content {
            display: flex;
            justify-content: space-between;
            align-items: center;
            gap: 20px;
        }

        .logo {
            font-size: 1.5rem;
            font-weight: 700;
            letter-spacing: -1px;
            text-decoration: none;
            color: var(--color-text);
            transition: opacity 0.3s ease;
        }

        .logo:hover {
            opacity: 0.7;
        }

        .search-bar {
            flex-grow: 1;
            max-width: 500px;
            display: flex;
            border: 1px solid var(--color-border);
            border-radius: 4px;
            overflow: hidden;
            transition: border-color 0.3s ease;
        }

        .search-bar:focus-within {
            border-color: var(--color-accent);
        }

        .search-bar input {
            width: 100%;
            padding: 10px 15px;
            border: none;
            outline: none;
            font-family: var(--font-main);
            font-size: 0.95rem;
        }

        .search-bar button {
            background: var(--color-bg);
            border: none;
            border-left: 1px solid var(--color-border);
            padding: 0 20px;
            cursor: pointer;
            font-weight: 600;
            transition: background 0.2s ease;
        }

        .search-bar button:hover {
            background: #f5f5f5;
        }

        .user-nav {
            display: flex;
            gap: 40px;
        }

        .user-nav a {
            font-size: 0.9rem;
            font-weight: 600;
            text-decoration: none;
            color: var(--color-text);
            transition: opacity 0.3s ease;
        }

        .user-nav a:hover {
            opacity: 0.6;
        }

        /* =========================================
           MARQUEE BANNER
           ========================================= */
        .marquee-container {
            background-color: #111;
            color: #fff;
            padding: 0.75rem 0;
            overflow: hidden;
            white-space: nowrap;
            font-size: 0.85rem;
            letter-spacing: 1px;
            text-transform: uppercase;
        }

        .marquee-track {
            display: flex;
            width: max-content;
            animation: scrollMarquee 24s linear infinite;
        }

        @keyframes scrollMarquee {
            0% {
                transform: translateX(0);
            }

            100% {
                transform: translateX(-50%);
            }
        }

        .marquee-container:hover .marquee-track {
            animation-play-state: paused;
        }

        .marquee-item {
            padding: 0 2rem;
            flex-shrink: 0;
        }

        .marquee-sep {
            flex-shrink: 0;
            padding: 0 0.25rem;
            opacity: 0.5;
        }

        /* =========================================
           HERO SECTION
           ========================================= */
        .hero {
            min-height: 75vh;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            text-align: center;
            padding: 4rem 2rem;
            background-color: #fafafa;
        }

        .hero-title {
            font-size: clamp(3rem, 10vw, 8rem);
            font-weight: 800;
            line-height: 0.95;
            letter-spacing: -2px;
            text-transform: uppercase;
            margin-bottom: 1.5rem;
            transition: letter-spacing 0.5s ease;
        }

        .hero-title:hover {
            letter-spacing: 2px;
        }

        .hero-subtitle {
            font-size: clamp(1rem, 2vw, 1.25rem);
            font-weight: 400;
            max-width: 600px;
            color: #555;
            margin-bottom: 3rem;
            line-height: 1.6;
        }

        .hero-actions {
            display: flex;
            gap: 1.5rem;
            flex-wrap: wrap;
            justify-content: center;
        }

        .btn {
            display: inline-block;
            padding: 1rem 2.5rem;
            font-size: 1rem;
            font-weight: 600;
            text-transform: uppercase;
            letter-spacing: 1px;
            text-decoration: none;
            cursor: pointer;
            transition: all 0.3s ease;
            border: none;
        }

        .btn-primary {
            background-color: #111;
            color: #fff;
            border: 2px solid #111;
        }

        .btn-primary:hover {
            background-color: transparent;
            color: #111;
        }

        .btn-secondary {
            background-color: transparent;
            color: #111;
            border: 2px solid transparent;
            position: relative;
        }

        .btn-secondary::after {
            content: '';
            position: absolute;
            width: 100%;
            height: 2px;
            bottom: 10px;
            left: 0;
            background-color: #111;
            transform: scaleX(0);
            transform-origin: bottom right;
            transition: transform 0.3s ease-out;
        }

        .btn-secondary:hover::after {
            transform: scaleX(1);
            transform-origin: bottom left;
        }

        /* =========================================
           TRENDING PRODUCTS SECTION
           ========================================= */
        .products-section {
            padding: 5rem 5%;
            background-color: #ffffff;
        }

        .products-section h2 {
            text-align: center;
            margin-bottom: 3rem;
            font-size: 2.5rem;
            font-weight: 700;
            letter-spacing: -1px;
        }

        .product-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
            gap: 2rem;
            max-width: 1400px;
            margin: 0 auto;
        }

        .product-card {
            background-color: #fafafa;
            border-radius: 8px;
            overflow: hidden;
            transition: transform 0.3s, box-shadow 0.3s;
            border: 1px solid #f0f0f0;
        }

        .product-card:hover {
            transform: translateY(-8px);
            box-shadow: 0 12px 24px rgba(0, 0, 0, 0.08);
        }

        .product-card img {
            width: 100%;
            height: 300px;
            object-fit: cover;
            transition: transform 0.3s ease;
        }

        .product-card:hover img {
            transform: scale(1.05);
        }

        .product-info {
            padding: 1.5rem;
        }

        .product-info .category {
            font-size: 0.75rem;
            text-transform: uppercase;
            color: #777777;
            letter-spacing: 1.5px;
            font-weight: 600;
        }

        .product-info h3 {
            font-size: 1.2rem;
            margin: 0.75rem 0;
            font-weight: 600;
            color: var(--color-text);
        }

        .product-info .price {
            font-size: 1.3rem;
            font-weight: 700;
            margin-bottom: 1rem;
            color: var(--color-text);
        }

        .product-card .btn-secondary {
            width: 100%;
            padding: 0.75rem;
            background-color: transparent;
            border: 1px solid #111111;
            color: #111111;
            cursor: pointer;
            font-weight: 600;
            border-radius: 4px;
            transition: all 0.3s;
        }

        .product-card .btn-secondary:hover {
            background-color: #111111;
            color: #ffffff;
        }

        .product-card .btn-secondary::after {
            display: none;
        }

        /* =========================================
           FOOTER
           ========================================= */
        footer {
            background-color: #111111;
            color: #ffffff;
            padding: 3rem 5%;
        }

        .footer-bottom {
            text-align: center;
            color: #999999;
            font-size: 0.9rem;
            padding-top: 2rem;
            border-top: 1px solid #333333;
        }

        /* =========================================
           RESPONSIVE DESIGN
           ========================================= */
        @media (max-width: 768px) {
            .header-content {
                flex-direction: column;
                align-items: stretch;
            }

            .search-bar {
                max-width: 100%;
                width: 100%;
            }

            .user-nav {
                justify-content: center;
                margin-top: 10px;
            }

            .hero {
                min-height: 60vh;
                padding: 3rem 1.5rem;
            }

            .product-grid {
                grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
                gap: 1.5rem;
            }

            .products-section {
                padding: 3rem 5%;
            }
        }

        @media (max-width: 480px) {
            .product-grid {
                grid-template-columns: 1fr;
            }

            .marquee-item {
                padding: 0 1rem;
                font-size: 0.75rem;
            }
        }
    </style>
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
                <a href="#">Account</a>
                <a href="#">Orders</a>
                <a href="#">Cart (0)</a>
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
            <a href="#products" class="btn btn-primary">Browse Products</a>
        </div>
    </section>

    <!-- TRENDING PRODUCTS -->
    <section class="products-section" id="products">
        <h2>Products</h2>
        <div class="product-grid">

            <div class="product-card">
                <img src="https://images.unsplash.com/photo-1468495244123-6c6c332eeece?w=500&q=80" alt="Electronics">
                <div class="product-info">
                    <span class="category">Electronics</span>
                    <h3>Laptop & Tech Accessories</h3>
                    <button class="btn-secondary">View Products</button>
                </div>
            </div>

            <div class="product-card">
                <img src="https://images.unsplash.com/photo-1549298916-b41d501d3772?w=500&q=80" alt="Footwear">
                <div class="product-info">
                    <span class="category">Footwear</span>
                    <h3>Designer Sneakers Collection</h3>
                    <button class="btn-secondary">View Products</button>
                </div>
            </div>

            <div class="product-card">
                <img src="https://images.unsplash.com/photo-1490481651871-ab68de25d43d?w=500&q=80" alt="Clothes">
                <div class="product-info">
                    <span class="category">Clothes</span>
                    <h3>Premium Fashion Apparel</h3>
                    <button class="btn-secondary">View Products</button>
                </div>
            </div>

            <div class="product-card">
                <img src="https://images.unsplash.com/photo-1556911220-bff31c812dba?w=500&q=80" alt="Home Essentials">
                <div class="product-info">
                    <span class="category">Home Essentials</span>
                    <h3>Modern Home Decor Set</h3>
                    <button class="btn-secondary">View Products</button>
                </div>
            </div>

        </div>
    </section>

    <!-- FOOTER -->
    <footer>
        <div class="footer-bottom">
            <p>&copy; 2026 Lumina Store. All rights reserved.</p>
        </div>
    </footer>

</body>

</html>