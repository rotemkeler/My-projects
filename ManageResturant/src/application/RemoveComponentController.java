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
import model.Component;
import model.Restaurant;

public class RemoveComponentController extends ControllerBase{

	@FXML
	private Button back;
	
	@FXML
	public ComboBox<Component> componentsList; 
	
	@FXML
	private AnchorPane removeComponentScreen;
	
	ControllerBase cs = new ControllerBase();
	
	public void removeComponentSceneToMainScene(ActionEvent event) throws Exception{
		cs.backToManagerMainWindow(removeComponentScreen);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<Component> list = FXCollections.observableArrayList();
		for(Component c : Restaurant.getInstance().getComponenets().values()) {
			list.add(c);
		}
		componentsList.setItems(list);
	}
	
	public void saveRemoveComponent(ActionEvent event) throws Exception{
		Restaurant.getInstance().removeComponent(componentsList.getValue());
		cs.changeScreen("/View/ManagerMainWindow.fxml", removeComponentScreen);
	}
	

}
