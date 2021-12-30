package gr.ihu.ermistv.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
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
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
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
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import org.controlsfx.control.RangeSlider;



public class Secondary_Controller implements Initializable {
    @FXML
    private TextArea lol;
    @FXML
    private FontAwesomeIconView x,x1,x2,x3,x4;
    @FXML
    private TextField searchName,broadcastSearch;
    @FXML
    private ScrollBar ScrollBar;
    @FXML
    private CheckBox checkBox;
    @FXML
    private AnchorPane addProgram,deleteFactor,addFactor,editFactorBroadcast,addBroadcast;
    @FXML
    private AnchorPane secondary,paneEkpompi,paneProgram,paneSyntelestes;
    @FXML
    private TextField addTimeBro,addNameBro;
    @FXML
    private TextField addFacSurname,addFacName,addFacPhoneN;
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
    private Slider searchSlider;
    @FXML
    private Label broErrLabel,searchIcon,labelGetName,errorlabel;
    @FXML
    private Button btnEkpompi,btnProgram,btnSyntelestes;
    @FXML
    private ChoiceBox<String> choiceFacRole,searchRating;
    @FXML
    private ChoiceBox<String> choiceDayPro,choiceEditDay,choiceTypePro,choiceRatingBro;
    @FXML
    private VBox ekpompivbox;
    @FXML
    private MenuItem miAddFactor,miDelete,miReload;
    

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
                filter();
                addNameBro.clear();
                choiceRatingBro.setValue(null);
                addTimeBro.clear();
                paneEkpompi.toFront();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Secondary_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    int low = 10;
    int high = 300;
    private String[] ratingC = {"","K","8","12","16","18"};
    private String[] factorC = {"","Presenter","Actor","Reporter"};
    private String[] typeC = {"Empty","movie","series","broadcast","documentary","NEWS"};
    private String[] dayC = {"Empty","monday","tuesday","wednesday","thursday","friday","saturday","sunday"};

    private void filter(){
        String id = "'" + filterID.getText() + "'";
            if(filterID.getText().isEmpty() || isNumeric(filterID.getText())){
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //choice day
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
        filterRating.getItems().addAll(ratingC);

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

       loadResults("null","null","null",String.valueOf(low),String.valueOf(high));
        
        
    }
    //getMethod
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
                    
                    hbox.addEventHandler(MouseEvent.MOUSE_ENTERED,
                            new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {

                                    HBox hbox = (HBox)event.getSource();
                                    hbox.getChildren().get(0);
                                    hbox.getChildren();

                                    for (int i=0;i<hbox.getChildren().size();i++) {

                                        HBox pane = (HBox) hbox.getChildren().get(i);
                                        pane.getStyleClass().add("hboxStyle");
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
                                        pane.getStyleClass().add("hboxStylehover");
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
    private void broadcastSearch(String str){
        ekpompivbox.getChildren().clear();
        String getRating = searchRating.getValue();
        String getName = searchgetName.getText();
        String getId = searchId.getText();
        //String getTime = ;



        String sercBroadcast = "select * from getekpompes();";
    }

}