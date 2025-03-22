package application;

import java.net.URL; 
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Component;
import model.Dish;
import model.Restaurant;
import utils.DishType;

public class AddDishController extends ControllerBase{

	@FXML
	public ComboBox<DishType> dishTypeList; 
	
	@FXML
	public ListView<Component> componentsList;
	
	@FXML
	private Button back;
	
	@FXML
	private Label alertFill, alertNumber;
	
	@FXML
	private TextField dishName, timeToMake;
	
	@FXML
	private AnchorPane addDishScene;
	
	ControllerBase cs = new ControllerBase();
	
	public void addDishSceneToMainScene(ActionEvent event) throws Exception{
		cs.backToManagerMainWindow(addDishScene);
	}
	
	ObservableList<DishType> list = FXCollections.observableArrayList(DishType.values());

	public void saveDish(ActionEvent event) throws Exception{
		if(dishName.getText().isEmpty() || dishTypeList.getSelectionModel().isEmpty() || componentsList.getSelectionModel().isEmpty() || timeToMake.getText().isEmpty()) {
			alertFill.setText("You must fill in all the fields");
			return;
		}
		else{
			int time = Integer.parseInt(timeToMake.getText());
			ArrayList<Component> componenets = new ArrayList<Component>(componentsList.getSelectionModel().getSelectedItems());
			Dish newDish = new Dish(dishName.getText(), dishTypeList.getValue(), componenets, time);
			Restaurant.getInstance().addDish(newDish);
			cs.changeScreen("/View/ManagerMainWindow.fxml", addDishScene);
		}
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
		dishTypeList.setItems(list);
		for(Component c : Restaurant.getInstance().getComponenets().values()) {
			componentsList.getItems().add(c);
		}
		componentsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		//Invalid input alert
		timeToMake.textProperty().addListener((observable, oldValue, newValue) -> {
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
	

	
}
