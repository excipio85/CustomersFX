package com.joachimh.customers.dao;

import com.joachimh.customers.model.Customer;

import java.util.List;

public interface CustomerDao {

    void addCustomer(Customer customer);
    void editCustomer(Customer customer);
    void deleteCustomer(int customerId);
    Customer getCustomer(int customerId);
    List<Customer> listCustomers();

}
