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
import model.DeliveryArea;
import model.Restaurant;

public class RemoveDaController extends ControllerBase{

	@FXML
	private Button back;
	
	@FXML
	private AnchorPane removeDaScreen;
	
	@FXML
	private ComboBox<DeliveryArea> daList;
	
	ControllerBase cs = new ControllerBase();
	
	public void removeDaSceneToMainScene(ActionEvent event) throws Exception{
		cs.backToManagerMainWindow(removeDaScreen);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<DeliveryArea> list = FXCollections.observableArrayList();
		for(DeliveryArea da : Restaurant.getInstance().getAreas().values()) {
			list.add(da);
		}
		daList.setItems(list);
	}
	
}
