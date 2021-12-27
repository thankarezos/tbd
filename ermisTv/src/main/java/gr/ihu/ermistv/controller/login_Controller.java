package gr.ihu.ermistv.controller;

import java.io.IOException;
import java.sql.*;

import gr.ihu.ermistv.App;
//import gr.ihu.ermistv.DBConnection;
import gr.ihu.ermistv.DBConnection;
import gr.ihu.ermistv.ScenesSet;
import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

public class login_Controller {
    @FXML
    private AnchorPane primary;

    @FXML
    private TextField fdUser, fdPass;

    @FXML
    private Label messageLabel;

    @FXML
    private void minimizedWindow(MouseEvent event) {
        Stage stage = (Stage) primary.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void closeWindow(MouseEvent event) {
        System.exit(0);
        Platform.exit();
    }

    @FXML
    private void validateLogin() throws SQLException, IOException {

        String user  =  String.valueOf(fdUser.getText());
        String pass =  fdPass.getText();
        String verifyLogin = "select * from checkaccount('" + user + "','" + pass +"');";
        System.out.println("select * from checkaccount('" + String.valueOf(fdUser.getText()) + "','" + fdPass.getText() +"')");

        try {

            Statement statement = DBConnection.c.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            queryResult.next();
            if(queryResult.getString("username") != null){
                messageLabel.setStyle("-fx-text-fill: green");
                messageLabel.setText("Congratulations!");
                Stage stage = (Stage) primary.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/secondary.fxml"));

                Parent root;
                try {
                    root = fxmlLoader.load();

                    Scene scene = new ScenesSet(root,stage,1100, 700);

                    stage.setScene(scene);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                messageLabel.setText("Invalid login. Please try again !");
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    @FXML
    private void switchToSecondary() throws IOException {
    }
}
