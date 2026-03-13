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
    public boolean saveProduct(Product product) {

        if(productRepoImpl.save(product) != null) {
            return true;
        }

        return false;

    }
}
