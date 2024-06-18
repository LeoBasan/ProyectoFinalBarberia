package controllers;

import com.aplicacion.cliente.Cliente;
import com.aplicacion.cliente.ClienteView;
import com.aplicacion.excepciones.CampoVacioException;
import com.aplicacion.excepciones.LetrasyNumerosException;
import com.aplicacion.excepciones.SoloLetrasException;
import com.aplicacion.excepciones.SoloNumerosException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class registerController extends BaseController{


    private ClienteView clienteView;
    @FXML
    private TextField adressField;

    @FXML
    private Label companyName;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private AnchorPane registerAnchorPane;

    @FXML
    private Button registerButton;

    @FXML
    private Button backButton;

    @FXML
    private ImageView backImage;

    @FXML
    private ImageView registerImage;

    @FXML
    private SplitPane registerSplitPane;

    @FXML
    private StackPane registerStackPane;

    @FXML
    private AnchorPane splitAnchorPane1;

    @FXML
    private AnchorPane splitAnchorPane2;

    @FXML
    private TextField usernameField;

    @FXML
    private Label welcomeMessage;

    @FXML
    private Label nameErrorLabel;

    @FXML
    private Label lastNameErrorLabel;
    @FXML
    private Label usernameErrorLabel;
    @FXML
    private Label passwordErrorLabel;
    @FXML
    private Label addressErrorLabel;
    @FXML
    private Label phoneNumberErrorLabel;

    @FXML
    private Label errorMessage;

    @FXML
    public void initialize(){
        agregarListenerValidacion();
        this.clienteView = new ClienteView();//despues eliminar esto, solo es para ver en consola la carga correcta
        backButton.setOnAction(event->goBack());//volver atras con el boton back
        registerButton.setOnAction(event->handleRegister());//si se registra lo mando al repo
    }

    private void goBack(){//metodo para retroceder al menu inicial
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/interfaz/inicio.fxml"));//vuelvo al fxml del inicio
            Parent root= loader.load();
            Stage stage= (Stage) registerAnchorPane.getScene().getWindow();
            stage.setScene(new Scene(root));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //Sobrecarga de metodos
    public void validarCamposVacios(String  valor,String fieldName) {
        if (valor.trim().isEmpty()) {
            throw new CampoVacioException("El campo " + fieldName + " no puede estar vacío");
        }
    }

    public void validarCamposVacios(String nombre,String apellido,String usuario,String contrasena,String direccion,String telefono){
        if(nombre.trim().isEmpty()) throw new CampoVacioException("error. el campo nombre no puede estar vacío.");
        if(apellido.trim().isEmpty()) throw new CampoVacioException("error. el campo apellido no puede estar vacío.");
        if(usuario.trim().isEmpty()) throw new CampoVacioException("error. el campo usuario no puede estar vacío.");
        if(contrasena.trim().isEmpty()) throw new CampoVacioException("error. el campo contraseña no puede estar vacío.");
        if(direccion.trim().isEmpty()) throw new CampoVacioException("error. el campo dirección no puede estar vacío.");
        if(telefono.trim().isEmpty()) throw new CampoVacioException("error. el campo teléfono no puede estar vacío.");
    }

    public void validarSoloLetras(String valor,String fieldName){
        if(!valor.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s]+$")) {  //Es una expresion regular que asegura que la cadena solo contenga letras sin numeros ni caracteres especiales
            throw new SoloLetrasException("El campo " + fieldName + " solo tiene que tener letras.");
        }
    }

    public void validarSoloNumeros(String valor, String fieldName){
        if(!valor.matches("\\d{10}")) { //Es una expresion regular que asegura que la cadena sea de 10 digitos
            throw new SoloNumerosException("El campo " + fieldName + " debe contener 10 dígitos.");
        }
    }

    public void validarLetrasYNumeros(String valor, String fieldname){
        if(!valor.matches(".*[a-zA-Z]+\\s+\\d+.*") ) {  //verifica que despues de escribir letras haya obligatoriamente un espacio y despues numeros.
            throw new LetrasyNumerosException("El campo " + fieldname + "debe contener letras y numeros");
        }
    }

    public void agregarListener(TextField textField,Label errorLabel, String fieldname){ //sirve para validar el texto en tiempo real
          textField.textProperty().addListener((observable,oldValue,newValue) -> {;
                    try {
                      validarCamposVacios(newValue,fieldname); //validas que el campo no este vacio
                        if(fieldname.equalsIgnoreCase("Nombre")|| fieldname.equalsIgnoreCase("Apellido")){ //Si el fieldName es nombre o apellido verifica que sean solo letras
                            validarSoloLetras(newValue,fieldname);
                        }else if(fieldname.equalsIgnoreCase("Telefono")){ // si es telefono verifica que son 10 digitos
                            validarSoloNumeros(newValue,fieldname);
                        }else if(fieldname.equalsIgnoreCase("Direccion")){
                            validarLetrasYNumeros(newValue,fieldname);
                        }
                        errorLabel.setText("");
                        errorMessage.setText("");
                    } catch (CampoVacioException  | SoloLetrasException | SoloNumerosException | LetrasyNumerosException e) {  // se utiliza el | para capturar mas de una excepcion en el mismo catch
                        errorLabel.setTextFill(Color.RED);
                        if(e instanceof  CampoVacioException) {
                            errorLabel.setText("El campo " + fieldname + " no puede estar vacío.");
                        } else if( e instanceof SoloLetrasException ){
                            errorLabel.setText("El campo " + fieldname + " solo debe contener letras.");
                        }else if(e instanceof SoloNumerosException){
                            errorLabel.setText("el campo" + fieldname + " debe contener 10 dígitos.");
                        }else if ( e instanceof  LetrasyNumerosException){
                            errorLabel.setText("El campo" + fieldname + " debe ser una dirección valida.");
                        }
                        errorMessage.setText("Error: " + e.getMessage()); // muestra el mensaje de error
                        System.out.println(e.getMessage());
                    }
            });
    }
    public void agregarListenerValidacion(){
        agregarListener(nameField,nameErrorLabel,"Nombre");
        agregarListener(lastNameField,lastNameErrorLabel,"Apellido");
        agregarListener(usernameField,usernameErrorLabel,"Usuario");
        agregarListener(passwordField,passwordErrorLabel, "Contraseña");
        agregarListener(adressField,addressErrorLabel,"Direccion");
        agregarListener(phoneNumberField,phoneNumberErrorLabel,"Telefono");
    }



    @FXML
    private void handleRegister(){ ///aca hacer validaciones con listener en tiempo real
        try {
            String nombre = nameField.getText();
            String apellido = lastNameField.getText();
            String usuario = usernameField.getText();
            String contrasena = passwordField.getText();
            String direccion = adressField.getText();
            String telefono = phoneNumberField.getText();
            validarCamposVacios(nombre,apellido,usuario,contrasena,direccion,telefono);
            validarSoloLetras(nombre,"Nombre");
            validarSoloLetras(apellido,"Apellido");               //Si pasa toda estas validaciones crea el cliente.
            validarLetrasYNumeros(direccion,"Direccion");
            validarSoloNumeros(telefono,"Telefono");


            Cliente newCliente = new Cliente(nombre,apellido,usuario,contrasena,direccion,telefono);
            getClienteRepository().agregarTurnos(newCliente);
            Map<String,Cliente> mapaCliente =getClienteRepository().getMapaCliente();
            clienteView.viewClientes(mapaCliente);

            errorMessage.setText("");
            System.out.println("Cliente Registrado exitosamente");
        }catch ( CampoVacioException e){
            errorMessage.setText("Error: " + e.getMessage());
            System.err.println("Error:" + e.getMessage()); //System err se utiliza para imprimir mensajes de error
        }



    }

}
