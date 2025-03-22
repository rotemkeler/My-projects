package application;

import java.net.URL; 
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Restaurant;

public class DeliveriesPerTypeController extends ControllerBase{

	@FXML
	private Button back;
	
	@FXML
	private Label expressDelivery, regularDelivery;

	@FXML
	private AnchorPane deliveriesPerType;
	
	public void deliveriesPerTypeSceneToMainScene(ActionEvent event) throws Exception{
		ControllerBase cs = new ControllerBase();
		cs.backToManagerMainWindow(deliveriesPerType);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		HashMap<String, Integer> numOfDeliveries = Restaurant.getInstance().getNumberOfDeliveriesPerType();
		expressDelivery.setText(numOfDeliveries.get("Express delivery").toString());
		regularDelivery.setText(numOfDeliveries.get("Regular delivery").toString());
		
	}
}
