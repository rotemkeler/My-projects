package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
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
import model.Restaurant;

public class ControllerBase implements Initializable{
	
	@FXML
	public ImageView customerPhoto;
	
	@FXML
	public Label alertPhoto;
	
	public static String name;
	
	public static Image image = null;
	
	public String imagePath = null;
	
	FileChooser fileChooser = new FileChooser();
	
	public void backToManagerMainWindow(AnchorPane sceneName) throws Exception{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ManagerMainWindow.fxml"));
		AnchorPane pane = loader.load();
		sceneName.getChildren().removeAll(sceneName.getChildren());
		sceneName.getChildren().add(pane);
	}
	
	//method to change screen
	public void changeScreen(String resourceName,AnchorPane sceneName) throws Exception{
		FXMLLoader loader = new FXMLLoader(getClass().getResource(resourceName));
		AnchorPane pane = loader.load();
		sceneName.getChildren().removeAll(sceneName.getChildren());
		sceneName.getChildren().add(pane);
		
	}
	
	public void backToCustomerMainWindow(AnchorPane sceneName) throws Exception{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/CustomerMainWindow.fxml"));
		AnchorPane pane = loader.load();
		sceneName.getChildren().removeAll(sceneName.getChildren());
		sceneName.getChildren().add(pane);
	}
	
	//View a customer image
	public void showCustomerPhoto(ImageView customerPhoto ) {
		if(customerPhoto == null) {
			return;
		}
		File folder = new File(System.getProperty("user.dir"));
		File[] listOfFiles = folder.listFiles();
		if(listOfFiles!=null)
		{
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile() && listOfFiles[i].getName().startsWith("profile.")) {
					 try {
						customerPhoto.setImage(new Image(new FileInputStream(System.getProperty("user.dir")+"\\"+listOfFiles[i].getName())));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				} 
			}	
		}
	}
	
	//Add a customer photo or edit photo
	public void addNewPhoto(ActionEvent event) throws Exception {
		File selectedFile = fileChooser.showOpenDialog(MainController.stage);
		if (selectedFile != null) {
			imagePath = selectedFile.getAbsolutePath();
			image = new Image(new FileInputStream(selectedFile.getAbsolutePath()));
			customerPhoto.setImage(image);
		} else {
			alertPhoto.setText("You have to \n"
					+ "choose photo");
		}
	}
	
	public String getNewPath() throws IOException {
		String extension = "";
		int i = imagePath.lastIndexOf('.');
		if (i > 0) {
			extension = imagePath.substring(i + 1);
		}
		String newPath = "profile"+NewAccountController.name+"." + extension;
		copyFile(new File(imagePath), new File(newPath));
		return newPath;
	}
	
	private void copyFile(File source, File dest) throws IOException {
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(source);
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		} finally {
			is.close();
			os.close();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Account a=Restaurant.getInstance().getAccounts().get(NewAccountController.name);
		if(a != null) {
			File folder = new File(System.getProperty("user.dir"));
			File[] listOfFiles = folder.listFiles();
			if(listOfFiles!=null){	
				
				if (a.getImage() != null && !a.isManager()) {
					 try {
						customerPhoto.setImage(new Image(new FileInputStream(System.getProperty("user.dir")+"\\"+a.getImage())));
					} 
					catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}	
		}
	}
}
