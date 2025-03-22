package application;

import java.net.URL; 
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

public class AddCustomerController extends ControllerBase{

	@FXML
	private Button back, save;
	
	@FXML
	private Label alertFill, alertNumber;

	@FXML
	private AnchorPane addCustomerScene;
	
	@FXML
	private TextField idNumber, firstName, lastName;
	
	@FXML
	private CheckBox lactose, gluten;
	
	@FXML
	private DatePicker dateOfBirth;
	
	@FXML
	private RadioButton male, unknown, female;
	
	@FXML
	private ToggleGroup genderGroup;
	
	@FXML
	public ComboBox<Neighberhood> neighberhoodList; 
	
	ObservableList<Neighberhood> list = FXCollections.observableArrayList(Neighberhood.values());

	ControllerBase cs = new ControllerBase();

	public void addCustomerSceneToMainScene(ActionEvent event) throws Exception{
		cs.backToManagerMainWindow(addCustomerScene);
	}
	
	public void saveCustomer(ActionEvent event) throws Exception{
		Gender gender = radioGenderSelect();
		if(firstName.getText().isEmpty() || lastName.getText().isEmpty() || dateOfBirth.getValue() == null||
				gender == null || neighberhoodList.getSelectionModel().isEmpty()) {
			alertFill.setText("You must fill in all the fields");
			return;
		}
		else {
			long id = Long.parseLong(idNumber.getText());
			Customer newCustomer = new Customer(firstName.getText(), lastName.getText(), dateOfBirth.getValue(),gender,
					neighberhoodList.getValue(), lactose.isSelected() ? true : false, gluten.isSelected() ? true : false, id);
			Restaurant.getInstance().addCustomer(newCustomer);
		}
		cs.changeScreen("/View/ManagerMainWindow.fxml", addCustomerScene);
	}
	
	private boolean allDigits(String s) {
		for (int i=0;i<s.length();i++) {
			if(s.charAt(i)<48 ||s.charAt(i)>57) 
				return false;
		}	
		return true;
	}
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		neighberhoodList.setItems(list);
		//Invalid input alert
		idNumber.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue.length()>0) {
				char newChar=newValue.charAt(newValue.toString().length()-1);
				if(alertNumber!=null&&(newChar<48 ||newChar>57)) {
					alertNumber.setText("You have to fill only number");
				}
				if(allDigits(newValue))
					alertNumber.setText("");
			}
		});
	}
	
	public Gender radioGenderSelect() throws Exception{
		if(male.isSelected()) {
			return Gender.Male;
		}
		if(female.isSelected()) {
			return Gender.Female;
		}
		if(unknown.isSelected()) {
			return Gender.Unknown;
		}
		return null;
	}

	
	

	

}
