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
import model.DeliveryArea;
import model.Restaurant;

public class RemoveDaController
extends ControllerBase {
    @FXML
    private Button back;
    @FXML
    private AnchorPane removeDaScreen;
    @FXML
    private ComboBox<DeliveryArea> daList;
    ControllerBase cs = new ControllerBase();

    public void removeDaSceneToMainScene(ActionEvent event) throws Exception {
        this.cs.backToManagerMainWindow(this.removeDaScreen);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ObservableList list = FXCollections.observableArrayList();
        for (DeliveryArea da : Restaurant.getInstance().getAreas().values()) {
            list.add((Object)da);
        }
        this.daList.setItems(list);
    }
}
