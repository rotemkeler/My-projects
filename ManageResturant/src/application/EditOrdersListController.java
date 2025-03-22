package application;

import java.net.URL; 
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.AnchorPane;
import model.Customer;
import model.Delivery;
import model.Dish;
import model.Order;
import model.Restaurant;

public class EditOrdersListController extends ControllerBase{

	@FXML
	private ComboBox<Customer> customersList;
	
	@FXML
	private ListView<Dish> dishesList;
	
	@FXML
	private ComboBox<Delivery> deliveriesList;
	
	@FXML
	private ListView<Dish> dishesOnOrder;
	
	@FXML
	private AnchorPane editOrder;
	
	ControllerBase cs = new ControllerBase();
	
	Order order = Restaurant.getInstance().getOrders().get(OrdersListController.orderToEdit.getId());
	
	public void backToOrdersList (ActionEvent event) throws Exception{
		cs.changeScreen("/View/OrdersList.fxml", editOrder);
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
		customersList.promptTextProperty().set(order.getCustomer().toString());
		dishesList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		dishesOnOrder.getItems().addAll(order.getDishes());
		deliveriesList.promptTextProperty().set(order.getDelivery().toString());
	
	}
	
	public void addDish (ActionEvent event) throws Exception{
		Dish dishChoose = dishesList.getSelectionModel().getSelectedItem();
		order.addDish(dishChoose);
		dishesOnOrder.getItems().add(dishChoose);
	}
	
	public void saveDetailsChange (ActionEvent event) throws Exception{
		order.setCustomer(customersList.getValue() == null ? order.getCustomer() : customersList.getValue());
		order.setDelivery(deliveriesList.getValue() == null ? order.getDelivery() : deliveriesList.getValue());
		Restaurant.getInstance().getOrders().put(order.getId(), order);
		cs.changeScreen("/View/OrdersList.fxml", editOrder);
	}
	
}
