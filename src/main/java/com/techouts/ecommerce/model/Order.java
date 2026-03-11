package com.techouts.ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @Column(name = "delivery_status")
    private String deliveryStatus = "PROCESSING";

    @Column(name = "total_price")
    private float totalPrice;

    @Column(name = "delivery_date")
    private LocalDate estimatedDeliveryDate;

    @Column(name = "ordered_date")
    private LocalDate orderedDate;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "delivery_address", nullable = false)
    private String address;

    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

}
