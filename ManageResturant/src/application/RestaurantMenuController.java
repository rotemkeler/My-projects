package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class RestaurantMenuController extends ControllerBase{
	
	@FXML
	public AnchorPane menu;
	
	ControllerBase cs = new ControllerBase();


	public void toMainCustomerScene(ActionEvent event) throws Exception{
		cs.backToCustomerMainWindow(menu);
	}
}
