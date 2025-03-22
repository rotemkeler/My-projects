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
import model.DeliveryPerson;
import model.Restaurant;

public class RemoveDpController extends ControllerBase{

	@FXML
	private Button back;
	
	@FXML
	private ComboBox<DeliveryPerson> dpList;
	
	@FXML
	private AnchorPane removeDpScreen;
	
	ControllerBase cs = new ControllerBase();
	
	public void removeDpSceneToMainScene(ActionEvent event) throws Exception{
		cs.backToManagerMainWindow(removeDpScreen);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<DeliveryPerson> list = FXCollections.observableArrayList();
		for(DeliveryPerson dp : Restaurant.getInstance().getDeliveryPersons().values()) {
			list.add(dp);
		}
		dpList.setItems(list);
	}
	
	public void saveRemoveDp(ActionEvent event) throws Exception{
		Restaurant.getInstance().removeDeliveryPerson(dpList.getValue());
		cs.changeScreen("/View/ManagerMainWindow.fxml", removeDpScreen);
	}
}
