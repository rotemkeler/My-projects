/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.event.ActionEvent
 *  javafx.fxml.FXML
 *  javafx.scene.control.Label
 *  javafx.scene.layout.AnchorPane
 */
package application;

import application.ControllerBase;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Component;
import model.Dish;
import model.Restaurant;

public class MenuController
extends ControllerBase {
    @FXML
    private AnchorPane menuScene;
    @FXML
    private Label theMenu1;
    @FXML
    private Label theMenu2;
    ControllerBase cs = new ControllerBase();

    public void toMainCustomerScene(ActionEvent event) throws Exception {
        this.cs.backToCustomerMainWindow(this.menuScene);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        super.initialize(arg0, arg1);
        String txt1 = "";
        String txt2 = "";
        int j = 0;
        HashMap<Integer, Dish> dm = Restaurant.getInstance().getDishes();
        for (Map.Entry<Integer, Dish> e : dm.entrySet()) {
            if (j <= 2) {
                txt1 = String.valueOf(txt1) + e.getValue().toMenu() + "\n";
            } else {
                txt2 = String.valueOf(txt2) + e.getValue().toMenu() + "\n";
            }
            List<Component> co = e.getValue().getComponenets();
            int i = 0;
            while (i < co.size()) {
                if (co.get(i) != null && j <= 2) {
                    txt1 = String.valueOf(txt1) + co.get(i).toMenu() + "\n";
                }
                if (co.get(i) != null && j > 2) {
                    txt2 = String.valueOf(txt2) + co.get(i).toMenu() + "\n";
                }
                ++i;
            }
            if (j == 2) {
                this.theMenu1.setText(txt1);
            }
            ++j;
        }
        this.theMenu2.setText(txt2);
    }
}
