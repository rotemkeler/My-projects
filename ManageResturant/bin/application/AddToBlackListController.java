/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.event.ActionEvent
 *  javafx.fxml.FXML
 *  javafx.scene.control.Button
 *  javafx.scene.control.ComboBox
 *  javafx.scene.layout.AnchorPane
 */
package application;

import application.ControllerBase;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import model.Customer;
import model.Restaurant;

public class AddToBlackListController
extends ControllerBase {
    @FXML
    private Button back;
    @FXML
    private ComboBox<Customer> customersList;
    @FXML
    private AnchorPane addToBlackListScreen;
    ControllerBase cs = new ControllerBase();

    public void addToBlSceneToMainScene(ActionEvent event) throws Exception {
        this.cs.backToManagerMainWindow(this.addToBlackListScreen);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        for (Customer c : Restaurant.getInstance().getCustomers().values()) {
            this.customersList.getItems().add((Object)c);
        }
    }

    public void saveToBlackList(ActionEvent event) throws Exception {
        Restaurant.getInstance().addCustomerToBlackList((Customer)this.customersList.getValue());
        Restaurant.getInstance().removeCustomer((Customer)this.customersList.getValue());
        this.cs.changeScreen("/View/ManagerMainWindow.fxml", this.addToBlackListScreen);
    }
}
