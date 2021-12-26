package gr.ihu.ermistv;

import java.io.IOException;
import java.sql.*;

import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

public class login_Controller {
    @FXML
    private AnchorPane primary;

    @FXML
    private TextField fdUser, fdPass;

    @FXML
    private Label messageLabel;

    @FXML
    private void minimizedWindow(MouseEvent event) {
        Stage stage = (Stage) primary.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void closeWindow(MouseEvent event) {
        System.exit(0);
        Platform.exit();
    }

    @FXML
    private void validateLogin() throws SQLException {
        Connection c = DriverManager.getConnection(credentials.url, credentials.user, credentials.pass);

        String verifyLogin = "select count(1) from account where username = '" +
                fdUser.getText() + "' and pass = '" + fdPass.getText() + "';";

        try {

            Statement statement = c.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    //game
                    messageLabel.setStyle("-fx-text-fill: green");
                    messageLabel.setText("Congratulations!");
                    App.setRoot("fxml/secondary");
                } else {
                    messageLabel.setText("Invalid login. Please try again !");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("fxml/secondary");
    }
}
