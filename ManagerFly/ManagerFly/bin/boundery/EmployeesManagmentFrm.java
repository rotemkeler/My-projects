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
 *  javafx.scene.layout.Pane
 */
package boundery;

import control.AssignLogic;
import control.Getters;
import entity.AirAttendant;
import entity.Employee;
import entity.GroundAttendant;
import entity.Pilot;
import exceptions.InvalidInputException;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
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
import javafx.scene.layout.Pane;
import util.Alerts;
import util.InputValidetions;

public class EmployeesManagmentFrm {
    @FXML
    private AnchorPane ManagmentPane;
    @FXML
    private Label firmTitle;
    @FXML
    private TextField IDFld;
    @FXML
    private ComboBox<Employee> EmpCmbBx;
    @FXML
    private DatePicker ContractFinishDate;
    @FXML
    private Button updateBtn;
    @FXML
    private TextField fNameFld;
    @FXML
    private TextField lNameFld;
    @FXML
    private Button nextBtn;
    @FXML
    private Button pervBtn;
    @FXML
    private DatePicker ContractStartDate;
    @FXML
    private ComboBox<String> EmpRoleCombo;
    private ObservableList<String> empRolesList = FXCollections.observableArrayList((Object[])new String[]{"Air Attendant", "Ground Attendant", "pilot"});
    @FXML
    private Pane PilotPane;
    @FXML
    private TextField LicenceIDFld;
    @FXML
    private DatePicker issuedDate;
    @FXML
    private Button loadEmptyFrmBtn;
    @FXML
    private Button addBtn;
    private Tooltip pervtooltip;
    private Tooltip nexttooltip;
    private Tooltip searchtooltip;
    private ArrayList<Employee> empArrList;
    private int currentEmpIndex;
    private Employee currentEmployee;
    private boolean inEditMode;
    private Alert a = new Alert(Alert.AlertType.NONE);
    private HashMap<String, Employee> empMap;

    @FXML
    public void initialize() {
        this.init();
    }

    private void init() {
        this.ContractStartDate.setOpacity(1.0);
        this.issuedDate.setOpacity(1.0);
        this.notInEdit();
        ArrayList<AirAttendant> airArr = Getters.getInstance().getAirAttendants();
        ArrayList<GroundAttendant> groundArr = Getters.getInstance().getGroundAttendants();
        ArrayList<Pilot> pilotsArr = Getters.getInstance().getPilots();
        this.empArrList = new ArrayList();
        this.empArrList.addAll(pilotsArr);
        this.empArrList.addAll(groundArr);
        this.empArrList.addAll(airArr);
        this.inEditMode = false;
        this.pervtooltip = new Tooltip();
        this.nexttooltip = new Tooltip();
        this.searchtooltip = new Tooltip();
        this.pervtooltip.setText("previous employee");
        this.nexttooltip.setText("next employee");
        this.searchtooltip.setText("search");
        this.pervBtn.setTooltip(this.pervtooltip);
        this.nextBtn.setTooltip(this.nexttooltip);
        this.IDFld.setTooltip(this.searchtooltip);
        this.currentEmpIndex = 0;
        this.EmpRoleCombo.setItems(this.empRolesList);
        this.EmpCmbBx.getItems().addAll(this.empArrList);
        if (!this.empArrList.isEmpty()) {
            this.EmpCmbBx.setValue((Object)this.empArrList.get(this.currentEmpIndex));
            this.empMap = new HashMap();
            for (Employee emloyee : this.empArrList) {
                this.empMap.put(emloyee.getID(), emloyee);
            }
            this.loadEmpByCmb(new ActionEvent());
        }
    }

    @FXML
    void LoadEmp(KeyEvent event) {
        String s = this.IDFld.getText();
        if (s != null && !s.isEmpty()) {
            Employee e = null;
            e = this.empMap.get(s);
            if (e != null) {
                this.currentEmployee = (Employee)this.EmpCmbBx.getValue();
                this.currentEmpIndex = this.empArrList.indexOf(e);
                this.EmpCmbBx.setValue((Object)e);
                this.notInEdit();
                this.setFields();
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @FXML
    void UpdateData(ActionEvent event) {
        String id = this.IDFld.getText();
        LocalDate finish = (LocalDate)this.ContractFinishDate.getValue();
        String fName = this.fNameFld.getText();
        String lName = this.lNameFld.getText();
        String query = ((String)this.EmpRoleCombo.getValue()).equals("pilot") ? "{ call qryUpdPilot(?,?,?,?) }" : (((String)this.EmpRoleCombo.getValue()).equals("Air Attendant") ? "{ call qryUpdAirAttendant(?,?,?,?) }" : "{ call qryUpdGroundAttendant(?,?,?,?) }");
        try {
            if (id == null || id.isEmpty() || !this.empMap.containsKey(id) || !InputValidetions.validateName(fName) || !InputValidetions.validateName(lName) || fName == null || lName == null || fName.isEmpty() || lName.isEmpty()) throw new InvalidInputException("Invalid input!");
            if ((finish == null || !finish.isAfter((ChronoLocalDate)this.ContractStartDate.getValue())) && finish != null) throw new InvalidInputException("contract finish date must be after contract start date!");
            if (!AssignLogic.getInstance().editEmployee(id, fName, lName, finish, query)) {
                throw new Exception();
            }
            this.a = Alerts.infoAlert("Updated successfully!");
            this.a.show();
            this.currentEmployee.setFirstName(fName);
            this.currentEmployee.setLastName(lName);
            this.currentEmployee.setContractFinish(finish);
            return;
        }
        catch (InvalidInputException inputE) {
            this.a = Alerts.errorAlert(inputE.getMessage());
            this.a.show();
            return;
        }
        catch (Exception e) {
            this.a = Alerts.errorAlert("Update Failed!");
            this.a.show();
        }
    }

    @FXML
    void addEmp(ActionEvent event) {
        boolean ans;
        int type;
        String id = this.IDFld.getText();
        LocalDate start = (LocalDate)this.ContractStartDate.getValue();
        LocalDate finish = (LocalDate)this.ContractFinishDate.getValue();
        String fName = this.fNameFld.getText();
        String lName = this.lNameFld.getText();
        String issued = null;
        LocalDate issuedDate = null;
        int n = ((String)this.EmpRoleCombo.getValue()).equals("pilot") ? 1 : (type = ((String)this.EmpRoleCombo.getValue()).equals("Air Attendant") ? 2 : 3);
        if (type == 1) {
            issued = this.LicenceIDFld.getText();
            issuedDate = (LocalDate)this.issuedDate.getValue();
        }
        if (ans = this.validateFields(id, start, finish, fName, lName, issued, issuedDate, type)) {
            if (type == 1) {
                ans = AssignLogic.getInstance().addPilot(id, fName, lName, start, finish, issued, issuedDate);
                Pilot p = new Pilot(id, fName, lName, start, finish, issued, issuedDate);
                this.addtoDataStructures(p);
            } else if (type == 2) {
                ans = AssignLogic.getInstance().addAirAttendant(id, fName, lName, start, finish);
                AirAttendant at = new AirAttendant(id, fName, lName, start, finish);
                this.addtoDataStructures(at);
            } else {
                ans = AssignLogic.getInstance().addGroundAttendant(id, fName, lName, start, finish);
                GroundAttendant ga = new GroundAttendant(id, fName, lName, start, finish);
                this.addtoDataStructures(ga);
            }
            if (ans) {
                this.a = Alerts.infoAlert("Added successfully!");
                this.a.show();
                this.notInEdit();
                this.EmpCmbBx.setValue((Object)((Employee)this.EmpCmbBx.getItems().get(this.empArrList.size() - 1)));
                this.loadEmpByCmb(new ActionEvent());
            } else {
                this.a = Alerts.errorAlert("Failed to add!");
                this.a.show();
            }
        }
    }

    @FXML
    void loadEmpByCmb(ActionEvent event) {
        if (this.EmpCmbBx.getValue() != null) {
            this.currentEmployee = (Employee)this.EmpCmbBx.getValue();
            this.deterMineClass(this.currentEmployee);
            this.currentEmpIndex = this.empArrList.indexOf(this.currentEmployee);
            this.IDFld.setText(String.valueOf(this.currentEmployee.getID()));
            this.notInEdit();
            this.setFields();
        }
    }

    @FXML
    void loadEmptyFrm(ActionEvent event) {
        this.inEdit();
    }

    @FXML
    void loadNextEmp(ActionEvent event) {
        if (this.currentEmpIndex + 1 < this.empArrList.size()) {
            ++this.currentEmpIndex;
            this.EmpCmbBx.setValue((Object)this.empArrList.get(this.currentEmpIndex));
            this.loadEmpByCmb(new ActionEvent());
        } else {
            this.a.setAlertType(Alert.AlertType.INFORMATION);
            this.a.setHeaderText("MESSAGE");
            this.a.setTitle("SYSTEM MESSAGE");
            this.a.setContentText("Last in the list!");
            this.a.show();
        }
    }

    @FXML
    void loadPervEmp(ActionEvent event) {
        if (this.currentEmpIndex - 1 >= 0) {
            --this.currentEmpIndex;
            this.EmpCmbBx.setValue((Object)this.empArrList.get(this.currentEmpIndex));
            this.loadEmpByCmb(new ActionEvent());
        } else {
            this.a.setAlertType(Alert.AlertType.INFORMATION);
            this.a.setHeaderText("MESSAGE");
            this.a.setTitle("SYSTEM MESSAGE");
            this.a.setContentText("First in the list!");
            this.a.show();
        }
    }

    @FXML
    void changeAddMode(ActionEvent event) {
        if (this.inEditMode) {
            this.inEdit();
        }
    }

    private void faildtoAddMsg() {
        this.a.setAlertType(Alert.AlertType.ERROR);
        this.a.setHeaderText("MESSAGE");
        this.a.setTitle("SYSTEM MESSAGE");
        this.a.setContentText("Operation Faild");
        this.a.show();
    }

    private void setFields() {
        this.ContractStartDate.setValue((Object)this.currentEmployee.getContractStart());
        this.ContractFinishDate.setValue((Object)this.currentEmployee.getContractFinish());
        this.fNameFld.setText(this.currentEmployee.getFirstName());
        this.lNameFld.setText(this.currentEmployee.getLastName());
    }

    private void deterMineClass(Employee currentEmployee) {
        if (currentEmployee instanceof Pilot) {
            this.PilotPane.setVisible(true);
            this.EmpRoleCombo.setValue((Object)"pilot");
            Pilot p = (Pilot)currentEmployee;
            this.issuedDate.setValue((Object)p.getIssuedDate());
            this.LicenceIDFld.setText(p.getLicenceID());
            this.firmTitle.setText("Pilot Details:");
        } else {
            this.PilotPane.setVisible(false);
            if (currentEmployee instanceof AirAttendant) {
                this.EmpRoleCombo.setValue((Object)"Air Attendant");
                this.firmTitle.setText("Air Attendant Details:");
            } else {
                this.EmpRoleCombo.setValue((Object)"Ground Attendant");
                this.firmTitle.setText("Ground Attendant Details:");
            }
        }
    }

    private void inEdit() {
        String empType;
        this.inEditMode = true;
        this.addBtn.setOpacity(1.0);
        this.addBtn.setDisable(false);
        this.updateBtn.setDisable(true);
        this.updateBtn.setOpacity(0.4);
        this.IDFld.setText(null);
        this.LicenceIDFld.setEditable(true);
        this.fNameFld.setText(null);
        this.lNameFld.setText(null);
        this.issuedDate.setDisable(false);
        this.issuedDate.setEditable(true);
        this.ContractStartDate.setEditable(true);
        this.ContractStartDate.setDisable(false);
        this.ContractStartDate.setValue(null);
        this.ContractFinishDate.setValue(null);
        this.LicenceIDFld.setText(null);
        this.issuedDate.setValue(null);
        this.firmTitle.setText("Add New Employee:");
        if (((String)this.EmpRoleCombo.getValue()).equals("pilot")) {
            this.PilotPane.setVisible(true);
            empType = "Pilot";
        } else {
            this.PilotPane.setVisible(false);
            empType = ((String)this.EmpRoleCombo.getValue()).equals("Air Attendant") ? "Air Attendant" : "Ground Attendant";
        }
        this.firmTitle.setText("Add New " + empType + ":");
    }

    private void notInEdit() {
        this.inEditMode = false;
        this.addBtn.setOpacity(0.4);
        this.updateBtn.setDisable(false);
        this.updateBtn.setOpacity(1.0);
        this.addBtn.setDisable(true);
        this.ContractStartDate.setEditable(false);
        this.ContractStartDate.setDisable(true);
        this.deterMineClass(this.currentEmployee);
        this.LicenceIDFld.setEditable(false);
        this.issuedDate.setDisable(true);
        this.issuedDate.setEditable(false);
        this.LicenceIDFld.setEditable(false);
    }

    private boolean validateFields(String id, LocalDate start, LocalDate finish, String fName, String lName, String issued, LocalDate issuedDate, int type) {
        try {
            if (id == null || id.isEmpty() || start == null || fName.isEmpty() || fName == null || lName.isEmpty() || lName == null) {
                throw new InvalidInputException("empty fields");
            }
            if (type == 1) {
                if (issued.isEmpty() || issued == null || issuedDate == null) {
                    throw new InvalidInputException("empty fields");
                }
                if (issuedDate.isAfter(start)) {
                    throw new InvalidInputException("Licence issued date must be before or equal to contract start date!");
                }
                if (issued.length() > 12) {
                    throw new InvalidInputException("max field size of Licence ID  is 12!");
                }
                if (!InputValidetions.validatePositiveIntegerOrZero(issued)) {
                    throw new InvalidInputException("Licence ID  can contain digits only!");
                }
            }
            if (!InputValidetions.validateName(fName) || !InputValidetions.validateName(lName)) {
                throw new InvalidInputException("name must contain letters only!");
            }
            if (finish != null && finish.isBefore(start)) {
                throw new InvalidInputException("contract finish date must be after contract start date!");
            }
            if (!InputValidetions.validatePositiveIntegerOrZero(id)) {
                throw new InvalidInputException("Licence ID  can contain digits only!");
            }
            if (id.length() != 9) {
                throw new InvalidInputException("id must conatin 9 digits!");
            }
        }
        catch (InvalidInputException inputE) {
            this.a = Alerts.errorAlert(inputE.getMessage());
            this.a.show();
            return false;
        }
        catch (Exception e) {
            this.a = Alerts.errorAlert("error");
            this.a.show();
            return false;
        }
        return true;
    }

    private void addtoDataStructures(Employee emp) {
        if (emp != null) {
            this.empMap.put(emp.getID(), emp);
            this.empArrList.add(emp);
            this.EmpCmbBx.getItems().add((Object)emp);
        }
    }
}
