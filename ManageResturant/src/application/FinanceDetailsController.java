package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import model.Dish;
import model.Restaurant;

public class FinanceDetailsController extends ControllerBase{

	@FXML
	private Button back;

	@FXML
	private ListView<Dish> dishesList;

	@FXML
	private Label revenue;

	@FXML
	private AnchorPane revenueFromExpressDelivery;

	public void revenueFromExpressDeliverySceneToMainScene(ActionEvent event) throws Exception{
		ControllerBase cs = new ControllerBase();
		cs.backToManagerMainWindow(revenueFromExpressDelivery);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
			double revenueFromExpressDelivery = Restaurant.getInstance().revenueFromExpressDeliveries();
			revenue.setText(revenueFromExpressDelivery+"");
			List<Dish> profitRelation = Restaurant.getInstance().getProfitRelation().stream().collect(Collectors.toList());
			ObservableList<Dish> pr = FXCollections.observableList(profitRelation);
			dishesList.setItems(pr);
	

	}
}
