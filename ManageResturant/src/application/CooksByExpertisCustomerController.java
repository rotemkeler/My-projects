package application;

import java.net.URL; 
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Restaurant;
import utils.Expertise;

public class CooksByExpertisCustomerController extends ControllerBase{

	@FXML
	private AnchorPane byExpertise;
	
	@FXML
	private ImageView customerPhoto;
	
	@FXML
	private ComboBox<Expertise> expertiseList;
	
	@FXML
	private ListView<String> cooksWithThisExpertise;
	
	ControllerBase cs = new ControllerBase();
	
	ObservableList<Expertise> list = FXCollections.observableArrayList(Expertise.values());
	
	public void toMainCustomerScene(ActionEvent event) throws Exception{
		cs.backToCustomerMainWindow(byExpertise);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		super.initialize(arg0, arg1);
		expertiseList.setItems(list);
	} 
		
	public void showCooksList (ActionEvent event) throws Exception {
		if( expertiseList.getSelectionModel().getSelectedItem() instanceof Expertise){
			Expertise e =(Expertise) expertiseList.getSelectionModel().getSelectedItem();
			cooksWithThisExpertise.getItems().clear();
			cooksWithThisExpertise.getItems().add(Restaurant.getInstance().GetCooksByExpertise(e).toString());
	
		}
		else {
			Expertise e=Expertise.valueOf(expertiseList.getSelectionModel().getSelectedItem()+"");
			cooksWithThisExpertise.getItems().add(Restaurant.getInstance().GetCooksByExpertise(e).toString());
			
		}
	}
}
