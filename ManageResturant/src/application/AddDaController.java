package application;

import java.net.URL; 
import java.util.HashSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.DeliveryArea;
import model.Restaurant;
import utils.Neighberhood;

public class AddDaController extends ControllerBase{
	
	@FXML
	public Button back; 
	
	@FXML
	public TextField areaName, time; 
	
	@FXML
	private Label alertFill, alertNumber;;
	
	@FXML
	private AnchorPane addDaScene;

	@FXML
	public ListView<Neighberhood> neighberhoodList; 
	
	
	ObservableList<Neighberhood> list = FXCollections.observableArrayList(Neighberhood.values());
	
	ControllerBase cs = new ControllerBase();

	public void addDaSceneToMainScene(ActionEvent event) throws Exception{
		cs.backToManagerMainWindow(addDaScene);
	}
	
	public void saveDeliveryArea(ActionEvent event) throws Exception{
		if(areaName.getText().isEmpty() || neighberhoodList.getSelectionModel().isEmpty() || time.getText().isEmpty()) {
			alertFill.setText("You must fill in all the fields");
			return;
		}
		else{
			int deliveryTime =Integer.parseInt(time.getText());
			HashSet<Neighberhood> neighberhoods = new HashSet<Neighberhood>(neighberhoodList.getSelectionModel().getSelectedItems());
			DeliveryArea newDeliveryArea = new DeliveryArea(areaName.getText(),neighberhoods ,deliveryTime);
			Restaurant.getInstance().addDeliveryArea(newDeliveryArea);
			cs.changeScreen("/View/ManagerMainWindow.fxml", addDaScene);
		}
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
		neighberhoodList.setItems(list);
		neighberhoodList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		//Invalid input alert
		time.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue.length()>0) {
				char newChar=newValue.charAt(newValue.toString().length()-1);
				if(alertNumber!=null&&(newChar<48 ||newChar>57)) {
					alertNumber.setText("You have to fill only number");
				}
				if(allDigits(newValue))
					alertNumber.setText("");
			}
		});
	}
}
