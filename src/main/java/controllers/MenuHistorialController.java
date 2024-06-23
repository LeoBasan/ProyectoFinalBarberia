package controllers;

import com.aplicacion.barbero.Barbero;
import com.aplicacion.barbero.BarberoRepository;
import com.aplicacion.cliente.Cliente;
import com.aplicacion.cliente.ClienteRepository;
import com.aplicacion.login.Login;
import com.aplicacion.login.LoginTemporal;
import com.aplicacion.turno.Turno;
import com.aplicacion.turno.TurnoRepository;
import javafx.beans.NamedArg;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

public class MenuHistorialController{
    private ClienteRepository clienteRepository;
    private BarberoRepository barberoRepository;
    private TurnoRepository turnoRepository;
    private LoginTemporal loginTemporal;
    //Ppal
    @FXML
    private AnchorPane historial_cliente;
    //Tabla historial turnos
    @FXML
    private Label historial_turnos_label;
    @FXML
    private TableView<Turno> historial_turnos_tabla;
    @FXML
    private TableColumn<Turno, String> columna_id_historial_turnos;
    @FXML
    private TableColumn<Turno, String> columna_date_historial_turnos;
    @FXML
    private TableColumn<Turno, String> columna_barbero_historial_turnos;
    @FXML
    private TableColumn<Turno, String> columna_cliente_historial_turnos;

    //Boton eliminar
    @FXML
    private Button button_eliminar_turno;
    @FXML
    private ImageView delete_logo;

    private ObservableList<Turno> turnoList =
            FXCollections.observableArrayList();

    public void initialize(){
        cargarRepositorios();
        configurarColumnasHistorialCliente();
        System.out.println("login en historial: "+loginTemporal.getListLogin().get(0));
        cargarTurnosEntabla(dniLogueado(loginTemporal.getListLogin().get(0)));
        button_eliminar_turno.setOnAction(ev->eliminarTurnoSeleccionado());
        //System.out.println("Cliente en historial: "+cliente);
    }
    public void posInitialize(){

//        updateUIBasedOnUser();
        //System.out.println("Cliente en historial: "+cliente);
//        configurarColumnasHistorialCliente();
//        System.out.println("Se rompio");
//        cargarTurnosEntabla(cliente.getDni());
        //historial_turnos_tabla= new TableView<>();
    }
    private String dniLogueado(Login login){
        if(!login.getDniCliente().equals("n")){
            return login.getDniCliente();
        }
        return login.getDniBarbero();
    }

    private void configurarColumnasHistorialCliente(){
        columna_id_historial_turnos.setCellValueFactory(new PropertyValueFactory<>("id"));
        columna_barbero_historial_turnos.setCellValueFactory(new PropertyValueFactory<>("dniBarbero"));
        columna_cliente_historial_turnos.setCellValueFactory(new PropertyValueFactory<>("dniCliente"));
        columna_date_historial_turnos.setCellValueFactory(cellDat->{
            LocalDate fecha= cellDat.getValue().getDate();
            LocalTime hora= cellDat.getValue().getTime();
            String fechaHora= fecha.toString()+" "+hora.toString();
            return new SimpleStringProperty(fechaHora);
        });
        historial_turnos_tabla.setEditable(false);
        historial_turnos_tabla.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        historial_turnos_tabla.refresh();
    }
    private void cargarTurnosEntabla(String dni){
        cargarRepositorios();
        List<Turno> aux=turnoRepository.devolverTurnosDni(dni,(byte) 1);
        turnoList=FXCollections.observableArrayList(aux);
        historial_turnos_tabla.setItems(turnoList);
    }
    private void eliminarTurnoSeleccionado(){
        Turno turnoSeleccionado=historial_turnos_tabla.getSelectionModel().getSelectedItem();
        if(turnoSeleccionado!=null){
            //Elimino de lista observable
            turnoList.remove(turnoSeleccionado);
            //Elimino del repo
            turnoRepository.removeTurno(turnoSeleccionado);
            //Refresco la tabla
            historial_turnos_tabla.refresh();
        }
    }
    private void cargarRepositorios(){
        clienteRepository= ClienteRepository.getInstance();
        barberoRepository= BarberoRepository.getInstance();
        turnoRepository= TurnoRepository.getInstance();
        loginTemporal= LoginTemporal.getInstance();
    }
}
