module com.jpinson.pendujfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.jpinson.pendujfx to javafx.fxml;
    exports com.jpinson.pendujfx;
    exports com.jpinson.pendujfx.controllers;
    opens com.jpinson.pendujfx.controllers to javafx.fxml;
}