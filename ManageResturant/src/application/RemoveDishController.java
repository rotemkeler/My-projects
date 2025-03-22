package application;

import java.net.URL; 
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import model.Dish;
import model.Restaurant;

public class RemoveDishController extends ControllerBase{

	@FXML
	private Button back;
	
	@FXML
	private ComboBox<Dish> dishesList;
	
	@FXML
	private AnchorPane removeDishScreen;
	
	ControllerBase cs = new ControllerBase();
	
	public void removeDishSceneToMainScene(ActionEvent event) throws Exception{
		cs.backToManagerMainWindow(removeDishScreen);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<Dish> list = FXCollections.observableArrayList();
		for(Dish d : Restaurant.getInstance().getDishes().values()) {
			list.add(d);
		}
		dishesList.setItems(list);
	}
	
	public void saveRemoveDish(ActionEvent event) throws Exception{
		Restaurant.getInstance().removeDish(dishesList.getValue());
		cs.changeScreen("/View/ManagerMainWindow.fxml", removeDishScreen);
	}
}
