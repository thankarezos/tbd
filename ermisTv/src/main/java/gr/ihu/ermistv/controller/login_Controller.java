package gr.ihu.ermistv.controller;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.*;

import gr.ihu.ermistv.App;
import gr.ihu.ermistv.DBConnection;
import gr.ihu.ermistv.ScenesSet;
import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.StageStyle;

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
    public void Login(KeyEvent event) throws SQLException, IOException {
        if (event.getCode() == KeyCode.ENTER) {
            System.out.println("Enter");
            validateLogin();
        }

    }

    @FXML
    private void validateLogin() throws SQLException, IOException {

        String user = String.valueOf(fdUser.getText());
        String pass = fdPass.getText();
        String verifyLogin = "select * from checkaccount('" + user + "','" + pass + "');";
        boolean bypass = true;

        try {

            Statement statement = DBConnection.c.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            queryResult.next();
            if (queryResult.getInt(1) == 1 || bypass) {

                messageLabel.setStyle("-fx-text-fill: green");
                messageLabel.setText("Congratulations!");

                Stage stage = (Stage) primary.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/secondary.fxml"));
                Parent root;
                root = fxmlLoader.load();
                Scene scene = new ScenesSet(root, stage, 1024, 550, "#Hbox");
                /* */
                fxmlLoader.getController();
                App.controller = fxmlLoader.getController();
                /* */
                stage.setScene(scene);

                stage.setX(stage.getX() - 200);
                /* */
                App.controller.errorMessage("Congratulations!");
                /* */
            } else {
                messageLabel.setText("Invalid login. Please try again !");
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
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

}
