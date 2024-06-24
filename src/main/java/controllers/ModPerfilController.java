package controllers;

import com.aplicacion.barbero.Barbero;
import com.aplicacion.barbero.BarberoRepository;
import com.aplicacion.cliente.Cliente;
import com.aplicacion.cliente.ClienteRepository;
import com.aplicacion.excepciones.CampoVacioException;
import com.aplicacion.excepciones.LetrasyNumerosException;
import com.aplicacion.excepciones.SoloLetrasException;
import com.aplicacion.excepciones.SoloNumerosException;
import com.aplicacion.login.Login;
import com.aplicacion.login.LoginTemporal;
import com.aplicacion.turno.TurnoRepository;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Optional;

public class ModPerfilController{
    private BarberoRepository barberoRepository;
    private ClienteRepository clienteRepository;
    private TurnoRepository turnoRepository;
    private LoginTemporal loginTemporal;
    private RegisterController registerController= new RegisterController();

    @FXML
    private AnchorPane anchorPane;
    //Campos modificables
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField adressTextField;
    @FXML
    private TextField phoneTextField;
    //Boton confirmar
    @FXML
    private Button confirmButton;
    //Iconos
    @FXML
    private ImageView icon1;

    @FXML
    private ImageView icon2;

    @FXML
    private ImageView icon3;

    @FXML
    private ImageView icon4;
    @FXML
    private Label nombreError;
    @FXML
    private Label apellidoError;
    @FXML
    private Label direccionError;
    @FXML
    private Label telefonoError;
    @FXML
    private Label errorMensaje;

    @FXML
    public void initialize(){
        cargarRepositorios();
        agregarListenerValidacion();
        Object usuario= buscarUsuario(typeOfUser(loginTemporal.getListLogin().get(0)));
        promptText(loginTemporal.getListLogin().get(0));
        confirmButton.setOnAction(ev->mostrarConfirmacion(usuario));
    }
    private void cargarRepositorios(){
        barberoRepository= BarberoRepository.getInstance();
        clienteRepository= ClienteRepository.getInstance();
        turnoRepository= TurnoRepository.getInstance();
        loginTemporal= LoginTemporal.getInstance();
    }
    private Object buscarUsuario(String[] userInfo){
        String dni=userInfo[0];
        String userType=userInfo[1];
        if(userType.equals("1")){
            return clienteRepository.findId(dni);
        }
        return barberoRepository.findId(dni);
    }
    private String[] typeOfUser(Login login){
        if(!login.getDniCliente().equals("n")){
            return new String[]{login.getDniCliente(),"1"};
        }
        return new String[]{login.getDniBarbero(),"2"};
    }
    private void promptText(Login login){
        if(!login.getDniCliente().equals("n")){
            Cliente cliente= clienteRepository.findId(login.getDniCliente());
            nameTextField.setPromptText(cliente.getNombre());
            lastNameTextField.setPromptText(cliente.getApellido());
            adressTextField.setPromptText(cliente.getDireccion());
            phoneTextField.setPromptText(cliente.getNumTelefono());
        }else{
            Barbero barbero= barberoRepository.findId(login.getDniBarbero());
            nameTextField.setPromptText(barbero.getNombre());
            lastNameTextField.setPromptText(barbero.getApellido());
            icon3.setVisible(false);
            icon4.setVisible(false);
            adressTextField.setVisible(false);
            phoneTextField.setVisible(false);
            adressTextField.setEditable(false);
            phoneTextField.setEditable(false);
        }
    }
    private void agregarListenerValidacion() {
        agregarListener(nameTextField, nombreError, "Nombre");
        agregarListener(lastNameTextField, apellidoError, "Apellido");
        agregarListener(adressTextField, direccionError, "Direccion");
        agregarListener(phoneTextField, telefonoError, "Telefono");
    }

    private void agregarListener(TextField textField, Label errorLabel, String fieldName) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                registerController.validarCamposVacios(newValue, fieldName);
                if (fieldName.equalsIgnoreCase("Nombre") || fieldName.equalsIgnoreCase("Apellido")) {
                    registerController.validarSoloLetras(newValue, fieldName);
                } else if (fieldName.equalsIgnoreCase("Telefono")) {
                    registerController.validarSoloNumeros(newValue, fieldName);
                } else if (fieldName.equalsIgnoreCase("Direccion")) {
                    registerController.validarLetrasYNumeros(newValue, fieldName);
                }
                errorLabel.setText("");
                errorMensaje.setText("");
            } catch (CampoVacioException | SoloLetrasException | SoloNumerosException | LetrasyNumerosException e) {
                errorLabel.setText(e.getMessage());
            }
        });
    }
    private  void validarCampo(String fieldName,String value,Object usuario)  {
        if(usuario instanceof Cliente) {
            switch (fieldName) {
                case "Nombre":
                case "Apellido":
                    registerController.validarCamposVacios(value, fieldName);
                    registerController.validarSoloLetras(value, fieldName);
                    break;

                case "Direccion":
                    registerController.validarCamposVacios(value, fieldName);
                    registerController.validarLetrasYNumeros(value, fieldName);
                    break;
                case "Telefono":
                    registerController.validarCamposVacios(value, fieldName);
                    registerController.validarSoloNumeros(value, fieldName);
                    break;
                default:
            }
        }else if(usuario instanceof  Barbero){
            switch (fieldName) {
                case "Nombre":
                case "Apellido":
                    registerController.validarCamposVacios(value, fieldName);
                    registerController.validarSoloLetras(value, fieldName);
                    break;
                // Para Barbero, direccion y telefono no deberían validarse
                default:
            }
        }
    }
    private void validarCampos(Object usuario) {
        validarCampo("Nombre", nameTextField.getText(),usuario);
        validarCampo("Apellido", lastNameTextField.getText(),usuario);
        validarCampo("Direccion", adressTextField.getText(),usuario);
        validarCampo("Telefono", phoneTextField.getText(),usuario);
    }
    private void mostrarConfirmacion(Object usuario) {
        try {
            validarCampos(usuario); // Validar todos los campos antes de proceder

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar cambios");
            alert.setHeaderText("Confirmar actualización de perfil");
            alert.setContentText("¿Estás seguro de que deseas guardar los cambios?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                actualizarDatos(usuario);
            }
        } catch (Exception e) {
            showAlertErrorModPerfil("Error de Validación", e.getMessage());
        }
    }
    private void showAlertErrorModPerfil(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void actualizarDatos(Object usuario) {
        if (usuario instanceof Cliente) {
            Cliente cliente = (Cliente) usuario;
            if (!nameTextField.getText().isEmpty()) {
                cliente.setNombre(nameTextField.getText());
            }
            if (!lastNameTextField.getText().isEmpty()) {
                cliente.setApellido(lastNameTextField.getText());
            }
            if (!adressTextField.getText().isEmpty()) {
                cliente.setDireccion(adressTextField.getText());
            }
            if (!phoneTextField.getText().isEmpty()) {
                cliente.setNumTelefono(phoneTextField.getText());
            }
            clienteRepository.update(cliente);
        } else if (usuario instanceof Barbero) {
            Barbero barbero = (Barbero) usuario;
            if (!nameTextField.getText().isEmpty()) {
                barbero.setNombre(nameTextField.getText());
            }
            if (!lastNameTextField.getText().isEmpty()) {
                barbero.setApellido(lastNameTextField.getText());
            }
            barberoRepository.update(barbero);
        }
    }
}

