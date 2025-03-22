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
import model.Delivery;
import model.Restaurant;

public class RemoveDeliveryController
extends ControllerBase {
    @FXML
    private Button back;
    @FXML
    private ComboBox<Delivery> deliveriesList;
    @FXML
    private AnchorPane removeDeliveryScreen;
    ControllerBase cs = new ControllerBase();

    public void removeDeliverySceneToMainScene(ActionEvent event) throws Exception {
        this.cs.backToManagerMainWindow(this.removeDeliveryScreen);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ObservableList list = FXCollections.observableArrayList();
        for (Delivery d : Restaurant.getInstance().getDeliveries().values()) {
            list.add((Object)d);
        }
        this.deliveriesList.setItems(list);
    }

    public void saveRemoveDelivery(ActionEvent event) throws Exception {
        Restaurant.getInstance().removeDelivery((Delivery)this.deliveriesList.getValue());
        this.cs.changeScreen("/View/ManagerMainWindow.fxml", this.removeDeliveryScreen);
    }
}
