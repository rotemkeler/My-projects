package application;

import java.net.URL; 
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.AnchorPane;
import model.Delivery;
import model.DeliveryArea;
import model.DeliveryPerson;
import model.Order;
import model.Restaurant;

public class AIMechineController extends ControllerBase{

	@FXML
	private Button back;
	
	@FXML
	private ComboBox<DeliveryPerson> dpList;
	
	@FXML
	private ComboBox<DeliveryArea> daList;
	
	@FXML
	private ListView<Order> ordersList;
	
	@FXML
	private ListView<Delivery> deliveriesList;
	
	@FXML
	private AnchorPane AIMechine;
	
	public void AIMechineSceneToMainScene(ActionEvent event) throws Exception{
		ControllerBase cs = new ControllerBase();
		cs.backToManagerMainWindow(AIMechine);
	}
	
	public void showDeliveries (ActionEvent event) throws Exception{
		List<Order> selectedOrders = FXCollections.observableList(ordersList.getSelectionModel().getSelectedItems().stream().collect(Collectors.toList()));
		TreeSet<Order> orders = new TreeSet<Order>(selectedOrders);
		ObservableList<Delivery> deliveries = FXCollections.observableList(Restaurant.getInstance().createAIMacine(dpList.getValue(), daList.getValue(),orders).stream().collect(Collectors.toList()));
		deliveriesList.setItems(deliveries);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for(DeliveryPerson dp : Restaurant.getInstance().getDeliveryPersons().values()) {
			dpList.getItems().add(dp);
		}
		for(DeliveryArea da : Restaurant.getInstance().getAreas().values()) {
			daList.getItems().add(da);
		}
		for(Order o : Restaurant.getInstance().getOrders().values()) {
			ordersList.getItems().add(o);
		}
		ordersList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
}
