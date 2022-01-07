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
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.stage.Screen;
//import com.jcraft.jsch.*;
//import java.util.Properties;

/**
 * JavaFX App
 */

public class App extends Application {

    private static Scene scene;
    public static Stage stage;
    public static Secondary_Controller controller;

    @Override
    public void start(Stage stage) throws IOException {
        
        CrunchifyGetPropertyValues properties = new CrunchifyGetPropertyValues("app/config.properties");
        String userDB = properties.getProperty("user");
        String passDB = properties.getProperty("pass");
        String url = properties.getProperty("url");
        try {
            DBConnection.connect(url, userDB, passDB);
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

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

    }

    public static void main(String[] args) {

        launch();
    }

}
