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
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import model.Cook;
import model.Restaurant;
import utils.Expertise;
import utils.Gender;


public class AddCookController extends ControllerBase{

	@FXML
	private Button back, save;
	
	@FXML
	private AnchorPane addCookScreen;
	
	@FXML
	private Label alertFill;
	
	@FXML
	private javafx.scene.control.TextField firstName, lastName;
	
	@FXML
	private DatePicker dateOfBirth;
	
	@FXML
	private RadioButton male, female, unknown, yes, no;
	
	@FXML
	private ToggleGroup genderGroup;

	@FXML
	private ToggleGroup isChef;
	
	@FXML
	public ComboBox<Expertise> expertiseList;
	
	ControllerBase cs = new ControllerBase();
	
	ObservableList<Expertise> list = FXCollections.observableArrayList(Expertise.values());

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		expertiseList.setItems(list);
	} 
	
	public void addCookSceneToMainScene(ActionEvent event) throws Exception{
		cs.backToManagerMainWindow(addCookScreen);
	}
	
	public void saveCook(ActionEvent event) throws Exception{
		Gender gender = radioGenderSelect();
		if(firstName.getText().isEmpty()|| lastName.getText().isEmpty() || dateOfBirth.getValue() == null || gender == null || expertiseList.getSelectionModel().isEmpty() ||
				(!yes.isSelected() && !no.isSelected())) {
			alertFill.setText("You must fill in all the fields!");
			return;
		}
		else {
			Cook newCook = new Cook(firstName.getText(), lastName.getText(), dateOfBirth.getValue(), gender, 
					expertiseList.getValue(),yes.isSelected() ? true : false );
			Restaurant.getInstance().addCook(newCook);
		}
		cs.changeScreen("/View/ManagerMainWindow.fxml", addCookScreen);
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
