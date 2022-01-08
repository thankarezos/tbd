package gr.ihu.ermistv.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.Scene;
import gr.ihu.ermistv.App;
import gr.ihu.ermistv.CrunchifyGetPropertyValues;
import gr.ihu.ermistv.DBConnection;
import static gr.ihu.ermistv.DBConnection.c;
import gr.ihu.ermistv.ScenesSet;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.layout.HBox;

public class Secondary_Controller extends Thread implements Initializable {
    @Override
     public void run()  
    {    
        String verifyLogin = "select * from checkaccount('" + App.cacheduseer + "','" + App.cachedpass + "');";
        System.out.println("test");
        

        try {
            DBConnection.connect();
            Statement statement = DBConnection.c.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            queryResult.next();
            if (queryResult.getInt(1) != 0) {
                loginBack();
            }
            else{
                reconnect.setVisible(false);
            }
            statement.close();
            queryResult.close();

        } catch (IOException ex) {
            Logger.getLogger(Secondary_Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.out.println("reconnect failed");
        }
    } 
    @FXML
    private AnchorPane secondary, paneEkpompi, paneProgram,paneLog;
    @FXML
    private Button btnEkpompi, btnProgram, btnLogout,btnLog;
    @FXML
    private Button btnSyntelestes;
    @FXML
    private AnchorPane paneSyntelestes;
    @FXML
    private TextFlow infoArea;
    
    @FXML
    private HBox reconnect;
//    private TextArea infoArea;

    String color = "-fx-background-color: #F5F6F8;";
    String color1 = "-fx-background-color: linear-gradient(#027F87, #02838C); -fx-text-fill: white;";
    String color2 = "-fx-background-color: linear-gradient(#02858F, #028994); -fx-text-fill: white;";
    String color3 = "-fx-background-color: linear-gradient(#028C98, #02909C); -fx-text-fill: white;";

    // Minimize Window
    
    @FXML
    private void reconnect(MouseEvent event) throws IOException{
        System.out.println("Reconnecting");
        this.start();
        

    }
    
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
    private void Logout(ActionEvent event) throws IOException, SQLException {

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

    public void errorMessage(Integer num,String error){
        String color = "";
        if(num == 0){
            color = "-fx-fill: black";
        }else if(num == 1){
            color = "-fx-fill: red";
        }else if(num == 2){
            color = "-fx-fill: green";
        }
        Text t1 = new Text();
        try {
            if (DBConnection.c.isClosed()) {
                reconnect.setVisible(true);
                
                return;
            }
        } catch (SQLException ex) {

        }

        
        t1.setStyle(color);
        t1.setText(error);
        Text t2 = new Text();
        t2.setText("\n");
        infoArea.getChildren().addAll(t1,t2);
        
        

    }
    
    public void errorMessage(){
        Text t1 = new Text();
        try {
            if (DBConnection.c.isClosed()) {
                reconnect.setVisible(true);
                return;
            }
        } catch (SQLException ex) {
        }
    }
    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        btnProgram.setStyle(color1);
        loadtabs();
        

    }

    private void loginBack() throws IOException, SQLException {
        DBConnection.c.close();
        Stage stage = (Stage) secondary.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/login_view.fxml"));
        Parent root;
        root = fxmlLoader.load();
        Scene scene = new ScenesSet(root, stage, 640, 480, "#Hbox");
        stage.setScene(scene);
        stage.setX(stage.getX() + 200);
        

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
                    controller.load();

                }

            });
            
            
            paneLog.getChildren().add(root);
        } catch (IOException ex) {
            ex.printStackTrace();
            ex.getCause();
        }
    }
    

    private void loadtabs(){
        
        loadProgram();
        loadEkpompi();
        loadSyntelestes();
        loadLog();
        
        
    }
}
