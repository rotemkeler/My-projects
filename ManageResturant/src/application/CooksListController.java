package application;

import java.net.URL;  
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Cook;
import model.Restaurant;

public class CooksListController extends ControllerBase{

	@FXML
	private Button back;
	
	@FXML
	private ListView<Cook> allCooks;
	
	@FXML
	private AnchorPane cooksList;
	
	public static Cook cookToEdit=null;
	
	ControllerBase cs = new ControllerBase();
	
	public void cooksListSceneToMainScene(ActionEvent event) throws Exception{
		cs.backToManagerMainWindow(cooksList);
	}
	
	@FXML 
	public void cookChoose(MouseEvent arg0) throws Exception{
		cookToEdit = allCooks.getSelectionModel().getSelectedItem();
	}
	
	public void editCook (ActionEvent event) throws Exception{
		if(cookToEdit!=null) {
			cs.changeScreen("/View/EditCooksList.fxml", cooksList);
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for(Cook c : Restaurant.getInstance().getCooks().values()) {
			allCooks.getItems().add(c);
		}
		cookToEdit = null;
	}
}
