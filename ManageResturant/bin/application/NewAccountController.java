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
 *  javafx.scene.control.ProgressBar
 *  javafx.scene.control.RadioButton
 *  javafx.scene.control.TextField
 *  javafx.scene.control.ToggleGroup
 *  javafx.scene.image.Image
 *  javafx.scene.layout.AnchorPane
 *  javafx.stage.FileChooser
 */
package application;

import application.Account;
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
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import model.Customer;
import model.Restaurant;
import utils.Gender;
import utils.Neighberhood;

public class NewAccountController
extends ControllerBase {
    @FXML
    private Button back;
    @FXML
    private Button save;
    @FXML
    private Button addPhoto;
    @FXML
    private AnchorPane creatNewAccount;
    @FXML
    private RadioButton manager;
    @FXML
    private RadioButton customer;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private RadioButton unknown;
    @FXML
    private RadioButton gluten;
    @FXML
    private RadioButton lactuse;
    @FXML
    private ToggleGroup type;
    @FXML
    public TextField idNumber;
    @FXML
    public TextField firstName;
    @FXML
    public TextField lastName;
    @FXML
    public TextField userName;
    @FXML
    public TextField password;
    @FXML
    public DatePicker dateOfBirth;
    @FXML
    public ComboBox<Neighberhood> neighberhoodList;
    @FXML
    public Label alertFill;
    @FXML
    public Label alertNumber;
    @FXML
    public Label sensitive;
    @FXML
    public ProgressBar progressBar;
    public static String name;
    public static Image image;
    public String imagePath = null;
    FileChooser fileChooser = new FileChooser();
    ObservableList<Neighberhood> list = FXCollections.observableArrayList((Object[])Neighberhood.values());
    ControllerBase cs = new ControllerBase();

    static {
        image = null;
    }

    public void saveNewAccount(ActionEvent event) throws Exception {
        Gender gender = this.radioGenderSelect();
        if (this.idNumber.getText().isEmpty() || this.firstName.getText().isEmpty() || this.lastName.getText().isEmpty() || this.dateOfBirth.getValue() == null || gender == null || this.neighberhoodList.getSelectionModel().isEmpty() || this.userName.getText().isEmpty() || this.password.getText().isEmpty() || !this.manager.isSelected() && !this.customer.isSelected()) {
            this.alertFill.setText("You must fill in all the fields");
            return;
        }
        if (Restaurant.getInstance().getAccounts().containsKey(this.userName.getText())) {
            this.alertFill.setText("The user name exists, \nplease choose another user name");
            return;
        }
        long id = Long.parseLong(this.idNumber.getText());
        Account ac = new Account(this.firstName.getText(), this.lastName.getText(), (LocalDate)this.dateOfBirth.getValue(), gender, (Neighberhood)((Object)this.neighberhoodList.getValue()), this.userName.getText(), this.password.getText(), this.manager.isSelected(), id);
        name = this.userName.getText();
        ac.setImage(this.getNewPath());
        Restaurant.getInstance().accountsProgram(ac, this.userName.getText());
        if (!ac.isManager()) {
            Customer c = new Customer(ac.getFirstName(), ac.getLastName(), ac.getBirthDay(), ac.getGender(), ac.getNeighberhood(), this.gluten.isSelected(), this.lactuse.isSelected(), ac.getIdNumber());
            Restaurant.getInstance().addCustomer(c);
            Restaurant.getInstance().getCustomers().put(c.getId(), c);
        }
        Restaurant.getInstance().serialize();
        this.cs.changeScreen("/View/Login.fxml", this.creatNewAccount);
    }

    public void NewManagerAccountToLoginScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/Login.fxml", this.creatNewAccount);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.neighberhoodList.setItems(this.list);
        this.addPhoto.setVisible(false);
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
        this.password.textProperty().addListener((observable, oldValue, newValue) -> {
            double level = this.level((String)newValue);
            this.progressBar.setProgress(level);
        });
    }

    private double level(String p) {
        double res = 0.1;
        if (p.length() < 5) {
            this.progressBar.setStyle("-fx-accent: orangered  ");
            return res;
        }
        int i = 0;
        while (i < p.length()) {
            if (p.charAt(i) >= '0' && p.charAt(i) <= '9') {
                res += 0.2;
                this.progressBar.setStyle("-fx-accent: khaki ");
                break;
            }
            ++i;
        }
        i = 0;
        while (i < p.length()) {
            if (p.charAt(i) >= 'A' && p.charAt(i) <= 'Z') {
                this.progressBar.setStyle("-fx-accent: orange");
                res += 0.2;
                break;
            }
            ++i;
        }
        i = 0;
        while (i < p.length()) {
            if (p.charAt(i) >= 'a' && p.charAt(i) <= 'z') {
                this.progressBar.setStyle("-fx-accent: yellow");
                res += 0.2;
                break;
            }
            ++i;
        }
        i = 0;
        while (i < p.length()) {
            if (p.charAt(i) >= '!' && p.charAt(i) <= '/' || p.charAt(i) >= '9' && p.charAt(i) <= '@' || p.charAt(i) >= '[' && p.charAt(i) <= '_' || p.charAt(i) >= '{' && p.charAt(i) <= '~') {
                this.progressBar.setStyle("-fx-accent: green");
                res += 0.3;
                break;
            }
            ++i;
        }
        return res;
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

    public void chooseCustomer(ActionEvent event) throws Exception {
        if (this.manager.isSelected()) {
            this.addPhoto.setVisible(false);
            this.sensitive.setVisible(false);
            this.gluten.setVisible(false);
            this.lactuse.setVisible(false);
        } else {
            this.addPhoto.setVisible(true);
            this.sensitive.setVisible(true);
            this.gluten.setVisible(true);
            this.lactuse.setVisible(true);
        }
    }
}
