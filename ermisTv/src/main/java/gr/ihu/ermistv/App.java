package gr.ihu.ermistv;

import gr.ihu.ermistv.controller.Secondary_Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import java.sql.*;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.stage.Screen;
//import com.jcraft.jsch.*;
//import java.util.Properties;

/**
 * JavaFX App
 */

public class App extends Application {
    
    public static String cacheduseer;
    public static String cachedpass;

    private static Scene scene;
    public static Stage stage;
    public static Secondary_Controller controller;

    @Override
    public void start(Stage stage) throws IOException {
        
        
        try {
            DBConnection.connect();
            
            
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/login_view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new ScenesSet(root, stage, 640, 480, "#Hbox");

            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
            double x = bounds.getMinX() + (bounds.getWidth() - scene.getWidth()) * 0.5;
            double y = bounds.getMinY() + (bounds.getHeight() - scene.getHeight()) * 0.5;
            stage.setX(x);
            stage.setY(y);
            stage.show();
            stage.setResizable(false);
            stage.getIcons().add(new Image(App.class.getResourceAsStream("images/tv.png")));
            this.stage = stage;
        } catch (SQLException ex) {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/error.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new ScenesSet(root, stage, 475, 200, "#Hbox");
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
            double x = bounds.getMinX() + (bounds.getWidth() - scene.getWidth()) * 0.5;
            double y = bounds.getMinY() + (bounds.getHeight() - scene.getHeight()) * 0.5;
            stage.setX(x);
            stage.setY(y);
            stage.show();
            stage.setResizable(false);
            stage.getIcons().add(new Image(App.class.getResourceAsStream("images/tv.png")));
            this.stage = stage;
        }


    }

    public static void main(String[] args) {

        launch();
    }

}
