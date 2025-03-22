package application;

import java.net.URL;  
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.DeliveryArea;
import model.Restaurant;

public class AreasListController extends ControllerBase{

	@FXML
	private Button back;
	
	@FXML
	private ListView<DeliveryArea> allAreas;
	
	@FXML
	private AnchorPane areasList;
	
	public static DeliveryArea areaToEdit=null;
	
	ControllerBase cs = new ControllerBase();
	
	
	
	public void areasListSceneToMainScene(ActionEvent event) throws Exception{
		cs.backToManagerMainWindow(areasList);
	}
	
	@FXML 
	public void areaChoose(MouseEvent arg0) throws Exception{
		areaToEdit = allAreas.getSelectionModel().getSelectedItem();
	}
	
	public void editArea (ActionEvent event) throws Exception{
		if(areaToEdit!=null) {
			cs.changeScreen("/View/EditAreasList.fxml", areasList);
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for(DeliveryArea c : Restaurant.getInstance().getAreas().values()) {
			allAreas.getItems().add(c);
		}
		areaToEdit = null;
	}
}
