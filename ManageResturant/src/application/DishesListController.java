package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Dish;
import model.Restaurant;

public class DishesListController extends ControllerBase{

	@FXML
	private Button back;
	
	@FXML
	private ListView<Dish> allDishes;
	
	@FXML
	private AnchorPane dishesList;
	
	public static Dish dishFromListToEdit=null;
	
	ControllerBase cs = new ControllerBase();
	
	@FXML 
	public void dishChoose(MouseEvent arg0) throws Exception{
		dishFromListToEdit = allDishes.getSelectionModel().getSelectedItem();
	}
	
	public void editDish (ActionEvent event) throws Exception{
		if(dishFromListToEdit!=null) {
			cs.changeScreen("/View/EditDishesList.fxml", dishesList);
		}
	}
	
	public void dishesListSceneToMainScene(ActionEvent event) throws Exception{
		ControllerBase cs = new ControllerBase();
		cs.backToManagerMainWindow(dishesList);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for(Dish d : Restaurant.getInstance().getDishes().values()) {
			allDishes.getItems().add(d);
		}
		dishFromListToEdit = null;
	}
}
