package application;

import java.net.URL; 
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import model.Restaurant;
import utils.Expertise;

public class GetCooksByExpertiseController extends ControllerBase{

	@FXML
	public Button back;

	@FXML
	public ListView<String> cooksList;

	@FXML
	public AnchorPane cooksByExpertise;

	@FXML
	public ComboBox<Expertise> expertiseList;

	ObservableList<Expertise> list = FXCollections.observableArrayList(Expertise.values());

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		expertiseList.setItems( FXCollections.observableArrayList(list));
		
	} 

	public void cooksByExpertiseSceneToMainScene(ActionEvent event) throws Exception{
		ControllerBase cs = new ControllerBase();
		cs.backToManagerMainWindow(cooksByExpertise);
	}

	public void showCooksList (ActionEvent event) throws Exception {
		if( expertiseList.getSelectionModel().getSelectedItem() instanceof Expertise)
		{
			Expertise e =(Expertise) expertiseList.getSelectionModel().getSelectedItem();
			cooksList.getItems().clear();
			cooksList.getItems().add(Restaurant.getInstance().GetCooksByExpertise(e).toString());

		}else {
			Expertise e=Expertise.valueOf(expertiseList.getSelectionModel().getSelectedItem()+"");
			cooksList.getItems().clear();
			cooksList.getItems().add(Restaurant.getInstance().GetCooksByExpertise(e).toString());
		}

	}
}
