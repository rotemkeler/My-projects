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
 *  javafx.scene.control.DatePicker
 *  javafx.scene.control.Label
 *  javafx.scene.control.RadioButton
 *  javafx.scene.control.TextField
 *  javafx.scene.control.ToggleGroup
 *  javafx.scene.layout.AnchorPane
 */
package application;

import application.ControllerBase;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import model.Cook;
import model.Restaurant;
import utils.Expertise;
import utils.Gender;

public class AddCookController
extends ControllerBase {
    @FXML
    private Button back;
    @FXML
    private Button save;
    @FXML
    private AnchorPane addCookScreen;
    @FXML
    private Label alertFill;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private DatePicker dateOfBirth;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private RadioButton unknown;
    @FXML
    private RadioButton yes;
    @FXML
    private RadioButton no;
    @FXML
    private ToggleGroup genderGroup;
    @FXML
    private ToggleGroup isChef;
    @FXML
    public ComboBox<Expertise> expertiseList;
    ControllerBase cs = new ControllerBase();
    ObservableList<Expertise> list = FXCollections.observableArrayList((Object[])Expertise.values());

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.expertiseList.setItems(this.list);
    }

    public void addCookSceneToMainScene(ActionEvent event) throws Exception {
        this.cs.backToManagerMainWindow(this.addCookScreen);
    }

    public void saveCook(ActionEvent event) throws Exception {
        Gender gender = this.radioGenderSelect();
        if (this.firstName.getText().isEmpty() || this.lastName.getText().isEmpty() || this.dateOfBirth.getValue() == null || gender == null || this.expertiseList.getSelectionModel().isEmpty() || !this.yes.isSelected() && !this.no.isSelected()) {
            this.alertFill.setText("You must fill in all the fields!");
            return;
        }
        Cook newCook = new Cook(this.firstName.getText(), this.lastName.getText(), (LocalDate)this.dateOfBirth.getValue(), gender, (Expertise)((Object)this.expertiseList.getValue()), this.yes.isSelected());
        Restaurant.getInstance().addCook(newCook);
        this.cs.changeScreen("/View/ManagerMainWindow.fxml", this.addCookScreen);
    }

    public Gender radioGenderSelect() throws Exception {
        if (this.male.isSelected()) {
            return Gender.Male;
        }
        if (this.female.isSelected()) {
            return Gender.Female;
        }
        if (this.unknown.isSelected()) {
            return Gender.Unknown;
        }
        return null;
    }
}
