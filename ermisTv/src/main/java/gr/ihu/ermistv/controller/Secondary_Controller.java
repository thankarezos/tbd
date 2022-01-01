package gr.ihu.ermistv.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.stage.Stage;
import javafx.scene.Scene;
import gr.ihu.ermistv.App;
import gr.ihu.ermistv.ScenesSet;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Secondary_Controller implements Initializable {

    @FXML
    private FontAwesomeIconView x3;
    @FXML
    private AnchorPane addProgram, addFactor, editFactorBroadcast, addBroadcast;
    @FXML
    private AnchorPane secondary, paneEkpompi, paneProgram;
    @FXML
    private TextField addNamePro;
    @FXML
    private TextField addTimePro;
    @FXML
    private Button btnConfPro;
    @FXML
    private Button btnEkpompi, btnProgram;
    @FXML
    private Button btnSyntelestes;
    @FXML
    private ChoiceBox<String> choiceDayPro;
    @FXML
    private ChoiceBox<String> choiceTypePro;
    @FXML
    private AnchorPane paneSyntelestes;

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

        Stage stage = (Stage) secondary.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/login_view.fxml"));
        Parent root;
        root = fxmlLoader.load();
        Scene scene = new ScenesSet(root, stage, 640, 480, "#Hbox");
        stage.setScene(scene);
        stage.setX(stage.getX() + 200);
    }

    // Is Numeric Method
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return true;
        }
        try {
            Integer d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return true;
        }
        return false;
    }

    // Handle Clicks Popup
    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
        if (event.getSource() == btnProgram) {

            paneProgram.toFront();
        } else if (event.getSource() == btnEkpompi) {
            paneEkpompi.toFront();
        } else if (event.getSource() == btnSyntelestes) {
            paneSyntelestes.toFront();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/ekpompi.fxml"));
            root = loader.load();

            Scene scene = new ScenesSet(root, App.stage, 876, 517);
            Ekpompi_Controller controller = loader.getController();
            controller.setAp(paneEkpompi);
            paneEkpompi.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(Secondary_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            root = FXMLLoader.load(App.class.getResource("fxml/syntelestes.fxml"));

            Scene scene = new ScenesSet(root, App.stage, 876, 517);
            paneSyntelestes.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(Secondary_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
