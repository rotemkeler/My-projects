/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.event.ActionEvent
 *  javafx.fxml.FXML
 *  javafx.scene.control.ListView
 *  javafx.scene.control.SelectionMode
 *  javafx.scene.layout.AnchorPane
 */
package application;

import application.Account;
import application.ControllerBase;
import application.NewAccountController;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.AnchorPane;
import model.Dish;
import model.Order;
import model.Restaurant;

public class NewOrderForCustomerController
extends ControllerBase {
    @FXML
    public AnchorPane newOrder;
    @FXML
    public ListView<Dish> chooseDishes;
    ControllerBase cs = new ControllerBase();

    public void toMainCustomerScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/OrdersCustomerAction.fxml", this.newOrder);
    }

    public void saveOrder(ActionEvent event) throws Exception {
        Account account = Restaurant.getInstance().getAccounts().get(NewAccountController.name);
        ArrayList<Dish> d = new ArrayList<Dish>((Collection<Dish>)this.chooseDishes.getSelectionModel().getSelectedItems());
        Order o = new Order(Restaurant.getInstance().getCustomerByIdNumber(account.getIdNumber()), d, null);
        Restaurant.getInstance().addOrder(o);
        Restaurant.getInstance().getOrders().put(o.getId(), o);
        this.cs.changeScreen("/View/OrdersCustomerAction.fxml", this.newOrder);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        super.initialize(arg0, arg1);
        for (Dish d : Restaurant.getInstance().getDishes().values()) {
            this.chooseDishes.getItems().add((Object)d);
        }
        this.chooseDishes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}
