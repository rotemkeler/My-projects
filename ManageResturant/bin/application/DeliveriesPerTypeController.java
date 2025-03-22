/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.event.ActionEvent
 *  javafx.fxml.FXML
 *  javafx.scene.control.Button
 *  javafx.scene.control.Label
 *  javafx.scene.layout.AnchorPane
 */
package application;

import application.ControllerBase;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Restaurant;

public class DeliveriesPerTypeController
extends ControllerBase {
    @FXML
    private Button back;
    @FXML
    private Label expressDelivery;
    @FXML
    private Label regularDelivery;
    @FXML
    private AnchorPane deliveriesPerType;

    public void deliveriesPerTypeSceneToMainScene(ActionEvent event) throws Exception {
        ControllerBase cs = new ControllerBase();
        cs.backToManagerMainWindow(this.deliveriesPerType);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        HashMap<String, Integer> numOfDeliveries = Restaurant.getInstance().getNumberOfDeliveriesPerType();
        this.expressDelivery.setText(numOfDeliveries.get("Express delivery").toString());
        this.regularDelivery.setText(numOfDeliveries.get("Regular delivery").toString());
    }
}
