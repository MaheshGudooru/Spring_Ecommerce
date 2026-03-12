package com.techouts.ecommerce.controller;


import com.techouts.ecommerce.model.User;
import com.techouts.ecommerce.service.UserService;

import jakarta.validation.Valid;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class HomeController {

    private UserService userService;

    HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String Home(Principal principal, Model model) {

        model.addAttribute("user", principal);
        return "home";
    }

    @GetMapping("/register")
    public String serveRegisterView(Model model) {

        model.addAttribute("user", new User());
        return "register";

    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {

        if(result.hasErrors()) {

            return "register";

        }

        userService.registerUser(user);
    
        return "redirect:/home";
    }

}
