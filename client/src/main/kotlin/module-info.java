module com.example.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    requires com.dlsc.formsfx;

    opens com.example.client to javafx.fxml;
    exports com.example.client;
}