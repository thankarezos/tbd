module gr.ihu.ermistv {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens gr.ihu.ermistv to javafx.fxml;
    exports gr.ihu.ermistv;
}