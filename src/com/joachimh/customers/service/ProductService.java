package com.joachimh.customers.service;

import com.joachimh.customers.model.Product;

import java.util.List;

/**
 * Created by Joachim on 28.10.2015.
 */
public interface ProductService {
    void addProduct(Product product);
    void editProduct(Product product);
    void deleteProduct(int productId);
    void deleteProduct(Product product);
    Product getProduct(int productId);
    List<Product> getProducts();
}
