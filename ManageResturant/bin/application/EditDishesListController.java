/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.collections.FXCollections
 *  javafx.collections.ObservableList
 *  javafx.event.ActionEvent
 *  javafx.fxml.FXML
 *  javafx.scene.control.ComboBox
 *  javafx.scene.control.ListView
 *  javafx.scene.control.TextField
 *  javafx.scene.layout.AnchorPane
 */
package application;

import application.ControllerBase;
import application.DishesListController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Component;
import model.Dish;
import model.Restaurant;
import utils.DishType;

public class EditDishesListController
extends ControllerBase {
    @FXML
    public AnchorPane editDish;
    @FXML
    public TextField name;
    @FXML
    public TextField timeToMake;
    @FXML
    public ComboBox<DishType> dishTypeList;
    @FXML
    public ListView<Component> compList;
    @FXML
    public ListView<Component> comps;
    ControllerBase cs = new ControllerBase();
    ObservableList<DishType> list = FXCollections.observableArrayList((Object[])DishType.values());
    Dish d = Restaurant.getInstance().getDishes().get(DishesListController.dishFromListToEdit.getId());

    public void backToDishesList(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/DishesList.fxml", this.editDish);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.dishTypeList.setItems(this.list);
        this.name.setText(this.d.getDishName());
        this.dishTypeList.promptTextProperty().set((Object)this.d.getType().toString());
        for (Component comp : this.d.getComponenets()) {
            if (comp == null) continue;
            this.comps.getItems().add((Object)comp);
        }
        this.compList.getItems().addAll(Restaurant.getInstance().getComponenets().values());
        String time = String.valueOf(this.d.getTimeToMake());
        this.timeToMake.setText(time);
    }

    public void addComponent(ActionEvent event) throws Exception {
        Component co = (Component)this.compList.getSelectionModel().getSelectedItem();
        this.d.addComponent(co);
        this.comps.getItems().add((Object)co);
    }

    public void saveDetailsChange(ActionEvent event) throws Exception {
        Integer t = Integer.valueOf(this.timeToMake.getText());
        this.d.setDishName(this.name.getText());
        this.d.setType(this.dishTypeList.getValue() == null ? this.d.getType() : (DishType)((Object)this.dishTypeList.getValue()));
        this.d.setTimeToMake(t);
        Restaurant.getInstance().getDishes().put(this.d.getId(), this.d);
        this.cs.changeScreen("/View/DishesList.fxml", this.editDish);
    }
}
