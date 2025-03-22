/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.event.ActionEvent
 *  javafx.fxml.FXML
 *  javafx.fxml.FXMLLoader
 *  javafx.scene.Node
 *  javafx.scene.Parent
 *  javafx.scene.layout.AnchorPane
 *  javafx.scene.layout.BorderPane
 *  javafx.scene.layout.Pane
 *  javafx.stage.Stage
 */
package boundery;

import boundery.Main;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.LoadPane;

public class MainMenuForEmloyeeManager {
    @FXML
    public BorderPane pannelRoot;
    @FXML
    private AnchorPane MainPane;

    @FXML
    public void initialize() {
        try {
            this.pannelRoot.setCenter(LoadPane.LoadFXML(this.getClass(), "HomePage.fxml"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void closeApp(ActionEvent event) {
        Main.closeApp();
    }

    @FXML
    void goAirPorts(ActionEvent event) throws IOException {
        Pane root = (Pane)LoadPane.LoadFXML(this.getClass(), "/boundery/AirPortsFrm.fxml");
        this.pannelRoot.setCenter((Node)root);
    }

    @FXML
    void goFlights(ActionEvent event) throws IOException {
        Pane root = (Pane)LoadPane.LoadFXML(this.getClass(), "/boundery/FlightManagmentFrm.fxml");
        this.pannelRoot.setCenter((Node)root);
    }

    @FXML
    void goHome(ActionEvent event) throws IOException {
        Pane root = (Pane)LoadPane.LoadFXML(this.getClass(), "/boundery/HomePage.fxml");
        this.pannelRoot.setCenter((Node)root);
    }

    @FXML
    void goPlanes(ActionEvent event) throws IOException {
        Pane root = (Pane)LoadPane.LoadFXML(this.getClass(), "/boundery/AirPlanesFrm.fxml");
        this.pannelRoot.setCenter((Node)root);
    }

    @FXML
    void goReports(ActionEvent event) throws IOException {
        Pane root = (Pane)LoadPane.LoadFXML(this.getClass(), "/boundery/ReprotsFrm.fxml");
        this.pannelRoot.setCenter((Node)root);
    }

    @FXML
    void loadAddFlight(ActionEvent event) throws IOException {
        Pane root = (Pane)LoadPane.LoadFXML(this.getClass(), "/boundery/AddFlightFrm.fxml");
        this.pannelRoot.setCenter((Node)root);
    }

    @FXML
    void goEmployees(ActionEvent event) throws IOException {
        Pane root = (Pane)LoadPane.LoadFXML(this.getClass(), "/boundery/EmployeesManagment.fxml");
        this.pannelRoot.setCenter((Node)root);
    }

    @FXML
    void goSetCrueToFlight(ActionEvent event) throws IOException {
        Pane root = (Pane)LoadPane.LoadFXML(this.getClass(), "/boundery/FlightCrueManagmentFrm.fxml");
        this.pannelRoot.setCenter((Node)root);
    }

    @FXML
    void goShifts(ActionEvent event) throws IOException {
        Pane root = (Pane)LoadPane.LoadFXML(this.getClass(), "/boundery/ShiftsManagemantFrm.fxml");
        this.pannelRoot.setCenter((Node)root);
    }

    @FXML
    void goPercReport(ActionEvent event) throws IOException {
        Pane root = (Pane)LoadPane.LoadFXML(this.getClass(), "/boundery/FligtByDestReprotFrm.fxml");
        this.pannelRoot.setCenter((Node)root);
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        Parent newRoot = (Parent)FXMLLoader.load((URL)this.getClass().getResource("/boundery/LoginPage.fxml"));
        Stage primaryStage = (Stage)this.MainPane.getScene().getWindow();
        primaryStage.getScene().setRoot(newRoot);
        primaryStage.show();
    }
}
