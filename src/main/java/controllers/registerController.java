package controllers;

import com.aplicacion.cliente.Cliente;
import com.aplicacion.cliente.ClienteRepository;
import com.aplicacion.cliente.ClienteView;
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
    public void initialize(){
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

    @FXML
    private void handleRegister(){ ///aca hacer validaciones con listener en tiempo real
        String nombre = nameField.getText();
        String apellido = lastNameField.getText();
        String usuario = usernameField.getText();
        String contrasena = passwordField.getText();
        String direccion = adressField.getText();
        String telefono = phoneNumberField.getText();

        Cliente newCliente = new Cliente(nombre,apellido,usuario,contrasena,direccion,telefono);
        getClienteRepository().agregarTurnos(newCliente);
        Map<String,Cliente> mapaCliente =getClienteRepository().getMapaCliente();
        clienteView.viewClientes(mapaCliente);

    }

}
