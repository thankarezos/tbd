package gr.ihu.ermistv;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import java.sql.*;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
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

    public static void main(String[] args) throws SQLException{
        Connection con = null;
        try {
           Class.forName("org.postgresql.Driver");
           con = DriverManager.getConnection(credentials.url, credentials.user, credentials.pass);
        } catch (Exception e) {
           e.printStackTrace();
           System.err.println(e.getClass().getName()+": "+e.getMessage());
           System.exit(0);
        }
        System.out.println("Opened database successfully");
        
        Statement stmt = con.createStatement();
        String selectekpompes = "Select * from ekpompes";
        
        ResultSet rs = stmt.executeQuery(selectekpompes);
        while (rs.next()) {
            String s = rs.getString("name");
            System.out.println(s);
        }
        String importi = "INSERT INTO ekpompes (name,rating)\n" +
                            "VALUES ('teswt','18+');";
        stmt.executeUpdate(importi);
        
        System.out.println();
        System.out.println();
        
        rs = stmt.executeQuery(selectekpompes);
        while (rs.next()) {
            String s = rs.getString("name");
            String s2 = rs.getString("sid");
            System.out.println(s2 + " " +s);
        }
        
        launch();
    }

}