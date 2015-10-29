package com.joachimh.customers.ui;

import com.joachimh.customers.controller.CustomerController;
import com.joachimh.customers.controller.ProductController;
import com.joachimh.customers.model.Customer;
import com.joachimh.customers.model.Product;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @Autowired
    CustomerController controller;
    @Autowired
    ProductController productController;

    @FXML
    private TableView<Customer> customersTable;
    @FXML
    private TableColumn<Customer, Integer> customersIdCol;
    @FXML
    private TableColumn<Customer, String> customersFirstnameCol;
    @FXML
    private TableColumn<Customer, String> customersLastnameCol;
    @FXML
    private TableColumn<Customer, String> customersEmailCol;
    @FXML
    private TableColumn<Customer, String> customersTelephoneCol;
    @FXML
    private TableColumn<Customer, Integer> customersNumProductsCol;
    @FXML
    private TextField filterTextField;

    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Integer> productIdCol;
    @FXML
    private TableColumn<Product, String> productNameCol;
    @FXML
    private TableColumn<Product, BigDecimal> productPriceCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ApplicationContext context = new ClassPathXmlApplicationContext("appContext.xml");
        controller = context.getBean(CustomerController.class);
        productController = context.getBean(ProductController.class);

        refreshTable();
    }

    private void refreshTable() {
        ObservableList<Customer> customers = FXCollections.observableArrayList(controller.getCustomers());
        customersIdCol.setCellValueFactory((data) -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
        customersFirstnameCol.setCellValueFactory((data) -> new SimpleStringProperty(data.getValue().getFirstname()));
        customersLastnameCol.setCellValueFactory((data) -> new SimpleStringProperty(data.getValue().getLastname()));
        customersEmailCol.setCellValueFactory((data) -> new SimpleStringProperty(data.getValue().getEmail()));
        customersTelephoneCol.setCellValueFactory((data) -> new SimpleStringProperty(data.getValue().getTelephone()));
        customersNumProductsCol.setCellValueFactory((data) -> new SimpleIntegerProperty(data.getValue().getProducts().size()).asObject());

        productIdCol.setCellValueFactory((data) -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
        productNameCol.setCellValueFactory((data) -> new SimpleStringProperty(data.getValue().getName()));
        productPriceCol.setCellValueFactory((data) -> new SimpleObjectProperty<BigDecimal>(data.getValue().getPrice()));

        Customer c1 = new Customer();
        c1.setFirstname("TEST");
        c1.setLastname("TEST");
        c1.setTelephone("TEST");
        c1.setEmail("TEST");

        Product p1 = new Product();
        p1.setName("TEST");
        p1.setPrice(BigDecimal.valueOf(1, 25));
        p1.setCustomer(c1);


        //controller.addCustomer(c1);
        //productController.addProduct(p1);

        customersTable.setItems(controller.getCustomers());

        FilteredList<Customer> customersFiltered = new FilteredList<Customer>(controller.getCustomers(), p -> true);
        filterTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            customersFiltered.setPredicate(customer -> {
                if (newValue.isEmpty() || newValue.equals("")) {
                    return true;
                }

                String lowerCaseFilter = filterTextField.getText().toLowerCase();
                if (customer.getFirstname().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (customer.getLastname().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }

                return false;

            });
        });

        SortedList<Customer> customersSorted = new SortedList<Customer>(customersFiltered);
        customersSorted.comparatorProperty().bind(customersTable.comparatorProperty());
        customersTable.setItems(customersSorted);
        filterTextField.setText("");

        if(customersTable.getSelectionModel().getSelectedItem() != null){
            productTable.setItems(FXCollections.observableArrayList(customersTable.getSelectionModel().getSelectedItem().getProducts()));
        }

        customersTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
            if(newValue != null){
                productTable.setItems(FXCollections.observableArrayList(customersTable.getSelectionModel().getSelectedItem().getProducts()));
            }
        });
    }

    @FXML
    private void handleAktualisieren(){
        refreshTable();
    }

    @FXML
    private void handleShowProducts(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/com/joachimh/customers/ui/ProductsView.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            customersTable.getScene().getWindow().hide();
            stage.show();
            stage.centerOnScreen();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDeleteProduct(){
        if(productTable.getSelectionModel().getSelectedItem() != null){
            Product selPro = productTable.getSelectionModel().getSelectedItem();
            System.out.println("Lösche Produkt: " + productTable.getSelectionModel().getSelectedItem().getName());
            selPro.setName("Test");
            //productController.editProduct(selPro);
            productController.deleteProduct(selPro);
            refreshTable();

            customersTable.getSelectionModel().selectFirst();


        }
    }
}
