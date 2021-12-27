package gr.ihu.ermistv.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gr.ihu.ermistv.App;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Secondary_Controller implements Initializable {
    @FXML
    private AnchorPane secondary;
    @FXML
    private ChoiceBox choise_day,choise_type;
    @FXML
    private Label label_test;

    @FXML
    private void minimizedWindow(MouseEvent event) {
        Stage stage = (Stage) secondary.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void closeWindow(MouseEvent event) {
        System.exit(0);
        Platform.exit();
    }
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("fxml/login_view");
    }
    private String[] dayC ={"monday","tuesday","wednesday","thrusday","friday","saturday","sunday"};
    private String[] typeC ={"movie","series","broadcast","documentary","NEWS"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choise_day.getItems().addAll(dayC);
        choise_day.setOnAction(this::getDay);

        choise_type.getItems().addAll(typeC);
        choise_type.setOnAction(this::geType);
    }

    private void geType(Event event) {
        String type = (String) choise_type.getValue();
        label_test.setText(type);
    }

    public void getDay(Event event){
        String day = (String) choise_day.getValue();
        label_test.setText(day);
    }
}