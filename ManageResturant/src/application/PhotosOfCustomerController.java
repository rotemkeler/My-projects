package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Restaurant;

public class PhotosOfCustomerController extends ControllerBase{
	
	@FXML
	public Label nameOfCustomer;
	
	@FXML
	public AnchorPane photosCustomers;
	
	ControllerBase cs = new ControllerBase();
	
	
	public void backToCustList(ActionEvent event)throws Exception{
		cs.changeScreen("/View/CustomersList.fxml", photosCustomers);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for(Account a : Restaurant.getInstance().getAccounts().values()) {
			if(a.getIdNumber().equals(CustomersListController.showThisCustomerPhoto.getIdNumber())) {
				NewAccountController.name = a.getUserName();
			}
		}
		super.initialize(arg0, arg1);
		nameOfCustomer.setText(CustomersListController.showThisCustomerPhoto.getFirstName()+" "+
							CustomersListController.showThisCustomerPhoto.getLastName());	
	}
}
