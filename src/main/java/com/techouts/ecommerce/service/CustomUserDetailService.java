package com.techouts.ecommerce.service;

import com.techouts.ecommerce.model.User;
import com.techouts.ecommerce.repositoryimpl.UserRepoImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomUserDetailService implements UserDetailsService {

    UserRepoImpl userRepo;

    CustomUserDetailService(UserRepoImpl userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> user = userRepo.findUser (email);

        if(user.isEmpty ()) {
            throw new UsernameNotFoundException ("User with the email: \"" + email + "\" not found!!!");
        }

        return org.springframework.security.core.userdetails.User
                .withUsername (user.get ().getName ())
                .password (user.get ().getPassword ())
                .build ();

    }
}
