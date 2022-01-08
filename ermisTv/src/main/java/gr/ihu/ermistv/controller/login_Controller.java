package gr.ihu.ermistv.controller;

import java.io.IOException;
import java.sql.*;

import gr.ihu.ermistv.App;
import gr.ihu.ermistv.DBConnection;
import gr.ihu.ermistv.ScenesSet;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class login_Controller implements Initializable, Runnable {
    @FXML
    private AnchorPane primary;
    @FXML
    private TextField fdUser, fdPass;
    @FXML
    private Label messageLabel, errLabel;
    @FXML
    private HBox reconnect;

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
    public void Login(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            validateLogin();
        }

    }

    @FXML
    private void validateLogin() throws IOException {

        String user = String.valueOf(fdUser.getText());
        String pass = fdPass.getText();
        String verifyLogin = "select * from checkaccount('" + user + "','" + pass + "');";
        boolean bypass = false;

        try {

            Statement statement = DBConnection.c.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            queryResult.next();
            if (queryResult.getInt(1) == 0 || bypass) {

                App.cacheduseer = user;
                App.cachedpass = pass;

                messageLabel.setStyle("-fx-text-fill: green");
                messageLabel.setText("Congratulations!");

                Stage stage = (Stage) primary.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/secondary.fxml"));
                Parent root;
                root = fxmlLoader.load();
                Scene scene = new ScenesSet(root, stage, 1024, 550, "#Hbox");
                fxmlLoader.getController();
                App.controller = fxmlLoader.getController();
                stage.setScene(scene);

                stage.setX(stage.getX() - 200);
            } else {
                messageLabel.setText("Invalid login. Please try again !");
            }
            statement.close();
            queryResult.close();

        } catch (SQLException ex) {
            reconnect.setVisible(true);
        }
    }

    @FXML
    private void validateRegister(MouseEvent event) throws IOException {
        Stage stage = (Stage) primary.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/register.fxml"));
        Parent root;
        root = fxmlLoader.load();
        Scene scene = new ScenesSet(root, stage, 459, 671, "#Hbox");

        stage.setScene(scene);

        stage.setX(stage.getX() + 100);

    }

    @FXML
    private void reconnect(MouseEvent event) {
        if (!running) {
            Thread thread = new Thread(this);
            thread.start();
        }
    }

    private boolean running = false;

    @Override
    public void run() {
        running = true;
        try {
            DBConnection.connect();
            reconnect.setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(login_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        running = false;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            DBConnection.connect();

        } catch (SQLException ex) {
            System.out.println("reconnect failed");
        }
    }
}
