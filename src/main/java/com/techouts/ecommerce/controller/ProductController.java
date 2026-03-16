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

        int totalProductsCnt = productService.getProducts (null).size ();

        model.addAttribute ("products", productService.getProducts (pageNo));
        model.addAttribute ("totalPages", (int) Math.ceil ((double) totalProductsCnt / 12));
        model.addAttribute ("pageNo", pageNo);

        return "products";

    }

//    @PostConstruct
//    public void loadProductsToDB() {
//        List<Product> products = List.of(
//
//                new Product("iPhone 14", 79999f, "Apple iPhone 14 smartphone", 25, "Electronics",
//                        "https://loremflickr.com/600/600/iphone"),
//
//                new Product("Samsung Galaxy S23", 74999f, "Samsung flagship Android smartphone", 30, "Electronics",
//                        "https://loremflickr.com/600/600/samsung-phone"),
//
//                new Product("MacBook Air M2", 119999f, "Apple MacBook Air laptop", 15, "Electronics",
//                        "https://loremflickr.com/600/600/macbook"),
//
//                new Product("Dell XPS 13", 109999f, "Dell XPS ultrabook laptop", 12, "Electronics",
//                        "https://loremflickr.com/600/600/dell-laptop"),
//
//                new Product("Sony WH-1000XM5", 29999f, "Noise cancelling headphones", 20, "Electronics",
//                        "https://loremflickr.com/600/600/headphones"),
//
//                new Product("Apple Watch Series 9", 45999f, "Apple smartwatch", 18, "Electronics",
//                        "https://loremflickr.com/600/600/apple-watch"),
//
//                new Product("iPad Air", 59999f, "Apple tablet device", 22, "Electronics",
//                        "https://loremflickr.com/600/600/ipad"),
//
//                new Product("Logitech MX Master 3 Mouse", 8999f, "Wireless productivity mouse", 40, "Electronics",
//                        "https://loremflickr.com/600/600/computer-mouse"),
//
//                new Product("Mechanical Keyboard", 6999f, "RGB mechanical keyboard", 35, "Electronics",
//                        "https://loremflickr.com/600/600/mechanical-keyboard"),
//
//                new Product("Gaming Monitor 27 inch", 24999f, "144Hz gaming monitor", 10, "Electronics",
//                        "https://loremflickr.com/600/600/gaming-monitor"),
//
//                new Product("Nike Running Shoes", 5999f, "Comfortable running shoes", 50, "Fashion",
//                        "https://loremflickr.com/600/600/running-shoes"),
//
//                new Product("Adidas Hoodie", 3999f, "Warm casual hoodie", 45, "Fashion",
//                        "https://loremflickr.com/600/600/hoodie"),
//
//                new Product("Levi's Jeans", 3499f, "Classic denim jeans", 38, "Fashion",
//                        "https://loremflickr.com/600/600/jeans"),
//
//                new Product("Puma Sports T-shirt", 1999f, "Breathable sports t-shirt", 60, "Fashion",
//                        "https://loremflickr.com/600/600/sports-tshirt"),
//
//                new Product("RayBan Sunglasses", 8999f, "Premium sunglasses", 25, "Fashion",
//                        "https://loremflickr.com/600/600/sunglasses"),
//
//                new Product("Leather Wallet", 1499f, "Genuine leather wallet", 70, "Accessories",
//                        "https://loremflickr.com/600/600/wallet"),
//
//                new Product("Laptop Backpack", 2499f, "Durable backpack for laptops", 55, "Accessories",
//                        "https://loremflickr.com/600/600/backpack"),
//
//                new Product("Office Chair", 12999f, "Ergonomic office chair", 20, "Furniture",
//                        "https://loremflickr.com/600/600/office-chair"),
//
//                new Product("Study Table", 8999f, "Wooden study desk", 18, "Furniture",
//                        "https://loremflickr.com/600/600/study-desk"),
//
//                new Product("Bookshelf", 6999f, "Modern bookshelf", 15, "Furniture",
//                        "https://loremflickr.com/600/600/bookshelf"),
//
//                new Product("LED Desk Lamp", 1999f, "Adjustable desk lamp", 40, "Home",
//                        "https://loremflickr.com/600/600/desk-lamp"),
//
//                new Product("Coffee Maker", 4999f, "Automatic coffee maker", 22, "Home Appliances",
//                        "https://loremflickr.com/600/600/coffee-maker"),
//
//                new Product("Air Fryer", 7999f, "Healthy air fryer", 20, "Home Appliances",
//                        "https://loremflickr.com/600/600/air-fryer"),
//
//                new Product("Kitchen Blender", 2999f, "High speed blender", 28, "Home Appliances",
//                        "https://loremflickr.com/600/600/blender"),
//
//                new Product("Electric Kettle", 1499f, "Fast boiling kettle", 50, "Home Appliances",
//                        "https://loremflickr.com/600/600/electric-kettle"),
//
//                new Product("Yoga Mat", 999f, "Non-slip yoga mat", 65, "Fitness",
//                        "https://loremflickr.com/600/600/yoga-mat"),
//
//                new Product("Adjustable Dumbbells", 8999f, "Adjustable dumbbell set", 25, "Fitness",
//                        "https://loremflickr.com/600/600/dumbbells"),
//
//                new Product("Resistance Bands", 799f, "Workout resistance bands", 70, "Fitness",
//                        "https://loremflickr.com/600/600/resistance-bands"),
//
//                new Product("Cricket Bat", 2999f, "Professional cricket bat", 30, "Sports",
//                        "https://loremflickr.com/600/600/cricket-bat"),
//
//                new Product("Football", 1499f, "Standard football", 45, "Sports",
//                        "https://loremflickr.com/600/600/football"),
//
//                new Product("Basketball", 1299f, "Indoor outdoor basketball", 40, "Sports",
//                        "https://loremflickr.com/600/600/basketball"),
//
//                new Product("Gaming Chair", 17999f, "Comfortable gaming chair", 12, "Furniture",
//                        "https://loremflickr.com/600/600/gaming-chair"),
//
//                new Product("Smart TV 55 inch", 54999f, "4K UHD Smart TV", 10, "Electronics",
//                        "https://loremflickr.com/600/600/smart-tv"),
//
//                new Product("Bluetooth Speaker", 3999f, "Portable bluetooth speaker", 35, "Electronics",
//                        "https://loremflickr.com/600/600/bluetooth-speaker"),
//
//                new Product("Wireless Earbuds", 4999f, "True wireless earbuds", 45, "Electronics",
//                        "https://loremflickr.com/600/600/wireless-earbuds")
//
//        );
//
//        for (Product product : products) {
//            productService.saveProduct(product);
//        }
//    }

    @GetMapping("/{id}")
    public String serveProductSpecificPage(@PathVariable("id") int id, Model model) {

        Product product = productService.getProduct (id);

        model.addAttribute ("productDetail", product);

        System.out.println (product.getId ());

        return "product";
    }
}
