package gr.ihu.ermistv.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class error_controller implements Initializable {
    @FXML
    private FontAwesomeIconView one, two;

    @FXML
    private void close(MouseEvent event) {
        System.exit(0);
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        one.getStyleClass().setAll("redF");
        two.getStyleClass().setAll("redF");
    }

}
