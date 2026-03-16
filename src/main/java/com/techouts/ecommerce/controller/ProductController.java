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
        model.addAttribute ("totalPages", (int) Math.ceil ( (double)totalProductsCnt / 12));
        model.addAttribute ("pageNo", pageNo);

        return "products";

    }

    @GetMapping("/{id}")
    public String serveProductSpecificPage(@PathVariable("id") int id, Model model) {

        Product product = productService.getProduct (id);

        model.addAttribute ("productDetail", product);

        System.out.println (product.getId ());

        return "product";
    }
}
