package application;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Component;
import model.Restaurant;

public class AddComponentController extends ControllerBase {

	@FXML
	private Button back, save;
	
	@FXML
	private Label alertFill;
	
	@FXML
	private TextField idComponent, componentName, price;
		
	@FXML
	private CheckBox gluten, lactose;
	
	@FXML
	private Label saveSuccesss, alertNumber;
	
	@FXML
	private AnchorPane addComponentScreen;
	
	ControllerBase cs = new ControllerBase();
	
	public void addComponentSceneToMainScene(ActionEvent event) throws Exception{
		cs.backToManagerMainWindow(addComponentScreen);
	}
		
	public void saveComponent(ActionEvent event) throws Exception{
		if(componentName.getText().isEmpty() || price.getText().isEmpty()) {
			alertFill.setText("You need to fill in all the fileds!");
			return;
		}
		else{
			double componentPrice =Double.parseDouble(price.getText());
			Component newComponent = new Component(componentName.getText(), lactose.isSelected() ? true : false,
					gluten.isSelected() ? true : false, componentPrice);
			Restaurant.getInstance().addComponent(newComponent);
		}
		cs.changeScreen("/View/ManagerMainWindow.fxml", addComponentScreen);
	}
	
	
	private boolean allDigits(String s) {
		for (int i=0;i<s.length();i++) {
			if(s.charAt(i)<48 ||s.charAt(i)>57) 
				return false;
		}	
		return true;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Invalid input alert
		price.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue.length()>0) {
				char newChar=newValue.charAt(newValue.toString().length()-1);
				if(alertNumber!=null&&(newChar<48 ||newChar>57) && newChar != 46) {

					alertNumber.setText("You have to fill only number");
				}
				if(allDigits(newValue))
					alertNumber.setText("");
			}
		});
	}
	
	

}