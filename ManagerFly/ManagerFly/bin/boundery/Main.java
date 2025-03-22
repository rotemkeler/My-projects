/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.application.Application
 *  javafx.event.EventHandler
 *  javafx.fxml.FXMLLoader
 *  javafx.scene.Parent
 *  javafx.scene.Scene
 *  javafx.scene.control.Alert
 *  javafx.scene.control.Alert$AlertType
 *  javafx.scene.control.ButtonType
 *  javafx.scene.input.KeyCode
 *  javafx.scene.input.KeyEvent
 *  javafx.stage.Stage
 *  javafx.stage.StageStyle
 */
package boundery;

import java.net.URL;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main
extends Application {
    static Stage stg;

    public void start(Stage primaryStage) throws Exception {
        stg = primaryStage;
        Parent root = (Parent)FXMLLoader.load((URL)((Object)((Object)this)).getClass().getResource("LoginPage.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add((Object)((Object)((Object)this)).getClass().getResource("app.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Manager-Fly");
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            Main.closeApp();
        });
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, (EventHandler)new EventHandler<KeyEvent>(){

            public void handle(KeyEvent t) {
                if (t.getCode() == KeyCode.ESCAPE) {
                    Main.closeApp();
                }
            }
        });
    }

    public static void main(String[] args) {
        Main.launch((String[])args);
    }

    public static void closeApp() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("SYSTEM MESSAGE");
        alert.setHeaderText("EXIT CONFIRMATION");
        alert.setContentText("Are you sure you want to close the app?");
        Optional result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            stg.close();
        }
    }
}
