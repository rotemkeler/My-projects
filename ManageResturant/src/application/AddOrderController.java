package application;

import java.net.URL; 
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.AnchorPane;
import model.Customer;
import model.Delivery;
import model.Dish;
import model.Order;
import model.Restaurant;

public class AddOrderController extends ControllerBase{

	@FXML
	private Button back;
	
	@FXML
	private Label alertFill;
	
	@FXML
	private ComboBox<Customer> customersList;
	
	@FXML
	private ListView<Dish> dishesList;
	
	@FXML
	private ComboBox<Delivery> deliveriesList;
	
	@FXML
	private AnchorPane addOrderScreen;
	
	ControllerBase cs = new ControllerBase();
	
	public void addOrderSceneToMainScene(ActionEvent event) throws Exception{
		cs.backToManagerMainWindow(addOrderScreen);
	}
	
	public void saveOrder(ActionEvent event) throws Exception{
		if(customersList.getSelectionModel().isEmpty() || dishesList.getSelectionModel().isEmpty()) {
			alertFill.setText("You must fill in all the fields");
			return;
		}
		ArrayList<Dish> dishes = new ArrayList<Dish>(dishesList.getSelectionModel().getSelectedItems());
		Order newOrder = new Order(customersList.getValue(), dishes, deliveriesList.getValue());
		Restaurant.getInstance().addOrder(newOrder);
		cs.changeScreen("/View/ManagerMainWindow.fxml", addOrderScreen);
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for(Customer c : Restaurant.getInstance().getCustomers().values()) {
			customersList.getItems().add(c);
		}
		for(Dish d : Restaurant.getInstance().getDishes().values()) {
			dishesList.getItems().add(d);
		}
		for(Delivery d : Restaurant.getInstance().getDeliveries().values()) {
			deliveriesList.getItems().add(d);
		}
		dishesList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
}
