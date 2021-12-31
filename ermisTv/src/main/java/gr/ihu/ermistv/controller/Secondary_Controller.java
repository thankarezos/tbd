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
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.controlsfx.control.RangeSlider;


public class Secondary_Controller implements Initializable {

    
    @FXML
    private FontAwesomeIconView x, x1, x3;
    @FXML
    private TextField searchName;
    @FXML
    private ScrollBar ScrollBar;
    @FXML
    private AnchorPane addProgram, addFactor, editFactorBroadcast, addBroadcast;
    @FXML
    private AnchorPane secondary, paneEkpompi, paneProgram;
    @FXML
    private TextField addNameBro;

    @FXML
    private TextField addNamePro, addTimePro;
    @FXML
    private Button btnAddFacPane, btnAddBroPane, btnEditBroPane;
    @FXML
    private Button btnConfFac, btnConfBro, btnConfPro, btnConfEdit;
    @FXML
    private TextField editTime;
    @FXML
    private Slider timeSlider;
    @FXML
    private Label broErrLabel, searchIcon, labelGetName;
    @FXML
    private Button btnEkpompi, btnProgram, btnSyntelestes;
    @FXML
    private ChoiceBox<String> choiceDayPro, choiceEditDay, choiceTypePro, choiceRatingBro;
    @FXML
    private VBox ekpompivbox;
    @FXML
    private Text txtRange;

    @FXML
    private TextField filterID;
    @FXML
    private TextField filterName;
    @FXML
    private ChoiceBox filterRating;
    @FXML
    private RangeSlider sliderr;
    @FXML
    private Text sliderText;

    @FXML
    private TextField syntelestisID, syntelestisName, syntelestisSurname, syntelestisPhoneN;
    @FXML
    private ChoiceBox  syntelestisRole;
    @FXML
    private AnchorPane paneSyntelestes;
    @FXML
    private VBox vboxSyntelestes;

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
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        double x = bounds.getMinX() + (bounds.getWidth() - scene.getWidth()) * 0.5;
        double y = bounds.getMinY() + (bounds.getHeight() - scene.getHeight()) * 0.5;
        stage.setX(x);
        stage.setY(y);
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
        }

        else if (event.getSource() == btnSyntelestes) {
            paneSyntelestes.toFront();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            Parent root;
            try {
                root = FXMLLoader.load(App.class.getResource("fxml/ekpompi.fxml"));
                
                Scene scene = new ScenesSet(root, App.stage, 876, 517);
                paneEkpompi.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(Secondary_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
}
