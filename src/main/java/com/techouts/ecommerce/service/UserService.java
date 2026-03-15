package com.techouts.ecommerce.service;

import com.techouts.ecommerce.model.Cart;
import com.techouts.ecommerce.model.User;
import com.techouts.ecommerce.repositoryimpl.UserRepoImpl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    UserRepoImpl userRepoImpl;
    PasswordEncoder passwordEncoder;

    UserService(UserRepoImpl userRepoImpl, PasswordEncoder passwordEncoder) {
        this.userRepoImpl = userRepoImpl;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public User getUser(String email) {

        Optional<User> user = userRepoImpl.findUserByEmail(email);
        return user.orElseGet(User::new);

    }

    @Transactional
    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Cart userCart = new Cart();
        userCart.setUserId(user);
        user.setCart(userCart);

        user.setJoinedDate(LocalDate.now());
        userRepoImpl.createUser(user);

    }

    @Transactional
    public String updateUserDetails(String emailAddress, String fullName, User currLoggedInUser) {

        User user = userRepoImpl.findUserByEmail(emailAddress).orElse(null);

        if (user == null) {

            currLoggedInUser.setName(fullName);
            currLoggedInUser.setEmail(emailAddress);

            userRepoImpl.updateUser(currLoggedInUser);

            return "success" ;

        }

        return "User with this email already exists";

    }
}
