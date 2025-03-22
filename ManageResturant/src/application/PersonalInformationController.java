package application;

import java.io.File; 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
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

public class PersonalInformationController extends ControllerBase {

	@FXML
	private AnchorPane personalInformationScene;

	@FXML
	private Label txtIdNumber;

	@FXML
	private TextField txtFirstName, txtLastName, txtUserName, txtPassword;

	@FXML
	private DatePicker txtDateBirth;

	@FXML
	private RadioButton male, female, unknown;
	
	@FXML
	private ComboBox<Neighberhood> chooseNeighberhood;

	@FXML
	private ToggleGroup gender;
	
	@FXML
	private Button changePhoto;
	
	@FXML
	private ImageView customerPhoto;

	ObservableList<Neighberhood> list = FXCollections.observableArrayList(Neighberhood.values());

	ControllerBase cs = new ControllerBase();
		
	public void toMainCustomerScene(ActionEvent event) throws Exception {
		cs.backToCustomerMainWindow(personalInformationScene);
	}

	public void saveDetailsChanges(ActionEvent event) throws Exception {
		Gender g = radioGenderSelect();
		long id = Long.parseLong(txtIdNumber.getText());
		Account account = Restaurant.getInstance().getAccounts().get(NewAccountController.name);
		Account updatedAccount = new Account(txtFirstName.getText(), txtLastName.getText(), txtDateBirth.getValue(), g,
				chooseNeighberhood.getValue(), txtUserName.getText(), txtPassword.getText(), account.isManager(), id);
		if(image!=null && imagePath!=null) {
			updatedAccount.setImage(getNewPath());
		}
		Restaurant.getInstance().accountsProgram(updatedAccount, txtUserName.getText());
		cs.backToCustomerMainWindow(personalInformationScene);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Account account = Restaurant.getInstance().getAccounts().get(NewAccountController.name);
		txtIdNumber.setText(account.getIdNumber().toString());
		txtFirstName.setText(account.getFirstName());
		txtLastName.setText(account.getLastName());
		txtDateBirth.setValue(account.getBirthDay());
		switch (account.getGender().toString().toLowerCase()) {
		case "male":
			male.setSelected(true);
			break;
		case "female":
			female.setSelected(true);
			break;
		case "unknown":
			unknown.setSelected(true);
			break;
		}
		chooseNeighberhood.setItems(list);
		txtUserName.setText(account.getUserName().toString());
		txtUserName.setDisable(true);
		txtPassword.setText(account.getPassword());
		cs.showCustomerPhoto(customerPhoto);
		Account a=Restaurant.getInstance().getAccounts().get(NewAccountController.name);
		File folder = new File(System.getProperty("user.dir"));
		File[] listOfFiles = folder.listFiles();
		if(listOfFiles!=null){	
			if (a.getImage() != null) {
				 try {
					customerPhoto.setImage(new Image(new FileInputStream(System.getProperty("user.dir")+"\\"+a.getImage())));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			} 
		}
	}

	public Gender radioGenderSelect() throws Exception {
		if (male.isSelected()) {
			return Gender.Male;
		}
		if (female.isSelected()) {
			return Gender.Female;
		}
		if (unknown.isSelected()) {
			return Gender.Unknown;
		}
		return null;
	}
	
	
	
	
}
