package gr.ihu.ermistv.controller;

import gr.ihu.ermistv.DBConnection;
import gr.ihu.ermistv.HboxEnch;
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
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javafx.geometry.Pos.CENTER;

public class addEkpompi_Controller implements Initializable {

    @FXML
    private AnchorPane addEkpompi;

    @FXML
    private Label broErrLabel;

    @FXML
    private TextField ekpompiID;

    @FXML
    private TextField ekpompiName;

    @FXML
    private ChoiceBox<String> ekpompiRating;

    @FXML
    private ChoiceBox<String> ekpompiType;

    @FXML
    private Text sliderText;

    @FXML
    private RangeSlider sliderr;

    @FXML
    private VBox vboxEkpompi;

    @FXML
    private void addfactor(MouseEvent event) {

    }
    private int low = 10;
    private int high = 300;

        @FXML
        private void reloadFactor(MouseEvent event) {
        filterAddEkpompi();
        createRating();
        createType();
        ekpompiID.clear();
        ekpompiName.clear();
        ekpompiType.setValue(null);
        ekpompiRating.setValue(null);
    }

//        AddFactor_Controller(int id, SyntelestesEkpompon_Controller seC) {
//        this.id = id;
//        this.seC = seC;
//    }


        private AnchorPane Pop;
        private Pane mainP;
        private int id;
        private SyntelestesEkpompon_Controller seC;
        private ArrayList<String> RatingC = new ArrayList<String>();
        private ArrayList<String> TypeC = new ArrayList<String>();
        private HashMap<Integer, Integer> add = new HashMap<Integer, Integer>();

        @FXML
        private void addfactor() {
        Iterator it = add.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            String checkif = "select checkif(" + this.id + "," + pair.getKey() + ");";
            Statement statement;
            try {
                statement = DBConnection.c.createStatement();
                ResultSet rscheck = statement.executeQuery(checkif);
                rscheck.next();
                int returncode = rscheck.getInt(1);
                if (returncode == 0) {

                    String addSyntelestes = "select addSyntelestesek(" + this.id + "," + pair.getKey() + ");";
                    ResultSet rs = statement.executeQuery(addSyntelestes);
                } else if (returncode == 3) {
                    System.out.println("Factor does not exist");
                }


            } catch (SQLException ex) {
                Logger.getLogger(AddFactor_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }

            it.remove();
            add.remove(pair.getKey());

//            System.out.println(pair.getKey() + " = " + pair.getValue());
            // avoids a ConcurrentModificationException
        }
        Pop.toBack();
        seC.filterSyntelestes();
        seC.createRole();


    }


    private void loadResults(String id, String name,String type_ek, String rating, String timeLow, String timeHigh) {
        vboxEkpompi.getChildren().clear();
        String getEkmompes = "select * from getResult(" + id + "," + name + "," + type_ek + "," + rating + "," +  timeLow + "," + timeHigh + ");";

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
                    if (add.containsKey(Integer.parseInt(rs.getString(1)))) {

                        hboxinside.getStyleClass().add("hboxStylehover");
                        hbox.setState(true);
                    } else {
                        hboxinside.getStyleClass().clear();
                        hboxinside.getStyleClass().add("hboxStyle");
                        hbox.setState(false);
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
                            hbox.getChildren().get(0);
                            hbox.getChildren();
                            if (hbox.getState()) {
                                add.remove(id);
                                for (int i = 0; i < hbox.getChildren().size(); i++) {
                                    HBox pane = (HBox) hbox.getChildren().get(i);
                                    pane.getStyleClass().clear();
                                    pane.getStyleClass().add("hboxStyle");
                                    Text text = (Text) pane.getChildren().get(0);
                                }
                                hbox.setState(false);

                            } else {
                                add.put(id, id);
                                for (int i = 0; i < hbox.getChildren().size(); i++) {


                                    HBox pane = (HBox) hbox.getChildren().get(i);
                                    pane.getStyleClass().add("hboxStylehover");
                                }
                                hbox.setState(true);
                            }


//                            HBox hbox = (HBox) event.getSource();
//                            hbox.getChildren().get(0);
//                            hbox.getChildren();
//
//
                        }

                    }

                });
                vboxEkpompi.getChildren().add(hbox);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Secondary_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

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

                loadResults(id, name, type_ek,rating, String.valueOf(low), String.valueOf(high));
        }

        private void createRating() {//createRole

        Statement statement;
        try {
            statement = DBConnection.c.createStatement();
            String setRating = "select role from getSyntelestes() EXCEPT select Distinct role from getSyntelestesek(" + this.id + ");";
            ResultSet rs2 = statement.executeQuery(setRating);
            RatingC.clear();
            RatingC.add("");
            while (rs2.next()) {
                RatingC.add(rs2.getString("rating"));
            }
            ObservableList<String> rating = FXCollections.observableArrayList(RatingC);
            /*syntelestisRole*/ekpompiRating.setItems(rating);

        } catch (SQLException ex) {
            Logger.getLogger(Secondary_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private void createType() {//createRole

        Statement statement;
        try {
            statement = DBConnection.c.createStatement();
            String setRating = "select role from getSyntelestes() EXCEPT select Distinct role from getSyntelestesek(" + this.id + ");";
            ResultSet rs2 = statement.executeQuery(setRating);
            TypeC.clear();
            TypeC.add("");
            while (rs2.next()) {
                TypeC.add(rs2.getString("rating"));
            }
            ObservableList<String> type = FXCollections.observableArrayList(TypeC);
            /*syntelestisRole*/ekpompiType.setItems(type);

        } catch (SQLException ex) {
            Logger.getLogger(Secondary_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
//             //choice Rating
//            createRating();
//            //choise Type
//            createType_ek();
//            // Range Slider
            sliderr.setLowValue(low);
            sliderr.setHighValue(high);
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
            loadResults("null", "null", "null", "null",String.valueOf(low), String.valueOf(high));
        }


        public void setId(int id) {
        this.id = id;
    }

        public void setPop(AnchorPane pop) {
        Pop = pop;
    }

        public void setP(Pane p) {
        mainP = p;
    }


}