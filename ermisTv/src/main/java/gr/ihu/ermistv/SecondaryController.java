package gr.ihu.ermistv;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SecondaryController {
    @FXML
    private AnchorPane secondary;

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
}