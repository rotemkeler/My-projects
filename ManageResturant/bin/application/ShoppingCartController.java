/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.event.ActionEvent
 *  javafx.fxml.FXML
 *  javafx.scene.control.ComboBox
 *  javafx.scene.control.ListView
 *  javafx.scene.control.SelectionMode
 *  javafx.scene.image.ImageView
 *  javafx.scene.input.MouseEvent
 *  javafx.scene.layout.AnchorPane
 */
package application;

import application.Account;
import application.ControllerBase;
import application.NewAccountController;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Customer;
import model.Dish;
import model.Restaurant;

public class ShoppingCartController
extends ControllerBase {
    @FXML
    private AnchorPane shoppingCartScene;
    @FXML
    private ComboBox<Dish> allDishesOfRest;
    @FXML
    private ListView<Dish> cart;
    @FXML
    private ImageView customerPhoto;
    ControllerBase cs = new ControllerBase();
    public static Dish dishToRemove = null;
    Customer c;

    public void toMainCustomerScene(ActionEvent event) throws Exception {
        this.cs.backToCustomerMainWindow(this.shoppingCartScene);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        super.initialize(arg0, arg1);
        for (Dish d : Restaurant.getInstance().getDishes().values()) {
            this.allDishesOfRest.getItems().add((Object)d);
        }
        this.cart.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        Account account = Restaurant.getInstance().getAccounts().get(NewAccountController.name);
        this.c = Restaurant.getInstance().getCustomerByIdNumber(account.getIdNumber());
        if (this.c != null && this.c.getDishes() == null) {
            this.c.setDishes(new ArrayList<Dish>());
        }
        if (this.c != null && this.c.getDishes().size() > 0) {
            this.cart.getItems().addAll(this.c.getDishes());
        }
    }

    public void addToCart(ActionEvent event) throws Exception {
        if (this.c != null) {
            this.c.getDishes().add((Dish)this.allDishesOfRest.getValue());
        }
        this.cart.getItems().add((Object)((Dish)this.allDishesOfRest.getValue()));
    }

    @FXML
    public void orderChoose(MouseEvent arg0) throws Exception {
        dishToRemove = (Dish)this.cart.getSelectionModel().getSelectedItem();
    }

    public void deleteDishFromCart(ActionEvent event) throws Exception {
        if (this.c != null) {
            this.c.getDishes().remove(dishToRemove);
        }
        this.cart.getItems().remove((Object)dishToRemove);
    }
}
