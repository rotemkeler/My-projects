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
import model.DeliveryArea;
import model.DeliveryPerson;
import model.Restaurant;
import utils.Gender;
import utils.Vehicle;

public class AddDpController
extends ControllerBase {
    @FXML
    private Button back;
    @FXML
    private Button save;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private Label alertFill;
    @FXML
    private DatePicker dateOfBirth;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private RadioButton unknown;
    @FXML
    private ToggleGroup genderGroup;
    @FXML
    private AnchorPane addDpScreen;
    @FXML
    public ComboBox<DeliveryArea> areasList;
    @FXML
    public ComboBox<Vehicle> vehicleList;
    ObservableList<Vehicle> list = FXCollections.observableArrayList((Object[])Vehicle.values());
    ControllerBase cs = new ControllerBase();

    public void addDpSceneToMainScene(ActionEvent event) throws Exception {
        this.cs.backToManagerMainWindow(this.addDpScreen);
    }

    public void saveDeliveryPerson(ActionEvent event) throws Exception {
        Gender gender = this.radioGenderSelect();
        if (this.firstName.getText().isEmpty() || this.lastName.getText().isEmpty() || this.dateOfBirth.getValue() == null || gender == null || this.vehicleList.getSelectionModel().isEmpty() || this.areasList.getSelectionModel().isEmpty()) {
            this.alertFill.setText("You must fill in all the fields");
            return;
        }
        DeliveryPerson newDeliveryPerson = new DeliveryPerson(this.firstName.getText(), this.lastName.getText(), (LocalDate)this.dateOfBirth.getValue(), gender, (Vehicle)((Object)this.vehicleList.getValue()), (DeliveryArea)this.areasList.getValue());
        Restaurant.getInstance().addDeliveryPerson(newDeliveryPerson, (DeliveryArea)this.areasList.getValue());
        this.cs.changeScreen("/View/ManagerMainWindow.fxml", this.addDpScreen);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.vehicleList.setItems(this.list);
        for (DeliveryArea da : Restaurant.getInstance().getAreas().values()) {
            this.areasList.getItems().add((Object)da);
        }
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
