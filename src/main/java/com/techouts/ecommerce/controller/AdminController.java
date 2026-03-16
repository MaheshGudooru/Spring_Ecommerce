package com.techouts.ecommerce.controller;

import com.techouts.ecommerce.model.Product;
import com.techouts.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String serveAdminPage(@RequestParam(name = "page", defaultValue = "1") int pageNo, Model model) {

        int totalProductsCnt = productService.getProducts(null).size();

        model.addAttribute("productList", productService.getProducts(pageNo));
        model.addAttribute("totalPages", (int) Math.ceil((double) totalProductsCnt / 12));
        model.addAttribute("pageNo", pageNo);

        return "allproducts";
    }

    @PostMapping("delete")
    public ResponseEntity<String> deleteProduct(@RequestParam("productId") int productId) {

        productService.deleteProduct(productId);

        return ResponseEntity.ok("success");

    }

    @GetMapping("/add")
    public String serveProductCreationForm() {

        return "addproduct";

    }

    @PostMapping("add")
    public ResponseEntity<String> addNewProduct(@RequestParam("productName") String productName,
                                @RequestParam("productCategory") String category,
                                @RequestParam("productPrice") float price,
                                @RequestParam("productStock") int stock,
                                @RequestParam("productDescription") String productDescription,
                                @RequestParam("productImage") String productImage) {

        Product product = productService.createNewProduct(productName, category, price, productDescription, stock, productImage);
        String message = product == null ? "warning::Product already exists" : "success::Product successfully created";
        return ResponseEntity.ok(message);
    }


    @GetMapping("update/{id}")
    public String serveUpdateForm(Model model, @PathVariable(name = "id") int productId) {


        Product product = productService.getProduct(productId);
        if (product == null) {
            return null;
        }

        model.addAttribute("product", productService.getProduct(productId));

        return "updateform";

    }

    @PostMapping("update")
    public String updateProduct(@RequestParam("productId") int productId,
                                @RequestParam("productName") String productName,
                                @RequestParam("category") String category,
                                @RequestParam("price") float price,
                                @RequestParam("stock") int stock,
                                @RequestParam("productDescription") String productDescription,
                                @RequestParam("productImage") String productImage,
                                RedirectAttributes redirectAttributes) {

        if(stock <= -1) {
            redirectAttributes.addFlashAttribute ("message", "Stock cannot be less than zero");
            return "redirect:/update/" + productId;
        }

        productService.updateProductDetails(productId, productName, category, price, stock, productDescription, productImage);

        return "redirect:/admin";

    }

}
