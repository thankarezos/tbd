package gr.ihu.ermistv.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

public class Secondary_Controller implements Initializable {
    @FXML
    private FontAwesomeIconView x,x1,x2,x3,x4,x5;
    @FXML
    private TextField searchName;
    @FXML
    private ScrollBar ScrollBar;
    @FXML
    private CheckBox checkBox;
    @FXML
    private AnchorPane addProgram,deleteFactor,addFactor,deleteBroadcast,editFactorBroadcast,addBroadcast;
    @FXML
    private AnchorPane secondary,paneEkpompi,paneProgram,paneSyntelestes;
    @FXML
    private TextField addTimeBro,addNameBro;
    @FXML
    private TextField addFacSurname,addFacName,addFacPhoneN;
    @FXML
    private TextField addNamePro,addTimePro;
    @FXML
    private Button btnAddFacPane,btnAddBroPane,btnEditBroPane,btnMergeFacPane,btnDelFacPane,btnDelBroPane;
    @FXML
    private Button btnCheckBro,btnCheckDelBro,btnConfBro,btnDelBro;
    @FXML
    private Button btnConfFac,btnDelFac,btnCheckFac;
    @FXML
    private Button btnCheckPro,btnConfPro;
    @FXML
    private Button btnConfEdit,btnEditCheck;
    @FXML
    private TextField delFacName,delFacSurname,delNameBro,delTimeBro;
    @FXML
    private TextField editName,editTime;
    @FXML
    private Label searchIcon,labelGetName;
    @FXML
    private Button btnEkpompi,btnProgram,btnSyntelestes;
    @FXML
    private ChoiceBox<String> choiceDelDay,choiceFacRole;
    @FXML
    private ChoiceBox<String> choiceDayPro,choiceEditDay,choiceTypePro,choiceRatingBro;

    @FXML
    private void minimizedWindow(MouseEvent event) {
        Stage stage = (Stage) secondary.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void closeWindow(MouseEvent event) {
        System.exit(0);
        Platform.exit();
    }
    @FXML
    private void switchToPrimary() throws IOException {
    }

    private String[] ratingC = {"K","8","12","16","18"};
    private String[] factorC = {"Presenter","Actor","Reporter"};
    private String[] typeC = {"movie","series","broadcast","documentary","NEWS"};
    private String[] dayC = {"monday","tuesday","wednesday","thursday","friday","saturday","sunday"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //choice day
        choiceDelDay.getItems().addAll(dayC);
        choiceDelDay.setOnAction(this::getDay1);

        choiceDayPro.getItems().addAll(dayC);
        choiceDayPro.setOnAction(this::getDay2);

        choiceEditDay.getItems().addAll(dayC);
        choiceEditDay.setOnAction(this::getDay3);
        //choice factor
        choiceFacRole.getItems().addAll(factorC);
        choiceFacRole.setOnAction(this::getRole);
        //choice type
        choiceTypePro.getItems().addAll(typeC);
        choiceTypePro.setOnAction(this::getType);
        //choice rating
        choiceRatingBro.getItems().addAll(ratingC);
        choiceRatingBro.setOnAction(this::getRating);
    }
    //getMethod
    private void getDay3(Event event){
        String day = (String) choiceDayPro.getValue();
    }
    private void getDay1(Event event) {
        String type = (String) choiceDelDay.getValue();
    }
    private void getDay2(Event event){
        String day = (String) choiceEditDay.getValue();
    }
    private void getRole(Event event){
        String day = (String) choiceFacRole.getValue();
    }
    private void getType(Event event){
        String day = (String) choiceTypePro.getValue();
    }
    private void getRating(Event event){
        String day = (String) choiceRatingBro.getValue();
    }



    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
        if (event.getSource() == btnProgram) {paneProgram.toFront();}
        else if (event.getSource() == btnEkpompi) {paneEkpompi.toFront();}
        else if (event.getSource() == btnSyntelestes) {paneSyntelestes.toFront();}
    }

    @FXML
    private void popupsHandleClicks(MouseEvent event) throws IOException {
        if (event.getSource() == x ) {paneEkpompi.toFront();}
        else if (event.getSource() == x1 ) {paneEkpompi.toFront();}
        else if (event.getSource() == x2 ) {paneEkpompi.toFront();}
        else if (event.getSource() == x3) {paneProgram.toFront();}
        else if (event.getSource() == x4 ) {paneSyntelestes.toFront();}
        else if (event.getSource() == x5 ) {paneSyntelestes.toFront();}


    }

    @FXML
    private void addProgram(MouseEvent event){
        addProgram.toFront();
    }
    @FXML
    private void addBroadcast(MouseEvent event){
        addBroadcast.toFront();
    }
    @FXML
    private void editFactorBroadcast(MouseEvent event){
        editFactorBroadcast.toFront();
    }
    @FXML
    private void deleteBroadcast(MouseEvent event){
        deleteBroadcast.toFront();
    }
    @FXML
    private void addFactor(MouseEvent event){
        addFactor.toFront();
    }
    @FXML
    private void deleteFactor(MouseEvent event){
        deleteFactor.toFront();
    }
    @FXML
    private void mergePaneSyntelestes(MouseEvent event){
        paneSyntelestes.toFront();
    }




}