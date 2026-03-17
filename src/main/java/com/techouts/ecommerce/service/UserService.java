package com.techouts.ecommerce.service;

import com.techouts.ecommerce.model.Cart;
import com.techouts.ecommerce.model.User;
import com.techouts.ecommerce.repository.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserService {

    UserRepo userRepoImpl;
    PasswordEncoder passwordEncoder;

    UserService(UserRepo userRepoImpl, PasswordEncoder passwordEncoder) {
        this.userRepoImpl = userRepoImpl;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public User getUser(String email) {

        Optional<User> user = userRepoImpl.findUserByEmail(email);
        return user.orElseGet(User::new);

    }

    @Transactional
    public boolean registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (userRepoImpl.findUserByEmail(user.getEmail()).isPresent()) {

            return false;
        }

        Cart userCart = new Cart();
        userCart.setUserId(user);
        user.setCart(userCart);

        user.setJoinedDate(LocalDate.now());
        userRepoImpl.createUser(user);

        return true;

    }

    @Transactional
    public String updateUserDetails(String emailAddress, String fullName, User currLoggedInUser) {

        User user = userRepoImpl.findUserByEmail(emailAddress).orElse(null);

        if (user != null) {
            if (user.getId() == currLoggedInUser.getId()) {
                currLoggedInUser.setName(fullName);
                userRepoImpl.updateUser(currLoggedInUser);
                return "success";
            }

        } else {
            currLoggedInUser.setName(fullName);
            currLoggedInUser.setEmail(emailAddress);

            userRepoImpl.updateUser(currLoggedInUser);
            return "success";
        }

        return "User with this email already exists";

    }
}
