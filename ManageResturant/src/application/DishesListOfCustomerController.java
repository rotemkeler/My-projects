package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Dish;
import model.Order;

public class DishesListOfCustomerController extends ControllerBase{
	
	@FXML
	public AnchorPane allDishesOfCustomer;
	
	@FXML
	public ListView<Dish> allDishes;
	
	ControllerBase cs = new ControllerBase();
	public static Dish dishToEdit=null;
	
	public void backToOrders (ActionEvent event) throws Exception{
		cs.changeScreen("/View/OrdersCustomerAction.fxml", allDishesOfCustomer);
	}
	
	public void editDishes (ActionEvent event) throws Exception{
		cs.changeScreen("/View/EditDish.fxml", allDishesOfCustomer);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Order orderToEdit=OrdersCustomerActionController.orderChosen;
		allDishes.getItems().addAll(orderToEdit.getDishes());
	}
	
	@FXML 
	public void dishChoose(MouseEvent arg0) throws Exception{
		 dishToEdit=allDishes.getSelectionModel().getSelectedItem();
	}

}
