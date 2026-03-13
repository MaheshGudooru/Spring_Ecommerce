package com.techouts.ecommerce.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techouts.ecommerce.model.CartItem;
import com.techouts.ecommerce.security.CustomUserDetails;
import com.techouts.ecommerce.service.CartService;
import com.techouts.ecommerce.service.ProductService;

@Controller
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;
    private ProductService productService;

    CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping
    public String serveCartPage(Model model, @AuthenticationPrincipal CustomUserDetails user) {

        List<CartItem> userCartItems = cartService.getCartItemsByUser(user.getUser());

        model.addAttribute("cartItemsList", userCartItems);
        model.addAttribute("cartTotalPrice", cartService.calculateTotalCartPrice(userCartItems));

        return "cart";

    }

    @PostMapping("/add")
    public ResponseEntity<String> addProductToCart(@RequestParam("productId") int productId,
            @AuthenticationPrincipal CustomUserDetails user) {

        System.out.println("PRODUCT-ID: " + productId);

        if (user == null) {
            return ResponseEntity.status(401).body("login_required");
        }

        cartService.addToCart(user.getUser(), productId);


        return ResponseEntity.ok("success::" + productService.getProduct(productId).getName());

    }

    @PostMapping("/remove")
    public ResponseEntity<String> removeProductFromCart(@RequestParam("cartItemId") int cartItemId) {

        cartService.removeFromCart(cartItemId);

        return ResponseEntity.ok("success");

    }

    @PostMapping("/increasecnt")
    public ResponseEntity<String> increaseProductQuantity(@RequestParam("cartItemId") int cartItemId) {

        cartService.changeProductQuantity(cartItemId, true);

        return ResponseEntity.ok("success");

    }

    @PostMapping("/decreasecnt")
    public ResponseEntity<String> decreaseProductQuantity(@RequestParam("cartItemId") int cartItemId) {

        cartService.changeProductQuantity(cartItemId, false);

        return ResponseEntity.ok("success");

    }
}
