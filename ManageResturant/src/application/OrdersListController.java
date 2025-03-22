package application;

import java.net.URL; 
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Order;
import model.Restaurant;

public class OrdersListController extends ControllerBase{

	@FXML
	private Button back;
	
	@FXML
	private ListView<Order> allOrders;
	
	@FXML
	private AnchorPane ordersList;
	
	public static Order orderToEdit=null;
	
	ControllerBase cs = new ControllerBase();
	
	public void ordersListSceneToMainScene(ActionEvent event) throws Exception{
		cs.backToManagerMainWindow(ordersList);
	}
	
	@FXML 
	public void orderChoose(MouseEvent arg0) throws Exception{
		orderToEdit = allOrders.getSelectionModel().getSelectedItem();
	}
	
	public void editOrder(ActionEvent event) throws Exception{
		if(orderToEdit!=null) {
			cs.changeScreen("/View/EditOrdersList.fxml", ordersList);
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for(Order o : Restaurant.getInstance().getOrders().values()) {
			allOrders.getItems().add(o);
		}
		orderToEdit = null;
	}
}
