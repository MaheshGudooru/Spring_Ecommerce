package com.techouts.ecommerce.controller;

import com.techouts.ecommerce.security.CustomUserDetails;
import com.techouts.ecommerce.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/order")
public class OrderController {

    OrderService orderService;

    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String serveOrdersPage(Model model, @AuthenticationPrincipal CustomUserDetails user) {

        model.addAttribute ("userOrderMap", orderService.getOrderByuser (user.getUser ()));

        return "order";
    }

    @PostMapping
    public String placeOrder(@Valid @RequestParam("address") String address,
                             @Valid @RequestParam("paymentMethod") String paymentMethod,
                             @RequestParam("cartTotalPrice") float cartTotalPrice,
                             @AuthenticationPrincipal CustomUserDetails user,
                             RedirectAttributes redirectAttributes) {

        String message = orderService.placeOrder (user.getUser (), address, cartTotalPrice, paymentMethod);

        if(!"success".equals (message)) {
            redirectAttributes.addFlashAttribute ("message", message);
            return "redirect:/cart";
        }

        return "redirect:/order";

    }

    @PostMapping("cancel")
    public ResponseEntity<String> cancelOrder(@RequestParam("orderId") int orderId) {

        orderService.cancelOrder (orderId);

        return ResponseEntity.ok ("success");
    }

}
   