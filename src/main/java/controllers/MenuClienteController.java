package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class MenuClienteController extends EscenasControlador{
    @FXML
    private ImageView add_logo;

    @FXML
    private Button button_agregar_turno;

    @FXML
    private Button button_cerrar_app;

    @FXML
    private Button button_eliminar_turno;

    @FXML
    private TableColumn<?, ?> columna_barbero_historial_turnos;

    @FXML
    private TableColumn<?, ?> columna_barbero_turnos_pendientes;

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
    private AnchorPane historial_cliente;

    @FXML
    private Label historial_turnos_label;

    @FXML
    private TableView<?> historial_turnos_tabla;

    @FXML
    private Label turnos_pendientes_label;

    @FXML
    private TableView<?> turnos_pendientes_tabla;
}
