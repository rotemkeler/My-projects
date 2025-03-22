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
import model.Customer;
import model.Restaurant;

public class RemoveCustomerController extends ControllerBase{

	@FXML
	private Button back;
	
	@FXML
	private AnchorPane removeCustomerScreen;
	
	@FXML
	private ComboBox<Customer> customersList;
	
	ControllerBase cs = new ControllerBase();
	
	public void removeCustomerSceneToMainScene(ActionEvent event) throws Exception{
		cs.backToManagerMainWindow(removeCustomerScreen);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<Customer> list = FXCollections.observableArrayList();
		for(Customer c : Restaurant.getInstance().getCustomers().values()) {
			list.add(c);
		}
		customersList.setItems(list);
	}
	
	public void saveRemoveCustomer(ActionEvent event) throws Exception{
		Restaurant.getInstance().removeCustomer(customersList.getValue());
		cs.changeScreen("/View/ManagerMainWindow.fxml", removeCustomerScreen);
	}
}
