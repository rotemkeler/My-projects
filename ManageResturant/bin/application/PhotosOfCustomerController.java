/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.event.ActionEvent
 *  javafx.fxml.FXML
 *  javafx.scene.control.Label
 *  javafx.scene.layout.AnchorPane
 */
package application;

import application.Account;
import application.ControllerBase;
import application.CustomersListController;
import application.NewAccountController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Restaurant;

public class PhotosOfCustomerController
extends ControllerBase {
    @FXML
    public Label nameOfCustomer;
    @FXML
    public AnchorPane photosCustomers;
    ControllerBase cs = new ControllerBase();

    public void backToCustList(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/CustomersList.fxml", this.photosCustomers);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        for (Account a : Restaurant.getInstance().getAccounts().values()) {
            if (!a.getIdNumber().equals(CustomersListController.showThisCustomerPhoto.getIdNumber())) continue;
            NewAccountController.name = a.getUserName();
        }
        super.initialize(arg0, arg1);
        this.nameOfCustomer.setText(String.valueOf(CustomersListController.showThisCustomerPhoto.getFirstName()) + " " + CustomersListController.showThisCustomerPhoto.getLastName());
    }
}
