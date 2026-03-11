package com.techouts.ecommerce.service;

import com.techouts.ecommerce.model.Cart;
import com.techouts.ecommerce.model.User;
import com.techouts.ecommerce.repositoryimpl.UserRepoImpl;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepoImpl userRepo;
    PasswordEncoder passwordEncoder;

    UserService (UserRepoImpl userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUser(String email) {

        Optional<User> user = userRepo.findUser (email);
        return user.orElseGet (User::new);

    }

    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Cart userCart = new Cart();
        userCart.setUserId(user);
        user.setCart(userCart);

        userRepo.createUser(user);

    }
}
