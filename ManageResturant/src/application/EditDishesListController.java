package application;

import java.net.URL; 
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Component;
import model.Dish;
import model.Restaurant;
import utils.DishType;

public class EditDishesListController extends ControllerBase{

	@FXML
	public AnchorPane editDish;
	
	@FXML
	public TextField name, timeToMake;
	
	@FXML
	public ComboBox<DishType> dishTypeList;
	
	@FXML
	public ListView<Component> compList;
	
	@FXML
	public ListView<Component> comps;
	
	ControllerBase cs = new ControllerBase();
	
	ObservableList<DishType> list = FXCollections.observableArrayList(DishType.values());
	
	Dish d  = Restaurant.getInstance().getDishes().get(DishesListController.dishFromListToEdit.getId());
	
	public void backToDishesList(ActionEvent event) throws Exception{
		cs.changeScreen("/View/DishesList.fxml", editDish);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dishTypeList.setItems(list);
		name.setText(d.getDishName());
		dishTypeList.promptTextProperty().set(d.getType().toString());
		for(Component comp : d.getComponenets()) {
			if(comp != null) {
				comps.getItems().add(comp);
			}
		}
		compList.getItems().addAll(Restaurant.getInstance().getComponenets().values());
		String time = String.valueOf(d.getTimeToMake());
		timeToMake.setText(time);
	}
	
	public void addComponent (ActionEvent event) throws Exception{
		Component co = compList.getSelectionModel().getSelectedItem();
		d.addComponent(co);
		comps.getItems().add(co);
	}
	
	public void saveDetailsChange (ActionEvent event) throws Exception{
		Integer t = Integer.valueOf(timeToMake.getText());
		d.setDishName(name.getText());
		d.setType(dishTypeList.getValue() == null ? d.getType() : dishTypeList.getValue());
		d.setTimeToMake(t);
		Restaurant.getInstance().getDishes().put(d.getId(), d);
		cs.changeScreen("/View/DishesList.fxml", editDish);
	}
}		

