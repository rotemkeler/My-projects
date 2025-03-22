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
 *  javafx.scene.control.ChoiceBox
 *  javafx.scene.control.ComboBox
 *  javafx.scene.control.TextField
 *  javafx.scene.control.Tooltip
 *  javafx.scene.input.KeyEvent
 *  javafx.scene.layout.Pane
 */
package boundery;

import boundery.AddFlightFrm;
import control.FlightsLogic;
import control.Getters;
import entity.AirPort;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import util.InputValidetions;

public class AirportsFrm {
    @FXML
    private TextField cityFld;
    @FXML
    private TextField countryFld;
    @FXML
    private TextField IDFld;
    @FXML
    private ComboBox<AirPort> airPortCmbBx;
    @FXML
    private ComboBox<Integer> timeZoneFld;
    @FXML
    private Button loadEmptyFrmBtn;
    @FXML
    private Button saveAirPort;
    @FXML
    private Button nextBtn;
    @FXML
    private Button pervBtn;
    private ObservableList<AirPort> airPortsList;
    private ObservableList<Integer> GMTValuesList;
    private Tooltip GMTtooltip;
    private Tooltip pervtooltip;
    private Tooltip nexttooltip;
    private Tooltip searchtooltip;
    private ArrayList<AirPort> airportArrList;
    private int currentAirPortIndex;
    private AirPort currentAirPort;
    private boolean inEditMode;
    private HashMap<Integer, AirPort> airportMap;
    private Alert a = new Alert(Alert.AlertType.NONE);
    @FXML
    private ChoiceBox<String> airPortStatusBox;
    @FXML
    private Button updateBtn;
    @FXML
    private Pane airPortPane;

    @FXML
    public void initialize() {
        this.init();
    }

    private void init() {
        this.inEditMode = false;
        this.GMTtooltip = new Tooltip();
        this.pervtooltip = new Tooltip();
        this.nexttooltip = new Tooltip();
        this.searchtooltip = new Tooltip();
        this.GMTtooltip.setText("GMT Values");
        this.pervtooltip.setText("previous airport");
        this.nexttooltip.setText("next airport");
        this.searchtooltip.setText("search");
        this.timeZoneFld.setTooltip(this.GMTtooltip);
        this.pervBtn.setTooltip(this.pervtooltip);
        this.nextBtn.setTooltip(this.nexttooltip);
        this.IDFld.setTooltip(this.searchtooltip);
        ArrayList<Integer> GmtArr = new ArrayList<Integer>();
        int i = -12;
        while (i <= 12) {
            GmtArr.add(i);
            ++i;
        }
        this.GMTValuesList = FXCollections.observableArrayList(GmtArr);
        this.timeZoneFld.setItems(this.GMTValuesList);
        this.currentAirPortIndex = 0;
        Object[] statusArrs = new String[]{"Open", "Close"};
        this.airPortStatusBox.getItems().addAll(statusArrs);
        this.airportArrList = Getters.getInstance().getAirports();
        if (this.airportArrList != null) {
            this.airPortsList = FXCollections.observableArrayList(this.airportArrList);
            this.airPortCmbBx.setItems(this.airPortsList);
            this.airPortCmbBx.setValue((Object)this.airportArrList.get(0));
            this.airportMap = new HashMap();
            for (AirPort ap : this.airportArrList) {
                this.airportMap.put(ap.getAirportCode(), ap);
            }
            this.loadAirPortByCmb(new ActionEvent());
        }
    }

    @FXML
    void addAirPort(ActionEvent event) {
        if (this.inEditMode) {
            String city = this.cityFld.getText();
            String country = this.countryFld.getText();
            String ID = this.IDFld.getText();
            int timeZone = (Integer)this.timeZoneFld.getValue();
            if (this.timeZoneFld.getValue() != null && this.IDFld.getText() != null && this.countryFld.getText() != null && this.cityFld.getText() != null && InputValidetions.validateName(city) && InputValidetions.validateName(country) && InputValidetions.validatePositiveIntegerOrZero(ID)) {
                int id = Integer.parseInt(ID);
                if (id > 0 && this.airportMap.get(id) == null) {
                    if (FlightsLogic.getInstance().addAirPort(id, city, country, timeZone)) {
                        this.a.setAlertType(Alert.AlertType.INFORMATION);
                        this.a.setHeaderText("MESSAGE");
                        this.a.setTitle("SYSTEM MESSAGE");
                        this.a.setContentText("Added succesfully");
                        this.a.show();
                        this.notInEdit();
                        AirPort newAP = new AirPort(id, city, country, timeZone);
                        this.addtoDataStructures(newAP);
                        AddFlightFrm.closeWindow();
                    } else {
                        this.faildtoAddMsg();
                    }
                } else {
                    this.faildtoAddMsg();
                }
            } else {
                this.faildtoAddMsg();
            }
        }
    }

    private void faildtoAddMsg() {
        this.a.setAlertType(Alert.AlertType.ERROR);
        this.a.setHeaderText("MESSAGE");
        this.a.setTitle("SYSTEM MESSAGE");
        this.a.setContentText("Operation Faild");
        this.a.show();
    }

    private void addtoDataStructures(AirPort ap) {
        this.airportMap.put(ap.getAirportCode(), ap);
        this.currentAirPort = ap;
        this.airportArrList.add(ap);
        this.currentAirPortIndex = this.airportArrList.size() - 1;
        this.airPortCmbBx.getItems().add((Object)ap);
        this.airPortCmbBx.setValue((Object)ap);
    }

    @FXML
    void LoadPort(KeyEvent event) {
        String s = this.IDFld.getText();
        if (s != null && !s.isEmpty()) {
            AirPort ap = null;
            ap = this.airportMap.get(Integer.parseInt(s));
            if (ap != null) {
                this.currentAirPort = ap;
                this.airPortCmbBx.setValue((Object)ap);
                this.IDFld.setText(String.valueOf(this.currentAirPort.getAirportCode()));
                this.cityFld.setText(this.currentAirPort.getCity());
                this.countryFld.setText(this.currentAirPort.getCountry());
                this.timeZoneFld.setValue((Object)this.currentAirPort.getTimeZone());
                this.currentAirPortIndex = this.airportArrList.indexOf(ap);
                System.out.println(this.currentAirPort.isOpen());
                String status = this.currentAirPort.isOpen() ? "Open" : "Close";
                this.airPortStatusBox.setValue((Object)status);
                this.notInEdit();
            }
        }
    }

    @FXML
    void loadAirPortByCmb(ActionEvent event) {
        if (this.airPortCmbBx.getValue() != null && !this.inEditMode) {
            this.currentAirPort = (AirPort)this.airPortCmbBx.getValue();
            this.IDFld.setText(String.valueOf(this.currentAirPort.getAirportCode()));
            this.cityFld.setText(this.currentAirPort.getCity());
            this.countryFld.setText(this.currentAirPort.getCountry());
            this.timeZoneFld.setValue((Object)this.currentAirPort.getTimeZone());
            this.currentAirPortIndex = this.airportArrList.indexOf(this.currentAirPort);
            String status = this.currentAirPort.isOpen() ? "Open" : "Close";
            this.airPortStatusBox.setValue((Object)status);
            this.notInEdit();
        }
    }

    @FXML
    void loadEmptyFrm(ActionEvent event) {
        this.inEdit();
    }

    @FXML
    void loadNextAirPort(ActionEvent event) {
        if (this.currentAirPortIndex + 1 < this.airportArrList.size()) {
            ++this.currentAirPortIndex;
            this.airPortCmbBx.setValue((Object)this.airportArrList.get(this.currentAirPortIndex));
            this.notInEdit();
            this.loadAirPortByCmb(new ActionEvent());
        } else {
            this.a.setAlertType(Alert.AlertType.INFORMATION);
            this.a.setHeaderText("MESSAGE");
            this.a.setTitle("SYSTEM MESSAGE");
            this.a.setContentText("Last in the list!");
            this.a.show();
            this.notInEdit();
        }
    }

    @FXML
    void loadPervAirPort(ActionEvent event) {
        if (this.currentAirPortIndex - 1 >= 0) {
            --this.currentAirPortIndex;
            this.airPortCmbBx.setValue((Object)this.airportArrList.get(this.currentAirPortIndex));
            this.notInEdit();
            this.loadAirPortByCmb(new ActionEvent());
        } else {
            this.a.setAlertType(Alert.AlertType.INFORMATION);
            this.a.setHeaderText("MESSAGE");
            this.a.setTitle("SYSTEM MESSAGE");
            this.a.setContentText("First in the list!");
            this.a.show();
            this.notInEdit();
        }
    }

    private void notInEdit() {
        this.inEditMode = false;
        this.saveAirPort.setOpacity(0.4);
        this.saveAirPort.setDisable(true);
        this.cityFld.setEditable(false);
        this.countryFld.setEditable(false);
        this.airPortPane.setVisible(true);
    }

    private void inEdit() {
        this.inEditMode = true;
        this.saveAirPort.setOpacity(1.0);
        this.saveAirPort.setDisable(false);
        this.cityFld.setText(null);
        this.countryFld.setText(null);
        this.IDFld.setText(null);
        this.cityFld.setEditable(true);
        this.countryFld.setEditable(true);
        this.airPortPane.setVisible(false);
        this.countryFld.setEditable(true);
    }

    public void inEditAddFlight() {
        this.inEditMode = true;
        this.pervtooltip.hide();
        this.nexttooltip.hide();
        this.nextBtn.setTooltip(this.nexttooltip);
        this.IDFld.clear();
        this.saveAirPort.setOpacity(1.0);
        this.saveAirPort.setDisable(false);
        this.cityFld.setText(null);
        this.countryFld.setText(null);
        this.IDFld.setText(null);
        this.cityFld.setEditable(true);
        this.countryFld.setEditable(true);
        this.airPortCmbBx.setVisible(false);
        this.pervBtn.setVisible(false);
        this.nextBtn.setVisible(false);
        this.loadEmptyFrmBtn.setVisible(false);
        this.airPortPane.setVisible(false);
    }

    @FXML
    void UpdateData(ActionEvent event) {
        Alert b = new Alert(Alert.AlertType.NONE);
        if (!this.inEditMode && this.airPortStatusBox.getValue() != null && this.currentAirPort != null) {
            boolean status;
            boolean bl = status = ((String)this.airPortStatusBox.getValue()).equals("Open");
            if (FlightsLogic.getInstance().editAirPortStatus(status, this.currentAirPort.getAirportCode())) {
                this.currentAirPort.setOpen(status);
                this.a.setAlertType(Alert.AlertType.INFORMATION);
                this.a.setHeaderText("MESSAGE");
                this.a.setTitle("SYSTEM MESSAGE");
                this.a.setContentText("Updated succesfully");
                this.a.show();
                if (this.updateFlightsStatus(status, this.currentAirPort.getAirportCode())) {
                    b.setAlertType(Alert.AlertType.INFORMATION);
                    b.setHeaderText("MESSAGE");
                    b.setTitle("SYSTEM MESSAGE");
                    b.setContentText("Updated Flights Status succesfully");
                    b.show();
                } else {
                    this.faildtoAddMsg();
                }
            } else {
                this.faildtoAddMsg();
            }
        }
    }

    private boolean updateFlightsStatus(boolean Status, int airportId) {
        String status = Status ? "on time" : "delayed";
        return FlightsLogic.getInstance().editFlightStatus(status, airportId);
    }
}
