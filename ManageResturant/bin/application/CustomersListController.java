/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.event.ActionEvent
 *  javafx.fxml.FXML
 *  javafx.scene.control.Button
 *  javafx.scene.control.ListView
 *  javafx.scene.input.MouseEvent
 *  javafx.scene.layout.AnchorPane
 */
package application;

import application.ControllerBase;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Customer;
import model.Restaurant;

public class CustomersListController
extends ControllerBase {
    @FXML
    private Button back;
    @FXML
    private ListView<Customer> allCustomers;
    @FXML
    private ListView<Customer> blackList;
    @FXML
    private AnchorPane customersList;
    public static Customer showThisCustomerPhoto = null;
    ControllerBase cs = new ControllerBase();

    public void customersListSceneToMainScene(ActionEvent event) throws Exception {
        this.cs.backToManagerMainWindow(this.customersList);
    }

    @FXML
    public void customerChoose(MouseEvent arg0) throws Exception {
        showThisCustomerPhoto = (Customer)this.allCustomers.getSelectionModel().getSelectedItem();
    }

    public void showPhoto(ActionEvent event) throws Exception {
        if (showThisCustomerPhoto != null) {
            this.cs.changeScreen("/View/PhotosOfCustomer.fxml", this.customersList);
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        for (Customer c : Restaurant.getInstance().getCustomers().values()) {
            this.allCustomers.getItems().add((Object)c);
        }
        for (Customer c : Restaurant.getInstance().getBlackList()) {
            this.blackList.getItems().add((Object)c);
            this.allCustomers.getItems().remove((Object)c);
        }
        showThisCustomerPhoto = null;
    }
}
