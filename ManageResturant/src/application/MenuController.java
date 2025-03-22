package application;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Component;
import model.Dish;
import model.Restaurant;

public class MenuController extends ControllerBase{

	@FXML
	private AnchorPane menuScene;
	
	@FXML
	private Label theMenu1;
	
	@FXML
	private Label theMenu2;
	
	ControllerBase cs = new ControllerBase();

	public void toMainCustomerScene(ActionEvent event) throws Exception{
		cs.backToCustomerMainWindow(menuScene);
	}
	
	//show the menu - dishes, price and components of dishes
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		super.initialize(arg0, arg1);
		
		String txt1="";
		String txt2="";
		int j=0;
		HashMap<Integer,Dish> dm=Restaurant.getInstance().getDishes();
		for(Map.Entry<Integer,Dish> e: dm.entrySet()) {
			if(j<= 2) {
				txt1+=e.getValue().toMenu()+"\n";
			}
			else {
				txt2+=e.getValue().toMenu()+"\n";
			}
			
			List<Component> co=e.getValue().getComponenets();
			for(int i=0;i<co.size();i++) {
				if(co.get(i)!=null && j <= 2) {
					txt1+=co.get(i).toMenu()+"\n";
				}
				if(co.get(i)!=null && j > 2) {
					txt2+=co.get(i).toMenu()+"\n";
				}
			}
			if(j==2) {
				theMenu1.setText(txt1);
			}
			j++;
		}
		theMenu2.setText(txt2);
	}
}
