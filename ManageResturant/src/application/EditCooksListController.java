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
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import model.Cook;
import model.Restaurant;
import utils.Expertise;
import utils.Gender;

public class EditCooksListController extends ControllerBase{

	@FXML
	public AnchorPane editCook;
	
	@FXML
	public Button back, save;
	
	@FXML
	public RadioButton male, female, unknown, yes, no;
	
	@FXML
	public TextField name, lastName;
	
	@FXML
	public DatePicker date;
	
	@FXML
	public ComboBox<Expertise> expertise;
	
	@FXML
	public ToggleGroup genderGroup, isChef;
	
	Cook cook = Restaurant.getInstance().getCooks().get(CooksListController.cookToEdit.getId());
	
	ControllerBase cs = new ControllerBase();
	
	ObservableList<Expertise> list = FXCollections.observableArrayList(Expertise.values());
	
	public void backToCooksList (ActionEvent event) throws Exception{
		cs.changeScreen("/View/CooksList.fxml", editCook);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		expertise.setItems(list);
		name.setText(cook.getFirstName());
		lastName.setText(cook.getLastName());
		date.setValue(cook.getBirthDay());
		switch (cook.getGender().toString().toLowerCase()) {
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
		expertise.promptTextProperty().set(cook.getExpert().toString());
		if(cook.isChef()) {
			yes.setSelected(true);
		}
		else {
			no.setSelected(true);
		}
	} 
	
	public void saveDetailsChange (ActionEvent event) throws Exception{
		cook.setFirstName(name.getText());
		cook.setLastName(lastName.getText());
		cook.setBirthDay(date.getValue() == null ? cook.getBirthDay() : date.getValue());
		cook.setGender(radioGenderSelect());
		cook.setExpert(expertise.getValue() == null ? cook.getExpert() : expertise.getValue());
		cook.setChef(yes.isSelected() ? true : false);
		Restaurant.getInstance().getCooks().put(cook.getId(), cook);
		cs.changeScreen("/View/CooksList.fxml", editCook);
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
