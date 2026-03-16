package com.techouts.ecommerce.repository;

import com.techouts.ecommerce.model.Product;

import java.util.List;

public interface ProductRepo {
    List<Product> getAll();

    List<Product> getLimitedProducts(int offset, int limit);

    List<Product> getDuplicateProducts(String name, String category, float price, String productDescription, String productImage);

    List<Product> getByCategory(String category);

    Product save(Product product);

    Product getById(int productId);

    void deleteProduct(int productId);

}
