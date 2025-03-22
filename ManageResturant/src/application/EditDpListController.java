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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.DeliveryArea;
import model.DeliveryPerson;
import model.Restaurant;
import utils.Gender;
import utils.Vehicle;

public class EditDpListController extends ControllerBase {

	@FXML
	public AnchorPane editDp;
	
	@FXML
	public Button back;
	
	@FXML
	public TextField name, lastName;
	
	@FXML
	public DatePicker dateOfBirth;
	
	@FXML
	public RadioButton male, female, unknown;
	
	@FXML
	public ComboBox<Vehicle> vehicleList;
	
	@FXML
	public ComboBox<DeliveryArea> areasList;
	
	ObservableList<Vehicle> list = FXCollections.observableArrayList(Vehicle.values());
	
	ControllerBase cs = new ControllerBase();
	
	DeliveryPerson dp = Restaurant.getInstance().getDeliveryPersons().get(DpListController.dpToEdit.getId());
	
	
	public void backToDpList (ActionEvent event) throws Exception{
		cs.changeScreen("/View/DeliveryPersonsList.fxml", editDp);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		vehicleList.setItems(list);
		areasList.getItems().addAll(Restaurant.getInstance().getAreas().values());
		name.setText(dp.getFirstName());
		lastName.setText(dp.getLastName());
		dateOfBirth.setValue(dp.getBirthDay());
		switch (dp.getGender().toString().toLowerCase()) {
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
		vehicleList.promptTextProperty().set(dp.getVehicle().toString());
		areasList.promptTextProperty().set(dp.getArea().toString());
	}
	
	public void saveDetailsChange (ActionEvent event) throws Exception{
		dp.setFirstName(name.getText());
		dp.setLastName(lastName.getText());
		dp.setBirthDay(dateOfBirth.getValue() == null ? dp.getBirthDay() : dateOfBirth.getValue());
		dp.setGender(radioGenderSelect());
		dp.setVehicle(vehicleList.getValue() == null ? dp.getVehicle() : vehicleList.getValue());
		dp.setArea(areasList.getValue() == null ? dp.getArea() : areasList.getValue());
		Restaurant.getInstance().getDeliveryPersons().put(dp.getId(), dp);
		cs.changeScreen("/View/DeliveryPersonsList.fxml", editDp);
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
