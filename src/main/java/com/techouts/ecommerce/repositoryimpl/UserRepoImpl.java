package com.techouts.ecommerce.repositoryimpl;

import com.techouts.ecommerce.model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public class UserRepoImpl {

    private SessionFactory sessionFactory;

    UserRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<User> findUserByEmail(String email) {

        Session session = sessionFactory.getCurrentSession();

        Query<User> query = session.createNamedQuery("User.findByEmail", User.class);
        query.setParameter("email", email);

        return query.uniqueResultOptional();

    }

    public Optional<User> getById(int userId) {

        Session session = sessionFactory.getCurrentSession();
        
        Query<User> query = session.createNamedQuery("User.findById", User.class);
        query.setParameter("userId", userId);

        return query.uniqueResultOptional();

    }

    public void createUser(User user) {

        Session session = sessionFactory.getCurrentSession();
        session.persist(user);

    }

    public void updateUser(User user) {

        Session session = sessionFactory.getCurrentSession();
        session.merge(user);

    }
}
