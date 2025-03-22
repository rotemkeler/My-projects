/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.collections.FXCollections
 *  javafx.collections.ObservableList
 *  javafx.event.ActionEvent
 *  javafx.fxml.FXML
 *  javafx.scene.control.Button
 *  javafx.scene.control.ListView
 *  javafx.scene.control.SelectionMode
 *  javafx.scene.control.TextField
 *  javafx.scene.layout.AnchorPane
 */
package application;

import application.AreasListController;
import application.ControllerBase;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.DeliveryArea;
import model.Restaurant;
import utils.Neighberhood;

public class EditAreasListController
extends ControllerBase {
    @FXML
    public AnchorPane editArea;
    @FXML
    public Button add;
    @FXML
    public Button back;
    @FXML
    public TextField name;
    @FXML
    public TextField time;
    @FXML
    public ListView<Neighberhood> allNeighberhoods;
    @FXML
    public ListView<Neighberhood> neighberhoods;
    ControllerBase cs = new ControllerBase();
    ObservableList<Neighberhood> list = FXCollections.observableArrayList((Object[])Neighberhood.values());
    DeliveryArea area = Restaurant.getInstance().getAreas().get(AreasListController.areaToEdit.getId());

    public void backToAreasList(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/AreasList.fxml", this.editArea);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.allNeighberhoods.setItems(this.list);
        this.allNeighberhoods.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.name.setText(this.area.getAreaName());
        this.neighberhoods.getItems().addAll(this.area.getNeighberhoods());
        String t = String.valueOf(this.area.getDeliverTime());
        this.time.setText(t);
    }

    public void addNeighberhood(ActionEvent event) throws Exception {
        Neighberhood chooseNeighberhood = (Neighberhood)((Object)this.allNeighberhoods.getSelectionModel().getSelectedItem());
        this.area.addNeighberhood(chooseNeighberhood);
        this.neighberhoods.getItems().add((Object)chooseNeighberhood);
    }

    public void saveDetailsChange(ActionEvent event) throws Exception {
        this.area.setAreaName(this.name.getText());
        Restaurant.getInstance().getAreas().put(this.area.getId(), this.area);
        this.cs.changeScreen("/View/AreasList.fxml", this.editArea);
    }
}
