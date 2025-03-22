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
 *  javafx.scene.image.ImageView
 *  javafx.scene.layout.AnchorPane
 */
package application;

import application.ControllerBase;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Restaurant;
import utils.Expertise;

public class CooksByExpertisCustomerController
extends ControllerBase {
    @FXML
    private AnchorPane byExpertise;
    @FXML
    private ImageView customerPhoto;
    @FXML
    private ComboBox<Expertise> expertiseList;
    @FXML
    private ListView<String> cooksWithThisExpertise;
    ControllerBase cs = new ControllerBase();
    ObservableList<Expertise> list = FXCollections.observableArrayList((Object[])Expertise.values());

    public void toMainCustomerScene(ActionEvent event) throws Exception {
        this.cs.backToCustomerMainWindow(this.byExpertise);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        super.initialize(arg0, arg1);
        this.expertiseList.setItems(this.list);
    }

    public void showCooksList(ActionEvent event) throws Exception {
        if (this.expertiseList.getSelectionModel().getSelectedItem() instanceof Expertise) {
            Expertise e = (Expertise)((Object)this.expertiseList.getSelectionModel().getSelectedItem());
            this.cooksWithThisExpertise.getItems().clear();
            this.cooksWithThisExpertise.getItems().add((Object)Restaurant.getInstance().GetCooksByExpertise(e).toString());
        } else {
            Expertise e = Expertise.valueOf("" + this.expertiseList.getSelectionModel().getSelectedItem());
            this.cooksWithThisExpertise.getItems().add((Object)Restaurant.getInstance().GetCooksByExpertise(e).toString());
        }
    }
}
