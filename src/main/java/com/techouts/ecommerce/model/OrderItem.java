package com.techouts.ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product productId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order orderId;

    private int quantity;

    @Column(name = "purchased_price")
    private BigDecimal purchasedPrice;

    public OrderItem(Product productId, Order orderId, int quantity, BigDecimal purchasedPrice) {

        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.purchasedPrice = purchasedPrice;

    }
}
