package com.techouts.ecommerce.security;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.techouts.ecommerce.model.User;

public class CustomUserDetails implements UserDetails{

    private User user;

    CustomUserDetails(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority (user.getRole().name ()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

}
