/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.collections.FXCollections
 *  javafx.collections.ObservableList
 *  javafx.event.Event
 *  javafx.fxml.FXML
 *  javafx.scene.control.Button
 *  javafx.scene.control.ListView
 */
package boundery;

import control.ImportXML;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class ImportFlightStatusFromXML {
    @FXML
    private Button importBtn;
    @FXML
    private ListView<String> flights;

    @FXML
    public void initialize() {
    }

    @FXML
    public void importBtn(Event event) {
        HashMap<String, String> results = ImportXML.getInstance().importFlightsFromXML();
        ArrayList<String> toshow = new ArrayList<String>();
        for (String key : results.keySet()) {
            toshow.add("             " + results.get(key) + "                                 " + key);
        }
        ObservableList flightsSt = FXCollections.observableArrayList(toshow);
        this.flights.setItems(FXCollections.observableArrayList((Collection)flightsSt));
    }
}
