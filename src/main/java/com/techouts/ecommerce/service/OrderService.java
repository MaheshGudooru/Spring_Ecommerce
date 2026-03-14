package com.techouts.ecommerce.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techouts.ecommerce.model.CartItem;
import com.techouts.ecommerce.model.Order;
import com.techouts.ecommerce.model.OrderItem;
import com.techouts.ecommerce.model.User;
import com.techouts.ecommerce.repositoryimpl.CartRepoImpl;
import com.techouts.ecommerce.repositoryimpl.OrderRepoImpl;

@Service
public class OrderService {

    private OrderRepoImpl orderRepoImpl;
    private CartRepoImpl cartRepoImpl;

    OrderService(OrderRepoImpl orderRepoImpl, CartRepoImpl cartRepoImpl) {

        this.orderRepoImpl = orderRepoImpl;
        this.cartRepoImpl = cartRepoImpl;

    }

    @Transactional
    public void placeOrder(User user, String address, float totalPrice, String paymentMethod) {

        Order order = orderRepoImpl.createOrder(user, address, paymentMethod, totalPrice);

        List<CartItem> removedCartItems = cartRepoImpl.clearCart(user.getCart());

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : removedCartItems) {

            orderItems.add(new OrderItem(
                    cartItem.getProductId(),
                    order,
                    cartItem.getQuantity(),
                    cartItem.getProductId().getPrice()));

        }

        order.setOrderItems(orderItems);

        orderRepoImpl.saveOrder(order);

    }

    @Transactional
    public Map<Order, List<OrderItem>> getOrderByuser(User user) {
        
        Map<Order, List<OrderItem>> orders = new HashMap<>();

        List<Order> ordersList = orderRepoImpl.getByUser(user);

        for(Order order : ordersList) {

            orders.putIfAbsent(order, order.getOrderItems());
        }

        return orders;

    }

    @Transactional
    public void cancelOrder(int orderId) {

        Order currOrder = orderRepoImpl.getById (orderId);

        currOrder.setDeliveryStatus ("CANCELLED");

    }

}
