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
import model.DeliveryArea;
import model.Restaurant;

public class AreasListController
extends ControllerBase {
    @FXML
    private Button back;
    @FXML
    private ListView<DeliveryArea> allAreas;
    @FXML
    private AnchorPane areasList;
    public static DeliveryArea areaToEdit = null;
    ControllerBase cs = new ControllerBase();

    public void areasListSceneToMainScene(ActionEvent event) throws Exception {
        this.cs.backToManagerMainWindow(this.areasList);
    }

    @FXML
    public void areaChoose(MouseEvent arg0) throws Exception {
        areaToEdit = (DeliveryArea)this.allAreas.getSelectionModel().getSelectedItem();
    }

    public void editArea(ActionEvent event) throws Exception {
        if (areaToEdit != null) {
            this.cs.changeScreen("/View/EditAreasList.fxml", this.areasList);
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        for (DeliveryArea c : Restaurant.getInstance().getAreas().values()) {
            this.allAreas.getItems().add((Object)c);
        }
        areaToEdit = null;
    }
}
