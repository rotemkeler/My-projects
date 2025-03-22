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
 *  javafx.scene.image.Image
 *  javafx.scene.image.ImageView
 *  javafx.scene.layout.AnchorPane
 */
package application;

import application.Account;
import application.ControllerBase;
import application.NewAccountController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Restaurant;
import utils.Gender;
import utils.Neighberhood;

public class PersonalInformationController
extends ControllerBase {
    @FXML
    private AnchorPane personalInformationScene;
    @FXML
    private Label txtIdNumber;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtPassword;
    @FXML
    private DatePicker txtDateBirth;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private RadioButton unknown;
    @FXML
    private ComboBox<Neighberhood> chooseNeighberhood;
    @FXML
    private ToggleGroup gender;
    @FXML
    private Button changePhoto;
    @FXML
    private ImageView customerPhoto;
    ObservableList<Neighberhood> list = FXCollections.observableArrayList((Object[])Neighberhood.values());
    ControllerBase cs = new ControllerBase();

    public void toMainCustomerScene(ActionEvent event) throws Exception {
        this.cs.backToCustomerMainWindow(this.personalInformationScene);
    }

    public void saveDetailsChanges(ActionEvent event) throws Exception {
        Gender g = this.radioGenderSelect();
        long id = Long.parseLong(this.txtIdNumber.getText());
        Account account = Restaurant.getInstance().getAccounts().get(NewAccountController.name);
        Account updatedAccount = new Account(this.txtFirstName.getText(), this.txtLastName.getText(), (LocalDate)this.txtDateBirth.getValue(), g, (Neighberhood)((Object)this.chooseNeighberhood.getValue()), this.txtUserName.getText(), this.txtPassword.getText(), account.isManager(), id);
        if (image != null && this.imagePath != null) {
            updatedAccount.setImage(this.getNewPath());
        }
        Restaurant.getInstance().accountsProgram(updatedAccount, this.txtUserName.getText());
        this.cs.backToCustomerMainWindow(this.personalInformationScene);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Account account = Restaurant.getInstance().getAccounts().get(NewAccountController.name);
        this.txtIdNumber.setText(account.getIdNumber().toString());
        this.txtFirstName.setText(account.getFirstName());
        this.txtLastName.setText(account.getLastName());
        this.txtDateBirth.setValue((Object)account.getBirthDay());
        switch (account.getGender().toString().toLowerCase()) {
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
        this.chooseNeighberhood.setItems(this.list);
        this.txtUserName.setText(account.getUserName().toString());
        this.txtUserName.setDisable(true);
        this.txtPassword.setText(account.getPassword());
        this.cs.showCustomerPhoto(this.customerPhoto);
        Account a = Restaurant.getInstance().getAccounts().get(NewAccountController.name);
        File folder = new File(System.getProperty("user.dir"));
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null && a.getImage() != null) {
            try {
                this.customerPhoto.setImage(new Image((InputStream)new FileInputStream(String.valueOf(System.getProperty("user.dir")) + "\\" + a.getImage())));
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
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
