package com.techouts.ecommerce.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techouts.ecommerce.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    ProductController(ProductService productService) {

        this.productService = productService;

    }

    // public void loadProductsToDB() {

    // for (Product product : products) {
    // productService.saveProduct(product);
    // }
    // }

    @GetMapping
    public String serveProductPage(@RequestParam(defaultValue = "1") int pageNo, Model model) {

        


        model.addAttribute("products", productService.getProducts(null));

        return "product";

    }
}
