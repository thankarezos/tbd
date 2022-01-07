package gr.ihu.ermistv.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.stage.Stage;
import javafx.scene.Scene;
import gr.ihu.ermistv.App;
import gr.ihu.ermistv.DBConnection;
import gr.ihu.ermistv.ScenesSet;
import java.sql.SQLException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.event.EventHandler;

public class Secondary_Controller implements Initializable {
    @FXML
    private AnchorPane secondary, paneEkpompi, paneProgram,paneLog;
    @FXML
    private Button btnEkpompi, btnProgram, btnLogout,btnLog;
    @FXML
    private Button btnSyntelestes;
    @FXML
    private AnchorPane paneSyntelestes;
    @FXML
    private TextArea infoArea;

    String color = "-fx-background-color: #F5F6F8;";
    // String color1 ="-fx-background-color: linear-gradient(#029EAC, #02A2B1);
    // -fx-text-fill: white;";
    // String color2 ="-fx-background-color: linear-gradient(#02A5B5, #02A9BA);
    // -fx-text-fill: white;";
    // String color3 ="-fx-background-color: linear-gradient(#02ABBD, #02AFC2);
    // -fx-text-fill: white;";
    String color1 = "-fx-background-color: linear-gradient(#027F87, #02838C); -fx-text-fill: white;";
    String color2 = "-fx-background-color: linear-gradient(#02858F, #028994); -fx-text-fill: white;";
    String color3 = "-fx-background-color: linear-gradient(#028C98, #02909C); -fx-text-fill: white;";

    // Minimize Window
    @FXML
    private void minimizedWindow(MouseEvent event) {
        Stage stage = (Stage) secondary.getScene().getWindow();
        stage.setIconified(true);
    }

    // Close Window
    @FXML
    private void closeWindow(MouseEvent event) {
        System.exit(0);
        Platform.exit();
    }

    // Logout
    @FXML
    private void Logout(ActionEvent event) throws IOException {

        loginBack();
    }

    // clear Color
    private void clearColor() {
        btnProgram.setStyle(color);
        btnEkpompi.setStyle(color);
        btnSyntelestes.setStyle(color);
        btnLog.setStyle(color);
    }

    // Handle Clicks Popup
    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
        if (event.getSource() == btnProgram) {
            

            
        } else if (event.getSource() == btnEkpompi) {
            
            
            
        } else if (event.getSource() == btnSyntelestes) {
            
            
        }else if (event.getSource() == btnLog){

        }
    }

    public void errorMessage(String error) throws IOException {
        infoArea.setWrapText(true);
        infoArea.appendText(error);
        infoArea.appendText("\n");
        try {
            if (DBConnection.c.isClosed()) {
                loginBack();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            ex.getCause();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        btnProgram.setStyle(color1);
        loadtabs();
        

    }

    private void loginBack() throws IOException {
        try {
            Stage stage = (Stage) secondary.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/login_view.fxml"));
            Parent root;
            root = fxmlLoader.load();
            Scene scene = new ScenesSet(root, stage, 640, 480, "#Hbox");
            stage.setScene(scene);
            stage.setX(stage.getX() + 200);
            DBConnection.c.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            ex.getCause();
        }

    }
    
    
    private void loadProgram(){
        Parent root;
        try {
            
            FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/program.fxml"));
            root = loader.load();
            Program_Controller controller = loader.getController();
            
                    
            btnProgram.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    clearColor();
                    btnProgram.setStyle(color1);
                    paneProgram.toFront();
                    controller.loadPrograms();

                }

            });
            
           
            paneProgram.getChildren().add(root);
        } catch (IOException ex) {
            ex.printStackTrace();
            ex.getCause();
        }
    }
    private void loadEkpompi(){
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/ekpompi.fxml"));
            root = loader.load();
            Ekpompi_Controller controller = loader.getController();

                    
            btnEkpompi.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    clearColor();
                    btnEkpompi.setStyle(color2);
                    paneEkpompi.toFront();
                    controller.reloadPage();

                }

            });
            controller.setAp(paneEkpompi);
            paneEkpompi.getChildren().add(root);
        } catch (IOException ex) {
            ex.printStackTrace();
            ex.getCause();
        }
    }
    
    private void loadSyntelestes(){
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/syntelestes.fxml"));
            root = loader.load();
            Syntelestes_Contoller controller = loader.getController();
            
                    
            btnSyntelestes.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    clearColor();
                    btnSyntelestes.setStyle(color2);
                    paneSyntelestes.toFront();
                    controller.reloadFactor();

                }

            });
            
            paneSyntelestes.getChildren().add(root);
        } catch (IOException ex) {
            ex.printStackTrace();
            ex.getCause();
        }
    }
    private void loadLog(){
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Log.fxml"));
            root = loader.load();
            Log_Controller controller = loader.getController();
            
                    
            btnLog.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    clearColor();
                    btnLog.setStyle(color2);
                    paneLog.toFront();
                    try {
                        controller.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            });
            
            
            paneLog.getChildren().add(root);
        } catch (IOException ex) {
            ex.printStackTrace();
            ex.getCause();
        }
    }
    
    @FXML
    private void loadtabs(){
        
        loadProgram();
        loadEkpompi();
        loadSyntelestes();
        loadLog();
        
        
    }
}
