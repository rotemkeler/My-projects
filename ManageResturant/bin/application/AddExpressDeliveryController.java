/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.event.ActionEvent
 *  javafx.fxml.FXML
 *  javafx.scene.control.Button
 *  javafx.scene.control.CheckBox
 *  javafx.scene.control.ComboBox
 *  javafx.scene.control.DatePicker
 *  javafx.scene.control.Label
 *  javafx.scene.control.TextField
 *  javafx.scene.layout.AnchorPane
 */
package application;

import application.ControllerBase;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.DeliveryArea;
import model.DeliveryPerson;
import model.ExpressDelivery;
import model.Order;
import model.Restaurant;

public class AddExpressDeliveryController
extends ControllerBase {
    @FXML
    private Button back;
    @FXML
    private Label alertFill;
    @FXML
    private Label alertNumber;
    @FXML
    private CheckBox isDelivered;
    @FXML
    private ComboBox<DeliveryPerson> deliveryPerson;
    @FXML
    private ComboBox<DeliveryArea> area;
    @FXML
    private ComboBox<Order> ordersList;
    @FXML
    private DatePicker deliveredDate;
    @FXML
    private TextField postage;
    @FXML
    private AnchorPane addExpressDeliveryScene;
    ControllerBase cs = new ControllerBase();

    public void addExpressDeliverySceneToMainScene(ActionEvent event) throws Exception {
        this.cs.backToManagerMainWindow(this.addExpressDeliveryScene);
    }

    public void saveExpressDelivery(ActionEvent event) throws Exception {
        if (this.deliveryPerson.getSelectionModel().isEmpty() || this.area.getSelectionModel().isEmpty() || this.deliveredDate.getValue() == null || this.ordersList.getSelectionModel().isEmpty() || this.postage.getText().isEmpty()) {
            this.alertFill.setText("You must fill in all the fields");
            return;
        }
        double postage1 = Double.parseDouble(this.postage.getText());
        ExpressDelivery newExpressDelivery = new ExpressDelivery((DeliveryPerson)this.deliveryPerson.getValue(), (DeliveryArea)this.area.getValue(), this.isDelivered.isSelected(), (Order)this.ordersList.getValue(), postage1, (LocalDate)this.deliveredDate.getValue());
        Restaurant.getInstance().addDelivery(newExpressDelivery);
        this.cs.changeScreen("/View/ManagerMainWindow.fxml", this.addExpressDeliveryScene);
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
        for (DeliveryPerson dp : Restaurant.getInstance().getDeliveryPersons().values()) {
            this.deliveryPerson.getItems().add((Object)dp);
        }
        for (DeliveryArea da : Restaurant.getInstance().getAreas().values()) {
            this.area.getItems().add((Object)da);
        }
        for (Order o : Restaurant.getInstance().getOrders().values()) {
            this.ordersList.getItems().add((Object)o);
        }
        this.postage.textProperty().addListener((observable, oldValue, newValue) -> {
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
