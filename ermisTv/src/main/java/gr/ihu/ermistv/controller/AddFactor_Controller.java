package gr.ihu.ermistv.controller;

import gr.ihu.ermistv.App;
import gr.ihu.ermistv.DBConnection;
import gr.ihu.ermistv.HboxEnch;
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
import javafx.scene.layout.Pane;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

import static javafx.geometry.Pos.CENTER;

public class AddFactor_Controller implements Initializable {

    @FXML
    private AnchorPane addFactor;
    @FXML
    private TextField syntelestisName, syntelestisPhoneN, syntelestisID;
    @FXML
    private ChoiceBox<String> syntelestisRole;
    @FXML
    private TextField syntelestisSurname;
    @FXML
    private VBox vboxSyntelestes;

    private AnchorPane Pop;
    private Pane mainP;
    private int id;
    private SyntelestesEkpompon_Controller seC;
    private ArrayList<String> Role = new ArrayList<String>();
    private HashMap<Integer, Integer> add = new HashMap<Integer, Integer>();

    AddFactor_Controller(int id, SyntelestesEkpompon_Controller seC) {
        this.id = id;
        this.seC = seC;
    }

    @FXML
    private void reloadFactor(MouseEvent event) {
        filterSyntelestes();
        createRole();
        syntelestisID.clear();
        syntelestisName.clear();
        syntelestisSurname.clear();
        syntelestisPhoneN.clear();
    }

    @FXML
    private void addfactor() throws IOException {
        Iterator it = add.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Statement statement;
            try {
                statement = DBConnection.c.createStatement();
                String addSyntelestes = "select addSyntelestesek(" + this.id + "," + pair.getKey() + ");";
                ResultSet rs = statement.executeQuery(addSyntelestes);
                rs.next();
                switch (rs.getInt(1)) {
                    case 0:
                        App.controller.errorMessage(2, "added Successfully!");
                        statement.close();
                        rs.close();
                        break;
                    case 1:
                        App.controller.errorMessage(0, "Rate Exists Species!");
                        break;
                    case 2:
                        App.controller.errorMessage(0, "Rate Does Not Exist!");
                        break;
                    case 3:
                        App.controller.errorMessage(0, "The Show Does Not Exist!");
                        break;
                    case 4:
                        App.controller.errorMessage(1, "ERROR!");
                        break;
                }
            } catch (SQLException ex) {
                App.controller.errorMessage(1, "Error");
            }
            it.remove();
            add.remove(pair.getKey());
        }
        Pop.toBack();
        seC.filterSyntelestes();
        seC.createRole();
    }

    private void loadResultsSyntelestes(String id, String name, String surname, String role, String phoneNumber) {
        vboxSyntelestes.getChildren().clear();
        String getSyntelestes = "select * from getResultSykminus(" + this.id + "," + id + "," + name + "," + surname
                + "," + role + "," + phoneNumber + ");";
        Statement statement;
        try {
            statement = DBConnection.c.createStatement();
            ResultSet rs = statement.executeQuery(getSyntelestes);
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
                        }

                    }

                });
                vboxSyntelestes.getChildren().add(hbox);
            }
            statement.close();
            rs.close();
        } catch (SQLException ex) {
            App.controller.errorMessage();
        }

    }

    // Filter Syntelestes
    private void filterSyntelestes() {
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

    private void createRole() {

        Statement statement;
        try {
            statement = DBConnection.c.createStatement();
            String setRating = "select Distinct role from (select role,sid from getSyntelestes() EXCEPT select role,sidsy from getSyntelestesek(" + this.id +")) as a;";
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
            App.controller.errorMessage();
        }

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
}
