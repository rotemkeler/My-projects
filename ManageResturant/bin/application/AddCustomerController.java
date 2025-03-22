/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.collections.FXCollections
 *  javafx.collections.ObservableList
 *  javafx.event.ActionEvent
 *  javafx.fxml.FXML
 *  javafx.scene.control.Button
 *  javafx.scene.control.CheckBox
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import model.Customer;
import model.Restaurant;
import utils.Gender;
import utils.Neighberhood;

public class AddCustomerController
extends ControllerBase {
    @FXML
    private Button back;
    @FXML
    private Button save;
    @FXML
    private Label alertFill;
    @FXML
    private Label alertNumber;
    @FXML
    private AnchorPane addCustomerScene;
    @FXML
    private TextField idNumber;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private CheckBox lactose;
    @FXML
    private CheckBox gluten;
    @FXML
    private DatePicker dateOfBirth;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton unknown;
    @FXML
    private RadioButton female;
    @FXML
    private ToggleGroup genderGroup;
    @FXML
    public ComboBox<Neighberhood> neighberhoodList;
    ObservableList<Neighberhood> list = FXCollections.observableArrayList((Object[])Neighberhood.values());
    ControllerBase cs = new ControllerBase();

    public void addCustomerSceneToMainScene(ActionEvent event) throws Exception {
        this.cs.backToManagerMainWindow(this.addCustomerScene);
    }

    public void saveCustomer(ActionEvent event) throws Exception {
        Gender gender = this.radioGenderSelect();
        if (this.firstName.getText().isEmpty() || this.lastName.getText().isEmpty() || this.dateOfBirth.getValue() == null || gender == null || this.neighberhoodList.getSelectionModel().isEmpty()) {
            this.alertFill.setText("You must fill in all the fields");
            return;
        }
        long id = Long.parseLong(this.idNumber.getText());
        Customer newCustomer = new Customer(this.firstName.getText(), this.lastName.getText(), (LocalDate)this.dateOfBirth.getValue(), gender, (Neighberhood)((Object)this.neighberhoodList.getValue()), this.lactose.isSelected(), this.gluten.isSelected(), id);
        Restaurant.getInstance().addCustomer(newCustomer);
        this.cs.changeScreen("/View/ManagerMainWindow.fxml", this.addCustomerScene);
    }

    private boolean allDigits(String s) {
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') {
                return false;
            }
            ++i;
        }
        return true;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.neighberhoodList.setItems(this.list);
        this.idNumber.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 0) {
                char newChar = newValue.charAt(newValue.toString().length() - 1);
                if (this.alertNumber != null && (newChar < '0' || newChar > '9')) {
                    this.alertNumber.setText("You have to fill only number");
                }
                if (this.allDigits((String)newValue)) {
                    this.alertNumber.setText("");
                }
            }
        });
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
