package gr.ihu.ermistv.controller;

import gr.ihu.ermistv.DBConnection;
import gr.ihu.ermistv.HboxEnch;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ResourceBundle;

import javafx.beans.DefaultProperty;
import javafx.collections.ObservableList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import static javafx.geometry.Pos.CENTER;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import javafx.scene.text.Text;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.TextAlignment;

public class Program_Controller implements Initializable{

    @FXML
    private Button btnMonday, btnTuesday, btnWednesday, btnThursday, btnFriday, btnSaturday, btnSunday;
    @FXML
    private VBox emptypane, time, program;
    //@FXML
    //private ScrollPane scrollPane;

    private String[] days = {"Monday","Tuesday","Thursday","Wednesday","Friday","Saturday","Sunday"};
    private String[] colors = {"white","grey","Thursday","Wednesday","Friday","Saturday","Sunday"};
    private int daysize = 130;
    private int emptyS = 15;
    private double spaces = 5;
    private double spacesH = 10;
//    private HashMap<String,HBox> scrollDay = new HashMap<String, HBox>;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ZoomableScrollPane dayZ =new ZoomableScrollPane();
        //ZoomableScrollPane zoomablePane = (ZoomableScrollPane) scrollPane;

        dayZ.getId();
//        System.out.println(scrollPane.getId());


        program.setLayoutX(daysize + spacesH);
        emptypane.setSpacing(spaces);
        time.setSpacing(spaces);
        AnchorPane.setTopAnchor(time, emptyS -((double)emptyS/2 - spaces/2));
        int t;
        
        String panecolor = "";
        for(int i=1; i<=7;i++){
            if(i%2 == 0){
                panecolor =  "-fx-background-color:grey"; 
            }
            else{
                panecolor =  "-fx-background-color:#f6f7f9";
               
            }
            t = 30;
            
            HBox dayBox = new HBox();
            Text dayText = new Text ();
            dayText.setStyle("-fx-font-size:25px");
            dayText.setText(days[i - 1]);
            dayBox.getChildren().add(dayText);
            dayBox.setStyle("-fx-background-color:red");
            dayBox.setPrefWidth(daysize);
            dayBox.setPrefHeight(30);
//            scrollDay.put(days[i - 1], dayBox);


            for(int j = 1; j <=47;j++){

                    HBox hbox = new HBox();
                    hbox.setSpacing(spacesH);
                    hbox.setMinHeight(Region.USE_PREF_SIZE);

                    HBox day = new HBox();
                    day.setPrefWidth(daysize);
                    day.setPrefHeight(emptyS);
                    day.setMinHeight(Region.USE_PREF_SIZE);
                    day.setMinWidth(Region.USE_PREF_SIZE);
                    if(dayBox != null){
                        day.getChildren().add(dayBox);
                        dayBox = null;
                    }


                    Pane pane = new Pane();
                    pane.setPrefHeight(emptyS);
                    pane.setMinHeight(Region.USE_PREF_SIZE);
                    pane.setStyle(panecolor);
                    HBox.setHgrow(pane, Priority.ALWAYS);

                    HBox Htime = new HBox();
                    Htime.setPrefHeight(emptyS);
                    Htime.setMinHeight(Region.USE_PREF_SIZE);
                    Htime.setStyle("-fx-background-color:red");
                    Htime.setAlignment(Pos.CENTER);
                    Text text = new Text();
                    int hours = t / 60; //since both are ints, you get an int
                    int minutes = t % 60;
                    text.setText(String.format("%02d", hours) + ":" + String.format("%02d", minutes));
                    t += 30;
                    Htime.getChildren().add(text);


                    text.getStyleClass().add("textname");

                    hbox.getChildren().addAll(day,pane);
                    time.getChildren().add(Htime);
                    emptypane.getChildren().add(hbox);
            }
            HBox hbox = new HBox();
            hbox.setSpacing(spacesH);
            hbox.setMinHeight(Region.USE_PREF_SIZE);

            HBox day = new HBox();
            day.setPrefWidth(daysize);
            day.setPrefHeight(emptyS);
            day.setMinHeight(Region.USE_PREF_SIZE);
            day.setMinWidth(Region.USE_PREF_SIZE);


            Pane pane = new Pane();
            pane.setPrefHeight(emptyS);
            pane.setMinHeight(Region.USE_PREF_SIZE);
            pane.setStyle(panecolor);
            HBox.setHgrow(pane, Priority.ALWAYS);

            HBox Htime = new HBox();
            Htime.setPrefHeight(emptyS);
            Htime.setMinHeight(Region.USE_PREF_SIZE);
            Htime.setStyle("-fx-background-color:red");
            Htime.setAlignment(Pos.CENTER);
            Text text = new Text();
            text.setText("00:00");
            Htime.getChildren().add(text);


            text.getStyleClass().add("textname");

            hbox.getChildren().addAll(day,pane);
            time.getChildren().add(Htime);
            emptypane.getChildren().add(hbox);
        }
        t = 30;
        for(int j = 1; j <=10;j++){

                    HBox hbox = new HBox();
                    hbox.setSpacing(spacesH);
                    hbox.setMinHeight(Region.USE_PREF_SIZE);

                    HBox day = new HBox();
                    day.setPrefWidth(daysize);
                    day.setPrefHeight(emptyS);
                    day.setMinHeight(Region.USE_PREF_SIZE);
                    day.setMinWidth(Region.USE_PREF_SIZE);


                    Pane pane = new Pane();
                    pane.setPrefHeight(emptyS);
                    pane.setMinHeight(Region.USE_PREF_SIZE);
                    pane.setStyle(panecolor);
                    HBox.setHgrow(pane, Priority.ALWAYS);

                    HBox Htime = new HBox();
                    Htime.setPrefHeight(emptyS);
                    Htime.setMinHeight(Region.USE_PREF_SIZE);
                    Htime.setStyle("-fx-background-color:red");
                    Htime.setAlignment(Pos.CENTER);
                    Text text = new Text();
                    int hours = t / 60; //since both are ints, you get an int
                    int minutes = t % 60;
                    text.setText(String.format("%02d", hours) + ":" + String.format("%02d", minutes));
                    t += 30;
                    Htime.getChildren().add(text);


                    text.getStyleClass().add("textname");

                    hbox.getChildren().addAll(day,pane);
                    time.getChildren().add(Htime);
                    emptypane.getChildren().add(hbox);
        }
        double halfhours ;
        
//        HBox hbox = new HBox();
//        hbox.setPrefHeight(466);
//        hbox.setStyle("-fx-background-color:red");
//        halfhours = 120/30;
//        hbox.setPrefHeight(emptyS*halfhours+halfhours*spaces);
//        program.getChildren().add(hbox);
//
//        hbox = new HBox();
//        hbox.setPrefHeight(466);
//        halfhours = 60/30;
//        hbox.setPrefHeight(emptyS*halfhours+halfhours*spaces);
//        program.getChildren().add(hbox);
//
//        hbox = new HBox();
//        hbox.setPrefHeight(466);
//        hbox.setStyle("-fx-background-color:red");
//        halfhours = 300/30;
//        hbox.setPrefHeight(emptyS*halfhours+halfhours*spaces);
//        program.getChildren().add(hbox);
//
        String getSyntelestes = "select * from getPrograms()";
        Statement statement;
        try {
            statement = DBConnection.c.createStatement();
            ResultSet rs = statement.executeQuery(getSyntelestes);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            double pTime = 0;
            while (rs.next()) {
//                HBox hboxS = new HBox();
//
//
//                program.getChildren().add(hboxS);
//
//                if(rs.getInt("strtime") - pTime  > 0){
//
//                }


                HBox hbox = new HBox();
                halfhours = (rs.getDouble("strtime") - pTime)/30;
//                System.out.println(halfhours);
                hbox.setPrefHeight(emptyS*halfhours+halfhours*spaces);
                program.getChildren().add(hbox);

                hbox = new HBox();
                hbox.setStyle("-fx-background-color:red; -fx-border-color:black");
//                hbox.setStyle();
                halfhours = rs.getDouble("time")/30;
                hbox.setPrefHeight(emptyS*halfhours+halfhours*spaces);
                program.getChildren().add(hbox);
                pTime = rs.getDouble("strtime") + rs.getDouble("time");
                System.out.println("endtime " + pTime);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Secondary_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
    }


@DefaultProperty(value = "extension")

    public class ZoomableScrollPane extends ScrollPane {

        @FXML private ScrollPane extension;



        private double scaleValue = 0.7;
        private double zoomIntensity = 0.02;
        private Node target;
        private Node zoomNode;

        public ZoomableScrollPane() {//Node target
            super();
//            this.target = target;
//            this.zoomNode = new Group(target);
//            setContent(outerNode(zoomNode));
//
//            setPannable(true);
//            setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//            setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//            setFitToHeight(true); //center
//            setFitToWidth(true); //center
//
//            updateScale();
        }

        private Node outerNode(Node node) {
            Node outerNode = centeredNode(node);
            outerNode.setOnScroll(e -> {
                e.consume();
                onScroll(e.getTextDeltaY(), new Point2D(e.getX(), e.getY()));
            });
            return outerNode;
        }

        private Node centeredNode(Node node) {
            VBox vBox = new VBox(node);
            vBox.setAlignment(Pos.CENTER);
            return vBox;
        }

        private void updateScale() {
            target.setScaleX(scaleValue);
            target.setScaleY(scaleValue);
        }

        private void onScroll(double wheelDelta, Point2D mousePoint) {
            double zoomFactor = Math.exp(wheelDelta * zoomIntensity);

            Bounds innerBounds = zoomNode.getLayoutBounds();
            Bounds viewportBounds = getViewportBounds();

            // calculate pixel offsets from [0, 1] range
            double valX = this.getHvalue() * (innerBounds.getWidth() - viewportBounds.getWidth());
            double valY = this.getVvalue() * (innerBounds.getHeight() - viewportBounds.getHeight());

            scaleValue = scaleValue * zoomFactor;
            updateScale();
            this.layout(); // refresh ScrollPane scroll positions & target bounds

            // convert target coordinates to zoomTarget coordinates
            Point2D posInZoomTarget = target.parentToLocal(zoomNode.parentToLocal(mousePoint));

            // calculate adjustment of scroll position (pixels)
            Point2D adjustment = target.getLocalToParentTransform().deltaTransform(posInZoomTarget.multiply(zoomFactor - 1));

            // convert back to [0, 1] range
            // (too large/small values are automatically corrected by ScrollPane)
            Bounds updatedInnerBounds = zoomNode.getBoundsInLocal();
            this.setHvalue((valX + adjustment.getX()) / (updatedInnerBounds.getWidth() - viewportBounds.getWidth()));
            this.setVvalue((valY + adjustment.getY()) / (updatedInnerBounds.getHeight() - viewportBounds.getHeight()));
        }
    }
}
