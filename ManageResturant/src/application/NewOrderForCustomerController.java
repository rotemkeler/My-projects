package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.AnchorPane;
import model.Dish;
import model.Order;
import model.Restaurant;

public class NewOrderForCustomerController extends ControllerBase{
	
	@FXML
	public AnchorPane newOrder;
	
	@FXML
	public ListView<Dish> chooseDishes;
	
	ControllerBase cs = new ControllerBase();

	public void toMainCustomerScene(ActionEvent event) throws Exception{
		cs.changeScreen("/View/OrdersCustomerAction.fxml", newOrder);
	}
	
	public void saveOrder(ActionEvent event) throws Exception{
		Account account = Restaurant.getInstance().getAccounts().get(NewAccountController.name);
		ArrayList<Dish> d = new ArrayList<Dish>(chooseDishes.getSelectionModel().getSelectedItems());
		Order o = new Order(Restaurant.getInstance().getCustomerByIdNumber(account.getIdNumber()), d, null);
		Restaurant.getInstance().addOrder(o);
		Restaurant.getInstance().getOrders().put(o.getId(), o);
		cs.changeScreen("/View/OrdersCustomerAction.fxml", newOrder);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		super.initialize(arg0, arg1);
		for(Dish d : Restaurant.getInstance().getDishes().values()) {
			chooseDishes.getItems().add(d);
		}
		chooseDishes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
}
