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

public class registerController {

    private ClienteRepository clienteRepository;
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

    public void initialize(ClienteRepository clienteRepository ){
        this.clienteView = new ClienteView();
        this.clienteRepository = clienteRepository;
        backButton.setOnAction(event->goBack());
        registerButton.setOnAction(event->handleRegister());
    }

    private void goBack(){
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/interfaz/inicio.fxml"));
            Parent root= loader.load();
            Stage stage= (Stage) registerAnchorPane.getScene().getWindow();
            stage.setScene(new Scene(root));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRegister(){ ///acá probar si puedo usar los trycatch para las validaciones
        String nombre = nameField.getText();
        String apellido = lastNameField.getText();
        String usuario = usernameField.getText();
        String contrasena = passwordField.getText();
        String direccion = adressField.getText();
        String telefono = phoneNumberField.getText();

        Cliente newCliente = new Cliente(nombre,apellido,usuario,contrasena,direccion,telefono);
        clienteRepository.agregarTurnos(newCliente);
        Map<String,Cliente> mapaCliente =clienteRepository.getMapaCliente();
        clienteView.viewClientes(mapaCliente);


    }

}
