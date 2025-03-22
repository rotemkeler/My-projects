/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.application.Application
 *  javafx.fxml.FXMLLoader
 *  javafx.scene.Parent
 *  javafx.scene.Scene
 *  javafx.stage.Stage
 */
package application;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Restaurant;

public class MainController
extends Application {
    public static Restaurant rest;
    public static Stage stage;

    public void setRest(Restaurant rest) {
        MainController.rest = rest;
    }

    public static void restSerialize() {
        rest.serialize();
    }

    public void start(Stage primaryStage) throws Exception {
        try {
            stage = primaryStage;
            rest = Restaurant.deserialize();
            if (rest == null) {
                rest = Restaurant.getInstance();
            }
            Parent root = (Parent)FXMLLoader.load((URL)((Object)((Object)this)).getClass().getResource("/View/Login.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add((Object)((Object)((Object)this)).getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MainController.launch((String[])args);
    }
}
