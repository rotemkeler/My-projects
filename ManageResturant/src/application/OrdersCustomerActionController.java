package application;


import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Order;
import model.RegularDelivery;
import model.Restaurant;

public class OrdersCustomerActionController extends ControllerBase{

	@FXML
	private AnchorPane ordersCustomer;

	@FXML
	private ListView<Order> ordersList;

	@FXML
	private Button addOrder, removeOrder;
	
	@FXML
	private Label alertChoose;

	private ControllerBase cs = new ControllerBase();	
	public static Order orderChosen=null;

	public void toMainCustomerScene(ActionEvent event) throws Exception{
		cs.backToCustomerMainWindow(ordersCustomer);
	}

	//select the choose order to edit
	@FXML 
	public void orderChoose(MouseEvent arg0) throws Exception{
		orderChosen=ordersList.getSelectionModel().getSelectedItem();
	}

	public void editOrder (ActionEvent event) throws Exception{
		if(orderChosen!=null) {
			cs.changeScreen("/View/DishesListOfCustomer.fxml", ordersCustomer);
		}
		else {
			alertChoose.setText("You must choose order!");
		}
	}

	public void addNewOrder (ActionEvent event) throws Exception{
		cs.changeScreen("/View/NewOrderForCustomer.fxml", ordersCustomer);
	}
	
	public void removeOrder (ActionEvent event) throws Exception{
		Restaurant.getInstance().removeOrder(orderChosen);
		ordersList.getItems().remove(orderChosen);
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		super.initialize(arg0, arg1);
		Account a=Restaurant.getInstance().getAccounts().get(NewAccountController.name);
		for(Map.Entry<Integer, Order> kv:Restaurant.getInstance().getOrders().entrySet()) {
			if(kv.getValue().getCustomer().getIdNumber().equals(a.getIdNumber())) {
				ordersList.getItems().add(kv.getValue());
			}
		}
		orderChosen=null;
	}

	




}
