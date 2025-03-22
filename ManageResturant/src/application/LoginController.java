package application;
 
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

public class LoginController extends ControllerBase{
	
	@FXML
	private Label lblStatus, loginFailed;
	
	@FXML
	private TextField txtUserName;
	
	@FXML
	private PasswordField txtPassword;
	
	@FXML
	private Button login, newAccount;
	
	@FXML
	private ImageView lock, javaeat; 
	
	@FXML
	private AnchorPane loginScene;
	
	private ControllerBase cs = new ControllerBase();
	
	public void Login(ActionEvent event) throws Exception {
		try {
			NewAccountController.name=txtUserName.getText();
			if ((txtUserName.getText().equals("manager") && txtPassword.getText().equals("manager"))){ //check if the details is true
				cs.changeScreen("/View/ManagerMainWindow.fxml", loginScene);
			}
			Account a = Restaurant.getInstance().getAccounts().get(txtUserName.getText());
			if(a != null && a.getPassword().equals(txtPassword.getText())){ //Check if the accounts is exists and if the user name and password is suitable
				if(a.isManager()) {
					cs.changeScreen("/View/ManagerMainWindow.fxml", loginScene);
				}
				else {
					cs.changeScreen("/View/CustomerMainWindow.fxml", loginScene);
				}
			}
			else {
				loginFailed.setText("Login Failed!");
				return;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createNewAccount (ActionEvent event) throws Exception {
		cs.changeScreen("/View/NewAccount.fxml", loginScene);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
	
	
	
}
