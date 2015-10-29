package com.joachimh.customers.service;

import com.joachimh.customers.dao.CustomerDao;
import com.joachimh.customers.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;

    @Override @Transactional
    public void addCustomer(Customer customer) {
        customerDao.addCustomer(customer);
    }

    @Override @Transactional
    public void editCustomer(Customer customer) {
        customerDao.editCustomer(customer);
    }

    @Override @Transactional
    public void deleteCustomer(int customerId) {
        customerDao.deleteCustomer(customerId);
    }

    @Override @Transactional(readOnly = true)
    public Customer getCustomer(int customerId) {
        return customerDao.getCustomer(customerId);
    }

    @Override @Transactional(readOnly = true)
    public List<Customer> listCustomers() {
        return customerDao.listCustomers();
    }


}
