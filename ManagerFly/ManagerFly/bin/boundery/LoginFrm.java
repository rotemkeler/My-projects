/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.event.ActionEvent
 *  javafx.fxml.FXML
 *  javafx.fxml.FXMLLoader
 *  javafx.scene.Parent
 *  javafx.scene.Scene
 *  javafx.scene.control.Button
 *  javafx.scene.control.Label
 *  javafx.scene.control.PasswordField
 *  javafx.scene.control.TextField
 *  javafx.stage.Stage
 */
package boundery;

import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginFrm {
    @FXML
    private Button loginBtn;
    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label lblStatus;

    @FXML
    public void initialize() {
    }

    @FXML
    void login(ActionEvent event) throws IOException {
        boolean isExist = false;
        if (this.txtUserName.getText().equals("flights") && this.txtPassword.getText().equals("flights")) {
            this.moveToScene("/boundery/MainMenu.fxml", (Stage)this.loginBtn.getScene().getWindow());
            isExist = true;
        }
        if (this.txtUserName.getText().equals("employees") && this.txtPassword.getText().equals("employees")) {
            this.moveToScene("/boundery/MainMenuForEmloyeesManager.fxml", (Stage)this.loginBtn.getScene().getWindow());
            isExist = true;
        }
        if (!isExist) {
            this.lblStatus.setText("Oops!");
        }
    }

    public void moveToScene(String fxmlName, Stage primaryStage) {
        try {
            Parent root = (Parent)FXMLLoader.load((URL)this.getClass().getResource(fxmlName));
            primaryStage.setScene(new Scene(root));
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
