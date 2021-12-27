package gr.ihu.ermistv;

import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.event.EventHandler;
import javafx.scene.SceneAntialiasing;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ScenesSet extends Scene {
    private double xOffset = 0;
    private double yOffset = 0;

    public ScenesSet(Parent root,Stage stage, float x, float y) throws IOException {
        super(root, x, y, false, SceneAntialiasing.BALANCED);
    }

    public ScenesSet(Parent root,Stage stage, float x, float y, String top) {
        super(root, x, y, false, SceneAntialiasing.BALANCED);
        HBox Hbox = (HBox) super.lookup(top);
        Hbox.setOnMousePressed((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        }));

        Hbox.setOnMouseDragged((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
                stage.setOpacity(0.8f);
            }
        }));
        Hbox.setOnMouseReleased((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
                stage.setOpacity(1.0f);
            }
        }));
    }
}
