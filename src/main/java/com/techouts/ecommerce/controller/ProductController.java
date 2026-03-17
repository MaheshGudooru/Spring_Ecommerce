package com.techouts.ecommerce.controller;

import com.techouts.ecommerce.model.Product;
import com.techouts.ecommerce.service.ProductService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    ProductController(ProductService productService) {

        this.productService = productService;

    }

    @GetMapping
    public String serveProductsPage(@RequestParam(name = "page", defaultValue = "1") int pageNo, Model model) {

        int totalProductsCnt = productService.getProducts(null).size();

        model.addAttribute("products", productService.getProducts(pageNo));
        model.addAttribute("totalPages", (int) Math.ceil((double) totalProductsCnt / 12));
        model.addAttribute("pageNo", pageNo);

        return "products";

    }

    // @PostConstruct
    public void loadProductsToDB() {
        List<Product> products = new ArrayList<>(List.of(

                /* ELECTRONICS */
                new Product("Apple AirPods Pro (2nd Gen)", 22999, "Noise cancelling wireless earbuds.", 25,
                        "ELECTRONICS", "https://m.media-amazon.com/images/I/61SUj2aKoEL._AC_SL1500_.jpg"),
                new Product("Sony WH-1000XM5 Headphones", 29999, "Premium noise cancelling headphones.", 15,
                        "ELECTRONICS", "https://m.media-amazon.com/images/I/61vIICn0KLL._AC_SL1500_.jpg"),
                new Product("Samsung 970 EVO Plus 500GB SSD", 5999, "High-speed NVMe SSD.", 35, "ELECTRONICS",
                        "https://m.media-amazon.com/images/I/81R2a7v7F+L._AC_SL1500_.jpg"),
                new Product("Canon EOS 1500D DSLR Camera", 38999, "24MP DSLR with WiFi.", 10, "ELECTRONICS",
                        "https://m.media-amazon.com/images/I/914hFeTU2-L._AC_SL1500_.jpg"),
                new Product("Amazon Echo Dot (5th Gen)", 4499, "Smart speaker with Alexa.", 40, "ELECTRONICS",
                        "https://m.media-amazon.com/images/I/714Rq4k05UL._AC_SL1500_.jpg"),

                /* FASHION */
                new Product("Levi's Men's Slim Fit Jeans", 2499, "Comfortable slim fit denim jeans.", 50, "FASHION",
                        "https://m.media-amazon.com/images/I/81QpkIctqPL._AC_UL1500_.jpg"),
                new Product("Puma Men's Cotton T-Shirt", 999, "Soft breathable cotton t-shirt.", 70, "FASHION",
                        "https://m.media-amazon.com/images/I/71jG+e7roXL._AC_UL1500_.jpg"),
                new Product("Allen Solly Formal Shirt", 1899, "Slim fit formal shirt for office wear.", 45, "FASHION",
                        "https://m.media-amazon.com/images/I/61kWB+uzR2L._AC_UL1500_.jpg"),
                new Product("BIBA Women's Kurta", 1999, "Elegant printed ethnic kurta.", 40, "FASHION",
                        "https://m.media-amazon.com/images/I/71ZyPpZ2vBL._AC_UL1500_.jpg"),
                new Product("H&M Women's Summer Dress", 2999, "Lightweight floral summer dress.", 35, "FASHION",
                        "https://images.unsplash.com/photo-1520962918287-7448c2878f65"),

                /* FOOTWEAR */
                new Product("Nike Air Max 270 Shoes", 7999, "Comfortable running shoes with air cushioning.", 30,
                        "FOOTWEAR", "https://m.media-amazon.com/images/I/71XH0ZyZ-PL._AC_UL1500_.jpg"),
                new Product("Adidas Ultraboost Shoes", 11999, "High performance running shoes.", 20, "FOOTWEAR",
                        "https://m.media-amazon.com/images/I/61utX8kBDlL._AC_UL1500_.jpg"),
                new Product("Puma Casual Sneakers", 2999, "Stylish everyday sneakers.", 50, "FOOTWEAR",
                        "https://images.unsplash.com/photo-1542291026-7eec264c27ff"),
                new Product("Woodland Leather Boots", 4999, "Durable outdoor boots.", 25, "FOOTWEAR",
                        "https://images.unsplash.com/photo-1600180758890-6b94519a8ba6"),

                /* HOME & KITCHEN */
                new Product("Philips Air Fryer HD9200", 9999, "Healthy oil-free cooking.", 20, "HOME APPLIANCES",
                        "https://m.media-amazon.com/images/I/71kJ8h6g3BL._AC_SL1500_.jpg"),
                new Product("Prestige Induction Cooktop", 2999, "Energy efficient cooking solution.", 30,
                        "HOME_APPLIANCES", "https://m.media-amazon.com/images/I/61S4bP9v7zL._AC_SL1500_.jpg"),
                new Product("Milton Thermosteel Bottle", 599, "Insulated stainless steel bottle.", 100, "HOME",
                        "https://m.media-amazon.com/images/I/71ZyPpZ2vBL._AC_SL1500_.jpg"),
                new Product("IKEA Study Table", 8999, "Minimal wooden study desk.", 15, "FURNITURE",
                        "https://images.unsplash.com/photo-1505693416388-ac5ce068fe85"),
                new Product("Ergonomic Office Chair", 12999, "Comfortable chair with lumbar support.", 12, "FURNITURE",
                        "https://images.unsplash.com/photo-1580480055273-228ff5388ef8"),

                /* BOOKS */
                new Product("Atomic Habits by James Clear", 499, "Build good habits effectively.", 80, "BOOKS",
                        "https://images.unsplash.com/photo-1544947950-fa07a98d237f"),
                new Product("Rich Dad Poor Dad", 399, "Personal finance classic.", 90, "BOOKS",
                        "https://images.unsplash.com/photo-1516979187457-637abb4f9353"),
                new Product("The Alchemist by Paulo Coelho", 350, "Inspirational novel.", 75, "BOOKS",
                        "https://images.unsplash.com/photo-1519682337058-a94d519337bc"),

                /* ACCESSORIES */
                new Product("Ray-Ban Aviator Sunglasses", 8999, "Classic aviator sunglasses.", 30, "ACCESSORIES",
                        "https://images.unsplash.com/photo-1511499767150-a48a237f0083"),
                new Product("Fossil Leather Wallet", 3499, "Premium leather wallet.", 45, "ACCESSORIES",
                        "https://images.unsplash.com/photo-1627123424574-724758594e93"),
                new Product("Casio Digital Watch", 2499, "Durable digital wrist watch.", 50, "ACCESSORIES",
                        "https://images.unsplash.com/photo-1523275335684-37898b6baf30"),

                /* FITNESS */
                new Product("HRX Adjustable Dumbbells Set", 4999, "Home workout dumbbells.", 30, "FITNESS",
                        "https://images.unsplash.com/photo-1599058917212-d750089bc07e"),
                new Product("Yoga Mat Anti-Slip", 999, "Comfortable yoga mat.", 60, "FITNESS",
                        "https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b"),
                new Product("Skipping Rope Fitness", 299, "High speed skipping rope.", 100, "FITNESS",
                        "https://images.unsplash.com/photo-1599058917765-a780eda07a3e"),

                /* BAGS */
                new Product("Skybags Laptop Backpack", 2499, "Durable backpack with laptop sleeve.", 40, "BAGS",
                        "https://images.unsplash.com/photo-1509762774605-f07235a08f1f"),
                new Product("American Tourister Trolley Bag", 6999, "Lightweight travel suitcase.", 20, "BAGS",
                        "https://images.unsplash.com/photo-1502920917128-1aa500764b8a")

        ));

        for (Product product : products) {
            productService.saveProduct(product);
        }
    }

    @GetMapping("/{id}")
    public String serveProductSpecificPage(@PathVariable("id") int id, Model model) {

        Product product = productService.getProduct(id);

        model.addAttribute("productDetail", product);

        System.out.println(product.getId());

        return "product";
    }
}
