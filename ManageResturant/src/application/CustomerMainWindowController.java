package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Restaurant;

public class CustomerMainWindowController extends ControllerBase{

	@FXML
	private AnchorPane mainCustomerScene;
	
	@FXML
	private Label txtWelcome;
	
	@FXML
	private Button logout, menu, shoppingCart,personalInformation,orders,dishes,relevantDish,cookByExpertise,popularComponents,saveSerialize  ;

	@FXML
	public ImageView customerPhoto;
	
	private ControllerBase cs = new ControllerBase();	
	
	//Save serialize
	@FXML
	public void saveData(ActionEvent event) throws Exception{
		 MainController.restSerialize();
	}
	
	@FXML
	public void logout(ActionEvent event) throws Exception{
		cs.changeScreen("/View/Login.fxml", mainCustomerScene);
	}
	
	@FXML
	public void ordersCustomerScene(ActionEvent event) throws Exception{
		cs.changeScreen("/View/OrdersCustomerAction.fxml", mainCustomerScene);
	}
	
	@FXML
	public void shoppingCartCustomerScene(ActionEvent event) throws Exception{
		cs.changeScreen("/View/ShoppingCart.fxml", mainCustomerScene);
	}
	
	public void personalInformationScene (ActionEvent event) throws Exception{
		cs.changeScreen("/View/PersonalInformation.fxml", mainCustomerScene);
	}
	
	public void menuScene (ActionEvent event) throws Exception{
		cs.changeScreen("/View/Menu.fxml", mainCustomerScene);
	}
	
	public void relevantDishes (ActionEvent event) throws Exception{
		cs.changeScreen("/View/RelevantDishesOfCustomer.fxml", mainCustomerScene);
	}
	
	public void cooksByExpertiseCustomer (ActionEvent event) throws Exception{
		cs.changeScreen("/View/CooksByExpertiseCustomer.fxml", mainCustomerScene);
	}
	
	public void popularComponentsCustomer (ActionEvent event) throws Exception{
		cs.changeScreen("/View/PopularComponentsCustomer.fxml", mainCustomerScene);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Account a=Restaurant.getInstance().getAccounts().get(NewAccountController.name);
		if(a != null && a.getImage() != null) {
			try {
				customerPhoto.setImage(new Image(new FileInputStream(a.getImage())));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
}
