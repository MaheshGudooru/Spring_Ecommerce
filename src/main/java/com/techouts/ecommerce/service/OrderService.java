package com.techouts.ecommerce.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.techouts.ecommerce.model.*;
import com.techouts.ecommerce.repository.CartRepo;
import com.techouts.ecommerce.repository.OrderRepo;
import com.techouts.ecommerce.repository.ProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class OrderService {

    private final OrderRepo orderRepoImpl;
    private final CartRepo cartRepoImpl;
    private final ProductRepo productRepoImpl;


    OrderService(OrderRepo orderRepoImpl, CartRepo cartRepoImpl, ProductRepo productRepoImpl) {

        this.orderRepoImpl = orderRepoImpl;
        this.cartRepoImpl = cartRepoImpl;
        this.productRepoImpl = productRepoImpl;
    }

    @Transactional
    public String placeOrder(User user, String address, float totalPrice, String paymentMethod) {

        List<CartItem> userCartItems = cartRepoImpl.getCartItems (user.getCart ());

        for (CartItem cartItem : userCartItems) {

            Product currProduct = cartItem.getProductId();

            if(currProduct.getStock () < cartItem.getQuantity()) {

                if(currProduct.getStock () == 0) {
                    return currProduct.getName () + " is out of stock";
                }
                return "Stock available for " + currProduct.getName () + " is " + currProduct.getStock ();
            }

        }

        Order order = orderRepoImpl.createOrder(user, address, paymentMethod, totalPrice);

        List<CartItem> removedCartItems = cartRepoImpl.clearCart(user.getCart());

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : removedCartItems) {

            Product currProduct = cartItem.getProductId();

            int updatedStock = currProduct.getStock () - cartItem.getQuantity ();

            if (updatedStock < 0) {
                throw new RuntimeException("Stock calculation error!");
            }

            currProduct.setStock (updatedStock);

            orderItems.add(new OrderItem(
                    cartItem.getProductId(),
                    order,
                    cartItem.getQuantity(),
                    cartItem.getProductId().getPrice()));

        }

        order.setOrderItems(orderItems);

        orderRepoImpl.saveOrder(order);

        return "success";

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

         List<OrderItem> orderItems = currOrder.getOrderItems ();

         for(OrderItem orderItem : orderItems) {

             int cancelledOrderStock = orderItem.getQuantity ();
             int alreadyPresentProductStock = orderItem.getProductId ().getStock ();

             orderItem.getProductId ().setStock (cancelledOrderStock + alreadyPresentProductStock);

             productRepoImpl.save (orderItem.getProductId ());

             System.out.println("Product ID: " + orderItem.getProductId().getId());
             System.out.println("Current Stock: " + alreadyPresentProductStock);
             System.out.println("Cancelled Qty: " + cancelledOrderStock);

         }

        currOrder.setDeliveryStatus("CANCELLED");

    }

}
