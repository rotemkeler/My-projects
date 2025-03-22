/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.collections.FXCollections
 *  javafx.collections.ObservableList
 *  javafx.event.Event
 *  javafx.fxml.FXML
 *  javafx.scene.control.Alert
 *  javafx.scene.control.Button
 *  javafx.scene.control.ComboBox
 *  javafx.scene.control.DatePicker
 *  javafx.scene.control.ListView
 */
package boundery;

import control.AssignLogic;
import control.Getters;
import entity.GroundAttendant;
import entity.GroundAttendantInShift;
import entity.Shift;
import exceptions.InvalidInputException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import util.Alerts;
import util.Consts;

public class ShiftsManagemantFrm {
    @FXML
    private DatePicker start;
    @FXML
    private DatePicker end;
    @FXML
    private ComboBox<Integer> startHour;
    @FXML
    private ComboBox<Integer> startMinute;
    @FXML
    private ComboBox<Integer> endHour;
    @FXML
    private ComboBox<Integer> endMinute;
    @FXML
    private ComboBox<GroundAttendant> att;
    @FXML
    private ComboBox<String> role;
    @FXML
    private ListView<GroundAttendantInShift> allShifts;
    @FXML
    private Button saveBtn;
    @FXML
    private Alert a;

    @FXML
    public void initialize() {
        this.initShifts();
        this.initTime();
        this.initCmbx();
    }

    private void initShifts() {
        ObservableList attInShift = FXCollections.observableArrayList(Getters.getInstance().getGroundAttendantShifts());
        this.allShifts.setItems(FXCollections.observableArrayList((Collection)attInShift));
    }

    private void initTime() {
        ArrayList<Integer> hoursList = new ArrayList<Integer>();
        ArrayList<Integer> minuteList = new ArrayList<Integer>();
        int i = 0;
        while (i < 24) {
            hoursList.add(i);
            ++i;
        }
        this.startHour.setItems(FXCollections.observableArrayList(hoursList));
        this.endHour.setItems(FXCollections.observableArrayList(hoursList));
        i = 0;
        while (i < 60) {
            minuteList.add(i);
            ++i;
        }
        this.startMinute.setItems(FXCollections.observableArrayList(minuteList));
        this.endMinute.setItems(FXCollections.observableArrayList(minuteList));
    }

    private void initCmbx() {
        ObservableList gatt = FXCollections.observableArrayList(Getters.getInstance().getGroundAttendants());
        this.att.setItems(FXCollections.observableArrayList((Collection)gatt));
        ObservableList roles = FXCollections.observableArrayList((Object[])Consts.SHIFT_ROLE);
        this.role.setItems(FXCollections.observableArrayList((Collection)roles));
    }

    private void clear() {
        this.start.setValue(null);
        this.end.setValue(null);
        this.startHour.setValue(null);
        this.endHour.setValue(null);
        this.startMinute.setValue(null);
        this.endMinute.setValue(null);
        this.att.setValue(null);
        this.role.setValue(null);
        this.initTime();
        this.initCmbx();
    }

    @FXML
    private void saveShift(Event event) {
        try {
            if (this.start.getValue() == null) {
                throw new InvalidInputException("Please select Start Date");
            }
            if (this.end.getValue() == null) {
                throw new InvalidInputException("Please select End Date");
            }
            if (this.endHour.getValue() == null) {
                throw new InvalidInputException("Please select End Hour");
            }
            if (this.startMinute.getValue() == null) {
                throw new InvalidInputException("Please select Start Minute");
            }
            if (this.endMinute.getValue() == null) {
                throw new InvalidInputException("Please select End Minute");
            }
            if (this.startHour.getValue() == null) {
                throw new InvalidInputException("Please select Start Hour");
            }
            if (this.att.getValue() == null) {
                throw new InvalidInputException("Please select Ground Attendant");
            }
            if (this.role.getValue() == null) {
                throw new InvalidInputException("Please select Role");
            }
            LocalDateTime startDT = LocalDateTime.of(((LocalDate)this.start.getValue()).getYear(), ((LocalDate)this.start.getValue()).getMonth(), ((LocalDate)this.start.getValue()).getDayOfMonth(), (int)((Integer)this.startHour.getValue()), (int)((Integer)this.startMinute.getValue()));
            LocalDateTime endDT = LocalDateTime.of(((LocalDate)this.end.getValue()).getYear(), ((LocalDate)this.end.getValue()).getMonth(), ((LocalDate)this.end.getValue()).getDayOfMonth(), (int)((Integer)this.endHour.getValue()), (int)((Integer)this.endMinute.getValue()));
            if (!AssignLogic.getInstance().addShift(new Shift(startDT, endDT)) || !AssignLogic.getInstance().addGroundAttendantToShift(new GroundAttendantInShift(new Shift(startDT, endDT), (GroundAttendant)this.att.getValue(), (String)this.role.getValue()))) {
                throw new InvalidInputException("something went wrong while adding a new flight. Try a different ID");
            }
            this.a = Alerts.infoAlert("Added Flight Successfully!");
            this.a.show();
            this.initShifts();
            this.clear();
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
