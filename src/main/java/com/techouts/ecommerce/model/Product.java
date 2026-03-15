package com.techouts.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;
import java.util.List;

@Entity
@Table(name = "products")
// @Cacheable
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private float price;

    @Column(name = "product_description")
    private String productDescription;

    @Column(nullable = false)
    private String category;

    @Column(name = "product_image")
    private String productImage;

    @Min(value = 1, message = "Stock must be atleast one")
    private int stock;

    @OneToMany(mappedBy = "productId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "productId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    public Product(String name, float price, String productDescription, int stock, String category, String productImage) {

        this.name = name;
        this.price = price;
        this.productDescription = productDescription;
        this.stock = stock;
        this.category = category;
        this.productImage = productImage;

    }

}
