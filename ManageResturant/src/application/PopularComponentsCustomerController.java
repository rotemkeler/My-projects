package application;

import java.net.URL; 
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import model.Component;
import model.Restaurant;

public class PopularComponentsCustomerController extends ControllerBase{

	@FXML
	private ListView<Component> thePopular;
	
	@FXML
	private AnchorPane thePopularComponents;
	
	ControllerBase cs = new ControllerBase();

	public void toMainCustomerScene(ActionEvent event) throws Exception{
		cs.backToCustomerMainWindow(thePopularComponents);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		super.initialize(arg0, arg1);
		LinkedList<Component> poplarComponents = Restaurant.getInstance().getPopularComponents();
		for(Component c : poplarComponents) {
			if(c != null) {
				thePopular.getItems().add(c);
			}
		}
	}
}
