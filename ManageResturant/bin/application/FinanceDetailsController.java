/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.collections.FXCollections
 *  javafx.collections.ObservableList
 *  javafx.event.ActionEvent
 *  javafx.fxml.FXML
 *  javafx.scene.control.Button
 *  javafx.scene.control.Label
 *  javafx.scene.control.ListView
 *  javafx.scene.layout.AnchorPane
 */
package application;

import application.ControllerBase;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import model.Dish;
import model.Restaurant;

public class FinanceDetailsController
extends ControllerBase {
    @FXML
    private Button back;
    @FXML
    private ListView<Dish> dishesList;
    @FXML
    private Label revenue;
    @FXML
    private AnchorPane revenueFromExpressDelivery;

    public void revenueFromExpressDeliverySceneToMainScene(ActionEvent event) throws Exception {
        ControllerBase cs = new ControllerBase();
        cs.backToManagerMainWindow(this.revenueFromExpressDelivery);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        double revenueFromExpressDelivery = Restaurant.getInstance().revenueFromExpressDeliveries();
        this.revenue.setText(String.valueOf(revenueFromExpressDelivery));
        List profitRelation = Restaurant.getInstance().getProfitRelation().stream().collect(Collectors.toList());
        ObservableList pr = FXCollections.observableList(profitRelation);
        this.dishesList.setItems(pr);
    }
}
