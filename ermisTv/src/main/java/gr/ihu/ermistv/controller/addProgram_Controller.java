package gr.ihu.ermistv.controller;

import gr.ihu.ermistv.App;
import gr.ihu.ermistv.DBConnection;
import gr.ihu.ermistv.HboxEnch;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import org.controlsfx.control.RangeSlider;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.*;
import com.dlsc.gemsfx.TimePicker;

import static javafx.geometry.Pos.CENTER;

public class addProgram_Controller implements Initializable {

    @FXML
    private ChoiceBox<String> addDay, addTime1,ekpompiType, ekpompiRating;
    @FXML
    private AnchorPane addEkpompi;
    @FXML
    private HBox timePicker;
    @FXML
    private Label broErrLabel;
    @FXML
    private TextField ekpompiName,ekpompiID, addTime2;
    @FXML
    private Text sliderText;
    @FXML
    private RangeSlider sliderr;
    @FXML
    private VBox vboxEkpompi;

    private AnchorPane Pop;
    private Pane mainP;
    private Program_Controller pC;
    private ArrayList<String> ratingC = new ArrayList<String>();
    private ArrayList<String> typeC = new ArrayList<String>();
    private ArrayList<String> dayC = new ArrayList<String>();
    private TimePicker timeP = new TimePicker();

    private int lowinit = 30;
    private int highinit = 300;
    private int low = lowinit;
    private int high = highinit;
    private int pressed = -1;

    @FXML
    private void reloadProgram(MouseEvent event) {
        filterAddEkpompi();
        createType();
        createRating();
        createDay();
        timeP.setTime(LocalTime.parse("00:00"));
        ekpompiID.clear();
        ekpompiName.clear();
        ekpompiType.setValue(null);
        ekpompiRating.setValue(null);
        addDay.setValue(null);
        sliderr.setLowValue(lowinit);
        sliderr.setHighValue(highinit);
    }

    @FXML
    private void addProgram() throws IOException {
        Statement statement;
        String time = String.valueOf(timeP.getTime());

        int dayId = 0;

        if (pressed == -1) {
            App.controller.errorMessage(1, "Choose Broadcast!");
        } else if (1 != 1) {
            App.controller.errorMessage(1, "Add Time!");
        } else if (addDay.getValue() == null) {
            App.controller.errorMessage(1, "Add Day!");
        } else {
            switch (addDay.getValue()) {
                case "Monday":
                    dayId = 1;
                    break;
                case "Tuesday":
                    dayId = 2;
                    break;
                case "Wednesday":
                    dayId = 3;
                    break;
                case "Thursday":
                    dayId = 4;
                    break;
                case "Friday":
                    dayId = 5;
                    break;
                case "Saturday":
                    dayId = 6;
                    break;
                case "Sunday":
                    dayId = 7;
                    break;
            }
            try {
                statement = DBConnection.c.createStatement();
                // "+dayId + " " + "10:00"+"','" + addDay.getValue() +"')"
                String addSyntelestes = "select addPrograms(" + pressed + ",'0001/01/" + dayId + " " + time + "','"
                        + addDay.getValue() + "');";
                ResultSet rs = statement.executeQuery(addSyntelestes);// 0 or 1
                rs.next();
                rs.getInt(1);
                // System.out.println(rs.getInt(1));
                if (rs.getInt(1) == 2) {
                    App.controller.errorMessage(1, "Overlapping");
                } else {
                    App.controller.errorMessage(2, "Added Successfully!");
                    Pop.toBack();
                    pC.loadPrograms();
                }
                statement.close();
                rs.close();

            } catch (SQLException ex) {
                App.controller.errorMessage(1, "Error");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timePicker.getChildren().add(timeP);
        timeP.setTime(LocalTime.parse("00:00"));
        createType();
        createDay();
        createRating();
        sliderr.setLowValue(low);
        sliderr.setHighValue(high);
        sliderr.setMin(lowinit);
        sliderr.setMax(highinit);
        sliderText.setText(String.valueOf(low) + " - " + String.valueOf(high));

        ekpompiID.textProperty().addListener((observable, oldValue, newValue) -> {
            filterAddEkpompi();
        });
        ekpompiName.textProperty().addListener((observable, oldValue, newValue) -> {
            filterAddEkpompi();
        });
        ekpompiType.valueProperty().addListener((observable, oldValue, newValue) -> {
            filterAddEkpompi();
        });
        ekpompiRating.valueProperty().addListener((observable, oldValue, newValue) -> {
            filterAddEkpompi();
        });
        sliderr.highValueProperty().addListener((observable, oldValue, newValue) -> {

            high = (int) Math.round(newValue.doubleValue());
            sliderText.setText(String.valueOf(low) + " - " + String.valueOf(high));
            filterAddEkpompi();

        });
        sliderr.lowValueProperty().addListener((observable, oldValue, newValue) -> {
            low = (int) Math.round(newValue.doubleValue());
            sliderText.setText(String.valueOf(low) + " - " + String.valueOf(high));
            filterAddEkpompi();

        });
        loadResults("null", "null", "null", "null", String.valueOf(low), String.valueOf(high));
    }

    // Filter Ekpompi
    private void filterAddEkpompi() {
        String id = "'" + ekpompiID.getText() + "'";
        if (isNumeric.isNumeric(ekpompiID.getText()) || ekpompiID.getText().isEmpty()) {
            id = "null";
        }
        String name = "'" + ekpompiName.getText() + "'";
        if (ekpompiName.getText().isEmpty()) {
            name = "null";
        }
        String type_ek = "'" + ekpompiType.getValue() + "'";
        if (String.valueOf(ekpompiType.getValue()).isEmpty() || ekpompiType.getValue() == null) {
            type_ek = "null";
        }
        String rating = "'" + ekpompiRating.getValue() + "'";
        if (String.valueOf(ekpompiRating.getValue()).isEmpty() || ekpompiRating.getValue() == null) {
            rating = "null";
        }

        loadResults(id, name, type_ek, rating, String.valueOf(low), String.valueOf(high));
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
            ekpompiRating.setItems(rate);
            statement.close();
            rs2.close();

        } catch (SQLException ex) {
            App.controller.errorMessage();
        }

    }

    private void createType() {

        Statement statement;
        try {
            statement = DBConnection.c.createStatement();
            String setType_ek = "SELECT unnest(enum_range(NULL::type_ek)) ";
            ResultSet rs2 = statement.executeQuery(setType_ek);
            typeC.clear();
            typeC.add("");
            while (rs2.next()) {
                typeC.add(rs2.getString("unnest"));
            }
            ObservableList<String> rate = FXCollections.observableArrayList(typeC);
            ekpompiType.setItems(rate);
            statement.close();
            rs2.close();
        } catch (SQLException ex) {
            App.controller.errorMessage();
        }

    }

    private void createDay() {
        Statement statement;
        try {
            statement = DBConnection.c.createStatement();
            String setPr_day = "SELECT unnest(enum_range(NULL::pr_day)) ";
            ResultSet rs2 = statement.executeQuery(setPr_day);
            dayC.clear();
            dayC.add("");
            while (rs2.next()) {
                dayC.add(rs2.getString("unnest"));
            }
            ObservableList<String> rate = FXCollections.observableArrayList(dayC);
            addDay.setItems(rate);
            statement.close();
            rs2.close();
        } catch (SQLException ex) {
            App.controller.errorMessage();
        }

    }

    public void setPop(AnchorPane pop) {
        Pop = pop;
    }

    public void setP(Pane p) {
        mainP = p;
    }

    private void loadResults(String id, String name, String type_ek, String rating, String timeLow, String timeHigh) {
        vboxEkpompi.getChildren().clear();

        String getEkmompes = "select * from getResults(" + id + "," + name + "," + type_ek + "," + rating + ","
                + timeLow + "," + timeHigh + ");";

        Statement statement;
        try {
            statement = DBConnection.c.createStatement();
            ResultSet rs = statement.executeQuery(getEkmompes);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                HboxEnch hbox = new HboxEnch();

                for (int i = 1; i <= columnsNumber; i++) {

                    hbox.setSpacing(3);
                    HBox hboxinside = new HBox();

                    hboxinside.setPrefWidth(131);
                    hboxinside.setAlignment(CENTER);
                    hboxinside.getStyleClass().add("hboxStyle");
                    hboxinside.setPadding(new Insets(5, 5, 5, 5));
                    Text text = new Text();
                    text.setText(String.valueOf(rs.getString(i)));

                    if (Integer.parseInt(rs.getString(1)) == pressed) {
                        hboxinside.getStyleClass().add("hboxStylehover");

                    } else {
                        hboxinside.getStyleClass().clear();
                        hboxinside.getStyleClass().add("hboxStyle");
                    }

                    text.setWrappingWidth(80);
                    text.setTextAlignment(TextAlignment.CENTER);
                    hboxinside.getChildren().add(text);
                    hbox.getChildren().add(hboxinside);

                }
                hbox.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        MouseButton button = event.getButton();
                        if (button == MouseButton.PRIMARY) {
                            HBox hboxC = (HBox) hbox.getChildren().get(0);
                            Text text2 = (Text) hboxC.getChildren().get(0);
                            int id = Integer.parseInt(text2.getText());

                            HboxEnch hbox = (HboxEnch) event.getSource();
                            if (hbox.getState()) {

                                for (int i = 0; i < vboxEkpompi.getChildren().size(); i++) {
                                    HboxEnch hbox2 = (HboxEnch) vboxEkpompi.getChildren().get(i);
                                    hbox2.setState(false);
                                    for (int j = 0; j < hbox.getChildren().size(); j++) {
                                        HBox pane = (HBox) hbox2.getChildren().get(j);
                                        pane.getStyleClass().clear();
                                        pane.getStyleClass().add("hboxStyle");

                                    }

                                }

                                hbox.setState(false);
                                pressed = -1;

                            } else {
                                for (int i = 0; i < vboxEkpompi.getChildren().size(); i++) {
                                    HboxEnch hbox2 = (HboxEnch) vboxEkpompi.getChildren().get(i);
                                    hbox2.setState(false);
                                    for (int j = 0; j < hbox.getChildren().size(); j++) {
                                        HBox pane = (HBox) hbox2.getChildren().get(j);
                                        pane.getStyleClass().clear();
                                        pane.getStyleClass().add("hboxStyle");
                                    }

                                }

                                for (int i = 0; i < hbox.getChildren().size(); i++) {
                                    HBox pane = (HBox) hbox.getChildren().get(i);
                                    pane.getStyleClass().add("hboxStylehover");
                                }

                                hbox.setState(true);
                                HBox inside = (HBox) hbox.getChildren().get(0);
                                pressed = Integer.parseInt(((Text) inside.getChildren().get(0)).getText());
                            }
                            //
                        }

                    }

                });
                vboxEkpompi.getChildren().add(hbox);
            }
        } catch (SQLException ex) {
            App.controller.errorMessage();
        }

    }

    addProgram_Controller(Program_Controller pC) {
        this.pC = pC;
    }
}
