module com.jpinson.pendujfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires dotenv.java;
    requires java.net.http;
    requires java.sql;

    opens com.jpinson.pendujfx to javafx.fxml;
    exports com.jpinson.pendujfx;
}