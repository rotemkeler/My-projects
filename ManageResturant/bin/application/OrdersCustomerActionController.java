/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.event.ActionEvent
 *  javafx.fxml.FXML
 *  javafx.scene.control.Button
 *  javafx.scene.control.Label
 *  javafx.scene.control.ListView
 *  javafx.scene.input.MouseEvent
 *  javafx.scene.layout.AnchorPane
 */
package application;

import application.Account;
import application.ControllerBase;
import application.NewAccountController;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Order;
import model.Restaurant;

public class OrdersCustomerActionController
extends ControllerBase {
    @FXML
    private AnchorPane ordersCustomer;
    @FXML
    private ListView<Order> ordersList;
    @FXML
    private Button addOrder;
    @FXML
    private Button removeOrder;
    @FXML
    private Label alertChoose;
    private ControllerBase cs = new ControllerBase();
    public static Order orderChosen = null;

    public void toMainCustomerScene(ActionEvent event) throws Exception {
        this.cs.backToCustomerMainWindow(this.ordersCustomer);
    }

    @FXML
    public void orderChoose(MouseEvent arg0) throws Exception {
        orderChosen = (Order)this.ordersList.getSelectionModel().getSelectedItem();
    }

    public void editOrder(ActionEvent event) throws Exception {
        if (orderChosen != null) {
            this.cs.changeScreen("/View/DishesListOfCustomer.fxml", this.ordersCustomer);
        } else {
            this.alertChoose.setText("You must choose order!");
        }
    }

    public void addNewOrder(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/NewOrderForCustomer.fxml", this.ordersCustomer);
    }

    public void removeOrder(ActionEvent event) throws Exception {
        Restaurant.getInstance().removeOrder(orderChosen);
        this.ordersList.getItems().remove((Object)orderChosen);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        super.initialize(arg0, arg1);
        Account a = Restaurant.getInstance().getAccounts().get(NewAccountController.name);
        for (Map.Entry<Integer, Order> kv : Restaurant.getInstance().getOrders().entrySet()) {
            if (!kv.getValue().getCustomer().getIdNumber().equals(a.getIdNumber())) continue;
            this.ordersList.getItems().add((Object)kv.getValue());
        }
        orderChosen = null;
    }
}
