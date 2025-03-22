package application;

import java.net.URL; 
import java.util.LinkedList;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import model.Component;
import model.Restaurant;

public class PopularComponentsController extends ControllerBase{

	
	@FXML
	private ListView<Component> thePopular;
	
	@FXML
	private AnchorPane thePopularComponents;
	
	public void popularComponentsSceneToMainScene(ActionEvent event) throws Exception{
		ControllerBase cs = new ControllerBase();
		cs.backToManagerMainWindow(thePopularComponents);
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		LinkedList<Component> poplarComponents = Restaurant.getInstance().getPopularComponents();
		poplarComponents.removeIf(Objects::isNull);
		ObservableList<Component> compList = FXCollections.observableList(poplarComponents);
		thePopular.setItems(compList);
	}
}
