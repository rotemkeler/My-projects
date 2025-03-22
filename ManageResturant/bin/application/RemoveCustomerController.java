/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.collections.FXCollections
 *  javafx.collections.ObservableList
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import model.Customer;
import model.Restaurant;

public class RemoveCustomerController
extends ControllerBase {
    @FXML
    private Button back;
    @FXML
    private AnchorPane removeCustomerScreen;
    @FXML
    private ComboBox<Customer> customersList;
    ControllerBase cs = new ControllerBase();

    public void removeCustomerSceneToMainScene(ActionEvent event) throws Exception {
        this.cs.backToManagerMainWindow(this.removeCustomerScreen);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ObservableList list = FXCollections.observableArrayList();
        for (Customer c : Restaurant.getInstance().getCustomers().values()) {
            list.add((Object)c);
        }
        this.customersList.setItems(list);
    }

    public void saveRemoveCustomer(ActionEvent event) throws Exception {
        Restaurant.getInstance().removeCustomer((Customer)this.customersList.getValue());
        this.cs.changeScreen("/View/ManagerMainWindow.fxml", this.removeCustomerScreen);
    }
}
