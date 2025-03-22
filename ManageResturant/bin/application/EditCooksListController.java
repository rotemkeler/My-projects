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
 *  javafx.scene.control.ToggleGroup
 *  javafx.scene.layout.AnchorPane
 */
package application;

import application.ControllerBase;
import application.CooksListController;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import model.Cook;
import model.Restaurant;
import utils.Expertise;
import utils.Gender;

public class EditCooksListController
extends ControllerBase {
    @FXML
    public AnchorPane editCook;
    @FXML
    public Button back;
    @FXML
    public Button save;
    @FXML
    public RadioButton male;
    @FXML
    public RadioButton female;
    @FXML
    public RadioButton unknown;
    @FXML
    public RadioButton yes;
    @FXML
    public RadioButton no;
    @FXML
    public TextField name;
    @FXML
    public TextField lastName;
    @FXML
    public DatePicker date;
    @FXML
    public ComboBox<Expertise> expertise;
    @FXML
    public ToggleGroup genderGroup;
    @FXML
    public ToggleGroup isChef;
    Cook cook = Restaurant.getInstance().getCooks().get(CooksListController.cookToEdit.getId());
    ControllerBase cs = new ControllerBase();
    ObservableList<Expertise> list = FXCollections.observableArrayList((Object[])Expertise.values());

    public void backToCooksList(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/CooksList.fxml", this.editCook);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.expertise.setItems(this.list);
        this.name.setText(this.cook.getFirstName());
        this.lastName.setText(this.cook.getLastName());
        this.date.setValue((Object)this.cook.getBirthDay());
        switch (this.cook.getGender().toString().toLowerCase()) {
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
        this.expertise.promptTextProperty().set((Object)this.cook.getExpert().toString());
        if (this.cook.isChef()) {
            this.yes.setSelected(true);
        } else {
            this.no.setSelected(true);
        }
    }

    public void saveDetailsChange(ActionEvent event) throws Exception {
        this.cook.setFirstName(this.name.getText());
        this.cook.setLastName(this.lastName.getText());
        this.cook.setBirthDay(this.date.getValue() == null ? this.cook.getBirthDay() : (LocalDate)this.date.getValue());
        this.cook.setGender(this.radioGenderSelect());
        this.cook.setExpert(this.expertise.getValue() == null ? this.cook.getExpert() : (Expertise)((Object)this.expertise.getValue()));
        this.cook.setChef(this.yes.isSelected());
        Restaurant.getInstance().getCooks().put(this.cook.getId(), this.cook);
        this.cs.changeScreen("/View/CooksList.fxml", this.editCook);
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
