package application;

import java.net.URL; 
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import model.Customer;
import model.Restaurant;

public class AddToBlackListController extends ControllerBase{

	@FXML
	private Button back;
	
	@FXML
	private ComboBox<Customer> customersList;
	
	@FXML
	private AnchorPane addToBlackListScreen;
	
	ControllerBase cs = new ControllerBase();
	
	public void addToBlSceneToMainScene(ActionEvent event) throws Exception{
		cs.backToManagerMainWindow(addToBlackListScreen);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for(Customer c : Restaurant.getInstance().getCustomers().values()) {
			customersList.getItems().add(c);
		}
	}
	
	public void saveToBlackList (ActionEvent event) throws Exception {
		Restaurant.getInstance().addCustomerToBlackList(customersList.getValue());
		Restaurant.getInstance().removeCustomer(customersList.getValue());
		cs.changeScreen("/View/ManagerMainWindow.fxml", addToBlackListScreen);
	}
	
}
