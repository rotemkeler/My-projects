package application;

import java.net.URL; 
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Customer;
import model.Restaurant;

public class CustomersListController extends ControllerBase{

	@FXML
	private Button back;
	
	@FXML
	private ListView<Customer> allCustomers;
	
	@FXML
	private ListView<Customer> blackList;
	
	@FXML
	private AnchorPane customersList;
	
	public static Customer showThisCustomerPhoto=null;
	
	ControllerBase cs = new ControllerBase();
	
	public void customersListSceneToMainScene(ActionEvent event) throws Exception{
		cs.backToManagerMainWindow(customersList);
	}
	
	@FXML 
	public void customerChoose(MouseEvent arg0) throws Exception{
		showThisCustomerPhoto = allCustomers.getSelectionModel().getSelectedItem();
	}
	
	public void showPhoto (ActionEvent event) throws Exception{
		if(showThisCustomerPhoto!=null) {
			cs.changeScreen("/View/PhotosOfCustomer.fxml", customersList);
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for(Customer c : Restaurant.getInstance().getCustomers().values()) {
			allCustomers.getItems().add(c);
		}
		for(Customer c : Restaurant.getInstance().getBlackList()) {
			blackList.getItems().add(c);
			allCustomers.getItems().remove(c);
		}
		showThisCustomerPhoto = null;
	}
}
