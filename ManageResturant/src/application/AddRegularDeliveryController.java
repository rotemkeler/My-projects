package application;

import java.net.URL; 
import java.util.ResourceBundle;
import java.util.TreeSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.AnchorPane;
import model.Delivery;
import model.DeliveryArea;
import model.DeliveryPerson;
import model.Order;
import model.RegularDelivery;
import model.Restaurant;

public class AddRegularDeliveryController extends ControllerBase{

	@FXML
	private Button back;
	
	@FXML
	private Label alertFill;
	
	@FXML
	private CheckBox isDelivered;
	
	@FXML
	private ComboBox<DeliveryPerson> deliveryPerson;
	
	@FXML
	private ListView<Order> ordersToChoose;
	
	@FXML
	private ComboBox<DeliveryArea> area;
		
	@FXML
	private DatePicker deliveredDate;
	
	@FXML
	private AnchorPane addRegularDeliveryScene;
	
	ControllerBase cs = new ControllerBase();
	
	public void addRegularDeliverySceneToMainScene(ActionEvent event) throws Exception{
		cs.backToManagerMainWindow(addRegularDeliveryScene);
	}
	
	public void saveRegularDelivery(ActionEvent event) throws Exception{
		if(deliveryPerson.getSelectionModel().isEmpty() || area.getSelectionModel().isEmpty() || deliveredDate.getValue() == null) {
			alertFill.setText("You must fill in all the fields");
			return;
		}
		TreeSet<Order> o = new TreeSet<>(ordersToChoose.getSelectionModel().getSelectedItems());
		Delivery newRegularDelivery = new RegularDelivery(o, deliveryPerson.getValue(), area.getValue(),
								isDelivered.isSelected() ? true : false, deliveredDate.getValue());
		Restaurant.getInstance().addDelivery(newRegularDelivery);
		cs.changeScreen("/View/ManagerMainWindow.fxml", addRegularDeliveryScene);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for(DeliveryPerson dp : Restaurant.getInstance().getDeliveryPersons().values()) {
			deliveryPerson.getItems().add(dp);
		}
		for(DeliveryArea da : Restaurant.getInstance().getAreas().values()) {
			area.getItems().add(da);
		}
		for(Order o : Restaurant.getInstance().getOrders().values()) {
			ordersToChoose.getItems().add(o);
		}
		ordersToChoose.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
	}
}
