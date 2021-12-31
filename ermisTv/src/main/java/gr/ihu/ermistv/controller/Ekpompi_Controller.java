package gr.ihu.ermistv.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import gr.ihu.ermistv.DBConnection;
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

import static javafx.geometry.Pos.CENTER;

public class Ekpompi_Controller  implements Initializable {

    @FXML
    private ScrollBar ScrollBar;

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
    private Button btnConfEdit;

    @FXML
    private Button btnEditBroPane;

    @FXML
    private Button btnEditCheck;

    @FXML
    private CheckBox checkBox;

    @FXML
    private ChoiceBox<?> choiceEditDay;

    @FXML
    private ChoiceBox choiceRatingBro;

    @FXML
    private AnchorPane editAnchorPane;

    @FXML
    private AnchorPane editFactorBroadcast;

    @FXML
    private TextField editName;

    @FXML
    private TextField editTime;

    @FXML
    private VBox ekpompivbox;

    @FXML
    private TextField filterID;

    @FXML
    private TextField filterName;

    @FXML
    private ChoiceBox<String> filterRating;

    @FXML
    private Label labelGetName;

    @FXML
    private AnchorPane paneEkpompi;

    @FXML
    private Label searchIcon;

    @FXML
    private TextField searchName;

    @FXML
    private Text sliderText;

    @FXML
    private RangeSlider sliderr;

    @FXML
    private Slider timeSlider;

    @FXML
    private Text txtRange;

    @FXML
    private FontAwesomeIconView x;

    @FXML
    private FontAwesomeIconView x1;


    @FXML
    void editFactorBroadcast(MouseEvent event) {

    }

    int low = 10;
    int high = 300;
    int valueRange;
    private ArrayList<String> ratingC = new ArrayList <String>();

    // Load Results Ekpompi
    private void loadResults(String id, String name, String rating, String timeLow, String timeHigh) {
        ekpompivbox.getChildren().clear();
        String getEkmompes = "select * from getResult(" + id + "," + name + "," + rating + "," + timeLow + ","
                + timeHigh + ");";


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
                    hboxinside.setPrefWidth(198);
                    hboxinside.setAlignment(CENTER);
                    hboxinside.setPadding(new Insets(5, 5, 5, 5));
                    Text text = new Text();
                    text.setText(String.valueOf(rs.getString(i)));
                    text.setWrappingWidth(160);
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
                hbox.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        MouseButton button = event.getButton();
                        if (button == MouseButton.SECONDARY) {
                            ContextMenu menu = new ContextMenu();
                            MenuItem item = new MenuItem();
                            item.setText("Delete");
                            menu.getItems().add(item);
                            menu.show(hbox, event.getScreenX(), event.getScreenY());
                            item.setOnAction(event2 -> {
                                HBox hboxC = (HBox) hbox.getChildren().get(0);
                                Text text2 = (Text) hboxC.getChildren().get(0);
                                String deleteek = "select * from deleteEkpompi(" + text2.getText() + ");";
                                try {
                                    statement.executeQuery(deleteek);
                                    filterEkpompi();

                                } catch (SQLException ex) {
                                    Logger.getLogger(Secondary_Controller.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            });
                        }

                    }

                });
                ekpompivbox.getChildren().add(hbox);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Secondary_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    private void createRating(){


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

        } catch (SQLException ex) {
            Logger.getLogger(Secondary_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    // Add Ekpompi
    @FXML
    private void addbroadcast() {
        int intValue;
        try {
            String name = addNameBro.getText();
            String rating = (String) choiceRatingBro.getValue();
            int time = valueRange;
            if (name == "") {
                broErrLabel.setText("ADD NAME!");
            } else if (rating == null || rating == "empty") {
                broErrLabel.setText("ADD RATING!");
            } else if (time == 0) {
                broErrLabel.setText("ADD TIME ");
            } else {
                String addbroadcast = "select addbroadcast('" + name + "','" + rating + "','" + time + "');";
                Statement statement = DBConnection.c.createStatement();
                ResultSet rs = statement.executeQuery(addbroadcast);

                System.out.println("Success");
                filterEkpompi();
                addNameBro.clear();
                choiceRatingBro.setValue(null);
                timeSlider.setValue(0);
                broErrLabel.setText("");
                paneEkpompi.toFront();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Secondary_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @FXML
    private void addBroadcast(MouseEvent event) {
        addBroadcast.toFront();
        createRating();
        
    }

    // Reload Table Ekpompi ??
    @FXML
    private void reloadPage(MouseEvent event) {
        filterEkpompi();
        createRating();
        filterID.clear();
        filterName.clear();
        high = 300;
        low = 10;
        sliderr.setHighValue(high);
        sliderr.setLowValue(low);
        sliderText.setText(String.valueOf(low) + " - " + String.valueOf(high));
    }
    // Is Numeric Method
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return true;
        }
        try {
            Integer d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return true;
        }
        return false;
    }
    // Filter Ekpompi
    private void filterEkpompi() {
        String id = "'" + filterID.getText() + "'";
        if (isNumeric(filterID.getText()) || filterID.getText().isEmpty()) {
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
        loadResults(id, name, rating, String.valueOf(low), String.valueOf(high));
    }

    // Override
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // choice rating
        createRating();

        // Range Slider
        sliderr.setLowValue(low);
        sliderr.setHighValue(high);
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

        timeSlider.setValue(0);
        timeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            valueRange = (int) Math.round((newValue.doubleValue()));
            txtRange.setText(" 0  -  " + String.valueOf(valueRange));
            filterEkpompi();
        });
        
        loadResults("null", "null", "null", String.valueOf(low), String.valueOf(high));
    }
    @FXML
    private void popupsHandleClicks(MouseEvent event) throws IOException {
        if (event.getSource() == x) {
            paneEkpompi.toFront();
        } else if (event.getSource() == x1) {
            paneEkpompi.toFront();
        }
    }
}