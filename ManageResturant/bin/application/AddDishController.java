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
 *  javafx.scene.control.Label
 *  javafx.scene.control.ListView
 *  javafx.scene.control.SelectionMode
 *  javafx.scene.control.TextField
 *  javafx.scene.layout.AnchorPane
 */
package application;

import application.ControllerBase;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Component;
import model.Dish;
import model.Restaurant;
import utils.DishType;

public class AddDishController
extends ControllerBase {
    @FXML
    public ComboBox<DishType> dishTypeList;
    @FXML
    public ListView<Component> componentsList;
    @FXML
    private Button back;
    @FXML
    private Label alertFill;
    @FXML
    private Label alertNumber;
    @FXML
    private TextField dishName;
    @FXML
    private TextField timeToMake;
    @FXML
    private AnchorPane addDishScene;
    ControllerBase cs = new ControllerBase();
    ObservableList<DishType> list = FXCollections.observableArrayList((Object[])DishType.values());

    public void addDishSceneToMainScene(ActionEvent event) throws Exception {
        this.cs.backToManagerMainWindow(this.addDishScene);
    }

    public void saveDish(ActionEvent event) throws Exception {
        if (this.dishName.getText().isEmpty() || this.dishTypeList.getSelectionModel().isEmpty() || this.componentsList.getSelectionModel().isEmpty() || this.timeToMake.getText().isEmpty()) {
            this.alertFill.setText("You must fill in all the fields");
            return;
        }
        int time = Integer.parseInt(this.timeToMake.getText());
        ArrayList<Component> componenets = new ArrayList<Component>((Collection<Component>)this.componentsList.getSelectionModel().getSelectedItems());
        Dish newDish = new Dish(this.dishName.getText(), (DishType)((Object)this.dishTypeList.getValue()), componenets, time);
        Restaurant.getInstance().addDish(newDish);
        this.cs.changeScreen("/View/ManagerMainWindow.fxml", this.addDishScene);
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
        this.dishTypeList.setItems(this.list);
        for (Component c : Restaurant.getInstance().getComponenets().values()) {
            this.componentsList.getItems().add((Object)c);
        }
        this.componentsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.timeToMake.textProperty().addListener((observable, oldValue, newValue) -> {
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
