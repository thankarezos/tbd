package gr.ihu.ermistv.controller;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class popupNewSyntelestes_Controller implements Initializable {
    @FXML
    private Button btCheck,btConf;
    @FXML
    private ChoiceBox<String> choiceRole;
    @FXML
    private TextField fdName,fdPhoneN,fdSurname;
    @FXML
    private Label label_test;

    @FXML
    private void closeWindow(MouseEvent event) {
        System.exit(0);
        Platform.exit();
    }
    private String[] typeRole ={"movie","series","broadcast","documentary","NEWS"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceRole.getItems().addAll(typeRole);
        choiceRole.setOnAction(this::getType);
    }

    private void getType(Event event) {
        String type = (String) choiceRole.getValue();
        label_test.setText(type);
    }

}
