package com.joachimh.customers.dao;

import com.joachimh.customers.model.Product;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Joachim on 28.10.2015.
 */
@Repository
public class ProductDaoImpl implements ProductDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addProduct(Product product) {
        sessionFactory.getCurrentSession().save(product);
    }

    @Override
    public void editProduct(Product product) {
        sessionFactory.getCurrentSession().update(product);
    }

    @Override
    public void deleteProduct(int productId) {
        sessionFactory.getCurrentSession().createQuery("DELETE FROM Product WHERE id =" + productId);
    }

    @Override
    public void deleteProduct(Product product) {
        sessionFactory.getCurrentSession().delete(product);
    }

    @Override
    public Product getProduct(int productId) {
        return (Product)sessionFactory.getCurrentSession().get(Product.class, productId);
    }

    @Override
    public List<Product> getProducts() {
        return sessionFactory.getCurrentSession().createCriteria(Product.class).list();
    }
}
