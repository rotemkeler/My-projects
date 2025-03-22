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
import model.Cook;
import model.Restaurant;

public class CooksListController
extends ControllerBase {
    @FXML
    private Button back;
    @FXML
    private ListView<Cook> allCooks;
    @FXML
    private AnchorPane cooksList;
    public static Cook cookToEdit = null;
    ControllerBase cs = new ControllerBase();

    public void cooksListSceneToMainScene(ActionEvent event) throws Exception {
        this.cs.backToManagerMainWindow(this.cooksList);
    }

    @FXML
    public void cookChoose(MouseEvent arg0) throws Exception {
        cookToEdit = (Cook)this.allCooks.getSelectionModel().getSelectedItem();
    }

    public void editCook(ActionEvent event) throws Exception {
        if (cookToEdit != null) {
            this.cs.changeScreen("/View/EditCooksList.fxml", this.cooksList);
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        for (Cook c : Restaurant.getInstance().getCooks().values()) {
            this.allCooks.getItems().add((Object)c);
        }
        cookToEdit = null;
    }
}
