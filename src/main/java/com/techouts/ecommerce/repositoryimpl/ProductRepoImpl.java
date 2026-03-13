package com.techouts.ecommerce.repositoryimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.techouts.ecommerce.model.Product;

@Repository
public class ProductRepoImpl {

    private SessionFactory sessionFactory;

    ProductRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Product> getAll() {

        Session session = sessionFactory.getCurrentSession();

        Query<Product> query = session.createQuery("FROM Product", Product.class);

        return query.setCacheable(true).list();

    }

    public List<Product> getLimitedProducts(int offset, int limit) {

        Session session = sessionFactory.getCurrentSession();

        return session.createQuery(
                "SELECT p FROM Product p", Product.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
            
             
    }

    public List<Product> getByCategory(String category) {

        Session session = sessionFactory.getCurrentSession();

        Query<Product> query = session.createQuery("FROM Product WHERE category = :category", Product.class)
        .setParameter("category", category);

        return query.setCacheable(true).list();

    }

    public Product save(Product product) {

        Session session = sessionFactory.getCurrentSession();

        session.persist(product);

        return product;
    }

    public Product getById(int productId) {

        Session session = sessionFactory.getCurrentSession();
        return session.get(Product.class, productId);

    }



}
