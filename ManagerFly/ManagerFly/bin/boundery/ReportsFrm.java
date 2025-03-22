/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.event.ActionEvent
 *  javafx.fxml.FXML
 *  javafx.scene.control.Alert
 *  javafx.scene.control.Alert$AlertType
 *  javafx.scene.control.Button
 *  javafx.scene.control.DatePicker
 *  javafx.scene.control.TextField
 *  javafx.scene.image.ImageView
 *  javafx.scene.input.MouseEvent
 */
package boundery;

import control.ReportsLogic;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.swing.JFrame;
import util.InputValidetions;

public class ReportsFrm {
    @FXML
    private DatePicker fromFld;
    @FXML
    private Button BiggestFlightsReportBtn;
    @FXML
    private DatePicker untilFld;
    @FXML
    private TextField seatsNumFld;
    @FXML
    private ImageView readMoreIcn;
    @FXML
    private Button readMoreBtn;
    private LocalDate from;
    private LocalDate until;
    private int seatsNum = 0;
    private String massage = "";
    private String biggestFlightsInfo = "biggest Flights Reprot = return a report of all the flights that occurred in range of 'from' and 'until' fields,\nand thier plane  contain at least a given amount of  tourists seats.\nreturn fields: flight serial num, city and country of dep airport and dest airport, dep and land time and flight status\nsorted by: main: country + city desc order.\n           secnd: dep + land time desc order.\n";
    private Alert a = new Alert(Alert.AlertType.NONE);
    private Alert b = new Alert(Alert.AlertType.NONE);

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
        boolean numCheck = this.validateSeatsFld();
        boolean datesheck = this.validateUntilafterFrom();
        if (numCheck && datesheck) {
            this.from = (LocalDate)this.fromFld.getValue();
            this.until = (LocalDate)this.untilFld.getValue();
            this.seatsNum = Integer.parseInt(this.seatsNumFld.getText());
            JFrame fram = ReportsLogic.getInstance().compileBiggestFlights(this.seatsNum, this.from, this.until);
            fram.setVisible(true);
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

    private boolean validateSeatsFld() {
        String s = this.seatsNumFld.getText();
        if (s != null) {
            boolean ans = InputValidetions.validatePositiveIntegerOrZero(s);
            if (!ans) {
                this.a.setAlertType(Alert.AlertType.ERROR);
                this.a.setHeaderText("INPUT RERROR");
                this.a.setContentText("must be positive integer or 0!");
                this.a.setTitle("ERROR MESSAGE");
                this.a.show();
                this.seatsNumFld.setText(null);
                return false;
            }
            return true;
        }
        this.missingFldWarning();
        return false;
    }

    private boolean validateUntilafterFrom() {
        LocalDate from = (LocalDate)this.fromFld.getValue();
        LocalDate until = (LocalDate)this.untilFld.getValue();
        if (until != null && from != null) {
            boolean ans = InputValidetions.validateLastAferFirst(from.atTime(0, 0, 0), until.atTime(0, 0, 0));
            if (!ans) {
                this.b.setAlertType(Alert.AlertType.ERROR);
                this.b.setHeaderText("INPUT RERROR");
                this.b.setContentText("until date must be greater than from date");
                this.b.setTitle("ERROR MESSAGE");
                this.b.show();
                this.untilFld.setValue(null);
                this.untilFld.setPromptText(null);
                return false;
            }
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
