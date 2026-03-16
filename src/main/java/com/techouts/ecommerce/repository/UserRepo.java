package com.techouts.ecommerce.repository;

import com.techouts.ecommerce.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Optional;

public interface UserRepo {

    Optional<User> findUserByEmail(String email);

    Optional<User> getById(int userId) ;

    void createUser(User user);

    void updateUser(User user);

}
