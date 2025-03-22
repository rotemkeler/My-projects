package application;


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

public class NewAccountController extends ControllerBase {

	@FXML
	private Button back, save, addPhoto;

	@FXML
	private AnchorPane creatNewAccount;

	@FXML
	private RadioButton manager, customer, male ,female, unknown, gluten, lactuse;

	@FXML
	private ToggleGroup type;

	@FXML
	public TextField idNumber, firstName, lastName, userName, password;

	@FXML
	public DatePicker dateOfBirth;

	@FXML
	public ComboBox<Neighberhood> neighberhoodList;
	
	@FXML
	public Label alertFill, alertNumber, sensitive;

	@FXML
	public ProgressBar progressBar;
	
	public static String name;
	
	public static Image image = null;
	
	public String imagePath = null;
	
	FileChooser fileChooser = new FileChooser();

	ObservableList<Neighberhood> list = FXCollections.observableArrayList(Neighberhood.values());

	ControllerBase cs = new ControllerBase();

	public void saveNewAccount(ActionEvent event) throws Exception {
		Gender gender = radioGenderSelect();
		//Checks that all fields are full
		if (idNumber.getText().isEmpty() || firstName.getText().isEmpty() || lastName.getText().isEmpty()
				|| dateOfBirth.getValue() == null || gender == null || neighberhoodList.getSelectionModel().isEmpty()
				|| userName.getText().isEmpty() || password.getText().isEmpty()
				|| (!manager.isSelected() && !customer.isSelected())) {
			alertFill.setText("You must fill in all the fields");
			return;
		}
		//Checks if the user already exists in the system
		if (Restaurant.getInstance().getAccounts().containsKey(userName.getText())) {
			alertFill.setText("The user name exists, \n"
					+ "please choose another user name");
			return;
		}
		long id = Long.parseLong(idNumber.getText());
		Account ac = new Account(firstName.getText(), lastName.getText(), dateOfBirth.getValue(), gender,
				neighberhoodList.getValue(), userName.getText(), password.getText(), manager.isSelected(), id);
		
		name = userName.getText();
		ac.setImage(getNewPath());
		Restaurant.getInstance().accountsProgram(ac, userName.getText());
		if(!ac.isManager()) {//check if the user is customer
			Customer c = new Customer(ac.getFirstName(), ac.getLastName(), ac.getBirthDay(), ac.getGender(), ac.getNeighberhood(),
					gluten.isSelected() ? true : false, lactuse.isSelected() ? true : false, ac.getIdNumber());
			Restaurant.getInstance().addCustomer(c);
			Restaurant.getInstance().getCustomers().put(c.getId(), c);
		}
		Restaurant.getInstance().serialize();
		cs.changeScreen("/View/Login.fxml", creatNewAccount);
	}

	public void NewManagerAccountToLoginScene(ActionEvent event) throws Exception {
		cs.changeScreen("/View/Login.fxml", creatNewAccount);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		neighberhoodList.setItems(list);
		addPhoto.setVisible(false);
		//Invalid input alert
		idNumber.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.length() > 0) {
				char newChar = newValue.charAt(newValue.toString().length() - 1);
				if (alertNumber != null && (newChar < 48 || newChar > 57)) {

					alertNumber.setText("You have to fill only number");
				}
				if (allDigits(newValue))
					alertNumber.setText("");
			}

		});
		
		//Check the password strength and alerts when you type incorrectly
		password.textProperty().addListener((observable, oldValue, newValue) -> {
			double level = level(newValue);
			progressBar.setProgress(level);

		});
	}

	private double level(String p) {
		double res = 0.1;

		if (p.length() < 5) {
			progressBar.setStyle("-fx-accent: orangered  ");

			return res;
		}

		for (int i = 0; i < p.length(); i++) {
			if (p.charAt(i) >= 48 && p.charAt(i) <= 57)// digit
			{
				res += 0.2;
				progressBar.setStyle("-fx-accent: khaki ");
				break;
			}
		}

		for (int i = 0; i < p.length(); i++) {

			if (p.charAt(i) >= 65 && p.charAt(i) <= 90)// upper-case
			{
				progressBar.setStyle("-fx-accent: orange");
				res += 0.2;
				break;
			}
		}

		for (int i = 0; i < p.length(); i++) {

			if (p.charAt(i) >= 97 && p.charAt(i) <= 122)// lower-case
			{
				progressBar.setStyle("-fx-accent: yellow");
				res += 0.2;
				break;
			}
		}

		for (int i = 0; i < p.length(); i++) {

			if (p.charAt(i) >= 33 && p.charAt(i) <= 47 || p.charAt(i) >= 57 && p.charAt(i) <= 64
					|| p.charAt(i) >= 91 && p.charAt(i) <= 95 || p.charAt(i) >= 123 && p.charAt(i) <= 126){// spacial char
				progressBar.setStyle("-fx-accent: green");
				res += 0.3;
				break;
			}
		}
		return res;

	}

	private boolean allDigits(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) < 48 || s.charAt(i) > 57)
				return false;
		}
		return true;
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

	
	//Add photo for customers and displays it on the rest of the screens
	public void chooseCustomer(ActionEvent event) throws Exception {
		if (manager.isSelected()) {
			addPhoto.setVisible(false);
			sensitive.setVisible(false);
			gluten.setVisible(false);
			lactuse.setVisible(false);
		} else {
			addPhoto.setVisible(true);
			sensitive.setVisible(true);
			gluten.setVisible(true);
			lactuse.setVisible(true);
		}
	}
}
