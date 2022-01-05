package gr.ihu.ermistv.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import gr.ihu.ermistv.App;
import gr.ihu.ermistv.DBConnection;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;



public class Program_Controller implements Initializable {

    @FXML
    private FontAwesomeIconView x;
    @FXML
    private ChoiceBox<String> choiceDayPro ,choiceTypePro;
    @FXML
    private TextField addNamePro,addTimePro;
    @FXML
    private Button Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday,btnConfPro;
    @FXML
    private VBox emptypane, time, program;
    @FXML
    private ScrollPane extension;
    @FXML
    private HBox daysV;
    @FXML
    private AnchorPane paneProgram;
    
    @FXML
    private AnchorPane addEkpompi;
    
    @FXML
    private AnchorPane popupEkpompi;
    
    
    
    @FXML
    private Label proErrLabel;



    private String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    private String[] colors = {"white", "grey", "Thursday", "Wednesday", "Friday", "Saturday", "Sunday"};
    private int daysize = 130;
    private int emptyS = 30;
    private double spaces = 5;
    private double spacesH = 10;
    private HashMap<String, HBox> scrollDay = new HashMap<String, HBox>();
    
    

    public static Bounds getVisibleBounds(Node aNode) {
        // If node not visible, return empty bounds
        if (!aNode.isVisible()) return new BoundingBox(0, 0, -1, -1);

        // If node has clip, return clip bounds in node coords
        if (aNode.getClip() != null) return aNode.getClip().getBoundsInParent();

        // If node has parent, get parent visible bounds in node coords
        Bounds bounds = aNode.getParent() != null ? getVisibleBounds(aNode.getParent()) : null;
        if (bounds != null && !bounds.isEmpty()) bounds = aNode.parentToLocal(bounds);
        return bounds;
    }

    @FXML
    private void add(MouseEvent event) {
        System.out.println("add");
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/addEkpompi.fxml"));
            addEkpompi_Controller controller = new addEkpompi_Controller(this);
            loader.setController(controller);
            controller.setPop(addEkpompi);
            popupEkpompi.getChildren().add(loader.load());

        } catch (IOException ex) {
            Logger.getLogger(Secondary_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        addEkpompi.toFront();
    }

    @FXML
    private void day(MouseEvent event) {
        Button button = (Button) event.getSource();
        String id = button.getId();
        System.out.println(id);
        Bounds bounds = extension.getViewportBounds();
        extension.setVvalue(scrollDay.get(id).getParent().getParent().getLayoutY() *
                (1 / (emptypane.getHeight() - bounds.getHeight())) - 0.003);
    }

    HBox test = new HBox();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        program.setLayoutX(daysize + spacesH);
        emptypane.setSpacing(spaces);
        time.setSpacing(spaces);
        AnchorPane.setTopAnchor(time, emptyS - ((double) emptyS / 2 - spaces / 2));
        int t;

        String panecolor = "";
        for (int i = 1; i <= 7; i++) {
            if (i % 2 == 0) {
                panecolor = "-fx-background-color:grey";
            } else {
                panecolor = "-fx-background-color:#f6f7f9";

            }
            t = 30;

            HBox dayBox = new HBox();
            Text dayText = new Text();
            dayText.setStyle("-fx-font-size:25px");
            dayText.setText(days[i - 1]);
            dayBox.setAlignment(Pos.CENTER);
            dayBox.getChildren().add(dayText);
            dayBox.getStyleClass().add("vboxDay");
            dayText.getStyleClass().add("daytext");
            dayBox.setPrefWidth(daysize);
            dayBox.setPrefHeight(30);
            scrollDay.put(days[i - 1], dayBox);
            dayBox.setId(days[i - 1]);


            for (int j = 1; j <= 47; j++) {

                HBox hbox = new HBox();
                hbox.setSpacing(spacesH);
                hbox.setMinHeight(Region.USE_PREF_SIZE);

                HBox day = new HBox();
                day.setPadding(new Insets(2, 0, 0, 10));
                day.setPrefWidth(daysize);
                day.setPrefHeight(emptyS);
                day.setMinHeight(Region.USE_PREF_SIZE);
                day.setMinWidth(Region.USE_PREF_SIZE);
                if (dayBox != null) {
                    day.getChildren().add(dayBox);
                    dayBox = null;
                }


                Pane pane = new Pane();
                pane.setPrefHeight(emptyS);
                pane.setMinHeight(Region.USE_PREF_SIZE);
                pane.setStyle(panecolor);
                HBox.setHgrow(pane, Priority.ALWAYS);

                HBox Htime = new HBox();
                Htime.setPrefHeight(emptyS);
                Htime.setMinHeight(Region.USE_PREF_SIZE);
                Htime.getStyleClass().add("vboxTime");
                Htime.setAlignment(Pos.CENTER);
                Text text = new Text();
                int hours = t / 60;
                int minutes = t % 60;
                text.setText(String.format("%02d", hours) + ":" + String.format("%02d", minutes));
                t += 30;
                Htime.getChildren().add(text);


                text.getStyleClass().add("textname");

                hbox.getChildren().addAll(day, pane);
                time.getChildren().add(Htime);
                emptypane.getChildren().add(hbox);
            }


            HBox hbox = new HBox();
            hbox.setSpacing(spacesH);
            hbox.setMinHeight(Region.USE_PREF_SIZE);

            HBox day = new HBox();
            day.setPrefWidth(daysize);
            day.setPrefHeight(emptyS);
            day.setMinHeight(Region.USE_PREF_SIZE);
            day.setMinWidth(Region.USE_PREF_SIZE);


            Pane pane = new Pane();
            pane.setPrefHeight(emptyS);
            pane.setMinHeight(Region.USE_PREF_SIZE);
            pane.setStyle(panecolor);
            HBox.setHgrow(pane, Priority.ALWAYS);

            HBox Htime = new HBox();
            Htime.setPrefHeight(emptyS);
            Htime.setMinHeight(Region.USE_PREF_SIZE);
            Htime.getStyleClass().add("vboxTime");
            Htime.setAlignment(Pos.CENTER);
            Text text = new Text();
            text.setText("00:00");
            Htime.getChildren().add(text);


            text.getStyleClass().add("textname");

            hbox.getChildren().addAll(day, pane);
            time.getChildren().add(Htime);
            emptypane.getChildren().add(hbox);
        }
        t = 30;
        for (int j = 1; j <= 12; j++) {

            HBox hbox = new HBox();
            hbox.setSpacing(spacesH);
            hbox.setMinHeight(Region.USE_PREF_SIZE);

            HBox day = new HBox();
            day.setPrefWidth(daysize);
            day.setPrefHeight(emptyS);
            day.setMinHeight(Region.USE_PREF_SIZE);
            day.setMinWidth(Region.USE_PREF_SIZE);


            Pane pane = new Pane();
            pane.setPrefHeight(emptyS);
            pane.setMinHeight(Region.USE_PREF_SIZE);
            pane.setStyle(panecolor);
            HBox.setHgrow(pane, Priority.ALWAYS);

            HBox Htime = new HBox();
            Htime.setPrefHeight(emptyS);
            Htime.setMinHeight(Region.USE_PREF_SIZE);
            Htime.getStyleClass().add("vboxTime");
            Htime.setAlignment(Pos.CENTER);
            Text text = new Text();
            int hours = t / 60;
            int minutes = t % 60;
            text.setText(String.format("%02d", hours) + ":" + String.format("%02d", minutes));
            t += 30;
            Htime.getChildren().add(text);


            text.getStyleClass().add("textname");

            hbox.getChildren().addAll(day, pane);
            time.getChildren().add(Htime);
            emptypane.getChildren().add(hbox);
        }


        double halfhours;

        String getSyntelestes = "select * from getPrograms()";
        Statement statement;
        try {
            statement = DBConnection.c.createStatement();
            ResultSet rs = statement.executeQuery(getSyntelestes);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            Timestamp pTime = Timestamp.valueOf("0001-01-01 00:00:00");
            while (rs.next()) {
//                System.out.println( - rs.getTime("endtime"));
                Timestamp start  = rs.getTimestamp("strtime");
                
                System.out.println("Prev: " + pTime);
                System.out.println("Next:" + start);
                LocalDateTime from = start.toLocalDateTime();
                LocalDateTime to = pTime.toLocalDateTime();
                Duration d = Duration.between(to, from);
//                System.out.println(d.toMinutes());
                
                System.out.println(d.toMinutes()/30);
                
                HBox hbox = new HBox();
                halfhours = ((double)d.toMinutes())/30;
                hbox.setPrefHeight(emptyS*halfhours+halfhours*spaces);
                program.getChildren().add(hbox);
////
                hbox = new HBox();
                hbox.getStyleClass().add("vboxProgram");
                halfhours = rs.getDouble("time") / 30;
                hbox.setPrefHeight(emptyS * halfhours + halfhours * spaces);
                program.getChildren().add(hbox);
                
                pTime = rs.getTimestamp("endtime");
//                System.out.println(pTime);
                System.out.println("--------------");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Secondary_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        String day;
        DayS days = new DayS();
        Listener listener = new Listener();
        days.addPropertyChangeListener(listener);
        extension.vvalueProperty().addListener((observable, oldValue, newValue) -> {
//            System.out.println(newValue);
            if ((double) newValue <= 0.109) {
                days.setText("Monday");
            } else if ((double) newValue > 0.109 && (double) newValue <= 0.259) {
                days.setText("Tuesday");
            } else if ((double) newValue > 0.259 && (double) newValue <= 0.404) {
                days.setText("Wednesday");
            } else if ((double) newValue > 0.404 && (double) newValue <= 0.554) {
                days.setText("Thursday");
            } else if ((double) newValue > 0.554 && (double) newValue <= 0.698) {
                days.setText("Friday");
            } else if ((double) newValue > 0.698 && (double) newValue <= 0.849) {
                days.setText("Saturday");
            } else if ((double) newValue > 0.849) {
                days.setText("Sunday");
            }


        });
        daysV.getChildren().get(1).getStyleClass().add("pressButton");

    }

    public class DayS {
        protected PropertyChangeSupport propertyChangeSupport;
        private String text;

        public DayS() {
            propertyChangeSupport = new PropertyChangeSupport(this);
        }

        public void setText(String text) {
            String oldText = this.text;
            this.text = text;
            propertyChangeSupport.firePropertyChange("MyTextProperty", oldText, text);
        }

        public void addPropertyChangeListener(PropertyChangeListener listener) {
            propertyChangeSupport.addPropertyChangeListener(listener);
        }
    }

    public class Listener implements PropertyChangeListener {
        @Override
        public void propertyChange(PropertyChangeEvent event) {
            if (event.getPropertyName().equals("MyTextProperty")) {

                Button button = (Button) daysV.lookup("#" + event.getNewValue().toString());
                for (int i = 1; i < daysV.getChildren().size(); i++) {
                    daysV.getChildren().get(i).getStyleClass().add("unpressbutton");
                }

                button.getStyleClass().clear();
                button.getStyleClass().add("pressButton");

                System.out.println(event.getNewValue().toString());
            }
        }
    }
    @FXML
    private void popupsHandleClicks(MouseEvent event) {
        if (event.getSource() == x) {
            paneProgram.toFront();
        }
    }
//
//
//    }

    @FXML
    private void addProgram() {
        int intValue;
        try {
            String day = choiceDayPro.getValue();
            String name = addNamePro.getText();
            String type = choiceTypePro.getValue();
            String time = String.valueOf(addTimePro.getText());
            if(day.isEmpty()) {
                proErrLabel.setText("ADD DAY!");
            }else if (name.isEmpty()) {
                proErrLabel.setText("ADD NAME!");
            } else if (type.isEmpty()) {
                proErrLabel.setText("ADD TYPE!");
            } else if (isNumeric.isNumeric(time) || time.isEmpty()) {
                proErrLabel.setText("ADD TIME! ");
            } else {
                String addbroadcast = "select getPrograms('" + day + "','" + name + "','"+ type + "','" + time + "');";
                Statement statement = DBConnection.c.createStatement();
                ResultSet rs = statement.executeQuery(addbroadcast);

                System.out.println("Success");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Secondary_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
