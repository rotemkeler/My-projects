package application;

import java.net.URL; 
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import model.Delivery;
import model.Restaurant;

public class DeliveriesListController extends ControllerBase{

	@FXML
	private Button back;
	
	@FXML
	private ListView<Delivery> allDeliveries;
	
	@FXML
	private AnchorPane deliveriesList;
	
	public void deliveriesListSceneToMainScene(ActionEvent event) throws Exception{
		ControllerBase cs = new ControllerBase();
		cs.backToManagerMainWindow(deliveriesList);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for(Delivery d : Restaurant.getInstance().getDeliveries().values()) {
			allDeliveries.getItems().add(d);
		}
	}
}
