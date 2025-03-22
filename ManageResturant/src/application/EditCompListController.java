package application;

import java.net.URL; 
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Component;
import model.Restaurant;

public class EditCompListController extends ControllerBase{

	@FXML
	public AnchorPane editComp;
	
	@FXML
	public Button back;
	
	@FXML
	public TextField name, price;
	
	@FXML
	public CheckBox gluten, lactose;
	
	ControllerBase cs = new ControllerBase();
	
	Component comp =Restaurant.getInstance().getComponenets().get(ComponentsListController.compToEdit.getId());
	
	public void backToComponentsList(ActionEvent event) throws Exception{
		cs.changeScreen("/View/ComponentsList.fxml", editComp);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		name.setText(comp.getComponentName());
		if(comp.isHasGluten()) {
			gluten.setSelected(true);
		}
		if(comp.isHasLactose()) {
			gluten.setSelected(true);
		}
		String p = String.valueOf(comp.getPrice());
		price.setText(p);
	}
	
	public void saveDetailsChange (ActionEvent event) throws Exception{
		Double p = Double.valueOf(price.getText());
		comp.setComponentName(name.getText());
		comp.setHasLactose(lactose.isSelected() ? true : false);
		comp.setHasGluten(gluten.isSelected() ? true : false);
		comp.setPrice(p);
		Restaurant.getInstance().getComponenets().put(comp.getId(), comp);
		cs.changeScreen("/View/ComponentsList.fxml", editComp);
	}
	
}
