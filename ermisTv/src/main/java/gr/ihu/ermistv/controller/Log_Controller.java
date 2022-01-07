package gr.ihu.ermistv.controller;

import gr.ihu.ermistv.App;
import gr.ihu.ermistv.DBConnection;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class Log_Controller implements Initializable{
    @FXML
    private TextFlow log;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        load();

    }

    public void load(){

        log.getChildren().clear();
        try {
            
            String logfile = "select * from getLog();";
            
            Statement statement = DBConnection.c.createStatement();
            ResultSet queryResult = statement.executeQuery(logfile);
            while(queryResult.next()){
                if(queryResult.getString("operation").equals("DELETE")){
                    String logString = queryResult.getTimestamp("tstamp") + " User:" + queryResult.getString("who") 
                    + " DELETED " + queryResult.getString("oldValue") + " FROM " + queryResult.getString("tabname");
                    Text t1 = new Text();
                    Text tStatic = new Text();
                    tStatic.setText("\n");
                    String c = "-fx-fill: red";
                    t1.setStyle(c);
                    t1.setText(logString);
                    log.getChildren().addAll(t1 , tStatic);
                }
                else if(queryResult.getString("operation").equals("INSERT")){
                    String logString = queryResult.getTimestamp("tstamp") + " User:" + queryResult.getString("who") 
                    + " INSERTED " + queryResult.getString("newValue") + " TO " + queryResult.getString("tabname");
                    Text t1 = new Text();
                    Text tStatic = new Text();
                    tStatic.setText("\n");
                    String c = "-fx-fill: green";
                    t1.setStyle(c);
                    t1.setText(logString);
                    log.getChildren().addAll(t1 , tStatic);
                }
                else{
                    String logString = queryResult.getTimestamp("tstamp") + " User:" + queryResult.getString("who") 
                    + " UPDATED " + queryResult.getString("oldValue") + " TO " + queryResult.getString("oldValue") + " ON "+ queryResult.getString("tabname");
                    Text t1 = new Text();
                    Text tStatic = new Text();
                    tStatic.setText("\n");
                    String c = "-fx-fill: black";
                    t1.setStyle(c);
                    t1.setText(logString);
                    log.getChildren().addAll(t1 , tStatic);
                }
                
            }
            
            
            statement.close();
            queryResult.close();

        } catch (SQLException e) {
            App.controller.errorMessage();
        }
    }
}
