package com.joachimh.customers.service;

import com.joachimh.customers.dao.ProductDao;
import com.joachimh.customers.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Joachim on 28.10.2015.
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override @Transactional
    public void addProduct(Product product) {
        productDao.addProduct(product);
    }

    @Override @Transactional
    public void editProduct(Product product) {
        productDao.editProduct(product);
    }

    @Override @Transactional
    public void deleteProduct(int productId) {
        productDao.deleteProduct(productId);
    }

    @Override @Transactional
    public void deleteProduct(Product product) {
        productDao.deleteProduct(product);
    }

    @Override @Transactional(readOnly = true)
    public Product getProduct(int productId) {
        return productDao.getProduct(productId);
    }

    @Override @Transactional(readOnly = true)
    public List<Product> getProducts() {
        return productDao.getProducts();
    }
}
