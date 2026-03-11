package com.techouts.ecommerce.repositoryimpl;

import com.techouts.ecommerce.model.User;

import jakarta.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public class UserRepoImpl {

    SessionFactory sessionFactory;

    UserRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<User> findUser(String email) {

        Session session = sessionFactory.getCurrentSession ();

        Query<User> query = session.createNamedQuery ("User.findByEmail", User.class);
        query.setParameter ("email", email);

        return query.uniqueResultOptional();

    }

    public void createUser(User user) {

        Session session = sessionFactory.getCurrentSession();
        session.persist(user); 
          
    }
}
