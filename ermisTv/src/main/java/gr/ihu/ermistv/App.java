package gr.ihu.ermistv;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import java.sql.*;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/login_view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new ScenesSet(root, stage, 640, 480, "#Hbox");

        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        stage.setResizable(false);
        this.stage = stage;
    }

    public static void main(String[] args) throws SQLException {
        CrunchifyGetPropertyValues properties = new CrunchifyGetPropertyValues("app/config.properties");
        String user = properties.getProperty("user");
        String pass = properties.getProperty("pass");
        String url = properties.getProperty("url");
        DBConnection.connect(url, user, pass);
        launch();
    }

}
