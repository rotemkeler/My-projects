/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.collections.FXCollections
 *  javafx.collections.ObservableList
 *  javafx.event.ActionEvent
 *  javafx.fxml.FXML
 *  javafx.scene.control.Alert
 *  javafx.scene.control.Alert$AlertType
 *  javafx.scene.control.Button
 *  javafx.scene.control.ComboBox
 *  javafx.scene.control.Label
 *  javafx.scene.control.ListView
 *  javafx.scene.control.TextField
 *  javafx.scene.control.Tooltip
 *  javafx.scene.input.KeyEvent
 *  javafx.scene.layout.AnchorPane
 */
package boundery;

import control.AssignLogic;
import control.FlightsLogic;
import control.Getters;
import entity.AirAttendant;
import entity.AirPlane;
import entity.Flight;
import entity.Pilot;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class FlightCrueManagmentFrm {
    @FXML
    private AnchorPane ManagmentPane;
    @FXML
    private TextField IDFld;
    @FXML
    private ComboBox<Flight> FlightCmbBx;
    @FXML
    private ComboBox<Pilot> pilotCmbx;
    @FXML
    private ComboBox<Pilot> coPilotCmbx;
    @FXML
    private ListView<AirAttendant> airAttendantList;
    @FXML
    private ListView<AirAttendant> currentAirAttendantList;
    @FXML
    private Button nextBtn;
    @FXML
    private Button pervBtn;
    @FXML
    private Button updateBtn;
    @FXML
    private Label attenNumberLbl;
    private Tooltip pervtooltip;
    private Tooltip nexttooltip;
    private Tooltip searchtooltip;
    private ObservableList<Flight> flightsList;
    private HashMap<String, Flight> flightsById;
    private Flight currentFlight;
    private static Flight curreStatucntFlight;
    private int currFlightIndex;
    private ArrayList<Flight> flightArr;
    private Alert a = new Alert(Alert.AlertType.NONE);
    private FlightsLogic flightsInstance = FlightsLogic.getInstance();

    public static Flight getCurrentFlight() {
        return curreStatucntFlight;
    }

    public static void setCurrentFlight(Flight currentFlight) {
        curreStatucntFlight = currentFlight;
    }

    @FXML
    public void initialize() {
        this.init();
    }

    private void init() {
        this.initFlights();
        this.initPilots();
        this.initAirAttendant();
        this.flightsById = new HashMap();
        this.pervtooltip = new Tooltip();
        this.nexttooltip = new Tooltip();
        this.searchtooltip = new Tooltip();
        this.pervtooltip.setText("previous flight");
        this.nexttooltip.setText("next flight");
        this.searchtooltip.setText("search");
        this.pervBtn.setTooltip(this.pervtooltip);
        this.nextBtn.setTooltip(this.nexttooltip);
        this.IDFld.setTooltip(this.searchtooltip);
        this.currFlightIndex = 0;
        if (this.flightArr != null) {
            this.flightsList = FXCollections.observableArrayList(this.flightArr);
            this.FlightCmbBx.setItems(this.flightsList);
            this.FlightCmbBx.setValue((Object)this.flightArr.get(0));
            this.flightsById = new HashMap();
            for (Flight f : this.flightArr) {
                this.flightsById.put(f.getFlightNum(), f);
            }
            this.loadFlightByCmb(new ActionEvent());
        }
    }

    private void initFlights() {
        this.flightArr = Getters.getInstance().getFlights();
    }

    public void initPilots() {
        ObservableList pilots = FXCollections.observableArrayList(Getters.getInstance().getPilots());
        this.pilotCmbx.setItems(FXCollections.observableArrayList((Collection)pilots));
        this.coPilotCmbx.setItems(FXCollections.observableArrayList((Collection)pilots));
    }

    public void initAirAttendant() {
        ObservableList airAttendants = FXCollections.observableArrayList(Getters.getInstance().getAirAttendants());
        this.airAttendantList.setItems(FXCollections.observableArrayList((Collection)airAttendants));
    }

    @FXML
    void LoadFlight(KeyEvent event) {
        String s = this.IDFld.getText();
        if (s != null && !s.isEmpty()) {
            Flight f = null;
            f = this.flightsById.get(s);
            if (f != null) {
                this.currentFlight = (Flight)this.FlightCmbBx.getValue();
                this.currFlightIndex = this.flightArr.indexOf(f);
                this.FlightCmbBx.setValue((Object)f);
                this.setFields();
            }
        }
    }

    @FXML
    void loadFlightByCmb(ActionEvent event) {
        if (this.FlightCmbBx.getValue() != null) {
            this.currentFlight = (Flight)this.FlightCmbBx.getValue();
            this.currFlightIndex = this.flightArr.indexOf(this.currentFlight);
            this.IDFld.setText(String.valueOf(this.currentFlight.getFlightNum()));
            this.setFields();
        }
    }

    @FXML
    void loadNextFlight(ActionEvent event) {
        if (this.currFlightIndex + 1 < this.flightArr.size()) {
            ++this.currFlightIndex;
            this.FlightCmbBx.setValue((Object)this.flightArr.get(this.currFlightIndex));
            this.loadFlightByCmb(new ActionEvent());
        } else {
            this.a.setAlertType(Alert.AlertType.INFORMATION);
            this.a.setHeaderText("MESSAGE");
            this.a.setTitle("SYSTEM MESSAGE");
            this.a.setContentText("Last in the list!");
            this.a.show();
        }
    }

    @FXML
    void loadPervFlight(ActionEvent event) {
        if (this.currFlightIndex - 1 >= 0) {
            --this.currFlightIndex;
            this.FlightCmbBx.setValue((Object)this.flightArr.get(this.currFlightIndex));
            this.loadFlightByCmb(new ActionEvent());
        } else {
            this.a.setAlertType(Alert.AlertType.INFORMATION);
            this.a.setHeaderText("MESSAGE");
            this.a.setTitle("SYSTEM MESSAGE");
            this.a.setContentText("First in the list!");
            this.a.show();
        }
    }

    private void setFields() {
        String pilotID = this.currentFlight.getCheifPilotID();
        String coPilotID = this.currentFlight.getCoPilotID();
        ArrayList<Pilot> pilots = Getters.getInstance().getPilots();
        if (pilotID != null) {
            Pilot pilot1 = (Pilot)pilots.stream().filter(p -> p.equals(new Pilot(pilotID))).collect(Collectors.toList()).get(0);
            this.pilotCmbx.setValue((Object)pilot1);
        } else {
            this.pilotCmbx.setValue(null);
        }
        if (coPilotID != null) {
            Pilot pilot2 = (Pilot)pilots.stream().filter(p -> p.equals(new Pilot(coPilotID))).collect(Collectors.toList()).get(0);
            this.coPilotCmbx.setValue((Object)pilot2);
        } else {
            this.coPilotCmbx.setValue(null);
        }
        ObservableList currentAirAttendants = FXCollections.observableArrayList(Getters.getInstance().getAirAttendantsByFlight(this.currentFlight));
        this.currentAirAttendantList.setItems(FXCollections.observableArrayList((Collection)currentAirAttendants));
        ArrayList<AirPlane> airplanes = Getters.getInstance().getAirplanes();
        int num = ((AirPlane)airplanes.stream().filter(a -> a.equals(this.currentFlight.getAirPlaneTailNum())).collect(Collectors.toList()).get(0)).getAttendantsNum();
        this.attenNumberLbl.setText(Integer.toString(num));
    }

    @FXML
    private void addAirAttendant() {
        AirAttendant selectedAirAttendant = (AirAttendant)this.airAttendantList.getSelectionModel().getSelectedItem();
        if (selectedAirAttendant != null) {
            ObservableList currentAirAttendants = this.currentAirAttendantList.getItems();
            currentAirAttendants.add((Object)selectedAirAttendant);
            this.currentAirAttendantList.setItems(currentAirAttendants);
        }
    }

    @FXML
    private void removeAirAttendant() {
        AirAttendant selectedAirAttendant = (AirAttendant)this.currentAirAttendantList.getSelectionModel().getSelectedItem();
        if (selectedAirAttendant != null) {
            ObservableList currentAirAttendants = this.currentAirAttendantList.getItems();
            currentAirAttendants.remove((Object)selectedAirAttendant);
            this.currentAirAttendantList.setItems(currentAirAttendants);
        }
    }

    @FXML
    private void saveChanges() {
        Pilot pilot1 = (Pilot)this.pilotCmbx.getSelectionModel().getSelectedItem();
        Pilot pilot2 = (Pilot)this.coPilotCmbx.getSelectionModel().getSelectedItem();
        if (pilot1 != null) {
            AssignLogic.getInstance().updateMainPilot(pilot1.getID(), this.currentFlight.getFlightNum());
            this.currentFlight.setCheifPilotID(pilot1.getID());
        }
        if (pilot2 != null) {
            AssignLogic.getInstance().updateSecondaryPilot(pilot2.getID(), this.currentFlight.getFlightNum());
            this.currentFlight.setCheifPilotID(pilot2.getID());
        }
        if (this.currentAirAttendantList.getItems() != null) {
            AssignLogic.getInstance().deleteAirAttendantsFromFlight(this.currentFlight);
            ObservableList currentAirAttendants = this.currentAirAttendantList.getItems();
            for (AirAttendant aa : currentAirAttendants) {
                AssignLogic.getInstance().addAirAttendantToFlight(aa, this.currentFlight);
            }
        } else {
            AssignLogic.getInstance().deleteAirAttendantsFromFlight(this.currentFlight);
        }
    }
}
