package com.techouts.ecommerce.controller;


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
    public String serveProductPage(@RequestParam(name = "page", defaultValue = "1") int pageNo, Model model) {

        int totalProductsCnt = productService.getProducts(null).size();

        model.addAttribute("products", productService.getProducts(pageNo));
        model.addAttribute("totalPages", (int) Math.ceil((double) (totalProductsCnt / 12)));
        model.addAttribute("pageNo", pageNo);

        return "product";

    }
}
