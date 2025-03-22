package application;

import java.net.URL; 
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Component;
import model.Restaurant;


public class ComponentsListController extends ControllerBase{
	
	@FXML
	private Button back, edit;
	
	@FXML
	private ListView<Component> allComponents;
	
	@FXML
	private AnchorPane componentsList;
	
	public static Component compToEdit=null;
	
	ControllerBase cs = new ControllerBase();
	
	@FXML 
	public void compChoose(MouseEvent arg0) throws Exception{
		compToEdit = allComponents.getSelectionModel().getSelectedItem();
	}
	
	public void editComp (ActionEvent event) throws Exception{
		if(compToEdit!=null) {
			cs.changeScreen("/View/EditCompList.fxml", componentsList);
		}
	}
	
	public void componentsListSceneToMainScene(ActionEvent event) throws Exception{
		ControllerBase cs = new ControllerBase();
		cs.backToManagerMainWindow(componentsList);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for(Component c : Restaurant.getInstance().getComponenets().values()) {
			allComponents.getItems().add(c);
		}
		compToEdit = null;
	}
	
	
}
