package com.techouts.ecommerce.security;

import com.techouts.ecommerce.model.User;
import com.techouts.ecommerce.repositoryimpl.UserRepoImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
public class CustomUserDetailService implements UserDetailsService {

    UserRepoImpl userRepo;

    CustomUserDetailService(UserRepoImpl userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> user = userRepo.findUserByEmail (email);

        if(user.isEmpty ()) {
            throw new UsernameNotFoundException ("User with the email: \"" + email + "\" not found!!!");
        }

        return new CustomUserDetails(user.get());

    }
}
