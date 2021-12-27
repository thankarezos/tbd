package gr.ihu.ermistv.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class popupDeleteSyntelestes_Controller {
    @FXML
    private Button btnDelete;
    @FXML
    private TextField dName,dSurname;

    @FXML
    private void closeWindow(MouseEvent event) {
        System.exit(0);
        Platform.exit();
    }
}


