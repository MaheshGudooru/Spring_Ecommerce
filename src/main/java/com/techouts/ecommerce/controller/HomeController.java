package com.techouts.ecommerce.controller;

import com.techouts.ecommerce.model.User;
import com.techouts.ecommerce.security.CustomUserDetails;
import com.techouts.ecommerce.service.UserService;

import jakarta.validation.Valid;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {

    private UserService userService;

    HomeController(UserService userService) {
        this.userService = userService;
    }

    public String home() {
        return "home";
    }

    @GetMapping("/denied")
    public String accessDenied() {

        return "page403";

    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping({ "home", "" })
    public String Home(Principal principal, Model model) {

        model.addAttribute("user", principal);
        return "home";
    }

    @GetMapping("register")
    public String serveRegisterView(Model model) {

        model.addAttribute("user", new User());
        return "register";

    }

    @PostMapping("register")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {

        if (result.hasErrors()) {

            return "register";

        }

        boolean emailInUse = userService.registerUser(user);

        if(!emailInUse) {
            model.addAttribute ("emailExists", true);
            return "register";
        }

        return "redirect:/login";
    }

    @GetMapping("account")
    public String serveProfilePage(Model model, @AuthenticationPrincipal CustomUserDetails user) {

        model.addAttribute("user", user.getUser());

        return "account";

    }

    @PostMapping("account")
    public ResponseEntity<String> updateLoggedInUser(@RequestParam("fullName") String fullName,
            @RequestParam("emailAddress") String emailAddress,
            @AuthenticationPrincipal CustomUserDetails user) {

        return ResponseEntity.ok(userService.updateUserDetails(emailAddress, fullName, user.getUser()));

    }

}
