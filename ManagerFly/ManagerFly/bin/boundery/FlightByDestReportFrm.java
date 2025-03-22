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
 *  javafx.scene.image.ImageView
 *  javafx.scene.input.MouseEvent
 *  org.jfree.ui.RefineryUtilities
 */
package boundery;

import boundery.FlightsByDepartureCountry;
import control.Getters;
import entity.AirPort;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Collection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.jfree.ui.RefineryUtilities;

public class FlightByDestReportFrm {
    @FXML
    private ComboBox<String> countryCmbx;
    @FXML
    private Button BiggestFlightsReportBtn;
    @FXML
    private ImageView readMoreIcn;
    @FXML
    private Button readMoreBtn;
    private String massage = "";
    private String biggestFlightsInfo = "biggest Flights Reprot = return a report of all the flights that occurred in range of 'from' and 'until' fields,\nand thier plane  contain at least a given amount of  tourists seats.\nreturn fields: flight serial num, city and country of dep airport and dest airport, dep and land time and flight status\nsorted by: main: country + city desc order.\n           secnd: dep + land time desc order.\n";
    private Alert a = new Alert(Alert.AlertType.NONE);
    private Alert b = new Alert(Alert.AlertType.NONE);

    @FXML
    public void initialize() {
        ArrayList<AirPort> airports = Getters.getInstance().getAirports();
        ArrayList<String> countries = new ArrayList<String>();
        for (AirPort ap : airports) {
            if (countries.contains(ap.getCountry())) continue;
            countries.add(ap.getCountry());
        }
        ObservableList countriesObs = FXCollections.observableArrayList(countries);
        this.countryCmbx.setItems(FXCollections.observableArrayList((Collection)countriesObs));
    }

    @FXML
    void CallreadMoreICn(MouseEvent event) {
        this.a.setAlertType(Alert.AlertType.INFORMATION);
        this.a.setHeaderText("REPORTS INFO");
        this.a.setContentText(String.valueOf(this.massage) + this.biggestFlightsInfo);
        this.a.setTitle("Info");
        this.a.setHeight(450.0);
        this.a.setWidth(450.0);
        this.a.show();
    }

    @FXML
    void callBiggestFlightsReport(ActionEvent event) {
        boolean datesheck = this.validateUntilafterFrom();
        if (datesheck) {
            FlightsByDepartureCountry chart = new FlightsByDepartureCountry((String)this.countryCmbx.getValue());
            chart.pack();
            RefineryUtilities.centerFrameOnScreen((Window)chart);
            chart.setVisible(true);
        }
    }

    @FXML
    void readMore(ActionEvent event) {
        this.a.setAlertType(Alert.AlertType.INFORMATION);
        this.a.setHeaderText("REPORTS INFO");
        this.a.setContentText(String.valueOf(this.massage) + this.biggestFlightsInfo);
        this.a.setTitle("Info");
        this.a.setHeight(450.0);
        this.a.setWidth(450.0);
        this.a.show();
    }

    private boolean validateUntilafterFrom() {
        String from = (String)this.countryCmbx.getValue();
        if (from != null) {
            return true;
        }
        this.missingFldWarning();
        return false;
    }

    private void missingFldWarning() {
        this.a.setAlertType(Alert.AlertType.WARNING);
        this.a.setHeaderText("MESSAGE");
        this.a.setContentText("Missing fields");
        this.a.setTitle("System MESSAGE");
        this.a.show();
    }
}
