module gr.ihu.ermistv {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires de.jensd.fx.glyphs.fontawesome;
    requires org.controlsfx.controls;
    requires org.postgresql.jdbc;
    
    
    opens gr.ihu.ermistv to javafx.fxml;

    exports gr.ihu.ermistv;
    exports gr.ihu.ermistv.controller;

    opens gr.ihu.ermistv.controller to javafx.fxml;
}
