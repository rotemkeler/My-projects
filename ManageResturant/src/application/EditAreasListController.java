package application;


import java.net.URL; 
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.AnchorPane;
import model.DeliveryArea;
import model.Restaurant;
import utils.Neighberhood;

public class EditAreasListController extends ControllerBase{

	@FXML
	public AnchorPane editArea;
	
	@FXML
	public Button add, back;
	
	@FXML
	public javafx.scene.control.TextField name, time;
	
	@FXML
	public ListView<Neighberhood> allNeighberhoods, neighberhoods;
	
	ControllerBase cs = new ControllerBase();
	
	ObservableList<Neighberhood> list = FXCollections.observableArrayList(Neighberhood.values());
	
	DeliveryArea area = Restaurant.getInstance().getAreas().get(AreasListController.areaToEdit.getId());
	
	public void backToAreasList (ActionEvent event) throws Exception{
		cs.changeScreen("/View/AreasList.fxml", editArea);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		allNeighberhoods.setItems(list);
		allNeighberhoods.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		name.setText(area.getAreaName());
		neighberhoods.getItems().addAll(area.getNeighberhoods());
		String t = String.valueOf(area.getDeliverTime());
		time.setText(t);
	}
	
	public void addNeighberhood (ActionEvent event) throws Exception{
		Neighberhood chooseNeighberhood = allNeighberhoods.getSelectionModel().getSelectedItem();
		area.addNeighberhood(chooseNeighberhood);
		neighberhoods.getItems().add(chooseNeighberhood);
	}
	
	public void saveDetailsChange (ActionEvent event) throws Exception{
		area.setAreaName(name.getText());
		Restaurant.getInstance().getAreas().put(area.getId(), area);
		cs.changeScreen("/View/AreasList.fxml", editArea);
	}
	
	
	
	
}
