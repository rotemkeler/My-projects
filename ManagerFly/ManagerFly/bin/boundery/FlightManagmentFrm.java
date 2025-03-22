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
 *  javafx.scene.control.DatePicker
 *  javafx.scene.control.Label
 *  javafx.scene.control.TextField
 *  javafx.scene.control.Tooltip
 *  javafx.scene.input.KeyEvent
 *  javafx.scene.layout.AnchorPane
 *  javafx.scene.paint.Color
 *  javafx.scene.paint.Paint
 */
package boundery;

import control.FlightsLogic;
import control.Getters;
import entity.AirPlane;
import entity.AirPort;
import entity.Flight;
import exceptions.InvalidInputException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class FlightManagmentFrm {
    @FXML
    private AnchorPane ManagmentPane;
    @FXML
    private TextField DepFld;
    @FXML
    private TextField IDFld;
    @FXML
    private ComboBox<Flight> FlightCmbBx;
    @FXML
    private Button nextBtn;
    @FXML
    private Button pervBtn;
    @FXML
    private TextField DestFld;
    @FXML
    private TextField DepIDFld;
    @FXML
    private TextField DestIDFld;
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
    private ComboBox<AirPlane> airPlanes;
    @FXML
    private Button updateBtn;
    @FXML
    private Label FlightStatusLbl;
    private Tooltip pervtooltip;
    private Tooltip nexttooltip;
    private Tooltip searchtooltip;
    private ObservableList<Flight> flightsList;
    private HashMap<String, Flight> flightsById;
    private HashMap<Integer, AirPort> airPortsById;
    private Flight currentFlight;
    private static Flight curreStatucntFlight;
    private int currFlightIndex;
    private ArrayList<Flight> flightArr;
    private Alert a = new Alert(Alert.AlertType.NONE);
    private FlightsLogic flightsInstance = FlightsLogic.getInstance();
    @FXML
    private Label orderStatusLbl;

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
        this.airPortsById = new HashMap();
        this.flightsById = new HashMap();
        this.initPlanesItems();
        this.initAirports();
        this.initFlights();
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
    }

    public void initPlanesItems() {
        ObservableList planes = FXCollections.observableArrayList(Getters.getInstance().getAirplanes());
        this.airPlanes.getItems().clear();
        this.airPlanes.setItems(FXCollections.observableArrayList((Collection)planes));
    }

    private void initAirports() {
        ObservableList airports = FXCollections.observableArrayList(Getters.getInstance().getAirports());
        for (AirPort ap : airports) {
            this.airPortsById.put(ap.getAirportCode(), ap);
        }
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
    void UpdateData(ActionEvent event) {
        if (this.validateFields()) {
            AirPlane plane = (AirPlane)this.airPlanes.getValue();
            LocalDateTime depatureDateTime = LocalDateTime.of(((LocalDate)this.departureDate.getValue()).getYear(), ((LocalDate)this.departureDate.getValue()).getMonth(), ((LocalDate)this.departureDate.getValue()).getDayOfMonth(), (int)((Integer)this.depHour.getValue()), (int)((Integer)this.depMinute.getValue()));
            LocalDateTime landingDateTime = LocalDateTime.of(((LocalDate)this.landingDate.getValue()).getYear(), ((LocalDate)this.landingDate.getValue()).getMonth(), ((LocalDate)this.landingDate.getValue()).getDayOfMonth(), (int)((Integer)this.arrHour.getValue()), (int)((Integer)this.arrMinute.getValue()));
            if (this.flightsInstance.editFlight(this.currentFlight.getFlightNum(), depatureDateTime, landingDateTime, plane.getTailNum())) {
                this.currentFlight.setDepatureTime(depatureDateTime);
                this.currentFlight.setLandingTime(landingDateTime);
                this.currentFlight.setAirPlaneTailNum(plane);
                this.a.setAlertType(Alert.AlertType.INFORMATION);
                this.a.setHeaderText("MESSAGE");
                this.a.setTitle("SYSTEM MESSAGE");
                this.a.setContentText("Flight Updated successfully!");
                this.a.show();
            } else {
                this.a.setAlertType(Alert.AlertType.ERROR);
                this.a.setHeaderText("MESSAGE");
                this.a.setTitle("SYSTEM MESSAGE");
                this.a.setContentText("Somthing went wrong!");
                this.a.show();
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
        LocalDateTime depTime = this.currentFlight.getDepatureTime();
        LocalDateTime destTime = this.currentFlight.getLandingTime();
        AirPort depAp = this.airPortsById.get(this.currentFlight.getDepatureAirportID().getAirportCode());
        AirPort destAp = this.airPortsById.get(this.currentFlight.getDestinationAirportID().getAirportCode());
        AirPlane plane = this.currentFlight.getAirPlaneTailNum();
        String fStatus = this.currentFlight.getFlightStatus();
        String oStatus = this.currentFlight.getOrderStatus();
        this.FlightStatusLbl.setText(fStatus);
        this.orderStatusLbl.setText(oStatus);
        if (fStatus.equals("on time")) {
            this.FlightStatusLbl.setTextFill((Paint)Color.web((String)"Green"));
        } else if (fStatus.equals("cancelled")) {
            this.FlightStatusLbl.setTextFill((Paint)Color.web((String)"Red"));
        } else {
            this.FlightStatusLbl.setTextFill((Paint)Color.web((String)"Blue"));
        }
        if (oStatus.equals("Init")) {
            this.orderStatusLbl.setTextFill((Paint)Color.web((String)"Blue"));
        } else if (oStatus.equals("Pre Order")) {
            this.orderStatusLbl.setTextFill((Paint)Color.web((String)"Yellow"));
        } else {
            this.orderStatusLbl.setTextFill((Paint)Color.web((String)"Green"));
        }
        this.airPlanes.setValue((Object)plane);
        this.DepIDFld.setText(String.valueOf(depAp.getAirportCode()));
        this.DestIDFld.setText(String.valueOf(destAp.getAirportCode()));
        this.DepFld.setText(String.valueOf(depAp.getCity()) + " " + depAp.getCountry());
        this.DestFld.setText(String.valueOf(destAp.getCity()) + " " + destAp.getCountry());
        this.departureDate.setValue((Object)depTime.toLocalDate());
        this.landingDate.setValue((Object)destTime.toLocalDate());
        this.depHour.setValue((Object)depTime.getHour());
        this.depMinute.setValue((Object)depTime.getMinute());
        this.arrHour.setValue((Object)destTime.getHour());
        this.arrMinute.setValue((Object)destTime.getMinute());
    }

    private boolean validateFields() {
        try {
            if (this.departureDate.getValue() == null) {
                throw new InvalidInputException("Please select Depature Date");
            }
            if (this.landingDate.getValue() == null) {
                throw new InvalidInputException("Please select Landing Date");
            }
            if (((LocalDate)this.departureDate.getValue()).isAfter((ChronoLocalDate)this.landingDate.getValue())) {
                throw new InvalidInputException("landing date should be after the departure date");
            }
            if (((LocalDate)this.departureDate.getValue()).isBefore(LocalDate.now().plusMonths(2L))) {
                throw new InvalidInputException("flight must be added 2 months before it's planned date");
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
            LocalDateTime depatureDateTime = LocalDateTime.of(((LocalDate)this.departureDate.getValue()).getYear(), ((LocalDate)this.departureDate.getValue()).getMonth(), ((LocalDate)this.departureDate.getValue()).getDayOfMonth(), (int)((Integer)this.depHour.getValue()), (int)((Integer)this.depMinute.getValue()));
            LocalDateTime landingDateTime = LocalDateTime.of(((LocalDate)this.landingDate.getValue()).getYear(), ((LocalDate)this.landingDate.getValue()).getMonth(), ((LocalDate)this.landingDate.getValue()).getDayOfMonth(), (int)((Integer)this.arrHour.getValue()), (int)((Integer)this.arrMinute.getValue()));
            if (this.airPlanes.getSelectionModel().getSelectedItem() == null) {
                throw new InvalidInputException("Please select an Airplane");
            }
            curreStatucntFlight = this.currentFlight;
            if (!this.flightsInstance.isPlaneOverlapping((AirPlane)this.airPlanes.getSelectionModel().getSelectedItem(), depatureDateTime, landingDateTime)) {
                throw new InvalidInputException("Airplane is already taken by another flight");
            }
            if (!this.flightsInstance.isAirportsOverlapping(this.currentFlight.getDepatureAirportID(), depatureDateTime, true)) {
                throw new InvalidInputException("Please select a different Departue Date - flights collison");
            }
            if (!this.flightsInstance.isAirportsOverlapping(this.currentFlight.getDestinationAirportID(), landingDateTime, false)) {
                throw new InvalidInputException("Please select a different Landing Date - flights collison");
            }
            curreStatucntFlight = null;
            return true;
        }
        catch (InvalidInputException e) {
            this.a.setAlertType(Alert.AlertType.ERROR);
            this.a.setHeaderText("MESSAGE");
            this.a.setTitle("SYSTEM MESSAGE");
            this.a.setContentText(e.getMessage());
            this.a.show();
            curreStatucntFlight = null;
            return false;
        }
    }
}
