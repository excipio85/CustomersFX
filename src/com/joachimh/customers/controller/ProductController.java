package com.joachimh.customers.controller;

import com.joachimh.customers.model.Product;
import com.joachimh.customers.service.ProductService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Joachim on 28.10.2015.
 */
@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    public ObservableList<Product> getProducts(){
        return FXCollections.observableArrayList(productService.getProducts());
    }

    public void addProduct (Product product){
        productService.addProduct(product);
    }

    public void deleteProduct(int productId){
        productService.deleteProduct(productId);
    }

    public void editProduct(Product product){
        productService.editProduct(product);
    }

    public void deleteProduct(Product product){
        productService.deleteProduct(product);
    }
}
