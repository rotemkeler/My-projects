package application;
 
import java.net.URL; 
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import model.Delivery;
import model.DeliveryPerson;
import model.Restaurant;

public class DeliveriesByPersonController extends ControllerBase{

	@FXML
	private Button back, ok;

	@FXML
	private AnchorPane deiveriesByPersonScreen;
	
	@FXML
	private ListView<Delivery> deliveriesList;
	
	@FXML
	public ComboBox<Integer> monthList;
	
	@FXML
	public ComboBox<DeliveryPerson> dpList;
	
	public void deiveriesByPersonSceneToMainScene(ActionEvent event) throws Exception{
		ControllerBase cs = new ControllerBase();
		cs.backToManagerMainWindow(deiveriesByPersonScreen);
	}
	
	ObservableList<Integer> list = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12);
	
	public void okButton(ActionEvent event) throws Exception{
		List<Delivery> deliveryByDp = new ArrayList<Delivery>();
		deliveriesList.getItems().clear();
		deliveryByDp = Restaurant.getInstance().getDeliveriesByPerson(dpList.getValue(), monthList.getValue()).stream().collect(Collectors.toList());
		for(Delivery d : deliveryByDp) {
			deliveriesList.getItems().add(d);
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		monthList.setItems(list);
		for(DeliveryPerson dp : Restaurant.getInstance().getDeliveryPersons().values()) {
			dpList.getItems().add(dp);
		}
	} 
}
