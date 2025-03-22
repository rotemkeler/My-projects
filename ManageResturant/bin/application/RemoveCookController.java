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
import model.Cook;
import model.Restaurant;

public class RemoveCookController
extends ControllerBase {
    @FXML
    private Button back;
    @FXML
    public ComboBox<Cook> cooksList;
    @FXML
    private AnchorPane removeCookScreen;
    ControllerBase cs = new ControllerBase();

    public void removeCookSceneToMainScene(ActionEvent event) throws Exception {
        this.cs.backToManagerMainWindow(this.removeCookScreen);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ObservableList list = FXCollections.observableArrayList();
        for (Cook c : Restaurant.getInstance().getCooks().values()) {
            list.add((Object)c);
        }
        this.cooksList.setItems(list);
    }

    public void saveRemoveCook(ActionEvent event) throws Exception {
        Restaurant.getInstance().removeCook((Cook)this.cooksList.getValue());
        this.cs.changeScreen("/View/ManagerMainWindow.fxml", this.removeCookScreen);
    }
}
