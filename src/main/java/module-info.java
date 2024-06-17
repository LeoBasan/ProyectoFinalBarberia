module prueba.pruebaloginccs {
    requires javafx.controls;
    requires javafx.fxml;


    opens controllers to javafx.fxml;
    exports main;
}