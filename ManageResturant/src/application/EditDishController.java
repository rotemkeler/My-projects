package application;

import java.net.URL; 
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import model.Component;
import model.Restaurant;

public class EditDishController extends ControllerBase{

	@FXML
	public AnchorPane editDish;
	
	@FXML
	public ListView<Component> componentsOfDish;
	
	@FXML
	public ComboBox<Component> addComponentToDish;
	
	@FXML
	public ComboBox<Component> removeComponentsFromDish;
	
	ControllerBase cs = new ControllerBase();
	
	public void backToDishesList (ActionEvent event) throws Exception{
		cs.changeScreen("/View/DishesListOfCustomer.fxml", editDish);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		addComponentToDish.getItems().addAll(Restaurant.getInstance().getComponenets().values());
		for(Component c : DishesListOfCustomerController.dishToEdit.getComponenets()) {
			if(c != null) {
				componentsOfDish.getItems().add(c);
			}
		}
		removeComponentsFromDish.getItems().addAll(DishesListOfCustomerController.dishToEdit.getComponenets());
	}
	
	public void saveChangesOnDish (ActionEvent event) throws Exception{
		DishesListOfCustomerController.dishToEdit.removeComponent(removeComponentsFromDish.getValue());
		DishesListOfCustomerController.dishToEdit.addComponent(addComponentToDish.getValue());
		cs.changeScreen("/View/DishesListOfCustomer.fxml", editDish);
	}
	
	
	
}
