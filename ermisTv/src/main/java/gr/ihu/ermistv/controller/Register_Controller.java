package gr.ihu.ermistv.controller;

import gr.ihu.ermistv.App;
import gr.ihu.ermistv.CrunchifyGetPropertyValues;
import gr.ihu.ermistv.DBConnection;
import gr.ihu.ermistv.ScenesSet;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class Register_Controller implements Runnable{

    @FXML
    private HBox Hbox,reconnect;
    @FXML
    private PasswordField fPass, cPass;
    @FXML
    private TextField email, fName, lName,uName ;
    @FXML
    private Label messageLabel;
    @FXML
    private AnchorPane register;

    @FXML
    void back(MouseEvent event) throws IOException {
        loginBack();
    }
    private String regexPattern = "^(.+)@(\\S+)$";

    @FXML
    private void minimizedWindow(MouseEvent event) {
        Stage stage = new Stage();
        stage = (Stage) register.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void closeWindow(MouseEvent event) {
        System.exit(0);
        Platform.exit();
    }

    @FXML
    private void clearbox(MouseEvent event) {
        TextField text = ((TextField) event.getSource());
        text.getStyleClass().add("error");
        setError("");
    }

    @FXML
    private void reconnect(MouseEvent event){
        if(!running){
            Thread thread = new Thread(this);
            thread.start();
        }
    }

    public void RegisterButtonOnAction() throws Exception {
        boolean anyEmpty = fName.getText().equals("") || isNumeric.isNotNumeric(fName.getText())||
                isNumeric.isNotNumeric(lName.getText()) ||
                lName.getText().equals("") ||
                email.getText().equals("") ||
                uName.getText().equals("") ||
                fPass.getText().equals("") ||
                cPass.getText().equals("");

        if (anyEmpty) {
            if (fName.getText().equals("") || isNumeric.isNotNumeric(fName.getText())) {
                fName.getStyleClass().add("error");

            }else {
                fName.getStyleClass().add("noError");
            }
            if (lName.getText().equals("") || isNumeric.isNotNumeric(lName.getText())) {
                lName.getStyleClass().add("error");
            }else {
                lName.getStyleClass().add("noError");
            }
            if (email.getText().equals("") ) {
                email.getStyleClass().add("error");

            }else {
                email.getStyleClass().add("noError");
            }
            if (uName.getText().equals("")) {
                uName.getStyleClass().add("error");

            }else {
                uName.getStyleClass().add("noError");
            }
            if (fPass.getText().equals("")) {
                fPass.getStyleClass().add("error");

            }else {
                fPass.getStyleClass().add("noError");
            }
            if (cPass.getText().equals("")) {
                cPass.getStyleClass().add("error");

            }else {
                cPass.getStyleClass().add("noError");
            }
            setError("Fill the Blanks");
            return;
        }
        if (patternMatches(email.getText(), regexPattern)) {
            if (fPass.getText().equals(cPass.getText())) {
                messageLabel.setText("");
                registerUser();

            } else {
                cPass.getStyleClass().add("error");
                setError("Not Matching Password");
            }
        } else {
            setError("Incorrect Email");
        }
//        loginBack();

    }

    public void registerUser() throws IOException {
        CrunchifyGetPropertyValues properties = new CrunchifyGetPropertyValues("app/config.properties");

        String firstName = fName.getText();
        String lastName = lName.getText();
        String Email = email.getText();
        String Username = uName.getText();
        String Password = fPass.getText();

        Statement statement;
        String setAccount = "select setAccount('" + firstName + "','" + lastName + "','" + Email  + "','" + Username + "','"
                + Password + "')";
        try {
            statement = DBConnection.c.createStatement();
            ResultSet rs =statement.executeQuery(setAccount) ;
            rs.next();
            switch (rs.getInt(1)){
                case 0:
                    messageLabel.getStyleClass().add("green");
                    messageLabel.setText("User has been registered successfully!");
                    statement.close();
                    rs.close();
                    loginBack();
                    break;
                case 1:
                    messageLabel.getStyleClass().add("red");
                    messageLabel.setText("To email xreisimopoiite eidi!");
                    break;
                case 2:
                    messageLabel.getStyleClass().add("red");
                    messageLabel.setText("To username xreisimopoiite eidi!");
                    break;
                case 3:
                    messageLabel.getStyleClass().add("red");
                    messageLabel.setText("Yparxei eidi!");
                    break;
                case 4:
                    messageLabel.getStyleClass().add("red");
                    messageLabel.setText("ERROR!");
                    break;
            }
//
//            //1234
//            statement.close();
//            rs.close();
//            statement.close();
//                rs.close();
//
        } catch (SQLException e) {
            reconnect.setVisible(true);
            messageLabel.setText("Connection Error!");
        }
    }

    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

    public void setError(String error) {
        messageLabel.setStyle("-fx-text-fill: red;");
        messageLabel.setText(error);
    }

    private void loginBack() throws IOException {
        Stage stage = (Stage) register.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/login_view.fxml"));
        Parent root;
        root = fxmlLoader.load();
        Scene scene = new ScenesSet(root, stage, 640, 480, "#Hbox");
        stage.setX(stage.getX() - 100);
        stage.setScene(scene);

    }

    private boolean running = false;

    @Override
    public void run(){
        running = true;
        try {
            DBConnection.connect();
            reconnect.setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(login_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        running = false;
    }
}
