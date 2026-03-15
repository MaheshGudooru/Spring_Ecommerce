package com.techouts.ecommerce.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techouts.ecommerce.model.Product;
import com.techouts.ecommerce.service.ProductService;


@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    ProductController(ProductService productService) {

        this.productService = productService;

    }

//     @PostConstruct
//     public void loadProductsToDB() {

//         List<Product> products = List.of(

//                 new Product("Wireless Mouse", 599, "Ergonomic wireless mouse", 50, "Electronics",
//                         "https://images.unsplash.com/photo-1527864550417-7fd91fc51a46"),

//                 new Product("Mechanical Keyboard", 2999, "RGB mechanical keyboard", 30, "Electronics",
//                         "https://images.unsplash.com/photo-1517336714731-489689fd1ca8"),

//                 new Product("Gaming Headset", 1999, "Surround sound gaming headset", 25, "Electronics",
//                         "https://images.unsplash.com/photo-1518441902110-8c1f2c0c16fa"),

//                 new Product("Bluetooth Speaker", 1499, "Portable bluetooth speaker", 40, "Electronics",
//                         "https://images.unsplash.com/photo-1585386959984-a41552231658"),

//                 new Product("Smart Watch", 3999, "Fitness tracking smartwatch", 35, "Electronics",
//                         "https://images.unsplash.com/photo-1516574187841-cb9cc2ca948b"),

//                 new Product("Laptop Backpack", 999, "Water resistant laptop bag", 60, "Accessories",
//                         "https://images.unsplash.com/photo-1504274066651-8d31a536b11a"),

//                 new Product("USB C Hub", 799, "Multiport USB-C hub", 45, "Electronics",
//                         "https://images.unsplash.com/photo-1587202372775-e229f172b9d7"),

//                 new Product("External Hard Drive", 3499, "1TB portable hard drive", 20, "Electronics",
//                         "https://images.unsplash.com/photo-1580894908361-967195033215"),

//                 new Product("Noise Cancelling Earbuds", 2499, "ANC wireless earbuds", 30, "Electronics",
//                         "https://images.unsplash.com/photo-1583394838336-acd977736f90"),

//                 new Product("LED Desk Lamp", 699, "Adjustable LED study lamp", 70, "Home",
//                         "https://images.unsplash.com/photo-1507473885765-e6ed057f782c"),

//                 new Product("Office Chair", 5999, "Ergonomic office chair", 15, "Furniture",
//                         "https://images.unsplash.com/photo-1580489944761-15a19d654956"),

//                 new Product("Gaming Mouse Pad", 399, "Large RGB mouse pad", 80, "Accessories",
//                         "https://images.unsplash.com/photo-1587202372616-b43abea06c2a"),

//                 new Product("Stainless Steel Water Bottle", 299, "Insulated bottle", 100, "Home",
//                         "https://images.unsplash.com/photo-1526401485004-2fa806b6fcb3"),

//                 new Product("Yoga Mat", 699, "Non slip yoga mat", 75, "Fitness",
//                         "https://images.unsplash.com/photo-1554306274-f23873d9a26c"),

//                 new Product("Dumbbells Set", 1999, "Adjustable dumbbells", 40, "Fitness",
//                         "https://images.unsplash.com/photo-1583454110551-21f2fa2afe61"),

//                 new Product("Running Shoes", 2999, "Comfortable running shoes", 50, "Fashion",
//                         "https://images.unsplash.com/photo-1542291026-7eec264c27ff"),

//                 new Product("Leather Wallet", 799, "Premium leather wallet", 65, "Fashion",
//                         "https://images.unsplash.com/photo-1580910051074-3eb694886505"),

//                 new Product("Men's Casual Shirt", 1199, "Cotton casual shirt", 55, "Fashion",
//                         "https://images.unsplash.com/photo-1521572163474-6864f9cf17ab"),

//                 new Product("Women's Handbag", 1899, "Stylish handbag", 40, "Fashion",
//                         "https://images.unsplash.com/photo-1584917865442-de89df76afd3"),

//                 new Product("Sunglasses", 699, "UV protection sunglasses", 60, "Fashion",
//                         "https://images.unsplash.com/photo-1511499767150-a48a237f0083"),

//                 new Product("Coffee Maker", 3499, "Automatic coffee maker", 20, "Home",
//                         "https://images.unsplash.com/photo-1509042239860-f550ce710b93"),

//                 new Product("Electric Kettle", 899, "Fast boiling kettle", 50, "Home",
//                         "https://images.unsplash.com/photo-1594223274512-ad4803739b7c"),

//                 new Product("Air Fryer", 4999, "Healthy cooking air fryer", 18, "Home",
//                         "https://images.unsplash.com/photo-1604908177522-3c5c4c0d1c65"),

//                 new Product("Vacuum Cleaner", 5999, "High power vacuum cleaner", 12, "Home",
//                         "https://images.unsplash.com/photo-1558317374-067fb5f30001"),

//                 new Product("Smart LED Bulb", 399, "WiFi smart bulb", 100, "Electronics",
//                         "https://images.unsplash.com/photo-1581091012184-5c4c56c0c6df"),

//                 new Product("Portable Power Bank", 1299, "20000mAh power bank", 70, "Electronics",
//                         "https://images.unsplash.com/photo-1580910051074-3eb694886505"),

//                 new Product("Tablet Stand", 399, "Adjustable tablet stand", 85, "Accessories",
//                         "https://images.unsplash.com/photo-1587202372775-e229f172b9d7"),

//                 new Product("Wireless Charger", 799, "Fast wireless charger", 65, "Electronics",
//                         "https://images.unsplash.com/photo-1585386959984-a41552231658"),

//                 new Product("Phone Tripod", 499, "Flexible mobile tripod", 70, "Accessories",
//                         "https://images.unsplash.com/photo-1519183071298-a2962ae0b2f9"),

//                 new Product("DSLR Camera Bag", 2499, "Waterproof camera bag", 25, "Accessories",
//                         "https://images.unsplash.com/photo-1519183071298-a2962ae0b2f9"),

//                 new Product("Graphic T-Shirt", 799, "Printed cotton t-shirt", 90, "Fashion",
//                         "https://images.unsplash.com/photo-1520975916090-3105956dac38"),

//                 new Product("Denim Jeans", 1599, "Slim fit jeans", 60, "Fashion",
//                         "https://images.unsplash.com/photo-1541099649105-f69ad21f3246"),

//                 new Product("Sports Jacket", 2499, "Lightweight sports jacket", 35, "Fashion",
//                         "https://images.unsplash.com/photo-1520975916090-3105956dac38"),

//                 new Product("Beanie Cap", 299, "Winter beanie", 120, "Fashion",
//                         "https://images.unsplash.com/photo-1516826957135-700dedea698c"),

//                 new Product("Desk Organizer", 349, "Wooden desk organizer", 80, "Home",
//                         "https://images.unsplash.com/photo-1519710164239-da123dc03ef4"),

//                 new Product("Notebook", 199, "Hardcover notebook", 150, "Stationery",
//                         "https://images.unsplash.com/photo-1507842217343-583bb7270b66"),

//                 new Product("Gel Pen Pack", 149, "Smooth writing pens", 200, "Stationery",
//                         "https://images.unsplash.com/photo-1519681393784-d120267933ba"),

//                 new Product("Whiteboard", 799, "Magnetic whiteboard", 30, "Stationery",
//                         "https://images.unsplash.com/photo-1518770660439-4636190af475"),

//                 new Product("Table Clock", 499, "Minimal desk clock", 60, "Home",
//                         "https://images.unsplash.com/photo-1509042239860-f550ce710b93"),

//                 new Product("Wall Art Frame", 1299, "Modern wall art", 35, "Home",
//                         "https://images.unsplash.com/photo-1493666438817-866a91353ca9"),

//                 new Product("Indoor Plant Pot", 399, "Ceramic plant pot", 90, "Home",
//                         "https://images.unsplash.com/photo-1501004318641-b39e6451bec6"),

//                 new Product("Cookware Set", 3999, "Non stick cookware set", 25, "Kitchen",
//                         "https://images.unsplash.com/photo-1586201375761-83865001e31c"),

//                 new Product("Knife Set", 1999, "Professional kitchen knives", 30, "Kitchen",
//                         "https://images.unsplash.com/photo-1586201375761-83865001e31c"),

//                 new Product("Cutting Board", 499, "Wooden chopping board", 70, "Kitchen",
//                         "https://images.unsplash.com/photo-1586201375761-83865001e31c"),

//                 new Product("Digital Thermometer", 399, "Instant read thermometer", 80, "Health",
//                         "https://images.unsplash.com/photo-1588776814546-ec7e62a89c2e"),

//                 new Product("Fitness Tracker", 2499, "Activity fitness band", 45, "Fitness",
//                         "https://images.unsplash.com/photo-1516574187841-cb9cc2ca948b"),

//                 new Product("Resistance Bands", 699, "Workout resistance bands", 70, "Fitness",
//                         "https://images.unsplash.com/photo-1599058917765-a780eda07a3e"),

//                 new Product("Skipping Rope", 299, "Speed skipping rope", 120, "Fitness",
//                         "https://images.unsplash.com/photo-1599058917765-a780eda07a3e"),

//                 new Product("Basketball", 899, "Outdoor basketball", 50, "Sports",
//                         "https://images.unsplash.com/photo-1519861531473-9200262188bf"),

//                 new Product("Football", 799, "Professional football", 55, "Sports",
//                         "https://images.unsplash.com/photo-1508098682722-e99c43a406b2"),

//                 new Product("Badminton Racket", 1499, "Lightweight badminton racket", 40, "Sports",
//                         "https://images.unsplash.com/photo-1595433562696-8d14f77f1a19"),

//                 new Product("Cricket Bat", 2499, "Premium wooden cricket bat", 35, "Sports",
//                         "https://images.unsplash.com/photo-1593341646782-e0b495cff86d"),

//                 new Product("Travel Suitcase", 3999, "Hard shell suitcase", 20, "Travel",
//                         "https://images.unsplash.com/photo-1502920917128-1aa500764b1c"),

//                 new Product("Travel Pillow", 499, "Memory foam neck pillow", 80, "Travel",
//                         "https://images.unsplash.com/photo-1526772662000-3f88f10405ff"),

//                 new Product("Laptop Cooling Pad", 999, "Cooling pad for laptops", 45, "Electronics",
//                         "https://images.unsplash.com/photo-1517336714731-489689fd1ca8"),

//                 new Product("Webcam", 1499, "HD streaming webcam", 35, "Electronics",
//                         "https://images.unsplash.com/photo-1587202372616-b43abea06c2a"),

//                 new Product("Microphone", 1999, "USB condenser microphone", 30, "Electronics",
//                         "https://images.unsplash.com/photo-1516280440614-37939bbacd81")

//         );

//         for (Product product : products) {
//             productService.saveProduct(product);
//         }
//     }

    @GetMapping
    public String serveProductsPage(@RequestParam(name = "page", defaultValue = "1") int pageNo, Model model) {

        int totalProductsCnt = productService.getProducts(null).size();

        model.addAttribute("products", productService.getProducts(pageNo));
        model.addAttribute("totalPages", (int) Math.ceil((double) (totalProductsCnt / 12)));
        model.addAttribute("pageNo", pageNo);

        return "products";

    }

    @GetMapping("/{id}")
    public String serveProductSpecificPage(@PathVariable("id") int id, Model model) {

        Product product = productService.getProduct(id);

        model.addAttribute("productDetail", product);

        return "product";
    }
}
