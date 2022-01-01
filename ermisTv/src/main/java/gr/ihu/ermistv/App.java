package gr.ihu.ermistv;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import com.jcraft.jsch.*;

import java.io.IOException;

import java.sql.*;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import com.jcraft.jsch.*;
/**
 * JavaFX App
 */
class localUserInfo implements UserInfo{
    String passwd;
    public String getPassword(){ return passwd; }
    public boolean promptYesNo(String str){return true;}
    public String getPassphrase(){ return null; }
    public boolean promptPassphrase(String message){return true; }
    public boolean promptPassword(String message){return true;}
    public void showMessage(String message){}
  }
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
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        double x = bounds.getMinX() + (bounds.getWidth() - scene.getWidth()) * 0.5;
        double y = bounds.getMinY() + (bounds.getHeight() - scene.getHeight()) * 0.5;
        stage.setX(x);
        stage.setY(y);
        stage.show();
        stage.setResizable(false);
        this.stage = stage;

    }

    public static void main(String[] args) throws SQLException, JSchException {
//        JSch jsch = new JSch();
//        Session session=jsch.getSession("it185193", "users.Iee.ihu.gr", 22);
//        session.setPassword("***REMOVED***");
//        localUserInfo lui=new localUserInfo();
//        session.setUserInfo(lui);
//        jsch.setKnownHosts("C:\\Users\\User\\.ssh\\known_hosts");
//        jsch.addIdentity("C:\\Users\\User\\.ssh\\id_rsa");
//        
//        session.connect(10000);
//        session.setPortForwardingL(5432, "dblabs.iee.ihu.gr", 5432);
//        System.out.println("Connected");
       
        CrunchifyGetPropertyValues properties = new CrunchifyGetPropertyValues("app/config.properties");
        String user = properties.getProperty("user");
        String pass = properties.getProperty("pass");
        String url = properties.getProperty("url");
        DBConnection.connect(url, user, pass);
        launch();
    }

}
