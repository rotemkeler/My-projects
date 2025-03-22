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
 *  javafx.scene.control.SelectionMode
 *  javafx.scene.layout.AnchorPane
 */
package application;

import application.ControllerBase;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.TreeSet;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.AnchorPane;
import model.Delivery;
import model.DeliveryArea;
import model.DeliveryPerson;
import model.Order;
import model.Restaurant;

public class AIMechineController
extends ControllerBase {
    @FXML
    private Button back;
    @FXML
    private ComboBox<DeliveryPerson> dpList;
    @FXML
    private ComboBox<DeliveryArea> daList;
    @FXML
    private ListView<Order> ordersList;
    @FXML
    private ListView<Delivery> deliveriesList;
    @FXML
    private AnchorPane AIMechine;

    public void AIMechineSceneToMainScene(ActionEvent event) throws Exception {
        ControllerBase cs = new ControllerBase();
        cs.backToManagerMainWindow(this.AIMechine);
    }

    public void showDeliveries(ActionEvent event) throws Exception {
        ObservableList selectedOrders = FXCollections.observableList(this.ordersList.getSelectionModel().getSelectedItems().stream().collect(Collectors.toList()));
        TreeSet<Order> orders = new TreeSet<Order>((Collection<Order>)selectedOrders);
        ObservableList deliveries = FXCollections.observableList(Restaurant.getInstance().createAIMacine((DeliveryPerson)this.dpList.getValue(), (DeliveryArea)this.daList.getValue(), orders).stream().collect(Collectors.toList()));
        this.deliveriesList.setItems(deliveries);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        for (DeliveryPerson dp : Restaurant.getInstance().getDeliveryPersons().values()) {
            this.dpList.getItems().add((Object)dp);
        }
        for (DeliveryArea da : Restaurant.getInstance().getAreas().values()) {
            this.daList.getItems().add((Object)da);
        }
        for (Order o : Restaurant.getInstance().getOrders().values()) {
            this.ordersList.getItems().add((Object)o);
        }
        this.ordersList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}
