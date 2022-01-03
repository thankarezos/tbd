package gr.ihu.ermistv.controller;

import gr.ihu.ermistv.CrunchifyGetPropertyValues;
import gr.ihu.ermistv.DBConnection;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.*;
import java.util.regex.Pattern;

public class Register_Controller {

    @FXML
    private HBox Hbox;

    @FXML
    private PasswordField cPass;

    @FXML
    private TextField email;

    @FXML
    private TextField fName;

    @FXML
    private PasswordField fPass;

    @FXML
    private TextField lName;

    @FXML
    private Label messageLabel;

    @FXML
    private AnchorPane register;

    @FXML
    private TextField userName;

    @FXML
    void Register(MouseEvent event) {

    }

    @FXML
    void back(MouseEvent event) {

    }
    private String regexPattern = "^(.+)@(\\S+)$";

    @FXML
    private void minimizedWindow(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) register.getScene().getWindow();
        stage.setIconified(true);
        testUsingSimpleRegex();
    }

    @FXML
    private void closeWindow(MouseEvent event) {
        System.exit(0);
        Platform.exit();
    }

    @FXML
    private void onClickClose(ActionEvent event) {
        System.exit(0);
        Platform.exit();
    }

    @FXML
    private void clearbox(MouseEvent event) {
        TextField text = ((TextField) event.getSource());
        text.setStyle("-fx-border-color: red ;");
        setError("");

    }

    public void RegisterButtonOnAction() throws Exception {
        boolean anyEmpty = fName.getText().equals("") ||
                lName.getText().equals("") ||
                email.getText().equals("") ||
                userName.getText().equals("") ||
                fPass.getText().equals("") ||
                cPass.getText().equals("");

        if (anyEmpty) {
            if (fName.getText().equals("")) {
                fName.setStyle("-fx-border-color: red;");

            }
            if (lName.getText().equals("")) {
                lName.setStyle("-fx-border-color: red;");
            }
            if (email.getText().equals("")) {
                email.setStyle("-fx-border-color: red;");

            }
            if (userName.getText().equals("")) {
                userName.setStyle("-fx-border-color: red;");

            }
            if (fPass.getText().equals("")) {
                fPass.setStyle("-fx-border-color: red;");

            }
            if (cPass.getText().equals("")) {
                cPass.setStyle("-fx-border-color: red;");

            }
            setError("Fill the Blanks");
            return;
        }
        if (patternMatches(email.getText(), regexPattern)) {
            if (fPass.getText().equals(cPass.getText())) {
                registerUser();

            } else {
                cPass.setStyle("-fx-border-color: red;");
                setError("Not Matching Password");
            }
        } else {
            setError("Incorect Email");
        }
    }

    public void registerUser() throws SQLException {
        CrunchifyGetPropertyValues properties = new CrunchifyGetPropertyValues("app/config.properties");
        String user = properties.getProperty("user");
        String pass = properties.getProperty("pass");
        String url = properties.getProperty("url");
//

        DBConnection.connect(url, user, pass);

        String firstName = fName.getText();
        String lastName = lName.getText();
        String Email = email.getText();
        String Username = userName.getText();
        String Password = fPass.getText();

        Statement statement;
        String setAccount= "";
        try {
            statement = DBConnection.c.createStatement();
            ResultSet rs = statement.executeQuery(setAccount);
            messageLabel.setStyle("-fx-text-fill: green;");
            messageLabel.setText("User has been registered successfully!");
            //System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }


    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

    public void testUsingSimpleRegex() {
        String emailAddress = "username@domain.com";
        String regexPattern = "^(.+)@(\\S+)$";
        if (patternMatches(emailAddress, regexPattern)) {
            System.out.println("YES");
        }
    }

    public void setError(String error) {
        messageLabel.setStyle("-fx-text-fill: red;");
        messageLabel.setText(error);
    }
}
