package application;

import java.net.URL;  
import java.util.Collection;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import model.Customer;
import model.Dish;
import model.Restaurant;

public class GetRelevantDishesController extends ControllerBase{

	
	@FXML
	private Button continueButton;
	
	@FXML
	private ComboBox<String> chooseCustomer;
	
	@FXML
	private ListView<Dish> getRelevantDishes;
	
	@FXML
	private AnchorPane getRelevantDish;
	
	public void getRelevanrDishesSceneToMainScene(ActionEvent event) throws Exception{
		ControllerBase cs = new ControllerBase();
		cs.backToManagerMainWindow(getRelevantDish);
	}
	
	public void showRelevantDish(ActionEvent event) throws Exception{
		String id=chooseCustomer.getValue().split(",")[0].trim();
		Customer c =Restaurant.getInstance().getCustomerByIdNumber(Long.valueOf(id));
		Collection<Dish> dishes = Restaurant.getInstance().getReleventDishList(c);
		getRelevantDishes.getItems().clear();
		for(Dish d : dishes) {
			getRelevantDishes.getItems().add(d);
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> names= FXCollections.observableArrayList();
		for(Customer c : Restaurant.getInstance().getCustomers().values()) {
			names.add(c.getIdNumber()+", "+c.getFirstName()+" "+c.getLastName());
		}
		chooseCustomer.setItems(names);
	}
}
