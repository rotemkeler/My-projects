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
 *  javafx.scene.control.ListView
 *  javafx.scene.control.SelectionMode
 *  javafx.scene.layout.AnchorPane
 */
package application;

import application.ControllerBase;
import java.net.URL;
import java.time.LocalDate;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.TreeSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.AnchorPane;
import model.DeliveryArea;
import model.DeliveryPerson;
import model.Order;
import model.RegularDelivery;
import model.Restaurant;

public class AddRegularDeliveryController
extends ControllerBase {
    @FXML
    private Button back;
    @FXML
    private Label alertFill;
    @FXML
    private CheckBox isDelivered;
    @FXML
    private ComboBox<DeliveryPerson> deliveryPerson;
    @FXML
    private ListView<Order> ordersToChoose;
    @FXML
    private ComboBox<DeliveryArea> area;
    @FXML
    private DatePicker deliveredDate;
    @FXML
    private AnchorPane addRegularDeliveryScene;
    ControllerBase cs = new ControllerBase();

    public void addRegularDeliverySceneToMainScene(ActionEvent event) throws Exception {
        this.cs.backToManagerMainWindow(this.addRegularDeliveryScene);
    }

    public void saveRegularDelivery(ActionEvent event) throws Exception {
        if (this.deliveryPerson.getSelectionModel().isEmpty() || this.area.getSelectionModel().isEmpty() || this.deliveredDate.getValue() == null) {
            this.alertFill.setText("You must fill in all the fields");
            return;
        }
        TreeSet<Order> o = new TreeSet<Order>((Collection<Order>)this.ordersToChoose.getSelectionModel().getSelectedItems());
        RegularDelivery newRegularDelivery = new RegularDelivery(o, (DeliveryPerson)this.deliveryPerson.getValue(), (DeliveryArea)this.area.getValue(), this.isDelivered.isSelected(), (LocalDate)this.deliveredDate.getValue());
        Restaurant.getInstance().addDelivery(newRegularDelivery);
        this.cs.changeScreen("/View/ManagerMainWindow.fxml", this.addRegularDeliveryScene);
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
            this.ordersToChoose.getItems().add((Object)o);
        }
        this.ordersToChoose.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}
