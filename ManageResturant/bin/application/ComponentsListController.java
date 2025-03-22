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
import model.Component;
import model.Restaurant;

public class ComponentsListController
extends ControllerBase {
    @FXML
    private Button back;
    @FXML
    private Button edit;
    @FXML
    private ListView<Component> allComponents;
    @FXML
    private AnchorPane componentsList;
    public static Component compToEdit = null;
    ControllerBase cs = new ControllerBase();

    @FXML
    public void compChoose(MouseEvent arg0) throws Exception {
        compToEdit = (Component)this.allComponents.getSelectionModel().getSelectedItem();
    }

    public void editComp(ActionEvent event) throws Exception {
        if (compToEdit != null) {
            this.cs.changeScreen("/View/EditCompList.fxml", this.componentsList);
        }
    }

    public void componentsListSceneToMainScene(ActionEvent event) throws Exception {
        ControllerBase cs = new ControllerBase();
        cs.backToManagerMainWindow(this.componentsList);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        for (Component c : Restaurant.getInstance().getComponenets().values()) {
            this.allComponents.getItems().add((Object)c);
        }
        compToEdit = null;
    }
}
