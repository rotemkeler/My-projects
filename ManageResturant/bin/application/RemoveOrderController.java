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
import model.Order;
import model.Restaurant;

public class RemoveOrderController
extends ControllerBase {
    @FXML
    private Button back;
    @FXML
    private ComboBox<Order> ordersList;
    @FXML
    private AnchorPane removeOrderScreen;
    ControllerBase cs = new ControllerBase();

    public void removeOrderSceneToMainScene(ActionEvent event) throws Exception {
        this.cs.backToManagerMainWindow(this.removeOrderScreen);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ObservableList list = FXCollections.observableArrayList();
        for (Order o : Restaurant.getInstance().getOrders().values()) {
            list.add((Object)o);
        }
        this.ordersList.setItems(list);
    }

    public void saveRemoveOrder(ActionEvent event) throws Exception {
        Restaurant.getInstance().removeOrder((Order)this.ordersList.getValue());
        this.cs.changeScreen("/View/ManagerMainWindow.fxml", this.removeOrderScreen);
    }
}
