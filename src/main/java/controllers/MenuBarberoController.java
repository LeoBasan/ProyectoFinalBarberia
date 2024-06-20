package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class MenuBarberoController extends EscenasControlador{
        @FXML
        private Button button_cerrar_app;

        @FXML
        private Button button_eliminar_turno;

        @FXML
        private TableColumn<?, ?> columna_cliente_historial_turnos;

        @FXML
        private TableColumn<?, ?> columna_cliente_turnos_pendientes;

        @FXML
        private TableColumn<?, ?> columna_date_historial_turnos;

        @FXML
        private TableColumn<?, ?> columna_date_turnos_pendientes;

        @FXML
        private TableColumn<?, ?> columna_id_historial_turnos;

        @FXML
        private TableColumn<?, ?> columna_id_turnos_pendientes;

        @FXML
        private ImageView delete_logo;

        @FXML
        private ImageView delete_logo1;

        @FXML
        private AnchorPane historial_barbero;

        @FXML
        private Label historial_turnos_label;

        @FXML
        private TableView<?> historial_turnos_tabla;

        @FXML
        private Label turnos_pendientes_label;

        @FXML
        private TableView<?> turnos_pendientes_tabla;
}
