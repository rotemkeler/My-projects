package application;

import java.net.URL; 
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Delivery;
import model.DeliveryArea;
import model.DeliveryPerson;
import model.ExpressDelivery;
import model.Order;
import model.Restaurant;

public class AddExpressDeliveryController extends ControllerBase{

	@FXML
	private Button back;
	
	@FXML
	private Label alertFill, alertNumber;
	
	@FXML
	private CheckBox isDelivered;
	
	@FXML
	private ComboBox<DeliveryPerson> deliveryPerson;
	
	@FXML
	private ComboBox<DeliveryArea> area;
	
	@FXML
	private ComboBox<Order> ordersList;
	
	@FXML
	private DatePicker deliveredDate;
	
	@FXML
	private TextField postage;
	
	@FXML
	private AnchorPane addExpressDeliveryScene;
	
	ControllerBase cs = new ControllerBase();
	
	public void addExpressDeliverySceneToMainScene(ActionEvent event) throws Exception{
		cs.backToManagerMainWindow(addExpressDeliveryScene);
	}
	
	public void saveExpressDelivery(ActionEvent event) throws Exception{
		if(deliveryPerson.getSelectionModel().isEmpty() || area.getSelectionModel().isEmpty() || deliveredDate.getValue() == null || ordersList.getSelectionModel().isEmpty() ||
				postage.getText().isEmpty()) {
			alertFill.setText("You must fill in all the fields");
			return;
		}
		else{
			double postage1 = Double.parseDouble(postage.getText());
			Delivery newExpressDelivery = new ExpressDelivery(deliveryPerson.getValue(), area.getValue(), isDelivered.isSelected() ? true : false, ordersList.getValue(),
					postage1, deliveredDate.getValue()); 
			Restaurant.getInstance().addDelivery(newExpressDelivery);
			cs.changeScreen("/View/ManagerMainWindow.fxml", addExpressDeliveryScene);
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
		for(DeliveryPerson dp : Restaurant.getInstance().getDeliveryPersons().values()) {
			deliveryPerson.getItems().add(dp);
		}
		for(DeliveryArea da : Restaurant.getInstance().getAreas().values()) {
			area.getItems().add(da);
		}
		for(Order o : Restaurant.getInstance().getOrders().values()) {
			ordersList.getItems().add(o);
		}
		//Invalid input alert
		postage.textProperty().addListener((observable, oldValue, newValue) -> {
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
