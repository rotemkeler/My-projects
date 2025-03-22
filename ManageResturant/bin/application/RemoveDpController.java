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
import model.DeliveryPerson;
import model.Restaurant;

public class RemoveDpController
extends ControllerBase {
    @FXML
    private Button back;
    @FXML
    private ComboBox<DeliveryPerson> dpList;
    @FXML
    private AnchorPane removeDpScreen;
    ControllerBase cs = new ControllerBase();

    public void removeDpSceneToMainScene(ActionEvent event) throws Exception {
        this.cs.backToManagerMainWindow(this.removeDpScreen);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ObservableList list = FXCollections.observableArrayList();
        for (DeliveryPerson dp : Restaurant.getInstance().getDeliveryPersons().values()) {
            list.add((Object)dp);
        }
        this.dpList.setItems(list);
    }

    public void saveRemoveDp(ActionEvent event) throws Exception {
        Restaurant.getInstance().removeDeliveryPerson((DeliveryPerson)this.dpList.getValue());
        this.cs.changeScreen("/View/ManagerMainWindow.fxml", this.removeDpScreen);
    }
}
