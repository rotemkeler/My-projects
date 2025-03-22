/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.event.ActionEvent
 *  javafx.fxml.FXML
 *  javafx.scene.control.ComboBox
 *  javafx.scene.control.ListView
 *  javafx.scene.control.SelectionMode
 *  javafx.scene.layout.AnchorPane
 */
package application;

import application.ControllerBase;
import application.OrdersListController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.AnchorPane;
import model.Customer;
import model.Delivery;
import model.Dish;
import model.Order;
import model.Restaurant;

public class EditOrdersListController
extends ControllerBase {
    @FXML
    private ComboBox<Customer> customersList;
    @FXML
    private ListView<Dish> dishesList;
    @FXML
    private ComboBox<Delivery> deliveriesList;
    @FXML
    private ListView<Dish> dishesOnOrder;
    @FXML
    private AnchorPane editOrder;
    ControllerBase cs = new ControllerBase();
    Order order = Restaurant.getInstance().getOrders().get(OrdersListController.orderToEdit.getId());

    public void backToOrdersList(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/OrdersList.fxml", this.editOrder);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        for (Customer customer : Restaurant.getInstance().getCustomers().values()) {
            this.customersList.getItems().add((Object)customer);
        }
        for (Dish dish : Restaurant.getInstance().getDishes().values()) {
            this.dishesList.getItems().add((Object)dish);
        }
        for (Delivery delivery : Restaurant.getInstance().getDeliveries().values()) {
            this.deliveriesList.getItems().add((Object)delivery);
        }
        this.customersList.promptTextProperty().set((Object)this.order.getCustomer().toString());
        this.dishesList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.dishesOnOrder.getItems().addAll(this.order.getDishes());
        this.deliveriesList.promptTextProperty().set((Object)this.order.getDelivery().toString());
    }

    public void addDish(ActionEvent event) throws Exception {
        Dish dishChoose = (Dish)this.dishesList.getSelectionModel().getSelectedItem();
        this.order.addDish(dishChoose);
        this.dishesOnOrder.getItems().add((Object)dishChoose);
    }

    public void saveDetailsChange(ActionEvent event) throws Exception {
        this.order.setCustomer(this.customersList.getValue() == null ? this.order.getCustomer() : (Customer)this.customersList.getValue());
        this.order.setDelivery(this.deliveriesList.getValue() == null ? this.order.getDelivery() : (Delivery)this.deliveriesList.getValue());
        Restaurant.getInstance().getOrders().put(this.order.getId(), this.order);
        this.cs.changeScreen("/View/OrdersList.fxml", this.editOrder);
    }
}
