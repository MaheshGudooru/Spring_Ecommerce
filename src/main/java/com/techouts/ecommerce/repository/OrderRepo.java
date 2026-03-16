package com.techouts.ecommerce.repository;

import com.techouts.ecommerce.model.Order;
import com.techouts.ecommerce.model.User;

import java.util.List;

public interface OrderRepo {
    Order createOrder(User user, String address, String paymentMethod, float totalPrice);

    Order updateOrder(Order order);

    List<Order> getByUser(User user);

    Order saveOrder(Order order);

    Order getById(int orderId);
}
