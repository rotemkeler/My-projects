/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.event.ActionEvent
 *  javafx.fxml.FXML
 *  javafx.scene.control.Button
 *  javafx.scene.control.CheckBox
 *  javafx.scene.control.Label
 *  javafx.scene.control.TextField
 *  javafx.scene.layout.AnchorPane
 */
package application;

import application.ControllerBase;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Component;
import model.Restaurant;

public class AddComponentController
extends ControllerBase {
    @FXML
    private Button back;
    @FXML
    private Button save;
    @FXML
    private Label alertFill;
    @FXML
    private TextField idComponent;
    @FXML
    private TextField componentName;
    @FXML
    private TextField price;
    @FXML
    private CheckBox gluten;
    @FXML
    private CheckBox lactose;
    @FXML
    private Label saveSuccesss;
    @FXML
    private Label alertNumber;
    @FXML
    private AnchorPane addComponentScreen;
    ControllerBase cs = new ControllerBase();

    public void addComponentSceneToMainScene(ActionEvent event) throws Exception {
        this.cs.backToManagerMainWindow(this.addComponentScreen);
    }

    public void saveComponent(ActionEvent event) throws Exception {
        if (this.componentName.getText().isEmpty() || this.price.getText().isEmpty()) {
            this.alertFill.setText("You need to fill in all the fileds!");
            return;
        }
        double componentPrice = Double.parseDouble(this.price.getText());
        Component newComponent = new Component(this.componentName.getText(), this.lactose.isSelected(), this.gluten.isSelected(), componentPrice);
        Restaurant.getInstance().addComponent(newComponent);
        this.cs.changeScreen("/View/ManagerMainWindow.fxml", this.addComponentScreen);
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
        this.price.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 0) {
                char newChar = newValue.charAt(newValue.toString().length() - 1);
                if (this.alertNumber != null && (newChar < '0' || newChar > '9') && newChar != '.') {
                    this.alertNumber.setText("You have to fill only number");
                }
                if (this.allDigits((String)newValue)) {
                    this.alertNumber.setText("");
                }
            }
        });
    }
}
