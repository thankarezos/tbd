package gr.ihu.ermistv.controller;

import gr.ihu.ermistv.DBConnection;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

public class Log_Controller implements Initializable{
    @FXML
    private TextArea log;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("test");
        log.setWrapText(true);
        lodad();
        
    }
    
    public void lodad(){
        log.clear();
        try {
            
            String logfile = "select * from getLog();";
            
            Statement statement = DBConnection.c.createStatement();
            ResultSet queryResult = statement.executeQuery(logfile);
            while(queryResult.next()){
                if(queryResult.getString("operation").equals("DELETE")){
                    String logString = queryResult.getTimestamp("tstamp") + " User:" + queryResult.getString("who") 
                    + " DELETED " + queryResult.getString("oldValue") + " FROM " + queryResult.getString("tabname");
                    log.appendText(logString + "\n");
                }
                else if(queryResult.getString("operation").equals("INSERT")){
                    String logString = queryResult.getTimestamp("tstamp") + " User:" + queryResult.getString("who") 
                    + " INSERTED " + queryResult.getString("newValue") + " TO " + queryResult.getString("tabname");
                    log.appendText(logString + "\n");
                }
                else{
                    String logString = queryResult.getTimestamp("tstamp") + " User:" + queryResult.getString("who") 
                    + " UPDATED " + queryResult.getString("oldValue") + " TO " + queryResult.getString("oldValue") + " ON"+ queryResult.getString("tabname");
                    log.appendText(logString + "\n");
                }
                
            }
            
            
            statement.close();
            queryResult.close();

        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
