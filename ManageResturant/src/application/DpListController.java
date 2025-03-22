package application;

import java.net.URL; 
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.DeliveryPerson;
import model.Restaurant;

public class DpListController extends ControllerBase{

	@FXML
	private Button back;
	
	@FXML
	private AnchorPane deliveryPersonsList;
	
	@FXML
	private ListView<DeliveryPerson> allDeliveryPersons;
	
	public static DeliveryPerson dpToEdit=null;
	
	ControllerBase cs = new ControllerBase();
	
	public void dpListSceneToMainScene(ActionEvent event) throws Exception{
		cs.backToManagerMainWindow(deliveryPersonsList);
	}
	
	@FXML 
	public void dpChoose(MouseEvent arg0) throws Exception{
		dpToEdit = allDeliveryPersons.getSelectionModel().getSelectedItem();
	}
	
	public void editDp (ActionEvent event) throws Exception{
		if(dpToEdit!=null) {
			cs.changeScreen("/View/EditDpList.fxml", deliveryPersonsList);
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for(DeliveryPerson dp : Restaurant.getInstance().getDeliveryPersons().values()) {
			allDeliveryPersons.getItems().add(dp);
		}
		dpToEdit = null;
	}
}
