/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.event.ActionEvent
 *  javafx.fxml.FXML
 *  javafx.scene.control.Button
 *  javafx.scene.control.CheckBox
 *  javafx.scene.control.TextField
 *  javafx.scene.layout.AnchorPane
 */
package application;

import application.ComponentsListController;
import application.ControllerBase;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Component;
import model.Restaurant;

public class EditCompListController
extends ControllerBase {
    @FXML
    public AnchorPane editComp;
    @FXML
    public Button back;
    @FXML
    public TextField name;
    @FXML
    public TextField price;
    @FXML
    public CheckBox gluten;
    @FXML
    public CheckBox lactose;
    ControllerBase cs = new ControllerBase();
    Component comp = Restaurant.getInstance().getComponenets().get(ComponentsListController.compToEdit.getId());

    public void backToComponentsList(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/ComponentsList.fxml", this.editComp);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.name.setText(this.comp.getComponentName());
        if (this.comp.isHasGluten()) {
            this.gluten.setSelected(true);
        }
        if (this.comp.isHasLactose()) {
            this.gluten.setSelected(true);
        }
        String p = String.valueOf(this.comp.getPrice());
        this.price.setText(p);
    }

    public void saveDetailsChange(ActionEvent event) throws Exception {
        Double p = Double.valueOf(this.price.getText());
        this.comp.setComponentName(this.name.getText());
        this.comp.setHasLactose(this.lactose.isSelected());
        this.comp.setHasGluten(this.gluten.isSelected());
        this.comp.setPrice(p);
        Restaurant.getInstance().getComponenets().put(this.comp.getId(), this.comp);
        this.cs.changeScreen("/View/ComponentsList.fxml", this.editComp);
    }
}
