package application;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Restaurant;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;


public class MainController extends Application {
	public static Restaurant rest;
	public static Stage stage;
	
	public void setRest(Restaurant rest) {
		MainController.rest = rest;
	}
	
	public static void restSerialize() {
		rest.serialize();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			stage = primaryStage;
			rest = Restaurant.deserialize();
			
			if(rest == null) {
				rest = Restaurant.getInstance();
			}
			Parent root = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
