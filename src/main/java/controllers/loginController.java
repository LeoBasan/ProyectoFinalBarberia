package controllers;

import com.aplicacion.barbero.Barbero;
import com.aplicacion.cliente.Cliente;
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

public class loginController extends BaseController{

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label companyLabel;

    @FXML
    private Button logginButton;

    @FXML
    private ImageView loginImage;

    @FXML
    private Button buttonBack;

    @FXML
    private ImageView imageBack;

    @FXML
    private PasswordField passwordField;

    @FXML
    private AnchorPane splitAnchorPane1;

    @FXML
    private AnchorPane splitAnchorPane2;

    @FXML
    private SplitPane splitaPane;

    @FXML
    private StackPane stackPane;

    @FXML
    private TextField userTextField;

    @FXML
    private Label welcomeMessageLabel;

    public void initialize(){
        logginButton.setOnAction(event -> handleLogin());//lo mando a loguearse, y falta despues que avance al siguiente menu
        buttonBack.setOnAction(event->goBack());//lo mando al menu de inicio
    }

    private void goBack(){//vuelvo al menu de inicio
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/interfaz/inicio.fxml"));//vuelvo al inicio interfaz
            Parent root= loader.load();
            Stage stage= (Stage) anchorPane.getScene().getWindow();
            stage.setScene(new Scene(root));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void handleLogin(){
        String usuario= userTextField.getText();
        String password= passwordField.getText();

        Cliente cliente= getClienteRepository().findByEmailAndPassword(usuario,password);
        Barbero barbero= getBarberoRepository().findByEmailAndPassword(usuario,password);
        //Aca deberia validar si se encontraron bien los user y en ese caso mostrar en la interfaz (Leo agregar)
        //Tendria que ver de agregar un cuadrito de mensaje de error o exito en la interfaz
        //Mas facil por ahora muestro en consola
        if(cliente!=null){
            System.out.println("Cliente encontrado: "+ cliente.getNombre());
        } else if (barbero!=null) {
            System.out.println("Barbero encontrado: "+ barbero.getNombre());
        }else {
            System.out.println("Usuario o contraseña incorrectos: ");
        }
    }
}
