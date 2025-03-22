/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.collections.FXCollections
 *  javafx.collections.ObservableList
 *  javafx.fxml.FXML
 *  javafx.fxml.FXMLLoader
 *  javafx.scene.Parent
 *  javafx.scene.Scene
 *  javafx.scene.control.Alert
 *  javafx.scene.control.Button
 *  javafx.scene.control.ComboBox
 *  javafx.scene.control.DateCell
 *  javafx.scene.control.DatePicker
 *  javafx.scene.control.TextField
 *  javafx.stage.Stage
 *  javafx.stage.StageStyle
 */
package boundery;

import boundery.AirplanesFrm;
import boundery.AirportsFrm;
import control.FlightsLogic;
import control.Getters;
import entity.AirPlane;
import entity.AirPort;
import exceptions.InvalidInputException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Collection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.Alerts;

public class AddFlightFrm {
    @FXML
    private TextField flightNum;
    @FXML
    private DatePicker departureDate;
    @FXML
    private DatePicker landingDate;
    @FXML
    private ComboBox<Integer> depHour;
    @FXML
    private ComboBox<Integer> depMinute;
    @FXML
    private ComboBox<Integer> arrHour;
    @FXML
    private ComboBox<Integer> arrMinute;
    @FXML
    private ComboBox<AirPort> depAirports;
    @FXML
    private ComboBox<AirPort> arrAirports;
    @FXML
    private ComboBox<AirPlane> airPlanes;
    @FXML
    private Button addFlight;
    @FXML
    private Button redAddPlane;
    @FXML
    private Button redAddPort;
    @FXML
    private Alert a;
    public static Stage primaryStagePlane;
    public static Stage primaryStagePort;
    private FlightsLogic flightsInstance;

    @FXML
    public void initialize() {
        this.init();
    }

    private void init() {
        this.initFeilds();
        this.initAirports();
        this.initAirplanes();
    }

    @FXML
    private void initAirplanes() {
        ObservableList airplanes = FXCollections.observableArrayList(Getters.getInstance().getAirplanes());
        this.airPlanes.setItems(FXCollections.observableArrayList((Collection)airplanes));
    }

    @FXML
    private void initFeilds() {
        ArrayList<Integer> hoursList = new ArrayList<Integer>();
        ArrayList<Integer> minuteList = new ArrayList<Integer>();
        int i = 0;
        while (i < 24) {
            hoursList.add(i);
            ++i;
        }
        this.depHour.setItems(FXCollections.observableArrayList(hoursList));
        this.arrHour.setItems(FXCollections.observableArrayList(hoursList));
        i = 0;
        while (i < 60) {
            minuteList.add(i);
            ++i;
        }
        this.depMinute.setItems(FXCollections.observableArrayList(minuteList));
        this.arrMinute.setItems(FXCollections.observableArrayList(minuteList));
        this.departureDate.setDayCellFactory(picker -> new DateCell(){

            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate twoMnthFromToday = LocalDate.now().plusMonths(2L);
                this.setDisable(empty || date.compareTo(twoMnthFromToday) < 0);
            }
        });
        this.landingDate.setDayCellFactory(picker -> new DateCell(){

            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate twoMnthFromToday = LocalDate.now().plusMonths(2L);
                this.setDisable(empty || date.compareTo(twoMnthFromToday) < 0);
            }
        });
    }

    @FXML
    private void initAirports() {
        ObservableList airports = FXCollections.observableArrayList(Getters.getInstance().getAirports());
        this.depAirports.setItems(FXCollections.observableArrayList((Collection)airports));
        this.arrAirports.setItems(FXCollections.observableArrayList((Collection)airports));
    }

    @FXML
    private void redirectAddPlane() throws IOException {
        primaryStagePlane = new Stage();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("AirPlanesFrm.fxml"));
        Parent root = (Parent)loader.load();
        AirplanesFrm controller = (AirplanesFrm)loader.getController();
        controller.inEditAddFlight();
        Scene scene = new Scene(root);
        scene.getStylesheets().add((Object)this.getClass().getResource("app.css").toExternalForm());
        primaryStagePlane.setScene(scene);
        primaryStagePlane.setTitle("Add New Plane");
        primaryStagePlane.setResizable(false);
        primaryStagePlane.initStyle(StageStyle.DECORATED);
        primaryStagePlane.setWidth(900.0);
        primaryStagePlane.setHeight(650.0);
        primaryStagePlane.show();
    }

    static void closeWindow() {
        if (primaryStagePlane != null) {
            primaryStagePlane.close();
        }
        if (primaryStagePort != null) {
            primaryStagePort.close();
        }
    }

    @FXML
    private void redirectAddAirport() throws IOException {
        primaryStagePort = new Stage();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("AirPortsFrm.fxml"));
        Parent root = (Parent)loader.load();
        AirportsFrm controller = (AirportsFrm)loader.getController();
        controller.inEditAddFlight();
        Scene scene = new Scene(root);
        scene.getStylesheets().add((Object)this.getClass().getResource("app.css").toExternalForm());
        primaryStagePort.setScene(scene);
        primaryStagePort.setTitle("Add New Airport");
        primaryStagePort.setResizable(false);
        primaryStagePort.initStyle(StageStyle.DECORATED);
        primaryStagePort.setWidth(900.0);
        primaryStagePort.setHeight(650.0);
        primaryStagePort.show();
    }

    @FXML
    private void addNewFlight() {
        try {
            this.flightsInstance = FlightsLogic.getInstance();
            String flightNumber = this.flightNum.getText();
            if (flightNumber.isEmpty()) {
                throw new InvalidInputException("Please fill Flight Number");
            }
            if (this.departureDate.getValue() == null) {
                throw new InvalidInputException("Please select Depature Date");
            }
            if (this.landingDate.getValue() == null) {
                throw new InvalidInputException("Please select Landing Date");
            }
            if (((LocalDate)this.departureDate.getValue()).isAfter((ChronoLocalDate)this.landingDate.getValue())) {
                throw new InvalidInputException("landing date should be after the departure date");
            }
            if (this.depHour.getValue() == null) {
                throw new InvalidInputException("Please select Depature Hour");
            }
            if (this.depMinute.getValue() == null) {
                throw new InvalidInputException("Please select Depature Minute");
            }
            if (this.arrHour.getValue() == null) {
                throw new InvalidInputException("Please select Landing Hour");
            }
            if (this.arrMinute.getValue() == null) {
                throw new InvalidInputException("Please select Landing Minute");
            }
            if (this.depAirports.getValue() == null) {
                throw new InvalidInputException("Please select Departue Airport");
            }
            if (this.arrAirports.getValue() == null) {
                throw new InvalidInputException("Please select Landing Airpoert");
            }
            if (this.airPlanes.getSelectionModel().getSelectedItem() == null) {
                throw new InvalidInputException("Please select an Airplane");
            }
            LocalDateTime depatureDateTime = LocalDateTime.of(((LocalDate)this.departureDate.getValue()).getYear(), ((LocalDate)this.departureDate.getValue()).getMonth(), ((LocalDate)this.departureDate.getValue()).getDayOfMonth(), (int)((Integer)this.depHour.getValue()), (int)((Integer)this.depMinute.getValue()));
            LocalDateTime landingDateTime = LocalDateTime.of(((LocalDate)this.landingDate.getValue()).getYear(), ((LocalDate)this.landingDate.getValue()).getMonth(), ((LocalDate)this.landingDate.getValue()).getDayOfMonth(), (int)((Integer)this.arrHour.getValue()), (int)((Integer)this.arrMinute.getValue()));
            if (((AirPort)this.depAirports.getValue()).equals(this.arrAirports.getValue())) {
                throw new InvalidInputException("Departure and Destination airports cannot be the same");
            }
            if (!this.flightsInstance.addFlight(flightNumber, depatureDateTime, landingDateTime, (AirPort)this.depAirports.getValue(), (AirPort)this.arrAirports.getValue(), (AirPlane)this.airPlanes.getSelectionModel().getSelectedItem(), null, null, "on time")) {
                throw new InvalidInputException("something went wrong while adding a new flight. Try a different ID");
            }
            this.a = Alerts.infoAlert("Added Flight Successfully!");
            this.a.show();
        }
        catch (InvalidInputException ipe) {
            this.a = Alerts.errorAlert(ipe.getMessage());
            this.a.show();
        }
        catch (Exception exc) {
            this.a = Alerts.errorAlert("an error has accured please try again");
            this.a.show();
        }
    }
}
