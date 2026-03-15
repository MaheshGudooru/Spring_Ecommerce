package com.techouts.ecommerce.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.techouts.ecommerce.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techouts.ecommerce.repositoryimpl.CartRepoImpl;
import com.techouts.ecommerce.repositoryimpl.OrderRepoImpl;

@Service
public class OrderService {

    private final OrderRepoImpl orderRepoImpl;
    private final CartRepoImpl cartRepoImpl;


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

            Product currProduct = cartItem.getProductId();

            if (currProduct.getStock() > 0) {
                int currStock = currProduct.getStock() < cartItem.getQuantity() ? 0 :
                        currProduct.getStock() - cartItem.getQuantity();
            }

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

        Map<Order, List<OrderItem>> orders = new LinkedHashMap<>();

        List<Order> ordersList = orderRepoImpl.getByUser(user);

        for (Order order : ordersList) {

            orders.putIfAbsent(order, order.getOrderItems());

        }

        return orders;

    }

    @Transactional
    public void cancelOrder(int orderId) {

        Order currOrder = orderRepoImpl.getById(orderId);

        currOrder.setDeliveryStatus("CANCELLED");

    }

}
