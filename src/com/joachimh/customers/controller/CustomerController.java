package com.joachimh.customers.controller;

import com.joachimh.customers.model.Customer;
import com.joachimh.customers.service.CustomerService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    public ObservableList<Customer> getCustomers(){
        return FXCollections.observableArrayList(customerService.listCustomers());
    }

    public void addCustomer(Customer customer){
        customerService.addCustomer(customer);
    }

    public Customer getCustomer(int customerId){
        return customerService.getCustomer(customerId);
    }
}
