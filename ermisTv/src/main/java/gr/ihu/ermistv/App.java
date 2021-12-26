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

    @Override
    public void start(Stage stage) throws IOException {
        /* THANASI HELP
        ΘΕΛΩ ΤΟ LOGIN ΝΑ ΕΙΝΑΙ  640 Χ 480
        ΚΑΙ ΤΟ SECONDARY 1200 X 600 Ή ΜΙΑ ΑΛΛΗ ΔΙΑΣΤΑΣΗ
        ΜΕΓΑΛΥΤΕΡΗ ΤΟΥ 640 Χ 480
         */
        scene = new Scene(loadFXML("fxml/login_view"), 1200, 600);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args){
        Connection c = null;
      try {
         //Class.forName("org.postgresql.Driver");
          // c = DriverManager.getConnection(credentials.url, credentials.user, credentials.pass);
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
      System.out.println("Opened database successfully");
        launch();
    }


}