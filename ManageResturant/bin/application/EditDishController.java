/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.event.ActionEvent
 *  javafx.fxml.FXML
 *  javafx.scene.control.ComboBox
 *  javafx.scene.control.ListView
 *  javafx.scene.layout.AnchorPane
 */
package application;

import application.ControllerBase;
import application.DishesListOfCustomerController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import model.Component;
import model.Restaurant;

public class EditDishController
extends ControllerBase {
    @FXML
    public AnchorPane editDish;
    @FXML
    public ListView<Component> componentsOfDish;
    @FXML
    public ComboBox<Component> addComponentToDish;
    @FXML
    public ComboBox<Component> removeComponentsFromDish;
    ControllerBase cs = new ControllerBase();

    public void backToDishesList(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/DishesListOfCustomer.fxml", this.editDish);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.addComponentToDish.getItems().addAll(Restaurant.getInstance().getComponenets().values());
        for (Component c : DishesListOfCustomerController.dishToEdit.getComponenets()) {
            if (c == null) continue;
            this.componentsOfDish.getItems().add((Object)c);
        }
        this.removeComponentsFromDish.getItems().addAll(DishesListOfCustomerController.dishToEdit.getComponenets());
    }

    public void saveChangesOnDish(ActionEvent event) throws Exception {
        DishesListOfCustomerController.dishToEdit.removeComponent((Component)this.removeComponentsFromDish.getValue());
        DishesListOfCustomerController.dishToEdit.addComponent((Component)this.addComponentToDish.getValue());
        this.cs.changeScreen("/View/DishesListOfCustomer.fxml", this.editDish);
    }
}
