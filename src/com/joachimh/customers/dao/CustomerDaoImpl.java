package com.joachimh.customers.dao;

import com.joachimh.customers.model.Customer;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CustomerDaoImpl implements CustomerDao{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addCustomer(Customer customer) {
        sessionFactory.getCurrentSession().save(customer);
    }

    @Override
    public void editCustomer(Customer customer) {
        sessionFactory.getCurrentSession().update(customer);
    }

    @Override
    public void deleteCustomer(int customerId) {
        sessionFactory.getCurrentSession().createQuery("DELETE FROM CUSTOMER WHERE id =" + customerId).executeUpdate();
    }

    @Override
    public Customer getCustomer(int customerId) {
        return (Customer)sessionFactory.getCurrentSession().get(Customer.class, customerId);
    }

    @Override
    public List<Customer> listCustomers() {
        return (List<Customer>)sessionFactory.getCurrentSession().createCriteria(Customer.class).list() ;
    }


}
