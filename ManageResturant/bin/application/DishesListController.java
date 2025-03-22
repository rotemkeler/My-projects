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
import model.Dish;
import model.Restaurant;

public class DishesListController
extends ControllerBase {
    @FXML
    private Button back;
    @FXML
    private ListView<Dish> allDishes;
    @FXML
    private AnchorPane dishesList;
    public static Dish dishFromListToEdit = null;
    ControllerBase cs = new ControllerBase();

    @FXML
    public void dishChoose(MouseEvent arg0) throws Exception {
        dishFromListToEdit = (Dish)this.allDishes.getSelectionModel().getSelectedItem();
    }

    public void editDish(ActionEvent event) throws Exception {
        if (dishFromListToEdit != null) {
            this.cs.changeScreen("/View/EditDishesList.fxml", this.dishesList);
        }
    }

    public void dishesListSceneToMainScene(ActionEvent event) throws Exception {
        ControllerBase cs = new ControllerBase();
        cs.backToManagerMainWindow(this.dishesList);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        for (Dish d : Restaurant.getInstance().getDishes().values()) {
            this.allDishes.getItems().add((Object)d);
        }
        dishFromListToEdit = null;
    }
}
