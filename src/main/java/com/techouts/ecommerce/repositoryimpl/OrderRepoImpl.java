package com.techouts.ecommerce.repositoryimpl;

import java.time.LocalDate;
import java.util.List;

import com.techouts.ecommerce.repository.OrderRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.techouts.ecommerce.model.Order;
import com.techouts.ecommerce.model.User;

@Repository
public class OrderRepoImpl implements OrderRepo {

    private final SessionFactory sessionFactory;


    OrderRepoImpl(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;

    }

    public Order createOrder(User user, String address, String paymentMethod, float totalPrice) {

        Session session = sessionFactory.getCurrentSession();

        Order order = new Order(user, totalPrice, LocalDate.now(), paymentMethod, address);

        session.persist(order);

        return order;

    }

    public Order updateOrder(Order order) {

        Session session = sessionFactory.getCurrentSession();

        session.merge(order);

        return order;
    }

    public List<Order> getByUser(User user) {

        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("FROM Order o WHERE o.userId = :user ORDER BY o.id DESC", Order.class)
                    .setParameter("user", user).list();
        
    }

    public Order saveOrder(Order order) {

        Session session = sessionFactory.getCurrentSession();

        session.merge(order);

        return order;

    }

    public Order getById(int orderId) {

        Session session = sessionFactory.getCurrentSession ();

        return session.get(Order.class, orderId);

    }

}
