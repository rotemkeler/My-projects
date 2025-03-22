/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.event.ActionEvent
 *  javafx.fxml.FXML
 *  javafx.scene.control.Button
 *  javafx.scene.control.Label
 *  javafx.scene.control.PasswordField
 *  javafx.scene.control.TextField
 *  javafx.scene.image.ImageView
 *  javafx.scene.layout.AnchorPane
 */
package application;

import application.Account;
import application.ControllerBase;
import application.NewAccountController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Restaurant;

public class LoginController
extends ControllerBase {
    @FXML
    private Label lblStatus;
    @FXML
    private Label loginFailed;
    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button login;
    @FXML
    private Button newAccount;
    @FXML
    private ImageView lock;
    @FXML
    private ImageView javaeat;
    @FXML
    private AnchorPane loginScene;
    private ControllerBase cs = new ControllerBase();

    public void Login(ActionEvent event) throws Exception {
        block6: {
            try {
                Account a;
                NewAccountController.name = this.txtUserName.getText();
                if (this.txtUserName.getText().equals("manager") && this.txtPassword.getText().equals("manager")) {
                    this.cs.changeScreen("/View/ManagerMainWindow.fxml", this.loginScene);
                }
                if ((a = Restaurant.getInstance().getAccounts().get(this.txtUserName.getText())) != null && a.getPassword().equals(this.txtPassword.getText())) {
                    if (a.isManager()) {
                        this.cs.changeScreen("/View/ManagerMainWindow.fxml", this.loginScene);
                    } else {
                        this.cs.changeScreen("/View/CustomerMainWindow.fxml", this.loginScene);
                    }
                    break block6;
                }
                this.loginFailed.setText("Login Failed!");
                return;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void createNewAccount(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/NewAccount.fxml", this.loginScene);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }
}
