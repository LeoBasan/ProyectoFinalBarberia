package controllers;
import com.aplicacion.barbero.Barbero;
import com.aplicacion.barbero.BarberoRepository;
import com.aplicacion.cliente.Cliente;
import com.aplicacion.cliente.ClienteRepository;
import com.aplicacion.login.LoginTemporal;
import com.aplicacion.turno.Turno;
import com.aplicacion.turno.TurnoRepository;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class MenuTurnosController{
    private LoginTemporal loginTemporal;
    private BarberoRepository barberoRepository;
    private ClienteRepository clienteRepository;
    private TurnoRepository turnoRepository;

    //Botones barberos
    @FXML
    private AnchorPane paneImageBarberos;
    @FXML
    private ImageView barberoAlexImage;
    @FXML
    private Button alexButton;
    @FXML
    private ImageView barberoLeoImage;
    @FXML
    private Button leoButton;
    @FXML
    private ImageView barberoLuchoImage;
    @FXML
    private Button luchoButton;
    //Turnos
    @FXML
    private SplitPane splitPaneTurnos;
    @FXML
    private AnchorPane tableroMenuTurno;
    @FXML
    private AnchorPane paneCalendarTime;
    //Date button
    @FXML
    private DatePicker datePickerTurno;
    //Time Buttons
    @FXML
    private Button nineHsButton;
    @FXML
    private Button tenHsButton;
    @FXML
    private Button elevenHsButton;
    @FXML
    private Button twelveHsButton;
    @FXML
    private Button thirteenHsButton;
    @FXML
    private Button fourteenHsButton;
    @FXML
    private Button fifteenHsButton;
    @FXML
    private Button sixteenHsButton;
    //Confirm Button
    @FXML
    private Button confirmButton;

    private String selectedDni="";
    private LocalDate selectedDate=null;
    private LocalTime selectedTime=null;
    @FXML
    public void initialize(){
        configButtons();
        precargarRepos();
        setInitialButtonsState();
        addDatePickerListener();

        // Seleccionar inicialmente el luchoButton
        luchoButton.setStyle("-fx-background-color: lightgreen;");
        selectedDni = (String) luchoButton.getUserData();
        updateAvaibleTimes(selectedDate, selectedDni);
    }
    private void configButtons(){
        alexButton.setUserData("44783654");//Pongo los dni
        leoButton.setUserData("44859726");
        luchoButton.setUserData("43387301");

        nineHsButton.setUserData(LocalTime.of(9,0));
        tenHsButton.setUserData(LocalTime.of(10,0));
        elevenHsButton.setUserData(LocalTime.of(11,0));
        twelveHsButton.setUserData(LocalTime.of(12,0));
        thirteenHsButton.setUserData(LocalTime.of(13,0));
        fourteenHsButton.setUserData(LocalTime.of(14,0));
        fifteenHsButton.setUserData(LocalTime.of(15,0));
        sixteenHsButton.setUserData(LocalTime.of(16,0));
    }
    private void setInitialButtonsState(){
        disableTimeButtons(true);
        confirmButton.setDisable(true);
    }
    private void disableTimeButtons(boolean cond){
        nineHsButton.setDisable(cond);
        tenHsButton.setDisable(cond);
        elevenHsButton.setDisable(cond);
        twelveHsButton.setDisable(cond);
        thirteenHsButton.setDisable(cond);
        fourteenHsButton.setDisable(cond);
        fifteenHsButton.setDisable(cond);
        sixteenHsButton.setDisable(cond);
    }
    private void resetBarberButtonStyles() {
        alexButton.setStyle("");
        leoButton.setStyle("");
        luchoButton.setStyle("");
    }

    private void resetTimeButtonStyles() {
        nineHsButton.setStyle("");
        tenHsButton.setStyle("");
        elevenHsButton.setStyle("");
        twelveHsButton.setStyle("");
        thirteenHsButton.setStyle("");
        fourteenHsButton.setStyle("");
        fifteenHsButton.setStyle("");
        sixteenHsButton.setStyle("");
    }
    private void addDatePickerListener(){
        datePickerTurno.valueProperty().addListener((observable,oldValue,newValue) -> {
            if(newValue!=null){
                if(newValue.isBefore(LocalDate.now())){
                    showAlert("Error","Fecha Invalida","Por favor, seleccione una fecha valida");
                    datePickerTurno.setValue(null);
                    disableTimeButtons(true);
                    selectedDate=null;
                }else {
                    selectedDate= newValue;
                    if(!selectedDni.isEmpty()){
                        updateAvaibleTimes(selectedDate,selectedDni);
                    }
                }
                checkConfirmButtonState();
            }
        });
    }
    private void updateAvaibleTimes(LocalDate date, String dni){//En base a una fecha habilito horarios disponibles
        List<Turno> auxTurnos= turnoRepository.devolverTurnosFecha(date,dni);
        disableTimeButtons(false);
        resetTimeButtonStyles();
        for(Turno turno: auxTurnos){
            LocalTime turnoTime= turno.getTime();
            if (turnoTime.equals(LocalTime.of(9, 0))) nineHsButton.setDisable(true);
            if (turnoTime.equals(LocalTime.of(10, 0))) tenHsButton.setDisable(true);
            if (turnoTime.equals(LocalTime.of(11, 0))) elevenHsButton.setDisable(true);
            if (turnoTime.equals(LocalTime.of(12, 0))) twelveHsButton.setDisable(true);
            if (turnoTime.equals(LocalTime.of(13, 0))) thirteenHsButton.setDisable(true);
            if (turnoTime.equals(LocalTime.of(14, 0))) fourteenHsButton.setDisable(true);
            if (turnoTime.equals(LocalTime.of(15, 0))) fifteenHsButton.setDisable(true);
            if (turnoTime.equals(LocalTime.of(16, 0))) sixteenHsButton.setDisable(true);
        }
    }
    @FXML
    private void handleBarberButtonAction(javafx.event.ActionEvent event){
        Button clickedButton=(Button) event.getSource();
        resetBarberButtonStyles();
        clickedButton.setStyle("-fx-background-color: lightgreen;");
        if(selectedDate!=null){
            selectedDni=(String) clickedButton.getUserData();
            updateAvaibleTimes(selectedDate,selectedDni);
        }
        checkConfirmButtonState();
    }
    @FXML
    private void handleTimeButtonAction(javafx.event.ActionEvent event){
        Button clickedButton=(Button) event.getSource();
        resetTimeButtonStyles();
        clickedButton.setStyle("-fx-background-color: lightgreen;");
        selectedTime=(LocalTime) clickedButton.getUserData();
        checkConfirmButtonState();
    }
    @FXML
    private void handleConfirmButton(javafx.event.ActionEvent event){
        selectedDate=datePickerTurno.getValue();
        if(selectedDni.isEmpty()||selectedTime==null||selectedDate==null){
            showAlert("Error","Seleccion incompleta","Por favor, seleccione todos los campos");
            return;
        }
        if(turnoRepository.existenceTurno(selectedDni,selectedDate,selectedTime)){
            showAlert("Turno existente","Error turno","El turno ya existe.");
        }else{
            Alert confirmAlert= new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirm");
            confirmAlert.setHeaderText("Esta seguro de confirmar?");
            confirmAlert.showAndWait().ifPresent(response->{
                if(response== ButtonType.OK){
                    saveAppointment(selectedDni,selectedDate,selectedTime);
                }
            });
        }
    }
    private void checkConfirmButtonState(){
        confirmButton.setDisable(selectedDni.isEmpty()||selectedTime==null||selectedDate==null);
    }
    private void saveAppointment(String dni, LocalDate date,LocalTime time){
        String getDniCliente=loginTemporal.getListLogin().get(0).getDniCliente();
        if(getDniCliente!="n"){
            turnoRepository.addTurno(new Turno(getDniCliente,dni,date,time));
        }
    }
    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    private void precargarRepos(){
        loginTemporal=LoginTemporal.getInstance();
        barberoRepository=BarberoRepository.getInstance();
        clienteRepository=ClienteRepository.getInstance();
        turnoRepository=TurnoRepository.getInstance();
    }
}
