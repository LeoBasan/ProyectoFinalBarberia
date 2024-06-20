module prueba.pruebaloginccs {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    opens com.aplicacion.abstracta to com.google.gson;
    opens com.aplicacion.barbero to com.google.gson;
    opens com.aplicacion.cliente to com.google.gson;
    opens com.aplicacion.turno to com.google.gson;
    opens com.aplicacion.interfaces to com.google.gson;
    opens com.aplicacion.adapter to com.google.gson;
    opens controllers to javafx.fxml;
    exports main;
}