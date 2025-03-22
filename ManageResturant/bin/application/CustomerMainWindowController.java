/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.event.ActionEvent
 *  javafx.fxml.FXML
 *  javafx.scene.control.Button
 *  javafx.scene.control.Label
 *  javafx.scene.image.Image
 *  javafx.scene.image.ImageView
 *  javafx.scene.layout.AnchorPane
 */
package application;

import application.Account;
import application.ControllerBase;
import application.MainController;
import application.NewAccountController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Restaurant;

public class CustomerMainWindowController
extends ControllerBase {
    @FXML
    private AnchorPane mainCustomerScene;
    @FXML
    private Label txtWelcome;
    @FXML
    private Button logout;
    @FXML
    private Button menu;
    @FXML
    private Button shoppingCart;
    @FXML
    private Button personalInformation;
    @FXML
    private Button orders;
    @FXML
    private Button dishes;
    @FXML
    private Button relevantDish;
    @FXML
    private Button cookByExpertise;
    @FXML
    private Button popularComponents;
    @FXML
    private Button saveSerialize;
    @FXML
    public ImageView customerPhoto;
    private ControllerBase cs = new ControllerBase();

    @FXML
    public void saveData(ActionEvent event) throws Exception {
        MainController.restSerialize();
    }

    @FXML
    public void logout(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/Login.fxml", this.mainCustomerScene);
    }

    @FXML
    public void ordersCustomerScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/OrdersCustomerAction.fxml", this.mainCustomerScene);
    }

    @FXML
    public void shoppingCartCustomerScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/ShoppingCart.fxml", this.mainCustomerScene);
    }

    public void personalInformationScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/PersonalInformation.fxml", this.mainCustomerScene);
    }

    public void menuScene(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/Menu.fxml", this.mainCustomerScene);
    }

    public void relevantDishes(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/RelevantDishesOfCustomer.fxml", this.mainCustomerScene);
    }

    public void cooksByExpertiseCustomer(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/CooksByExpertiseCustomer.fxml", this.mainCustomerScene);
    }

    public void popularComponentsCustomer(ActionEvent event) throws Exception {
        this.cs.changeScreen("/View/PopularComponentsCustomer.fxml", this.mainCustomerScene);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Account a = Restaurant.getInstance().getAccounts().get(NewAccountController.name);
        if (a != null && a.getImage() != null) {
            try {
                this.customerPhoto.setImage(new Image((InputStream)new FileInputStream(a.getImage())));
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
