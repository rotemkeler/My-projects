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
 *  javafx.scene.control.ListView
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
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import model.Restaurant;
import utils.Expertise;

public class GetCooksByExpertiseController
extends ControllerBase {
    @FXML
    public Button back;
    @FXML
    public ListView<String> cooksList;
    @FXML
    public AnchorPane cooksByExpertise;
    @FXML
    public ComboBox<Expertise> expertiseList;
    ObservableList<Expertise> list = FXCollections.observableArrayList((Object[])Expertise.values());

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.expertiseList.setItems(FXCollections.observableArrayList(this.list));
    }

    public void cooksByExpertiseSceneToMainScene(ActionEvent event) throws Exception {
        ControllerBase cs = new ControllerBase();
        cs.backToManagerMainWindow(this.cooksByExpertise);
    }

    public void showCooksList(ActionEvent event) throws Exception {
        if (this.expertiseList.getSelectionModel().getSelectedItem() instanceof Expertise) {
            Expertise e = (Expertise)((Object)this.expertiseList.getSelectionModel().getSelectedItem());
            this.cooksList.getItems().clear();
            this.cooksList.getItems().add((Object)Restaurant.getInstance().GetCooksByExpertise(e).toString());
        } else {
            Expertise e = Expertise.valueOf("" + this.expertiseList.getSelectionModel().getSelectedItem());
            this.cooksList.getItems().clear();
            this.cooksList.getItems().add((Object)Restaurant.getInstance().GetCooksByExpertise(e).toString());
        }
    }
}
