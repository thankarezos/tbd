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

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;

import static javafx.geometry.Pos.CENTER;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class SyntelestesEkpompon_Controller implements Initializable {
    @FXML
    private FontAwesomeIconView backIcon, x;
    @FXML
    private Button btnAddFacPane;

    @FXML
    private AnchorPane paneSyntelestes;

    @FXML
    private AnchorPane addFactor;

    @FXML
    private AnchorPane popupFactor;

    @FXML
    private TextField syntelestisID;

    @FXML
    private TextField syntelestisName;

    @FXML
    private TextField syntelestisPhoneN;

    @FXML
    private ChoiceBox<String> syntelestisRole;

    @FXML
    private TextField syntelestisSurname;

    @FXML
    private VBox vboxSyntelestes;

    @FXML
    private Label facErrLabel;

    @FXML
    private TextField addFacSurname, addFacName, addFacPhoneN, addFacRole;

    @FXML
    private void reloadFactor(MouseEvent event) {
        filterSyntelestes();
        createRole();
        syntelestisID.clear();
        syntelestisName.clear();
        syntelestisSurname.clear();
        syntelestisPhoneN.clear();
    }

    SyntelestesEkpompon_Controller(int id) {
        this.id = id;
    }

    private AnchorPane mainAp;
    private int id;
    private ArrayList<String> Role = new ArrayList<String>();

    // Load Results Syntelestes
    private void loadResultsSyntelestes(String id, String name, String surname, String role, String phoneNumber) {
        vboxSyntelestes.getChildren().clear();
        String getSyntelestes = "select * from getResultSyntelestesek(" + this.id + "," + id + "," + name + ","
                + surname + "," + role
                + "," + phoneNumber + ");";
        int setid = this.id;
        Statement statement;
        try {
            statement = DBConnection.c.createStatement();
            ResultSet rs = statement.executeQuery(getSyntelestes);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                HBox hbox = new HBox();

                for (int i = 3; i <= columnsNumber; i++) {

                    hbox.setSpacing(3);
                    HBox hboxinside = new HBox();
                    hboxinside.getStyleClass().add("hboxStyle");
                    hboxinside.setPrefWidth(160);
                    hboxinside.setAlignment(CENTER);
                    hboxinside.setPadding(new Insets(5, 5, 5, 5));
                    Text text = new Text();
                    text.setText(String.valueOf(rs.getString(i)));
                    text.setWrappingWidth(80);
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
                                String deleteek = "select * from deleteSyntelestesek(" + setid + "," + text2.getText()
                                        + ");";
                                try {
                                    statement2.executeQuery(deleteek);
                                    filterSyntelestes();
                                    createRole();

                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                    ex.getCause();
                                }
                            });
                        }

                    }

                });
                //
                vboxSyntelestes.getChildren().add(hbox);

            }
            statement.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            ex.getCause();
        }

    }

    // Filter Syntelestes
    public void filterSyntelestes() {
        String id = "'" + syntelestisID.getText() + "'";
        if (isNumeric.isNumeric(syntelestisID.getText()) || syntelestisID.getText().isEmpty()) {
            id = "null";
        }

        String name = "'" + syntelestisName.getText() + "'";
        if (syntelestisName.getText().isEmpty()) {
            name = "null";
        }

        String surname = "'" + syntelestisSurname.getText() + "'";
        if (syntelestisSurname.getText().isEmpty()) {
            surname = "null";
        }

        String role = "'" + String.valueOf(syntelestisRole.getValue()) + "'";

        if (String.valueOf(syntelestisRole.getValue()).isEmpty() || syntelestisRole.getValue() == null) {
            role = "null";
        }

        String phoneN = "'" + syntelestisPhoneN.getText() + "'";
        if (syntelestisPhoneN.getText().isEmpty()) {
            phoneN = "null";
        }

        loadResultsSyntelestes(id, name, surname, role, phoneN);
    }

    // add Factor Syntelestes

    @FXML
    private void addFactor(MouseEvent event) {
        addFactor.toFront();
        createRole();
    }

    public void createRole() {

        Statement statement;
        try {
            statement = DBConnection.c.createStatement();
            String setRating = "select Distinct role from getSyntelestesek(" + this.id + ");";
            ResultSet rs2 = statement.executeQuery(setRating);
            Role.clear();
            Role.add("");
            while (rs2.next()) {
                Role.add(rs2.getString("role"));
            }
            ObservableList<String> rate = FXCollections.observableArrayList(Role);
            syntelestisRole.setItems(rate);
            statement.close();
            rs2.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            ex.getCause();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(id);
        createRole();

        syntelestisID.textProperty().addListener((observable, oldValue, newValue) -> {
            filterSyntelestes();
        });
        syntelestisName.textProperty().addListener((observable, oldValue, newValue) -> {
            filterSyntelestes();
        });
        syntelestisSurname.textProperty().addListener((observable, oldValue, newValue) -> {
            filterSyntelestes();
        });
        syntelestisRole.valueProperty().addListener((observable, oldValue, newValue) -> {
            filterSyntelestes();
        });
        syntelestisPhoneN.textProperty().addListener((observable, oldValue, newValue) -> {
            filterSyntelestes();
        });

        loadResultsSyntelestes("null", "null", "null", "null", "null");

    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        System.out.println("test");
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/ekpompi.fxml"));
            root = loader.load();

            Scene scene = new ScenesSet(root, App.stage, 876, 517);
            Ekpompi_Controller controller = loader.getController();
            controller.setAp(mainAp);
            mainAp.getChildren().add(root);
        } catch (IOException ex) {
            ex.printStackTrace();
            ex.getCause();
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAp(AnchorPane ap) {
        mainAp = ap;
    }

    @FXML
    private void popupsHandleClicks(MouseEvent event) throws IOException {
        if (event.getSource() == x) {
            paneSyntelestes.toFront();
        }
    }

    @FXML
    public void onClkick(MouseEvent event) throws IOException {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/addFactor.fxml"));
            AddFactor_Controller controller = new AddFactor_Controller(id, this);
            loader.setController(controller);
            controller.setPop(addFactor);
            popupFactor.getChildren().add(loader.load());

        } catch (IOException ex) {
            ex.printStackTrace();
            ex.getCause();
        }

        addFactor.toFront();

    }

    public void set() {

    }
}
