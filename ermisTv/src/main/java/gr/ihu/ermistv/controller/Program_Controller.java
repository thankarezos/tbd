package gr.ihu.ermistv.controller;

import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Program_Controller implements Initializable{
    
    @FXML
    private VBox emptypane, time, program;
    
    private String[] days = {"Monday","Tuesday","Thurstday","Wednesday","Friday","Saturday","Sunday"};
    private String[] colors = {"white","grey","Thurstday","Wednesday","Friday","Saturday","Sunday"};
    private int daysize = 130;
    private int emptyS = 15;
    private double spaces = 5;
    private double spacesH = 10;
     
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
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
        int halfhours; 
        
        HBox hbox = new HBox();
        hbox.setPrefHeight(466);
        hbox.setStyle("-fx-background-color:red");
        halfhours = 120/30;
        hbox.setPrefHeight(emptyS*halfhours+halfhours*spaces);
        program.getChildren().add(hbox);
        
        hbox = new HBox();
        hbox.setPrefHeight(466);
        halfhours = 60/30;
        hbox.setPrefHeight(emptyS*halfhours+halfhours*spaces);
        program.getChildren().add(hbox);
        
        hbox = new HBox();
        hbox.setPrefHeight(466);
        hbox.setStyle("-fx-background-color:red");
        halfhours = 300/30;
        hbox.setPrefHeight(emptyS*halfhours+halfhours*spaces);
        program.getChildren().add(hbox);
        
        
    }


}
