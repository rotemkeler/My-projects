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
 *  javafx.scene.control.RadioButton
 *  javafx.scene.control.TextField
 *  javafx.scene.layout.AnchorPane
 */
package application;

import application.ControllerBase;
import application.DpListController;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.DeliveryArea;
import model.DeliveryPerson;
import model.Restaurant;
import utils.Gender;
import utils.Vehicle;

public class EditDpListController
extends ControllerBase {
    @FXML
    public AnchorPane editDp;
    @FXML
    public Button back;
    @FXML
    public TextField name;
    @FXML
    public TextField lastName;
    @FXML
    public DatePicker dateOfBirth;
    @FXML
    public RadioButton male;
    @FXML
    public RadioButton female;
    @FXML
    public RadioButton unknown;
    @FXML
    public ComboBox<Vehicle> vehicleList;
    @FXML
    public ComboBox<DeliveryArea> areasList;
    ObservableList<Vehicle> list = FXCollections.observableArrayList((Object[])Vehicle.values());
    ControllerBase cs = new ControllerBase();
    DeliveryPerson dp = Restaurant.getInstance().getDeliveryPersons().get(DpListController.dpToEdit.getId());

    public void backToDpList(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/DeliveryPersonsList.fxml", this.editDp);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.vehicleList.setItems(this.list);
        this.areasList.getItems().addAll(Restaurant.getInstance().getAreas().values());
        this.name.setText(this.dp.getFirstName());
        this.lastName.setText(this.dp.getLastName());
        this.dateOfBirth.setValue((Object)this.dp.getBirthDay());
        switch (this.dp.getGender().toString().toLowerCase()) {
            case "male": {
                this.male.setSelected(true);
                break;
            }
            case "female": {
                this.female.setSelected(true);
                break;
            }
            case "unknown": {
                this.unknown.setSelected(true);
            }
        }
        this.vehicleList.promptTextProperty().set((Object)this.dp.getVehicle().toString());
        this.areasList.promptTextProperty().set((Object)this.dp.getArea().toString());
    }

    public void saveDetailsChange(ActionEvent event) throws Exception {
        this.dp.setFirstName(this.name.getText());
        this.dp.setLastName(this.lastName.getText());
        this.dp.setBirthDay(this.dateOfBirth.getValue() == null ? this.dp.getBirthDay() : (LocalDate)this.dateOfBirth.getValue());
        this.dp.setGender(this.radioGenderSelect());
        this.dp.setVehicle(this.vehicleList.getValue() == null ? this.dp.getVehicle() : (Vehicle)((Object)this.vehicleList.getValue()));
        this.dp.setArea(this.areasList.getValue() == null ? this.dp.getArea() : (DeliveryArea)this.areasList.getValue());
        Restaurant.getInstance().getDeliveryPersons().put(this.dp.getId(), this.dp);
        this.cs.changeScreen("/View/DeliveryPersonsList.fxml", this.editDp);
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
