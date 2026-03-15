package com.techouts.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techouts.ecommerce.model.Product;
import com.techouts.ecommerce.repositoryimpl.ProductRepoImpl;

@Service
public class ProductService {

    ProductRepoImpl productRepoImpl;

    ProductService(ProductRepoImpl productRepoImpl) {

        this.productRepoImpl = productRepoImpl;

    }

    @Transactional
    public Product getProduct(int productId) {

        return productRepoImpl.getById(productId);

    }

    @Transactional(readOnly = true)
    public List<Product> getProducts(String category) {

        List<Product> productsList = category == null || category.isBlank() ? productRepoImpl.getAll()
                : productRepoImpl.getByCategory(category);

        return productsList == null ? new ArrayList<>() : productsList;

    }

    @Transactional(readOnly = true)
    public List<Product> getProducts(int page) {

        int productCnt = 12;

        int offset = (page - 1) * productCnt;

        return productRepoImpl.getLimitedProducts(offset, productCnt);

    }

    @Transactional
    public void saveProduct(Product product) {

        productRepoImpl.save(product);

    }
    @Transactional
    public Product createNewProduct(String name, String category, float price, String productDescription, int stock, String productImage) {

        List<Product> alreadyExistingProducts = productRepoImpl.getDuplicateProducts(name, category, price,productDescription, productImage);

        if (alreadyExistingProducts.isEmpty()) {
            return productRepoImpl.save(new Product(name, price, productDescription, stock, category, productImage));
        }

        return null;

    }

    @Transactional
    public void updateProductDetails(int productId, String productName, String category, float price, int stock, String productDescription, String productImage) {

        Product product = productRepoImpl.getById(productId);

        if (productName != null && !productName.isBlank()) {
            product.setName(productName.trim());
        }

        if (category != null && !category.isBlank()) {
            product.setCategory(category.trim());
        }

        if (productDescription != null && !productDescription.isBlank()) {
            product.setProductDescription(productDescription.trim());
        }

        if (productImage != null && !productImage.isBlank()) {
            product.setProductImage(productImage.trim());
        }

        product.setPrice(price);
        product.setStock(stock);

        productRepoImpl.save(product);
        // productRepoImpl.evictProductFromCache(productId);

    }

    @Transactional
    public void deleteProduct(int productId) {

        productRepoImpl.deleteProduct(productId);

    }

}
