package gr.ihu.ermistv;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

public class PrimaryController {
    @FXML
    private AnchorPane primary;

    @FXML
    private TextField fdUser, fdPass;

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
    private void switchToSecondary() throws IOException {
        App.setRoot("fxml/secondary");
    }
}
