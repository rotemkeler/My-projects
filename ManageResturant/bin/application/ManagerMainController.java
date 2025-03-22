/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.application.Platform
 *  javafx.event.ActionEvent
 *  javafx.fxml.FXML
 *  javafx.fxml.Initializable
 *  javafx.scene.control.Button
 *  javafx.scene.control.Label
 *  javafx.scene.control.MenuBar
 *  javafx.scene.layout.AnchorPane
 */
package application;

import application.ControllerBase;
import application.MainController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;

public class ManagerMainController
implements Initializable {
    @FXML
    private Label txtWelcome;
    @FXML
    private Button saveSerialize;
    @FXML
    private Button cooks;
    @FXML
    private Button deliveryPersons;
    @FXML
    private Button customers;
    @FXML
    private Button dishes;
    @FXML
    private Button components;
    @FXML
    private Button orders;
    @FXML
    private Button deliveries;
    @FXML
    private Button areas;
    @FXML
    private MenuBar menu;
    @FXML
    private AnchorPane mainScreen;
    private ControllerBase cs = new ControllerBase();

    @FXML
    public void saveData(ActionEvent event) throws Exception {
        MainController.restSerialize();
    }

    @FXML
    public void exit(ActionEvent event) throws Exception {
        Platform.exit();
        System.exit(0);
    }

    public void logout(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/Login.fxml", this.mainScreen);
    }

    @FXML
    public void addCooksManagerScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/AddCook.fxml", this.mainScreen);
    }

    @FXML
    public void addDpManagerScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/AddDeliveryPerson.fxml", this.mainScreen);
    }

    @FXML
    public void addCustomerManagerScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/AddCustomer.fxml", this.mainScreen);
    }

    @FXML
    public void addDishManagerScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/AddDish.fxml", this.mainScreen);
    }

    @FXML
    public void addComponentManagerScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/AddComponent.fxml", this.mainScreen);
    }

    @FXML
    public void addOrderManagerScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/AddOrder.fxml", this.mainScreen);
    }

    @FXML
    public void addExpressDeliveryManagerScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/AddExpressDelivery.fxml", this.mainScreen);
    }

    @FXML
    public void addRegularDeliveryManagerScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/AddRegularDelivery.fxml", this.mainScreen);
    }

    @FXML
    public void addDaManagerScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/AddDeliveryArea.fxml", this.mainScreen);
    }

    @FXML
    public void addCustomerToBlManagerScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/AddToBlackList.fxml", this.mainScreen);
    }

    @FXML
    public void removeCooksManagerScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/RemoveCook.fxml", this.mainScreen);
    }

    @FXML
    public void removeDpManagerScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/RemoveDeliveryPerson.fxml", this.mainScreen);
    }

    @FXML
    public void removeCustomerManagerScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/RemoveCustomer.fxml", this.mainScreen);
    }

    @FXML
    public void removeDishManagerScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/RemoveDish.fxml", this.mainScreen);
    }

    @FXML
    public void removeComponentManagerScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/RemoveComponent.fxml", this.mainScreen);
    }

    @FXML
    public void removeOrderManagerScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/RemoveOrder.fxml", this.mainScreen);
    }

    @FXML
    public void removeDeliveryManagerScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/RemoveDelivery.fxml", this.mainScreen);
    }

    @FXML
    public void removeDaManagerScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/RemoveDeliveryArea.fxml", this.mainScreen);
    }

    @FXML
    public void deliveriesByPersonManagerScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/DeliveriesByPerson.fxml", this.mainScreen);
    }

    @FXML
    public void cookListManagerScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/CooksList.fxml", this.mainScreen);
    }

    @FXML
    public void dpListManagerScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/DeliveryPersonsList.fxml", this.mainScreen);
    }

    @FXML
    public void customersListManagerScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/CustomersList.fxml", this.mainScreen);
    }

    @FXML
    public void dishesListManagerScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/DishesList.fxml", this.mainScreen);
    }

    @FXML
    public void componentsListManagerScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/ComponentsList.fxml", this.mainScreen);
    }

    @FXML
    public void ordersListManagerScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/OrdersList.fxml", this.mainScreen);
    }

    @FXML
    public void deliveriesListManagerScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/DeliveriesList.fxml", this.mainScreen);
    }

    @FXML
    public void areasListManagerScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/AreasList.fxml", this.mainScreen);
    }

    @FXML
    public void getRelevantDishesManagerScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/GetRelevantDishes.fxml", this.mainScreen);
    }

    @FXML
    public void cooksByExpertiseManagerScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/GetCooksByExpertise.fxml", this.mainScreen);
    }

    @FXML
    public void deliveriesPerTypeManagerScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/DeliveriesPerType.fxml", this.mainScreen);
    }

    @FXML
    public void AIMechineManagerScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/AIMechine.fxml", this.mainScreen);
    }

    @FXML
    public void popularComponentsManagerScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/PopularComponents.fxml", this.mainScreen);
    }

    @FXML
    public void financeDetailsSceneManagerScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/FinanceDetails.fxml", this.mainScreen);
    }

    public void initialize(URL arg0, ResourceBundle arg1) {
    }
}
