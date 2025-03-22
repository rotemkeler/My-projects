/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.event.ActionEvent
 *  javafx.fxml.FXML
 *  javafx.scene.control.Button
 *  javafx.scene.control.ComboBox
 *  javafx.scene.control.Label
 *  javafx.scene.control.ListView
 *  javafx.scene.control.SelectionMode
 *  javafx.scene.layout.AnchorPane
 */
package application;

import application.ControllerBase;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.AnchorPane;
import model.Customer;
import model.Delivery;
import model.Dish;
import model.Order;
import model.Restaurant;

public class AddOrderController
extends ControllerBase {
    @FXML
    private Button back;
    @FXML
    private Label alertFill;
    @FXML
    private ComboBox<Customer> customersList;
    @FXML
    private ListView<Dish> dishesList;
    @FXML
    private ComboBox<Delivery> deliveriesList;
    @FXML
    private AnchorPane addOrderScreen;
    ControllerBase cs = new ControllerBase();

    public void addOrderSceneToMainScene(ActionEvent event) throws Exception {
        this.cs.backToManagerMainWindow(this.addOrderScreen);
    }

    public void saveOrder(ActionEvent event) throws Exception {
        if (this.customersList.getSelectionModel().isEmpty() || this.dishesList.getSelectionModel().isEmpty()) {
            this.alertFill.setText("You must fill in all the fields");
            return;
        }
        ArrayList<Dish> dishes = new ArrayList<Dish>((Collection<Dish>)this.dishesList.getSelectionModel().getSelectedItems());
        Order newOrder = new Order((Customer)this.customersList.getValue(), dishes, (Delivery)this.deliveriesList.getValue());
        Restaurant.getInstance().addOrder(newOrder);
        this.cs.changeScreen("/View/ManagerMainWindow.fxml", this.addOrderScreen);
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
        this.dishesList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}
