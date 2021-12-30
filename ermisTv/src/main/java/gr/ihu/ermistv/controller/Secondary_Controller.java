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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import org.controlsfx.control.RangeSlider;

import static javafx.geometry.Pos.CENTER;


public class Secondary_Controller implements Initializable {
    @FXML
    private FontAwesomeIconView x,x1,x3,x4;
    @FXML
    private TextField searchName,broadcastSearch;
    @FXML
    private ScrollBar ScrollBar;
    @FXML
    private CheckBox checkBox;
    @FXML
    private AnchorPane addProgram,deleteFactor,addFactor,editFactorBroadcast,addBroadcast;
    @FXML
    private AnchorPane secondary,paneEkpompi,paneProgram;
    @FXML
    private TextField addTimeBro,addNameBro;
    @FXML
    private TextField addFacSurname,addFacName,addFacPhoneN,addFacRole;
    @FXML
    private TextField addNamePro,addTimePro;
    @FXML
    private Button btnAddFacPane,btnAddBroPane,btnEditBroPane,logout;
    @FXML
    private Button btnCheckBro,btnCheckDelBro,btnConfBro;
    @FXML
    private Button btnConfFac,btnCheckFac;
    @FXML
    private Button btnCheckPro,btnConfPro;
    @FXML
    private Button btnConfEdit,btnEditCheck;
    @FXML
    private TextField error,editName,editTime;
    @FXML
    private TextField searchId,searchgetName;
    @FXML
    private Slider timeSlider;
    @FXML
    private Label broErrLabel,searchIcon,labelGetName,errorlabel,facErrLabel;
    @FXML
    private Button btnEkpompi,btnProgram,btnSyntelestes;
    @FXML
    private ChoiceBox<String> searchRating;
    @FXML
    private ChoiceBox<String> choiceDayPro,choiceEditDay,choiceTypePro,choiceRatingBro;
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
    private TextField syntelestisID,syntelestisName,syntelestisSurname,syntelestisRole,syntelestisPhoneN;
    @FXML
    private AnchorPane paneSyntelestes;
    @FXML
    private VBox vboxSyntelestes;

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
        filter();
    }
    @FXML
    private void rangeSlide(MouseEvent event){
        System.out.println("moved");
    }
    
    @FXML
    private void addbroadcast() {
        int intValue;
        try {
            String name = addNameBro.getText();
            String rating = choiceRatingBro.getValue();
            int time = valueRange;
            if(name == "" ){
                broErrLabel.setText("ADD NAME!");
            }else if(rating == null || rating == "empty"){
                broErrLabel.setText("ADD RATING!");
            }else if(time == 0 ){
                broErrLabel.setText("ADD TIME ");
            }else {
                String addbroadcast = "select addbroadcast('" + name + "','" + rating + "','" + time + "');";
                Statement statement = DBConnection.c.createStatement();
                ResultSet rs = statement.executeQuery(addbroadcast);

                System.out.println("Success");
                filter();
                addNameBro.clear();
                choiceRatingBro.setValue(null);
                timeSlider.setValue(0);
                broErrLabel.setText("");
                paneEkpompi.toFront();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Secondary_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    int low = 10;
    int high = 300;
    int valueRange;
    private String[] ratingC = {"","K","8","12","16","18"};
    private String[] typeC = {"Empty","movie","series","broadcast","documentary","NEWS"};
    private String[] dayC = {"Empty","monday","tuesday","wednesday","thursday","friday","saturday","sunday"};

    private void filter(){
        String id = "'" + filterID.getText() + "'";
            if(filterID.getText().isEmpty()){
                id = "null";
            }
            String name = "'" + filterName.getText() + "'";

            if(filterName.getText().isEmpty()){
                name = "null";
            }
            String rating = "'" + String.valueOf(filterRating.getValue()) + "'";
            if(String.valueOf(filterRating.getValue()).isEmpty()){
                rating = "null";
            }
            loadResults(id,name,rating,String.valueOf(low),String.valueOf(high));
    }

    private void filter1(){
        String id = "'" + syntelestisID.getText() + "'";
        if(syntelestisID.getText().isEmpty()){
            id = "null";
        }

        String name = "'" + syntelestisName.getText() + "'";
        if(syntelestisName.getText().isEmpty()){
            name = "null";
        }

        String surname = "'" + syntelestisSurname.getText() + "'";
        if(syntelestisSurname.getText().isEmpty()){
            surname = "null";
        }

        String role = "'" + syntelestisRole.getText() + "'";
        if(syntelestisRole.getText().isEmpty()){
            role = "null";
        }

        String phoneN = "'" + syntelestisPhoneN.getText() + "'";
        if(syntelestisPhoneN.getText().isEmpty()){
            phoneN = "null";
        }

        loadResults1(id,name,surname,role,phoneN);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //choice day
        choiceDayPro.getItems().addAll(dayC);
        choiceDayPro.setOnAction(this::getDay2);

        choiceEditDay.getItems().addAll(dayC);
        choiceEditDay.setOnAction(this::getDay3);
        //choice type
        choiceTypePro.getItems().addAll(typeC);
        choiceTypePro.setOnAction(this::getType);
        //choice rating
        choiceRatingBro.getItems().addAll(ratingC);
        choiceRatingBro.setOnAction(this::getRating);
        //choise search
        filterRating.getItems().addAll(ratingC);

        //Range Slider
        sliderr.setLowValue(low);
        sliderr.setHighValue(high);
        sliderText.setText(String.valueOf(low) + " - " + String.valueOf(high));
        
        filterID.textProperty().addListener((observable, oldValue, newValue) -> {
          filter();
        });
        filterName.textProperty().addListener((observable, oldValue, newValue) -> {
          filter();
        });
        filterRating.valueProperty().addListener((observable, oldValue, newValue) -> {
          filter();
        });

        sliderr.highValueProperty().addListener((observable, oldValue, newValue) -> {
            
            high = (int)Math.round(newValue.doubleValue());
            sliderText.setText(String.valueOf(low) + " - " + String.valueOf(high));
            filter();

        });
        sliderr.lowValueProperty().addListener((observable, oldValue, newValue) -> {
            low = (int)Math.round(newValue.doubleValue());
            sliderText.setText(String.valueOf(low) + " - " + String.valueOf(high));
            filter();

        });

        timeSlider.setValue(0);
        timeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            valueRange = (int)Math.round((newValue.doubleValue()));
            txtRange.setText(" 0  -  " + String.valueOf(valueRange));
            filter();
        });

        syntelestisID.textProperty().addListener((observable, oldValue, newValue) -> {
            filter1();
        });
        syntelestisName.textProperty().addListener((observable, oldValue, newValue) -> {
            filter1();
        });
        syntelestisSurname.textProperty().addListener((observable, oldValue, newValue) -> {
            filter1();
        });
        syntelestisRole.textProperty().addListener((observable, oldValue, newValue) -> {
            filter1();
        });
        syntelestisPhoneN.textProperty().addListener((observable, oldValue, newValue) -> {
            filter1();
        });

        loadResults1("null","null","null","null","null");
    }
    //getMethod
    private void getDay2(Event event){
        String day = (String) choiceEditDay.getValue();
    }
    private void getDay3(Event event){
        String day = (String) choiceDayPro.getValue();
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
        if (event.getSource() == btnProgram) {

            paneProgram.toFront();}
        else if (event.getSource() == btnEkpompi) {
            loadResults("null","null","null",String.valueOf(low),String.valueOf(high));
            paneEkpompi.toFront();}

        else if (event.getSource() == btnSyntelestes) {
            loadResults1("null","null","null","null","null");
            paneSyntelestes.toFront();}
    }
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

    @FXML
    private void popupsHandleClicks(MouseEvent event) throws IOException {
        if (event.getSource() == x ) {paneEkpompi.toFront();}
        else if (event.getSource() == x1 ) {paneEkpompi.toFront();}
        else if (event.getSource() == x3) {paneProgram.toFront();}
        else if (event.getSource() == x4 ) {paneSyntelestes.toFront();}


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

    private void loadResults(String id,String name,String rating,String timeLow, String timeHigh){
        ekpompivbox.getChildren().clear();
        String getEkmompes = "select * from getResult(" + id + "," + name + "," + rating + "," + timeLow + "," + timeHigh + ");";
        
        Statement statement;
        try {
            statement = DBConnection.c.createStatement();
            ResultSet rs = statement.executeQuery(getEkmompes);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while(rs.next()){
                HBox hbox = new HBox();

                for(int i = 1;i <= columnsNumber;i++){
                    
                    hbox.setSpacing(3);
                    HBox hboxinside = new HBox();
                    hboxinside.getStyleClass().add("hboxStyle" );
                    hboxinside.setPrefWidth(198);
                    hboxinside.setAlignment(CENTER);
                    hboxinside.setPadding(new Insets(5, 5, 5, 5));
                    Text text = new Text();
                    text.setText(String.valueOf(rs.getString(i)));
                    text.setWrappingWidth(160);
                    text.setTextAlignment(TextAlignment.CENTER);
                    hboxinside.getChildren().add(text);
                    hbox.getChildren().add(hboxinside);
                    
                    hbox.addEventHandler(MouseEvent.MOUSE_ENTERED,
                            new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {

                                    HBox hbox = (HBox)event.getSource();
                                    hbox.getChildren().get(0);
                                    hbox.getChildren();

                                    for (int i=0;i<hbox.getChildren().size();i++) {

                                        HBox pane = (HBox) hbox.getChildren().get(i);
                                        pane.getStyleClass().add("hboxStylehover");
                                    }

                                }
                            });

                    hbox.addEventHandler(MouseEvent.MOUSE_EXITED,
                            new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {

                                    HBox hbox = (HBox)event.getSource();
                                    hbox.getChildren().get(0);
                                    hbox.getChildren();

                                    for (int i=0;i<hbox.getChildren().size();i++) {
                                        HBox pane = (HBox) hbox.getChildren().get(i);
                                        pane.getStyleClass().clear();
                                        pane.getStyleClass().add("hboxStyle");
                                        Text text = (Text)pane.getChildren().get(0);
                                    }

                                }
                    });
                    
                }
                hbox.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        MouseButton button = event.getButton();
                        if(button == MouseButton.SECONDARY){
                            ContextMenu menu = new ContextMenu();
                            MenuItem item = new MenuItem();
                            item.setText("Delete");
                            menu.getItems().add(item);
                            menu.show(hbox, event.getScreenX(), event.getScreenY());
                            item.setOnAction(event2 -> {
                                HBox hboxC = (HBox)hbox.getChildren().get(0);
                                Text text2 = (Text)hboxC.getChildren().get(0);
                                System.out.println(text2.getText());
                                String deleteek = "select * from deleteEkpompi("+ text2.getText() +");";
                                try {
                                    statement.executeQuery(deleteek);
                                    filter();
                                    
                                } catch (SQLException ex) {
                                    Logger.getLogger(Secondary_Controller.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            });
                        }
                        
                        
                    }
                    
                });
                ekpompivbox.getChildren().add(hbox);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Secondary_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    private void loadResults1(String id,String name,String surname,String role, String phoneNumber){
        vboxSyntelestes.getChildren().clear();
        String getSyntelestes = "select * from getResultSyntelestes(" + id + "," + name + "," + surname + "," + role + "," + phoneNumber + ");";

        Statement statement;
        try {
            statement = DBConnection.c.createStatement();
            ResultSet rs = statement.executeQuery(getSyntelestes);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while(rs.next()){
                HBox hbox = new HBox();

                for(int i = 1 ; i <= columnsNumber ; i++){

                    hbox.setSpacing(3);
                    HBox hboxinside = new HBox();
                    hboxinside.getStyleClass().add("hboxStyle" );
                    hboxinside.setPrefWidth(160);
                    hboxinside.setAlignment(CENTER);
                    hboxinside.setPadding(new Insets(5, 5, 5, 5));
                    Text text = new Text();
                    text.setText(String.valueOf(rs.getString(i)));
                    text.setWrappingWidth(80);
                    text.setTextAlignment(TextAlignment.CENTER);
                    hboxinside.getChildren().add(text);
                    hbox.getChildren().add(hboxinside);

                    hbox.addEventHandler(MouseEvent.MOUSE_ENTERED,
                            new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {

                                    HBox hbox = (HBox)event.getSource();
                                    hbox.getChildren().get(0);
                                    hbox.getChildren();

                                    for (int i=0;i<hbox.getChildren().size();i++) {

                                        HBox pane = (HBox) hbox.getChildren().get(i);
                                        pane.getStyleClass().add("hboxStylehover");
                                    }

                                }
                            });

                    hbox.addEventHandler(MouseEvent.MOUSE_EXITED,
                            new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {

                                    HBox hbox = (HBox)event.getSource();
                                    hbox.getChildren().get(0);
                                    hbox.getChildren();

                                    for (int i=0;i<hbox.getChildren().size();i++) {
                                        HBox pane = (HBox) hbox.getChildren().get(i);
                                        pane.getStyleClass().clear();
                                        pane.getStyleClass().add("hboxStyle");
                                        Text text = (Text)pane.getChildren().get(0);
                                    }

                                }
                            });

                }
                hbox.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        MouseButton button = event.getButton();
                        if(button == MouseButton.SECONDARY){
                            ContextMenu menu = new ContextMenu();
                            MenuItem item = new MenuItem();
                            item.setText("Delete");
                            menu.getItems().add(item);
                            menu.show(hbox, event.getScreenX(), event.getScreenY());
                            item.setOnAction(event2 -> {
                                HBox hboxC = (HBox)hbox.getChildren().get(0);
                                Text text2 = (Text)hboxC.getChildren().get(0);
                                System.out.println(text2.getText());
                                String deleteek = "select * from deleteSyntelestes("+ text2.getText() +");";
                                try {
                                    statement.executeQuery(deleteek);
                                    filter1();

                                } catch (SQLException ex) {
                                    Logger.getLogger(Secondary_Controller.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            });
                        }


                    }

                });
                vboxSyntelestes.getChildren().add(hbox);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Secondary_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void handleFactor(ActionEvent actionEvent) {
        errorlabel.setText("Factor Ok!");
    }

    public void handleReload(ActionEvent actionEvent) {
        loadResults("null","null","null",String.valueOf(low),String.valueOf(high));
        errorlabel.setText("Reload Ok!");
    }

    public void handleDelete(ActionEvent actionEvent) {
        errorlabel.setText("Delete Ok!");
    }

    @FXML
    private void addfactor() {
        try {
            String name = addFacName.getText();
            String surname = addFacSurname.getText();
            String role = addFacRole.getText();
            String phoneN = addFacPhoneN.getText();

            if(name == "" ){
                facErrLabel.setText("ADD NAME!");
            }else if(surname == "" ){
                facErrLabel.setText("ADD SURNAME!");
            }else if(role == "" ){
                facErrLabel.setText("ADD ROLE ");
            }else if(phoneN == "" ){
                facErrLabel.setText("ADD PHONE NUMBER ");
            }else {
                String addSyntelestes = "select addSyntelestes('" + name + "','" + surname + "','" + role + "','" + phoneN + "');";
                Statement statement = DBConnection.c.createStatement();
                ResultSet rs = statement.executeQuery(addSyntelestes);

                System.out.println("Success");
                filter1();
                addFacName.clear();
                addFacSurname.clear();
                addFacRole.clear();
                addFacPhoneN.clear();
                facErrLabel.setText("");
                paneSyntelestes.toFront();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Secondary_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}