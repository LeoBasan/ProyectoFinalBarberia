module prueba.pruebaloginccs {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens controllers to javafx.fxml;
    exports main;
}