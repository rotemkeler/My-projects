package application;



import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;


public class ManagerMainController implements Initializable{
	
	@FXML
	private Label txtWelcome;
	
	@FXML
	private Button saveSerialize, cooks, deliveryPersons, customers, dishes, components, orders, deliveries, areas;
	
	@FXML
	private MenuBar menu;
	
	@FXML
	private AnchorPane mainScreen;
	
	private  ControllerBase cs = new ControllerBase();	
	
	@FXML
	public void saveData(ActionEvent event) throws Exception{
		 MainController.restSerialize();
	}
	
	@FXML
	public void exit(ActionEvent event) throws Exception{
		Platform.exit();
		System.exit(0);
	}

	public void logout(ActionEvent event) throws Exception{
		cs.changeScreen("/View/Login.fxml", mainScreen);
	}
	
	@FXML
	public void addCooksManagerScene(ActionEvent event) throws Exception{
		cs.changeScreen("/View/AddCook.fxml", mainScreen);
	}
	
	@FXML
	public void addDpManagerScene(ActionEvent event) throws Exception{
		cs.changeScreen("/View/AddDeliveryPerson.fxml", mainScreen);
	}
	
	@FXML
	public void addCustomerManagerScene(ActionEvent event) throws Exception{
		cs.changeScreen("/View/AddCustomer.fxml", mainScreen);
	}
	
	@FXML
	public void addDishManagerScene(ActionEvent event) throws Exception{
		cs.changeScreen("/View/AddDish.fxml", mainScreen);
	}
	
	@FXML
	public void addComponentManagerScene(ActionEvent event) throws Exception{
		cs.changeScreen("/View/AddComponent.fxml", mainScreen);
	}
	
	@FXML
	public void addOrderManagerScene(ActionEvent event) throws Exception{
		cs.changeScreen("/View/AddOrder.fxml", mainScreen);
	}
	
	@FXML
	public void addExpressDeliveryManagerScene(ActionEvent event) throws Exception{
		cs.changeScreen("/View/AddExpressDelivery.fxml", mainScreen);
	}
	
	@FXML
	public void addRegularDeliveryManagerScene(ActionEvent event) throws Exception{
		cs.changeScreen("/View/AddRegularDelivery.fxml", mainScreen);
	}
	
	@FXML
	public void addDaManagerScene(ActionEvent event) throws Exception{
		cs.changeScreen("/View/AddDeliveryArea.fxml", mainScreen);
	}
	
	@FXML
	public void addCustomerToBlManagerScene(ActionEvent event) throws Exception{
		cs.changeScreen("/View/AddToBlackList.fxml", mainScreen);
	}
	
	@FXML
	public void removeCooksManagerScene(ActionEvent event) throws Exception{
		cs.changeScreen("/View/RemoveCook.fxml", mainScreen);
	}
	
	@FXML
	public void removeDpManagerScene(ActionEvent event) throws Exception{
		cs.changeScreen("/View/RemoveDeliveryPerson.fxml", mainScreen);
	}
	
	@FXML
	public void removeCustomerManagerScene(ActionEvent event) throws Exception{
		cs.changeScreen("/View/RemoveCustomer.fxml", mainScreen);
	}
	
	@FXML
	public void removeDishManagerScene(ActionEvent event) throws Exception{
		cs.changeScreen("/View/RemoveDish.fxml", mainScreen);
	}
	
	@FXML
	public void removeComponentManagerScene(ActionEvent event) throws Exception{
		cs.changeScreen("/View/RemoveComponent.fxml", mainScreen);
	}
	
	@FXML
	public void removeOrderManagerScene(ActionEvent event) throws Exception{
		cs.changeScreen("/View/RemoveOrder.fxml", mainScreen);
	}
	
	@FXML
	public void removeDeliveryManagerScene(ActionEvent event) throws Exception{
		cs.changeScreen("/View/RemoveDelivery.fxml", mainScreen);
	}
	
	@FXML
	public void removeDaManagerScene(ActionEvent event) throws Exception{
		cs.changeScreen("/View/RemoveDeliveryArea.fxml", mainScreen);
	}
	
	@FXML
	public void deliveriesByPersonManagerScene(ActionEvent event) throws Exception{
		cs.changeScreen("/View/DeliveriesByPerson.fxml", mainScreen);
	}
	
	@FXML
	public void cookListManagerScene(ActionEvent event) throws Exception{
		cs.changeScreen("/View/CooksList.fxml", mainScreen);
	}
	
	@FXML
	public void dpListManagerScene(ActionEvent event) throws Exception{
		cs.changeScreen("/View/DeliveryPersonsList.fxml", mainScreen);
	}
	
	@FXML
	public void customersListManagerScene(ActionEvent event) throws Exception{
		cs.changeScreen("/View/CustomersList.fxml", mainScreen);
	}
	
	@FXML
	public void dishesListManagerScene(ActionEvent event) throws Exception{
		cs.changeScreen("/View/DishesList.fxml", mainScreen);
	}
	
	@FXML
	public void componentsListManagerScene(ActionEvent event) throws Exception{
		cs.changeScreen("/View/ComponentsList.fxml", mainScreen);
	}
	
	@FXML
	public void ordersListManagerScene(ActionEvent event) throws Exception{
		cs.changeScreen("/View/OrdersList.fxml", mainScreen);
	}
	
	@FXML
	public void deliveriesListManagerScene(ActionEvent event) throws Exception{
		cs.changeScreen("/View/DeliveriesList.fxml", mainScreen);
	}
	
	@FXML
	public void areasListManagerScene(ActionEvent event) throws Exception{
		cs.changeScreen("/View/AreasList.fxml", mainScreen);
	}
	
	@FXML
	public void getRelevantDishesManagerScene(ActionEvent event) throws Exception{
		cs.changeScreen("/View/GetRelevantDishes.fxml", mainScreen);
	}
	
	@FXML
	public void cooksByExpertiseManagerScene(ActionEvent event) throws Exception{
		cs.changeScreen("/View/GetCooksByExpertise.fxml", mainScreen);
	}
	
	@FXML
	public void deliveriesPerTypeManagerScene(ActionEvent event) throws Exception{
		cs.changeScreen("/View/DeliveriesPerType.fxml", mainScreen);
	}
	
	@FXML
	public void AIMechineManagerScene(ActionEvent event) throws Exception{
		cs.changeScreen("/View/AIMechine.fxml", mainScreen);
	}
	
	@FXML
	public void popularComponentsManagerScene(ActionEvent event) throws Exception{
		cs.changeScreen("/View/PopularComponents.fxml", mainScreen);
	}
	
	@FXML
	public void financeDetailsSceneManagerScene(ActionEvent event) throws Exception{
		cs.changeScreen("/View/FinanceDetails.fxml", mainScreen);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
	}


	
	
	
	
	
	
}
