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
 *  javafx.scene.layout.AnchorPane
 */
package application;

import application.ControllerBase;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import model.Delivery;
import model.DeliveryPerson;
import model.Restaurant;

public class DeliveriesByPersonController
extends ControllerBase {
    @FXML
    private Button back;
    @FXML
    private Button ok;
    @FXML
    private AnchorPane deiveriesByPersonScreen;
    @FXML
    private ListView<Delivery> deliveriesList;
    @FXML
    public ComboBox<Integer> monthList;
    @FXML
    public ComboBox<DeliveryPerson> dpList;
    ObservableList<Integer> list = FXCollections.observableArrayList((Object[])new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});

    public void deiveriesByPersonSceneToMainScene(ActionEvent event) throws Exception {
        ControllerBase cs = new ControllerBase();
        cs.backToManagerMainWindow(this.deiveriesByPersonScreen);
    }

    public void okButton(ActionEvent event) throws Exception {
        List<Object> deliveryByDp = new ArrayList();
        this.deliveriesList.getItems().clear();
        deliveryByDp = Restaurant.getInstance().getDeliveriesByPerson((DeliveryPerson)this.dpList.getValue(), (Integer)this.monthList.getValue()).stream().collect(Collectors.toList());
        for (Delivery delivery : deliveryByDp) {
            this.deliveriesList.getItems().add((Object)delivery);
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.monthList.setItems(this.list);
        for (DeliveryPerson dp : Restaurant.getInstance().getDeliveryPersons().values()) {
            this.dpList.getItems().add((Object)dp);
        }
    }
}
