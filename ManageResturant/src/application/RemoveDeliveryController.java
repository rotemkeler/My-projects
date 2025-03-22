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
import model.Delivery;
import model.Restaurant;

public class RemoveDeliveryController extends ControllerBase{

	@FXML
	private Button back;
	
	@FXML
	private ComboBox<Delivery> deliveriesList;
	
	@FXML
	private AnchorPane removeDeliveryScreen;
	
	ControllerBase cs = new ControllerBase();
	
	public void removeDeliverySceneToMainScene(ActionEvent event) throws Exception{
		cs.backToManagerMainWindow(removeDeliveryScreen);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<Delivery> list = FXCollections.observableArrayList();
		for(Delivery d : Restaurant.getInstance().getDeliveries().values()) {
			list.add(d);
		}
		deliveriesList.setItems(list);
	}
	
	public void saveRemoveDelivery(ActionEvent event) throws Exception{
		Restaurant.getInstance().removeDelivery(deliveriesList.getValue());
		cs.changeScreen("/View/ManagerMainWindow.fxml", removeDeliveryScreen);
	}
}
