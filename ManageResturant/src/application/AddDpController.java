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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import model.DeliveryArea;
import model.DeliveryPerson;
import model.Restaurant;
import utils.Gender;
import utils.Vehicle;

public class AddDpController extends ControllerBase{

	@FXML
	private Button back, save;
	
	@FXML
	private TextField firstName, lastName;
	
	@FXML
	private Label alertFill;
	
	@FXML
	private DatePicker dateOfBirth;
	
	@FXML
	private RadioButton male, female, unknown;

	@FXML
	private ToggleGroup genderGroup;
	
	@FXML
	private AnchorPane addDpScreen;
	
	@FXML
	public ComboBox<DeliveryArea> areasList;
	
	@FXML
	public ComboBox<Vehicle> vehicleList;
	
	
	ObservableList<Vehicle> list = FXCollections.observableArrayList(Vehicle.values());
	
	ControllerBase cs = new ControllerBase();

	public void addDpSceneToMainScene(ActionEvent event) throws Exception{
		cs.backToManagerMainWindow(addDpScreen);
	}
	
	public void saveDeliveryPerson(ActionEvent event) throws Exception{
		Gender gender = radioGenderSelect();
		if(firstName.getText().isEmpty() || lastName.getText().isEmpty() || dateOfBirth.getValue() == null || gender == null || 
				vehicleList.getSelectionModel().isEmpty() || areasList.getSelectionModel().isEmpty()) {
			alertFill.setText("You must fill in all the fields");
			return;
		}
		DeliveryPerson newDeliveryPerson = new DeliveryPerson(firstName.getText(), lastName.getText(), dateOfBirth.getValue(), gender , 
				vehicleList.getValue(), areasList.getValue());
		Restaurant.getInstance().addDeliveryPerson(newDeliveryPerson, areasList.getValue());
		cs.changeScreen("/View/ManagerMainWindow.fxml", addDpScreen);
	}
		
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		vehicleList.setItems(list);
		for(DeliveryArea da : Restaurant.getInstance().getAreas().values()) {
			areasList.getItems().add(da);
		}
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
