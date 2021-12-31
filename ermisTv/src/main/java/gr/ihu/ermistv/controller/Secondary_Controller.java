package gr.ihu.ermistv.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.*;
import gr.ihu.ermistv.App;
import gr.ihu.ermistv.ScenesSet;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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
import gr.ihu.ermistv.DBConnection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import org.controlsfx.control.RangeSlider;

import static javafx.geometry.Pos.CENTER;

public class Secondary_Controller implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
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

    // Fixed Statement

    private String[] typeC = { "Empty", "movie", "series", "broadcast", "documentary", "NEWS" };
    private String[] dayC = { "Empty", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday" };


    // getMethod
    private void getDay2(Event event) {
        String day = (String) choiceEditDay.getValue();
    }

    private void getDay3(Event event) {
        String day = (String) choiceDayPro.getValue();
    }

    private void getType(Event event) {
        String type = (String) choiceTypePro.getValue();
    }

    private void getRating(Event event) {
        String rating = (String) choiceRatingBro.getValue();
    }

    // add Methos
    @FXML
    private void addProgram(MouseEvent event) {
        addProgram.toFront();
    }



    @FXML
    private void editFactorBroadcast(MouseEvent event) {
        editFactorBroadcast.toFront();
    }

    @FXML
    private void addFactor(MouseEvent event) {
        addFactor.toFront();
    }



    // Handle Clicks Popup
    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
        if (event.getSource() == btnProgram) {

            paneProgram.toFront();
        } else if (event.getSource() == btnEkpompi) {
            //loadResults("null", "null", "null", String.valueOf(low), String.valueOf(high));
            paneEkpompi.toFront();
        }

//        else if (event.getSource() == btnSyntelestes) {
//            Syntelestes_Contoller.loadResultsSyntelestes("null", "null", "null", "null", "null");
//            paneSyntelestes.toFront();
//        }
    }

    // Popup switch
    @FXML
    private void popupsHandleClicks(MouseEvent event) throws IOException {
        if (event.getSource() == x) {
            paneEkpompi.toFront();
        } else if (event.getSource() == x1) {
            paneEkpompi.toFront();
        } else if (event.getSource() == x3) {
            paneProgram.toFront();
//        } else if (event.getSource() == x4) {
//            paneSyntelestes.toFront();
//        }

        }


    }
}
