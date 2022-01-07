package gr.ihu.ermistv.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import gr.ihu.ermistv.App;
import gr.ihu.ermistv.DBConnection;
import gr.ihu.ermistv.ScenesSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import org.controlsfx.control.RangeSlider;

import java.io.IOException;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;

import static javafx.geometry.Pos.CENTER;

import javafx.scene.Parent;
import javafx.scene.Scene;

public class Ekpompi_Controller implements Initializable {

    @FXML
    private AnchorPane addBroadcast;

    @FXML
    private TextField addNameBro;

    @FXML
    private Label broErrLabel;

    @FXML
    private Button btnAddBroPane;

    @FXML
    private Button btnConfBro;

    @FXML
    private ChoiceBox choiceRatingBro, choiceTypeBro;

    @FXML
    private VBox ekpompivbox;

    @FXML
    private TextField filterID, addTimeBro, filterName;

    @FXML
    private ChoiceBox<String> filterRating, filterType;

    @FXML
    private AnchorPane paneEkpompi;
    @FXML
    private Text sliderText;

    @FXML
    private RangeSlider sliderr;

    @FXML
    private FontAwesomeIconView x;

    @FXML
    void editFactorBroadcast(MouseEvent event) {

    }

    private AnchorPane mainAp;
    int lowinit = 30;
    int highinit = 300;
    int low = lowinit;
    int high = highinit;
    private ArrayList<String> ratingC = new ArrayList<String>();
    private ArrayList<String> type_ekC = new ArrayList<String>();

    // Load Results Ekpompi
    private void loadResults(String id, String name, String type_ek, String rating, String timeLow, String timeHigh) {
        ekpompivbox.getChildren().clear();
        String getEkmompes = "select * from getResults(" + id + "," + name + "," + type_ek + "," + rating + ","
                + timeLow + "," + timeHigh + ");";

        Statement statement;
        try {

            statement = DBConnection.c.createStatement();
            ResultSet rs = statement.executeQuery(getEkmompes);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                HBox hbox = new HBox();

                for (int i = 1; i <= columnsNumber; i++) {
                    hbox.setSpacing(3);
                    HBox hboxinside = new HBox();
                    hboxinside.getStyleClass().add("hboxStyle");
                    hboxinside.setPrefWidth(160);
                    hboxinside.setAlignment(CENTER);
                    hboxinside.setPadding(new Insets(5, 5, 5, 5));
                    Text text = new Text();
                    text.setText(String.valueOf(rs.getString(i)));
                    text.setWrappingWidth(145);
                    text.setTextAlignment(TextAlignment.CENTER);
                    hboxinside.getChildren().add(text);
                    hbox.getChildren().add(hboxinside);

                    hbox.addEventHandler(MouseEvent.MOUSE_ENTERED,
                            new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {

                                    HBox hbox = (HBox) event.getSource();
                                    hbox.getChildren().get(0);
                                    hbox.getChildren();

                                    for (int i = 0; i < hbox.getChildren().size(); i++) {

                                        HBox pane = (HBox) hbox.getChildren().get(i);
                                        pane.getStyleClass().add("hboxStylehover");
                                    }

                                }
                            });

                    hbox.addEventHandler(MouseEvent.MOUSE_EXITED,
                            new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {

                                    HBox hbox = (HBox) event.getSource();
                                    hbox.getChildren().get(0);
                                    hbox.getChildren();

                                    for (int i = 0; i < hbox.getChildren().size(); i++) {
                                        HBox pane = (HBox) hbox.getChildren().get(i);
                                        pane.getStyleClass().clear();
                                        pane.getStyleClass().add("hboxStyle");
                                        Text text = (Text) pane.getChildren().get(0);
                                    }

                                }
                            });

                }
                ContextMenu menu = new ContextMenu();
                Statement statement2 = DBConnection.c.createStatement();
                hbox.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        MouseButton button = event.getButton();
                        if (button == MouseButton.SECONDARY) {
                            menu.getItems().clear();
                            MenuItem item = new MenuItem();
                            item.setText("Delete");
                            menu.getItems().add(item);
                            menu.show(hbox, event.getScreenX(), event.getScreenY());
                            item.setOnAction(event2 -> {
                                HBox hboxC = (HBox) hbox.getChildren().get(0);
                                Text text2 = (Text) hboxC.getChildren().get(0);
                                String deleteek = "select * from deleteEkpompi(" + text2.getText() + ");";
                                try {
                                    statement2.executeQuery(deleteek);
                                    filterEkpompi();
                                    statement2.close();

                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                    ex.getCause();
                                }
                            });
                        }
                        if (button == MouseButton.PRIMARY) {
                            Parent root;
                            try {
                                HBox hboxC = (HBox) hbox.getChildren().get(0);
                                Text text2 = (Text) hboxC.getChildren().get(0);
                                int id = Integer.parseInt(text2.getText());

                                FXMLLoader loader = new FXMLLoader(
                                        App.class.getResource("fxml/syntelestes_ekpompon.fxml"));
                                SyntelestesEkpompon_Controller controller = new SyntelestesEkpompon_Controller(id);
                                loader.setController(controller);
                                root = loader.load();
                                Scene scene = new ScenesSet(root, App.stage, 876, 517);

                                controller.setAp(mainAp);
                                mainAp.getChildren().add(root);

                            } catch (IOException ex) {
                                ex.printStackTrace();
                                ex.getCause();
                            }
                        }

                    }

                });
                ekpompivbox.getChildren().add(hbox);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            ex.getCause();
        }

    }

    private void createRating() {

        Statement statement;
        try {
            statement = DBConnection.c.createStatement();
            String setRating = "SELECT unnest(enum_range(NULL::rating)) ";
            ResultSet rs2 = statement.executeQuery(setRating);
            ratingC.clear();
            ratingC.add("");
            while (rs2.next()) {
                ratingC.add(rs2.getString("unnest"));
            }
            ObservableList<String> rate = FXCollections.observableArrayList(ratingC);
            filterRating.setItems(rate);
            choiceRatingBro.setItems(rate);
            statement.close();
            rs2.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            ex.getCause();
        }

    }

    private void createType_ek() {

        Statement statement;
        try {
            statement = DBConnection.c.createStatement();
            String setType_ek = "SELECT unnest(enum_range(NULL::type_ek)) ";
            ResultSet rs2 = statement.executeQuery(setType_ek);
            type_ekC.clear();
            type_ekC.add("");
            while (rs2.next()) {
                type_ekC.add(rs2.getString("unnest"));
            }
            ObservableList<String> rate = FXCollections.observableArrayList(type_ekC);
            filterType.setItems(rate);
            choiceTypeBro.setItems(rate);
            statement.close();
            rs2.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            ex.getCause();
            ;
        }

    }

    // Add Ekpompi
    @FXML
    private void addbroadcast() throws IOException {
        int intValue;
        try {
            String name = addNameBro.getText();
            String rating = (String) choiceRatingBro.getValue();
            String type = (String) choiceTypeBro.getValue();
            String time = addTimeBro.getText();
            if (name == "") {
                broErrLabel.setText("ADD NAME!");
            } else if (rating == null || rating == "") {
                broErrLabel.setText("ADD RATING!");
            } else if (type == null || type == "") {
                broErrLabel.setText("ADD TYPE! ");
            } else if (time == null || isNumeric.isNumeric(time)) {
                broErrLabel.setText("ADD TIME! ");
            } else {
                int x = Integer.parseInt(time);
                if (x >= 30 && x <= 300) {
                    String addbroadcast = "select addbroadcast('" + name + "','" + type + "','" + rating + "','" + time
                            + "');";
                    Statement statement = DBConnection.c.createStatement();
                    ResultSet rs = statement.executeQuery(addbroadcast);

                    App.controller.errorMessage("Added Successfully!");
                    filterEkpompi();
                    addNameBro.clear();
                    choiceRatingBro.setValue(null);
                    choiceTypeBro.setValue(null);
                    addTimeBro.clear();
                    broErrLabel.setText("");
                    paneEkpompi.toFront();
                } else {
                    broErrLabel.setText("Time must be greater than 30sec and less than 300sec");
                }
            }
        } catch (SQLException ex) {
            App.controller.errorMessage("Error");
            ex.printStackTrace();
            ex.getCause();
        }

    }

    @FXML
    private void addBroadcast(MouseEvent event) {
        addBroadcast.toFront();
        createRating();
        createType_ek();

    }

    // Reload Table Ekpompi ??
    @FXML
    public void reloadPage() {
        // App.controller.errorMessage("Reload");
        filterEkpompi();
        createRating();
        createType_ek();
        filterID.clear();
        filterName.clear();
        sliderr.setHighValue(highinit);
        sliderr.setLowValue(lowinit);
        sliderText.setText(String.valueOf(low) + " - " + String.valueOf(high));
    }

    // Is Numeric Method

    // Filter Ekpompi
    private void filterEkpompi() {
        String id = "'" + filterID.getText() + "'";
        if (isNumeric.isNumeric(filterID.getText()) || filterID.getText().isEmpty()) {
            id = "null";
        }
        String name = "'" + filterName.getText() + "'";

        if (filterName.getText().isEmpty()) {
            name = "null";
        }

        String rating = "'" + String.valueOf(filterRating.getValue()) + "'";

        if (String.valueOf(filterRating.getValue()).isEmpty() || filterRating.getValue() == null) {
            rating = "null";
        }

        String type_ek = "'" + String.valueOf(filterType.getValue()) + "'";

        if (String.valueOf(filterType.getValue()).isEmpty() || filterType.getValue() == null) {
            type_ek = "null";
        }
        loadResults(id, name, type_ek, rating, String.valueOf(low), String.valueOf(high));
    }

    // Override
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // choice Rating
        createRating();
        // choise Type
        createType_ek();

        // Range Slider
        sliderr.setLowValue(low);
        sliderr.setHighValue(high);
        sliderr.setMin(lowinit);
        sliderr.setMax(highinit);
        sliderText.setText(String.valueOf(low) + " - " + String.valueOf(high));

        filterID.textProperty().addListener((observable, oldValue, newValue) -> {
            filterEkpompi();
        });
        filterName.textProperty().addListener((observable, oldValue, newValue) -> {
            filterEkpompi();
        });
        filterRating.valueProperty().addListener((observable, oldValue, newValue) -> {
            filterEkpompi();
        });
        filterType.valueProperty().addListener((observable, oldValue, newValue) -> {
            filterEkpompi();
        });
        sliderr.highValueProperty().addListener((observable, oldValue, newValue) -> {

            high = (int) Math.round(newValue.doubleValue());
            sliderText.setText(String.valueOf(low) + " - " + String.valueOf(high));
            filterEkpompi();

        });
        sliderr.lowValueProperty().addListener((observable, oldValue, newValue) -> {

            low = (int) Math.round(newValue.doubleValue());
            sliderText.setText(String.valueOf(low) + " - " + String.valueOf(high));
            filterEkpompi();

        });

        loadResults("null", "null", "null", "null", String.valueOf(low), String.valueOf(high));
    }

    @FXML
    private void popupsHandleClicks(MouseEvent event) throws IOException {
        if (event.getSource() == x) {
            paneEkpompi.toFront();
        }
    }

    public void setAp(AnchorPane ap) {
        mainAp = ap;
    }
}
