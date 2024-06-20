package controllers;

import com.aplicacion.cliente.Cliente;
import com.aplicacion.cliente.ClienteView;
import com.aplicacion.excepciones.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private TextField emailField;
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
    private TextField dniField;

    @FXML
    private Label welcomeMessage;

    @FXML
    private Label nameErrorLabel;

    @FXML
    private Label lastNameErrorLabel;
    @FXML
    private Label dniErrorLabel;
    @FXML
    private Label passwordErrorLabel;
    @FXML
    private Label addressErrorLabel;
    @FXML
    private Label phoneNumberErrorLabel;
    @FXML
    private Label emailErrorLabel;
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

    public void validarCamposVacios(String nombre,String apellido,String dni,String contrasena,String direccion,String telefono){
        if(nombre.trim().isEmpty()) throw new CampoVacioException("error. el campo nombre no puede estar vacío.");
        if(apellido.trim().isEmpty()) throw new CampoVacioException("error. el campo apellido no puede estar vacío.");
        if(dni.trim().isEmpty()) throw new CampoVacioException("error. el campo dni no puede estar vacío.");
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
        if(!valor.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]+\\s\\d{1,5}$") ) {  //verifica que el usuario no pueda poner como primer caracter un espacio, despues que haya letras separado de un unico espacio y despues hasta 5 numeros
            throw new LetrasyNumerosException("El campo " + fieldname + " debe contener letras y números");
        }
    }

    public void validarEmail(String valor,String fieldName){ //me faltaria verificar si el email existe dentro del mapa Usuario para evitar que se repita
        if (!valor.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) { //verifica que la cadena de caracteres contiene un "@" y un "."
            throw new EmailException("El campo " + fieldName + " debe poseer un formato valido");
        }
    }

    public void validarDni(String valor, String fieldName){
        if(!valor.matches("^\\d{7,8}$")){ //verifica que sean solo o 7 o 8 digitos.
            throw new DniInvalidoException("El campo " + fieldName + " no es valido");
        }
    }

    public void validarContrasena(String valor,String fieldName){ //verifica que la contra sea de minimo 6 caracteres
        if(!valor.matches("^.{6,}$")){
            throw new PasswordException("El campo " + fieldName + " debe tener minimo 6 caracteres");
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
                        }else if(fieldname.equalsIgnoreCase("Direccion")){ // si es direccion verifica que tenga letra,espacio y numeros
                            validarLetrasYNumeros(newValue,fieldname);
                        } else if (fieldname.equalsIgnoreCase("Email")) {
                            validarEmail(newValue,fieldname);
                        }else if(fieldname.equalsIgnoreCase("Dni")){
                            validarDni(newValue,fieldname);
                        }else if(fieldname.equalsIgnoreCase("Contraseña")){
                            validarContrasena(newValue,fieldname);
                        }

                        errorLabel.setText("");
                        errorMessage.setText("");
                    } catch (CampoVacioException  | SoloLetrasException | SoloNumerosException | LetrasyNumerosException | EmailException | DniInvalidoException | PasswordException e) {  // se utiliza el | para capturar mas de una excepcion en el mismo catch
                        errorLabel.setTextFill(Color.RED);
                        if(e instanceof  CampoVacioException) {
                            errorLabel.setText("El campo " + fieldname + " no puede estar vacío.");
                        } else if( e instanceof SoloLetrasException ){
                            errorLabel.setText("El campo " + fieldname + " solo debe contener letras.");
                        }else if(e instanceof SoloNumerosException){
                            errorLabel.setText("el campo " + fieldname + " debe contener 10 dígitos.");
                        }else if ( e instanceof  LetrasyNumerosException){
                            errorLabel.setText("El campo " + fieldname + " debe ser una dirección valida.");
                        }else if(e instanceof EmailException ){
                            errorLabel.setText("El campo " + fieldname + " tiene que poseer un formato valida");
                        } else if (e instanceof DniInvalidoException) {
                            errorLabel.setText("El campo " + fieldname + " no es valido");
                        }else if ( e instanceof PasswordException ){
                            errorLabel.setText("El campo " + fieldname + " debe poseer minimo 6 caracteres");
                        }
                        errorMessage.setText("Error: " + e.getMessage()); // muestra el mensaje de error

                    }
            });
    }
    public void agregarListenerValidacion(){
        agregarListener(nameField,nameErrorLabel,"Nombre");
        agregarListener(lastNameField,lastNameErrorLabel,"Apellido");
        agregarListener(dniField,dniErrorLabel,"Dni");
        agregarListener(passwordField,passwordErrorLabel, "Contraseña");
        agregarListener(adressField,addressErrorLabel,"Direccion");
        agregarListener(phoneNumberField,phoneNumberErrorLabel,"Telefono");
        agregarListener(emailField,emailErrorLabel,"Email");
    }


    private  void showAlertError(String title, String message){   //En caso de que aprete el boton de registro y no se cumpla alguna de las validaciones va aparecer por pantalla un mensaje de error
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private  void irAlLogin(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaz/login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) registerAnchorPane.getScene().getWindow();
            stage.setScene(new Scene(root));
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    private  void showAlertConfirmation(String title,String message){  //En caso de que todos los campos sean validos, le mostrara al usuario una alerta de que se registro correctamente y una vez que haga click en aceptar, dicho evento lo mandara al login
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.setOnHidden(evt-> irAlLogin());
        alert.show();
    }



    @FXML
    private void handleRegister(){ ///aca hacer validaciones con listener en tiempo real
        try {
            String nombre = nameField.getText().trim();
            String apellido = lastNameField.getText().trim();
            String dni = dniField.getText().trim();
            String contrasena = passwordField.getText();
            String direccion = adressField.getText();
            String telefono = phoneNumberField.getText().trim();
            String email = emailField.getText().trim();
            validarCamposVacios(nombre,apellido,dni,contrasena,direccion,telefono);
            validarSoloLetras(nombre,"Nombre");
            validarSoloLetras(apellido,"Apellido");
            validarEmail(email,"Email");//Si pasa toda estas validaciones crea el cliente.
            validarContrasena(contrasena,"Contraseña");
            validarLetrasYNumeros(direccion,"Direccion");
            validarSoloNumeros(telefono,"Telefono");
            validarDni(dni,"Dni");


            Cliente newCliente = new Cliente(dni,nombre,apellido,email,contrasena,telefono,direccion);
            getClienteRepository().add(newCliente);
            Map<String,Cliente> mapaCliente =getClienteRepository().getMapaCliente();
            clienteView.viewClientes(mapaCliente);

            errorMessage.setText("");
            showAlertConfirmation("Registro exitoso","El cliente fue registrado correctamente");

        }catch ( CampoVacioException e){
            errorMessage.setText("Error: " + e.getMessage());
            System.err.println("Error: " + e.getMessage()); //System err se utiliza para imprimir mensajes de error
            showAlertError("Error", "Rellene todos los campos obligatorios.");
        }catch (SoloLetrasException | SoloNumerosException | LetrasyNumerosException | EmailException | DniInvalidoException | PasswordException e) {
            errorMessage.setText("Error: " + e.getMessage());
            System.err.println("Error: " + e.getMessage());
            showAlertError("Error de Validación", e.getMessage());
        }
    }

}
