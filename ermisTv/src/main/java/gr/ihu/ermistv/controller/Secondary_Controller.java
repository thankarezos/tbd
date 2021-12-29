package gr.ihu.ermistv.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import gr.ihu.ermistv.DBConnection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.WindowEvent;

public class Secondary_Controller implements Initializable {
    @FXML
    private FontAwesomeIconView x,x1,x2,x3,x4,x5;
    @FXML
    private TextField searchName,broadcastSearch;
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
    private TextField error,editName,editTime;
    @FXML
    private TextField searchId,searchgetName;
    @FXML
    private Slider searchSlider;
    @FXML
    private Label broErrLabel,searchIcon,labelGetName,errorlabel;
    @FXML
    private Button btnEkpompi,btnProgram,btnSyntelestes;
    @FXML
    private ChoiceBox<String> choiceDelDay,choiceFacRole,searchRating;
    @FXML
    private ChoiceBox<String> choiceDayPro,choiceEditDay,choiceTypePro,choiceRatingBro;
    @FXML
    private VBox ekpompivbox;
    @FXML
    private MenuItem miAddFactor,miDelete,miReload;

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
    @FXML
    private void reloadPage(MouseEvent event){
        System.out.println("OK!");
        loadResults("null","null","null","null");
    }
    @FXML
    private void addbroadcast() {
        int intValue;
        try {
            String name = addNameBro.getText();
            String rating = choiceRatingBro.getValue();
            String time = addTimeBro.getText();
            if(name == "" ){
                broErrLabel.setText("ADD NAME!");
            }else if(rating == null || rating == "empty"){
                broErrLabel.setText("ADD RATING!");
            }else if(isNumeric(time) ){
                broErrLabel.setText("ADD TIME OR PUT A NUMBER");
            }else {
                String addbroadcast = "select addbroadcast('" + name + "','" + rating + "','" + time + "');";
                Statement statement = DBConnection.c.createStatement();
                ResultSet rs = statement.executeQuery(addbroadcast);

                System.out.println("Success");
                loadResults("null","null","null","null");
                paneEkpompi.toFront();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Secondary_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private String[] searchC = {"Empty","K","8","12","16","18"};
    private String[] ratingC = {"Empty","K","8","12","16","18"};
    private String[] factorC = {"Empty","Presenter","Actor","Reporter"};
    private String[] typeC = {"Empty","movie","series","broadcast","documentary","NEWS"};
    private String[] dayC = {"Empty","monday","tuesday","wednesday","thursday","friday","saturday","sunday"};

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
        //choise search
//        searchRating.getItems().addAll(searchC);
//        searchRating.setOnAction(this::getSearch);
        
        loadResults("null","null","null","null");
        
        
    }
    //getMethod
    private void getDay1(Event event) {
        String day = (String) choiceDelDay.getValue();
    }
    private void getDay2(Event event){
        String day = (String) choiceEditDay.getValue();
    }
    private void getDay3(Event event){
        String day = (String) choiceDayPro.getValue();
    }
    private void getRole(Event event){
        String role = (String) choiceFacRole.getValue();
    }
    private void getType(Event event){
        String type = (String) choiceTypePro.getValue();
    }
    private void getRating(Event event){
        String rating = (String) choiceRatingBro.getValue();
    }
    private void getSearch(Event event){ String search = (String) searchRating.getValue();}


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

    private void loadResults(String id,String name,String rating,String time){
        ekpompivbox.getChildren().clear();
        String getEkmompes = "select * from getResult(" + id + "," + name + "," + rating + "," + time + ");";
        
        Statement statement;
        try {
            statement = DBConnection.c.createStatement();
            ResultSet rs = statement.executeQuery(getEkmompes);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            System.out.println(columnsNumber);
            while(rs.next()){
                System.out.println(rs.getInt("sid") + " " + rs.getString("name") + " " + rs.getString("rating") + " " + rs.getInt("time"));
                HBox hbox = new HBox();
                for(int i = 1;i <= columnsNumber;i++){
                    
                    hbox.setSpacing(3);
                    HBox hboxinside = new HBox();
                    hboxinside.setStyle("-fx-background-color: #F5F6F8; -fx-background-radius: 5px; " );
                    hboxinside.setPrefWidth(198);
                    hboxinside.setAlignment(Pos.CENTER);
                    hboxinside.setPadding(new Insets(5, 5, 5, 5));
                    Text text = new Text();
                    text.setText(String.valueOf(rs.getString(i)));
                    text.setWrappingWidth(160);
                    text.setTextAlignment(TextAlignment.CENTER);
                    hboxinside.getChildren().add(text);
                    hbox.getChildren().add(hboxinside);
                }
                ekpompivbox.getChildren().add(hbox);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Secondary_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }


    public void handleFactor(ActionEvent actionEvent) {
        errorlabel.setText("Factor Ok!");
    }

    public void handleReload(ActionEvent actionEvent) {
        loadResults("null","null","null","null");
        errorlabel.setText("Reload Ok!");
    }

    public void handleDelete(ActionEvent actionEvent) {
        errorlabel.setText("Delete Ok!");
    }


    @FXML
    private void broadcastSearch(String str){
        ekpompivbox.getChildren().clear();
        String getRating = searchRating.getValue();
        String getName = searchgetName.getText();
        String getId = searchId.getText();
        //String getTime = ;



        String sercBroadcast = "select * from getekpompes();";
    }

}