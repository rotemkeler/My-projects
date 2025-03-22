/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.collections.FXCollections
 *  javafx.collections.ObservableList
 *  javafx.event.ActionEvent
 *  javafx.fxml.FXML
 *  javafx.scene.control.Button
 *  javafx.scene.control.Label
 *  javafx.scene.control.ListView
 *  javafx.scene.control.SelectionMode
 *  javafx.scene.control.TextField
 *  javafx.scene.layout.AnchorPane
 */
package application;

import application.ControllerBase;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.DeliveryArea;
import model.Restaurant;
import utils.Neighberhood;

public class AddDaController
extends ControllerBase {
    @FXML
    public Button back;
    @FXML
    public TextField areaName;
    @FXML
    public TextField time;
    @FXML
    private Label alertFill;
    @FXML
    private Label alertNumber;
    @FXML
    private AnchorPane addDaScene;
    @FXML
    public ListView<Neighberhood> neighberhoodList;
    ObservableList<Neighberhood> list = FXCollections.observableArrayList((Object[])Neighberhood.values());
    ControllerBase cs = new ControllerBase();

    public void addDaSceneToMainScene(ActionEvent event) throws Exception {
        this.cs.backToManagerMainWindow(this.addDaScene);
    }

    public void saveDeliveryArea(ActionEvent event) throws Exception {
        if (this.areaName.getText().isEmpty() || this.neighberhoodList.getSelectionModel().isEmpty() || this.time.getText().isEmpty()) {
            this.alertFill.setText("You must fill in all the fields");
            return;
        }
        int deliveryTime = Integer.parseInt(this.time.getText());
        HashSet<Neighberhood> neighberhoods = new HashSet<Neighberhood>((Collection<Neighberhood>)this.neighberhoodList.getSelectionModel().getSelectedItems());
        DeliveryArea newDeliveryArea = new DeliveryArea(this.areaName.getText(), neighberhoods, deliveryTime);
        Restaurant.getInstance().addDeliveryArea(newDeliveryArea);
        this.cs.changeScreen("/View/ManagerMainWindow.fxml", this.addDaScene);
    }

    private boolean allDigits(String s) {
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') {
                return false;
            }
            ++i;
        }
        return true;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.neighberhoodList.setItems(this.list);
        this.neighberhoodList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.time.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 0) {
                char newChar = newValue.charAt(newValue.toString().length() - 1);
                if (this.alertNumber != null && (newChar < '0' || newChar > '9')) {
                    this.alertNumber.setText("You have to fill only number");
                }
                if (this.allDigits((String)newValue)) {
                    this.alertNumber.setText("");
                }
            }
        });
    }
}
