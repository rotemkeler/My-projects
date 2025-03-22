/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.event.ActionEvent
 *  javafx.fxml.FXML
 *  javafx.fxml.FXMLLoader
 *  javafx.fxml.Initializable
 *  javafx.scene.control.Label
 *  javafx.scene.image.Image
 *  javafx.scene.image.ImageView
 *  javafx.scene.layout.AnchorPane
 *  javafx.stage.FileChooser
 *  javafx.stage.Window
 */
package application;

import application.Account;
import application.MainController;
import application.NewAccountController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import model.Restaurant;

public class ControllerBase
implements Initializable {
    @FXML
    public ImageView customerPhoto;
    @FXML
    public Label alertPhoto;
    public static String name;
    public static Image image;
    public String imagePath = null;
    FileChooser fileChooser = new FileChooser();

    static {
        image = null;
    }

    public void backToManagerMainWindow(AnchorPane sceneName) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/View/ManagerMainWindow.fxml"));
        AnchorPane pane = (AnchorPane)loader.load();
        sceneName.getChildren().removeAll((Collection)sceneName.getChildren());
        sceneName.getChildren().add((Object)pane);
    }

    public void changeScreen(String resourceName, AnchorPane sceneName) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(resourceName));
        AnchorPane pane = (AnchorPane)loader.load();
        sceneName.getChildren().removeAll((Collection)sceneName.getChildren());
        sceneName.getChildren().add((Object)pane);
    }

    public void backToCustomerMainWindow(AnchorPane sceneName) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/View/CustomerMainWindow.fxml"));
        AnchorPane pane = (AnchorPane)loader.load();
        sceneName.getChildren().removeAll((Collection)sceneName.getChildren());
        sceneName.getChildren().add((Object)pane);
    }

    public void showCustomerPhoto(ImageView customerPhoto) {
        if (customerPhoto == null) {
            return;
        }
        File folder = new File(System.getProperty("user.dir"));
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            int i = 0;
            while (i < listOfFiles.length) {
                if (listOfFiles[i].isFile() && listOfFiles[i].getName().startsWith("profile.")) {
                    try {
                        customerPhoto.setImage(new Image((InputStream)new FileInputStream(String.valueOf(System.getProperty("user.dir")) + "\\" + listOfFiles[i].getName())));
                    }
                    catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                ++i;
            }
        }
    }

    public void addNewPhoto(ActionEvent event) throws Exception {
        File selectedFile = this.fileChooser.showOpenDialog((Window)MainController.stage);
        if (selectedFile != null) {
            this.imagePath = selectedFile.getAbsolutePath();
            image = new Image((InputStream)new FileInputStream(selectedFile.getAbsolutePath()));
            this.customerPhoto.setImage(image);
        } else {
            this.alertPhoto.setText("You have to \nchoose photo");
        }
    }

    public String getNewPath() throws IOException {
        String extension = "";
        int i = this.imagePath.lastIndexOf(46);
        if (i > 0) {
            extension = this.imagePath.substring(i + 1);
        }
        String newPath = "profile" + NewAccountController.name + "." + extension;
        this.copyFile(new File(this.imagePath), new File(newPath));
        return newPath;
    }

    private void copyFile(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            int length;
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        }
        finally {
            is.close();
            os.close();
        }
    }

    public void initialize(URL arg0, ResourceBundle arg1) {
        File folder;
        File[] listOfFiles;
        Account a = Restaurant.getInstance().getAccounts().get(NewAccountController.name);
        if (a != null && (listOfFiles = (folder = new File(System.getProperty("user.dir"))).listFiles()) != null && a.getImage() != null && !a.isManager()) {
            try {
                this.customerPhoto.setImage(new Image((InputStream)new FileInputStream(String.valueOf(System.getProperty("user.dir")) + "\\" + a.getImage())));
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
