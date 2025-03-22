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
import model.Order;
import model.Restaurant;

public class RemoveOrderController extends ControllerBase{

	@FXML
	private Button back;
	
	@FXML
	private ComboBox<Order> ordersList;
	
	@FXML
	private AnchorPane removeOrderScreen;
	
	ControllerBase cs = new ControllerBase();
	
	public void removeOrderSceneToMainScene(ActionEvent event) throws Exception{
		cs.backToManagerMainWindow(removeOrderScreen);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<Order> list = FXCollections.observableArrayList();
		for(Order o : Restaurant.getInstance().getOrders().values()) {
			list.add(o);
		}
		ordersList.setItems(list);
	}
	
	public void saveRemoveOrder(ActionEvent event) throws Exception{
		Restaurant.getInstance().removeOrder(ordersList.getValue());
		cs.changeScreen("/View/ManagerMainWindow.fxml", removeOrderScreen);
	}
}
