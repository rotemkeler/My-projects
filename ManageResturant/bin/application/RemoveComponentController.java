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
import model.Component;
import model.Restaurant;

public class RemoveComponentController
extends ControllerBase {
    @FXML
    private Button back;
    @FXML
    public ComboBox<Component> componentsList;
    @FXML
    private AnchorPane removeComponentScreen;
    ControllerBase cs = new ControllerBase();

    public void removeComponentSceneToMainScene(ActionEvent event) throws Exception {
        this.cs.backToManagerMainWindow(this.removeComponentScreen);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ObservableList list = FXCollections.observableArrayList();
        for (Component c : Restaurant.getInstance().getComponenets().values()) {
            list.add((Object)c);
        }
        this.componentsList.setItems(list);
    }

    public void saveRemoveComponent(ActionEvent event) throws Exception {
        Restaurant.getInstance().removeComponent((Component)this.componentsList.getValue());
        this.cs.changeScreen("/View/ManagerMainWindow.fxml", this.removeComponentScreen);
    }
}
