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
import model.Dish;
import model.Restaurant;

public class RemoveDishController
extends ControllerBase {
    @FXML
    private Button back;
    @FXML
    private ComboBox<Dish> dishesList;
    @FXML
    private AnchorPane removeDishScreen;
    ControllerBase cs = new ControllerBase();

    public void removeDishSceneToMainScene(ActionEvent event) throws Exception {
        this.cs.backToManagerMainWindow(this.removeDishScreen);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ObservableList list = FXCollections.observableArrayList();
        for (Dish d : Restaurant.getInstance().getDishes().values()) {
            list.add((Object)d);
        }
        this.dishesList.setItems(list);
    }

    public void saveRemoveDish(ActionEvent event) throws Exception {
        Restaurant.getInstance().removeDish((Dish)this.dishesList.getValue());
        this.cs.changeScreen("/View/ManagerMainWindow.fxml", this.removeDishScreen);
    }
}
