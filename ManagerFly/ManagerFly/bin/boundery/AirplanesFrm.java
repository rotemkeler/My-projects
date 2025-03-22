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
 *  javafx.scene.control.ListView
 *  javafx.scene.control.TextField
 *  javafx.scene.control.Tooltip
 *  javafx.scene.input.KeyEvent
 *  javafx.scene.layout.Pane
 */
package boundery;

import boundery.AddFlightFrm;
import control.FlightsLogic;
import control.Getters;
import entity.AirPlane;
import entity.FlightSeat;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import util.Consts;
import util.InputValidetions;

public class AirplanesFrm {
    @FXML
    private TextField IDFld;
    @FXML
    private ComboBox<Integer> attNumcoboBox;
    @FXML
    private Button loadEmptyFrmBtn;
    @FXML
    private Button saveAirPlane;
    @FXML
    private Button nextBtn;
    @FXML
    private Button pervBtn;
    @FXML
    private Pane seatsPane;
    @FXML
    private ListView<FlightSeat> seatsTable;
    @FXML
    private Pane addSeatsPane;
    @FXML
    private ComboBox<Integer> bsnsCombo;
    @FXML
    private ComboBox<Integer> firstClassCombo;
    @FXML
    private ComboBox<Integer> TouristsCombo;
    @FXML
    private ComboBox<Integer> totalCollsCombo;
    @FXML
    private ComboBox<AirPlane> planeCmbo;
    private ObservableList<AirPlane> airPlaneList;
    private ObservableList<FlightSeat> flightSeatsList;
    private ObservableList<Integer> AttensdList;
    private ObservableList<Integer> buissnessList;
    private ObservableList<Integer> firstClsList;
    private ObservableList<Integer> touristsList;
    private ObservableList<Integer> totalCollsList;
    private Tooltip IDtooltip;
    private Tooltip pervtooltip;
    private Tooltip nexttooltip;
    private ArrayList<AirPlane> airplaneArrList;
    private ArrayList<FlightSeat> flightSeatsArrList;
    private int currentAirPlaneIndex;
    private AirPlane currentPlane;
    private boolean inEditMode;
    private HashMap<String, AirPlane> airPlaneMap;
    private HashMap<String, ArrayList<FlightSeat>> seatsMap;
    private Alert a = new Alert(Alert.AlertType.NONE);
    private int biggestSeatID;

    @FXML
    public void initialize() {
        this.init();
    }

    private void init() {
        this.inEditMode = false;
        this.IDtooltip = new Tooltip();
        this.pervtooltip = new Tooltip();
        this.nexttooltip = new Tooltip();
        this.IDtooltip.setText("Search ,for Example: A-2079");
        this.pervtooltip.setText("previous airplane");
        this.nexttooltip.setText("next airplane");
        this.IDFld.setTooltip(this.IDtooltip);
        this.pervBtn.setTooltip(this.pervtooltip);
        this.nextBtn.setTooltip(this.nexttooltip);
        ArrayList<Integer> AttendArr = new ArrayList<Integer>();
        ArrayList<Integer> buissnessArr = new ArrayList<Integer>();
        ArrayList<Integer> firstClsArr = new ArrayList<Integer>();
        ArrayList<Integer> touristsArr = new ArrayList<Integer>();
        ArrayList<Integer> totalColsArr = new ArrayList<Integer>();
        int i = 1;
        while (i <= 12) {
            AttendArr.add(i);
            ++i;
        }
        i = 1;
        while (i <= 10) {
            buissnessArr.add(i);
            ++i;
        }
        i = 1;
        while (i <= 6) {
            firstClsArr.add(i);
            ++i;
        }
        i = 20;
        while (i <= 60) {
            touristsArr.add(i);
            i += 5;
        }
        i = 2;
        while (i <= 5) {
            totalColsArr.add(i);
            ++i;
        }
        this.AttensdList = FXCollections.observableArrayList(AttendArr);
        this.buissnessList = FXCollections.observableArrayList(buissnessArr);
        this.firstClsList = FXCollections.observableArrayList(firstClsArr);
        this.touristsList = FXCollections.observableArrayList(touristsArr);
        this.totalCollsList = FXCollections.observableArrayList(totalColsArr);
        this.attNumcoboBox.setItems(this.AttensdList);
        this.bsnsCombo.setItems(this.buissnessList);
        this.bsnsCombo.setValue((Object)((Integer)buissnessArr.get(0)));
        this.firstClassCombo.setItems(this.firstClsList);
        this.firstClassCombo.setValue((Object)((Integer)firstClsArr.get(0)));
        this.TouristsCombo.setItems(this.touristsList);
        this.TouristsCombo.setValue((Object)((Integer)touristsArr.get(0)));
        this.totalCollsCombo.setItems(this.totalCollsList);
        this.totalCollsCombo.setValue((Object)((Integer)totalColsArr.get(0)));
        this.currentAirPlaneIndex = 0;
        this.seatsTable.setFixedCellSize(45.0);
        this.airplaneArrList = Getters.getInstance().getAirplanes();
        if (this.airplaneArrList != null) {
            this.airPlaneList = FXCollections.observableArrayList(this.airplaneArrList);
            this.planeCmbo.setItems(this.airPlaneList);
            this.planeCmbo.setValue((Object)this.airplaneArrList.get(0));
            this.airPlaneMap = new HashMap();
            this.seatsMap = new HashMap();
            this.flightSeatsArrList = Getters.getInstance().getFlightSeats();
            this.biggestSeatID = this.flightSeatsArrList.get(0).getSeatID();
            for (FlightSeat fs : this.flightSeatsArrList) {
                ArrayList<FlightSeat> sArr;
                if (fs.getSeatID() > this.biggestSeatID) {
                    this.biggestSeatID = fs.getSeatID();
                }
                if ((sArr = this.seatsMap.get(fs.getPlane().getTailNum())) == null) {
                    sArr = new ArrayList();
                }
                sArr.add(fs);
                this.seatsMap.put(fs.getPlane().getTailNum(), sArr);
            }
            for (AirPlane ap : this.airplaneArrList) {
                ArrayList<FlightSeat> fs = this.seatsMap.get(ap.getTailNum());
                for (FlightSeat seat : fs) {
                    ap.getSeats().put(seat.getSeatID(), seat);
                }
                this.airPlaneMap.put(ap.getTailNum(), ap);
            }
            this.loadPlaneByCmbo(new ActionEvent());
        }
    }

    @FXML
    void LoadPlane(KeyEvent event) {
        String s = this.IDFld.getText();
        if (this.IDFld.getText() != null && !this.inEditMode && !s.isEmpty()) {
            AirPlane ap = null;
            ap = this.airPlaneMap.get(s);
            if (ap != null) {
                this.currentPlane = ap;
                this.IDFld.setText(String.valueOf(this.currentPlane.getTailNum()));
                this.attNumcoboBox.setValue((Object)this.currentPlane.getAttendantsNum());
                this.currentAirPlaneIndex = this.airplaneArrList.indexOf(this.currentPlane);
                this.flightSeatsList = FXCollections.observableArrayList(this.setSeatsInCombo(this.currentPlane.getSeats()));
                this.seatsTable.setItems(this.flightSeatsList);
                this.notInEdit();
            }
        }
    }

    @FXML
    void loadPlaneByCmbo(ActionEvent event) {
        if (this.planeCmbo.getValue() != null && !this.inEditMode) {
            this.currentPlane = (AirPlane)this.planeCmbo.getValue();
            this.IDFld.setText(String.valueOf(this.currentPlane.getTailNum()));
            this.attNumcoboBox.setValue((Object)this.currentPlane.getAttendantsNum());
            this.currentAirPlaneIndex = this.airplaneArrList.indexOf(this.currentPlane);
            this.flightSeatsList = FXCollections.observableArrayList(this.setSeatsInCombo(this.currentPlane.getSeats()));
            this.seatsTable.setItems(this.flightSeatsList);
            this.notInEdit();
        }
    }

    private ArrayList<FlightSeat> setSeatsInCombo(HashMap<Integer, FlightSeat> seats) {
        ArrayList<FlightSeat> seatsLists = new ArrayList<FlightSeat>(seats.values());
        return seatsLists;
    }

    @FXML
    void addAirPlane(ActionEvent event) {
        if (this.inEditMode) {
            String tailNum = this.IDFld.getText();
            Integer attendNumByCombo = (Integer)this.attNumcoboBox.getValue();
            Integer totalColl = (Integer)this.totalCollsCombo.getValue();
            Integer toursitsRows = (Integer)this.TouristsCombo.getValue();
            Integer firstClassRows = (Integer)this.firstClassCombo.getValue();
            Integer BuissnessRows = (Integer)this.bsnsCombo.getValue();
            if (tailNum != null && !tailNum.isEmpty() && this.airPlaneMap.get(tailNum) == null && InputValidetions.validateTailNum(tailNum)) {
                if (this.totalCollsCombo.getValue() != null && this.TouristsCombo.getValue() != null && this.firstClassCombo.getValue() != null && this.bsnsCombo.getValue() != null) {
                    if (attendNumByCombo != null) {
                        Integer attNum = attendNumByCombo;
                        if (FlightsLogic.getInstance().addAirPlane(tailNum, attNum)) {
                            this.a.setAlertType(Alert.AlertType.INFORMATION);
                            this.a.setHeaderText("MESSAGE");
                            this.a.setTitle("SYSTEM MESSAGE");
                            this.a.setContentText("Added succesfully");
                            this.a.show();
                            AirPlane newAP = new AirPlane(tailNum, attNum, null);
                            HashMap<Integer, FlightSeat> seats = this.createSeats(tailNum, totalColl, firstClassRows, BuissnessRows, toursitsRows, newAP);
                            newAP.setSeats(seats);
                            this.addtoDataStructures(newAP);
                            this.notInEdit();
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
            } else {
                this.a.setAlertType(Alert.AlertType.ERROR);
                this.a.setHeaderText("ERROR");
                this.a.setTitle("INPUT ERROR");
                this.a.setContentText("Tail number must be of this pattern Letter-Letters/integers\nExample: A-2079B");
                this.a.show();
            }
        } else {
            this.faildtoAddMsg();
        }
    }

    private HashMap<Integer, FlightSeat> createSeats(String tailNum, int totalCl, int firstClsRow, int buisRow, int tourRow, AirPlane plane) {
        FlightSeat fs;
        int j;
        HashMap<Integer, FlightSeat> seats = new HashMap<Integer, FlightSeat>();
        String[] colls = new String[]{"A", "B", "C", "D", "E"};
        int rowindex = 1;
        int idBegin = this.biggestSeatID + 1;
        int i = 1;
        while (i <= firstClsRow) {
            j = 0;
            while (j < totalCl) {
                fs = new FlightSeat(idBegin++, rowindex, colls[j], Consts.SEAT_TYPES[0], plane);
                seats.put(fs.getSeatID(), fs);
                FlightsLogic.getInstance().addFlightSeat(idBegin, rowindex, colls[j], Consts.SEAT_TYPES[0], tailNum);
                ++j;
            }
            ++rowindex;
            ++i;
        }
        i = 1;
        while (i <= buisRow) {
            j = 0;
            while (j < totalCl) {
                fs = new FlightSeat(idBegin++, rowindex, colls[j], Consts.SEAT_TYPES[1], plane);
                seats.put(fs.getSeatID(), fs);
                FlightsLogic.getInstance().addFlightSeat(idBegin, rowindex, colls[j], Consts.SEAT_TYPES[1], tailNum);
                ++j;
            }
            ++rowindex;
            ++i;
        }
        i = 1;
        while (i <= tourRow) {
            j = 0;
            while (j < totalCl) {
                fs = new FlightSeat(idBegin++, rowindex, colls[j], Consts.SEAT_TYPES[2], plane);
                seats.put(fs.getSeatID(), fs);
                FlightsLogic.getInstance().addFlightSeat(idBegin, rowindex, colls[j], Consts.SEAT_TYPES[2], tailNum);
                ++j;
            }
            ++rowindex;
            ++i;
        }
        this.biggestSeatID = idBegin - 1;
        return seats;
    }

    @FXML
    void loadEmptyFrm(ActionEvent event) {
        this.inEdit();
    }

    @FXML
    void loadNextAirPlane(ActionEvent event) {
        if (this.currentAirPlaneIndex + 1 < this.airplaneArrList.size()) {
            ++this.currentAirPlaneIndex;
            this.planeCmbo.setValue((Object)this.airplaneArrList.get(this.currentAirPlaneIndex));
            this.notInEdit();
            this.loadPlaneByCmbo(new ActionEvent());
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
    void loadPervAirPlane(ActionEvent event) {
        if (this.currentAirPlaneIndex - 1 >= 0) {
            --this.currentAirPlaneIndex;
            this.planeCmbo.setValue((Object)this.airplaneArrList.get(this.currentAirPlaneIndex));
            this.notInEdit();
            this.loadPlaneByCmbo(new ActionEvent());
        } else {
            this.a.setAlertType(Alert.AlertType.INFORMATION);
            this.a.setHeaderText("MESSAGE");
            this.a.setTitle("SYSTEM MESSAGE");
            this.a.setContentText("First in the list!");
            this.a.show();
            this.notInEdit();
        }
    }

    private void faildtoAddMsg() {
        this.a.setAlertType(Alert.AlertType.WARNING);
        this.a.setHeaderText("MESSAGE");
        this.a.setTitle("SYSTEM MESSAGE");
        this.a.setContentText("Faild to add");
        this.a.show();
        this.notInEdit();
    }

    private void addtoDataStructures(AirPlane ap) {
        this.airPlaneMap.put(ap.getTailNum(), ap);
        this.currentPlane = ap;
        this.airplaneArrList.add(ap);
        this.currentAirPlaneIndex = this.airplaneArrList.size() - 1;
        this.seatsMap.put(ap.getTailNum(), new ArrayList<FlightSeat>(ap.getSeats().values()));
        this.planeCmbo.getItems().add((Object)ap);
        this.planeCmbo.setValue((Object)ap);
        this.flightSeatsArrList.addAll(ap.getSeats().values());
        this.flightSeatsList.setAll(ap.getSeats().values());
        this.seatsTable.setItems(this.flightSeatsList);
    }

    private void notInEdit() {
        this.inEditMode = false;
        this.saveAirPlane.setOpacity(0.4);
        this.saveAirPlane.setDisable(true);
        this.addSeatsPane.setVisible(false);
        this.seatsPane.setVisible(true);
        this.planeCmbo.setVisible(true);
    }

    private void inEdit() {
        this.inEditMode = true;
        this.saveAirPlane.setOpacity(1.0);
        this.saveAirPlane.setDisable(false);
        this.addSeatsPane.setVisible(true);
        this.seatsPane.setVisible(false);
        this.planeCmbo.setVisible(false);
        this.IDFld.setText(null);
    }

    public void inEditAddFlight() {
        this.inEditMode = true;
        this.IDtooltip.setText("Format: A-1234");
        this.pervtooltip.hide();
        this.nexttooltip.hide();
        this.nextBtn.setTooltip(this.nexttooltip);
        this.IDFld.clear();
        this.saveAirPlane.setOpacity(1.0);
        this.saveAirPlane.setDisable(false);
        this.addSeatsPane.setVisible(true);
        this.seatsPane.setVisible(false);
        this.planeCmbo.setVisible(false);
        this.pervBtn.setVisible(false);
        this.nextBtn.setVisible(false);
        this.loadEmptyFrmBtn.setVisible(false);
    }
}
