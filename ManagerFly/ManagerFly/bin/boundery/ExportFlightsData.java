/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.collections.FXCollections
 *  javafx.collections.ObservableList
 *  javafx.fxml.FXML
 *  javafx.scene.control.Button
 *  javafx.scene.control.ListView
 *  javafx.scene.input.MouseEvent
 */
package boundery;

import control.ExportControl;
import control.Getters;
import entity.Flight;
import java.util.ArrayList;
import java.util.Collection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class ExportFlightsData {
    @FXML
    Button exportBtn;
    @FXML
    private ListView<Flight> flights;

    @FXML
    public void initialize() {
    }

    @FXML
    public void doingExport(MouseEvent event) {
        ExportControl.getInstance().exportFlightsToJSON();
        ArrayList<Flight> flightsAll = Getters.getInstance().getFlights();
        ObservableList flightsSt = FXCollections.observableArrayList(flightsAll);
        this.flights.setItems(FXCollections.observableArrayList((Collection)flightsSt));
    }
}
