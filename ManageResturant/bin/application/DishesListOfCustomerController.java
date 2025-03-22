/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.event.ActionEvent
 *  javafx.fxml.FXML
 *  javafx.scene.control.ListView
 *  javafx.scene.input.MouseEvent
 *  javafx.scene.layout.AnchorPane
 */
package application;

import application.ControllerBase;
import application.OrdersCustomerActionController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Dish;
import model.Order;

public class DishesListOfCustomerController
extends ControllerBase {
    @FXML
    public AnchorPane allDishesOfCustomer;
    @FXML
    public ListView<Dish> allDishes;
    ControllerBase cs = new ControllerBase();
    public static Dish dishToEdit = null;

    public void backToOrders(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/OrdersCustomerAction.fxml", this.allDishesOfCustomer);
    }

    public void editDishes(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/EditDish.fxml", this.allDishesOfCustomer);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Order orderToEdit = OrdersCustomerActionController.orderChosen;
        this.allDishes.getItems().addAll(orderToEdit.getDishes());
    }

    @FXML
    public void dishChoose(MouseEvent arg0) throws Exception {
        dishToEdit = (Dish)this.allDishes.getSelectionModel().getSelectedItem();
    }
}
