/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.event.ActionEvent
 *  javafx.fxml.FXML
 *  javafx.scene.control.ListView
 *  javafx.scene.image.ImageView
 *  javafx.scene.layout.AnchorPane
 */
package application;

import application.Account;
import application.ControllerBase;
import application.NewAccountController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Customer;
import model.Dish;
import model.Restaurant;

public class RelevantDishesOfCustController
extends ControllerBase {
    @FXML
    private AnchorPane relevantDishesScene;
    @FXML
    private ImageView customerPhoto;
    @FXML
    private ListView<Dish> relevantForCustomer;
    ControllerBase cs = new ControllerBase();

    public void toMainCustomerScene(ActionEvent event) throws Exception {
        this.cs.backToCustomerMainWindow(this.relevantDishesScene);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        super.initialize(arg0, arg1);
        Account account = Restaurant.getInstance().getAccounts().get(NewAccountController.name);
        Customer c = Restaurant.getInstance().getCustomerByIdNumber(account.getIdNumber());
        if (c != null) {
            for (Dish d : Restaurant.getInstance().getReleventDishList(c)) {
                this.relevantForCustomer.getItems().add((Object)d);
            }
        }
    }
}
