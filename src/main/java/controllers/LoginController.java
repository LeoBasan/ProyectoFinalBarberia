package controllers;

import com.aplicacion.barbero.Barbero;
import com.aplicacion.barbero.BarberoRepository;
import com.aplicacion.cliente.Cliente;
import com.aplicacion.cliente.ClienteRepository;
import com.aplicacion.login.Login;
import com.aplicacion.login.LoginTemporal;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController{
    private ClienteRepository clienteRepository;
    private BarberoRepository barberoRepository;
    private LoginTemporal loginTemporal;

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
    private TextField emailField;

    @FXML
    private Label welcomeMessageLabel;

    public void initialize(){
        cargarRepos();
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

    public void showAlertErrorLogin(String title, String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void showAlertConfirmationLogin(String title,String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
        alert.setOnHidden(evt ->irAlMenu());
    }

    public void irAlMenu(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaz/menuPpal.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleLogin(){
        String email= emailField.getText();
        String password= passwordField.getText();

        Cliente cliente= clienteRepository.findByEmailAndPassword(email,password);
        Barbero barbero= barberoRepository.findByEmailAndPassword(email,password);

        if(cliente!=null){
            showAlertConfirmationLogin("Inicio de sesion Exitoso", "el cliente fue encontrado.");
            Login aux= new Login(cliente.getDni(),"n");
            loginTemporal.addLogin(aux);
        }
        else if (barbero!= null) {
            showAlertConfirmationLogin("Inicio de sesion Exitoso", "el barbero fue encontrado.");
            Login aux= new Login("n",barbero.getDni());
            loginTemporal.addLogin(aux);
        }else {
           showAlertErrorLogin("Error" , "El usuario no fue encontrado.");
        }
    }
    private void cargarRepos(){
        clienteRepository= ClienteRepository.getInstance();
        barberoRepository= BarberoRepository.getInstance();
        loginTemporal= LoginTemporal.getInstance();
    }
}
