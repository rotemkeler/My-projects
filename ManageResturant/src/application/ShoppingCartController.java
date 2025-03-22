package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Customer;
import model.Dish;
import model.Restaurant;

public class ShoppingCartController extends ControllerBase{
	
	@FXML
	private AnchorPane shoppingCartScene;
	
	@FXML
	private ComboBox<Dish> allDishesOfRest;
	
	@FXML
	private ListView<Dish> cart;
	
	@FXML
	private ImageView customerPhoto;
	
	ControllerBase cs = new ControllerBase();
	
	public static Dish dishToRemove=null;

	public void toMainCustomerScene(ActionEvent event) throws Exception{
		cs.backToCustomerMainWindow(shoppingCartScene);
	}
	
	Customer c;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		super.initialize(arg0, arg1);
		for(Dish d : Restaurant.getInstance().getDishes().values()) {
			allDishesOfRest.getItems().add(d);
		}
		cart.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		Account account = Restaurant.getInstance().getAccounts().get(NewAccountController.name);
		 c = Restaurant.getInstance().getCustomerByIdNumber(account.getIdNumber());
		 if(c!=null &&c.getDishes()==null)
			 c.setDishes(new ArrayList<Dish>());
		if(c!=null &&c.getDishes().size()>0)
		cart.getItems().addAll(c.getDishes());
	}
	
	public void addToCart (ActionEvent event) throws Exception{
		if(c!=null)
			c.getDishes().add(allDishesOfRest.getValue());
		cart.getItems().add(allDishesOfRest.getValue());
	}
	
	@FXML 
	public void orderChoose(MouseEvent arg0) throws Exception{
		dishToRemove=cart.getSelectionModel().getSelectedItem();
	}
	
	public void deleteDishFromCart (ActionEvent event) throws Exception{		
		if(c!=null) {
			c.getDishes().remove(dishToRemove);
		}
		cart.getItems().remove(dishToRemove);
	}
}
