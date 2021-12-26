package gr.ihu.ermistv;

import java.io.IOException;
import java.sql.*;

import javafx.fxml.FXML;
import javafx.application.Platform;
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
//            if (queryResult.next() == false) { System.out.println("ResultSet in empty in Java"); }
//            System.out.println(queryResult.next());
//            System.out.println(queryResult.getString("username"));
            queryResult.next();
            if(queryResult.getString("username") != null){
                messageLabel.setStyle("-fx-text-fill: green");
                messageLabel.setText("Congratulations!");
                App.setRoot("fxml/secondary");
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
        App.setRoot("fxml/secondary");
    }
}
