package controllers;

import com.aplicacion.barbero.BarberoRepository;
import com.aplicacion.cliente.ClienteRepository;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Platform;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class InicioController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button closeButton;

    @FXML
    private ImageView closeImage;

    @FXML
    private ImageView companyLogo;

    @FXML
    private Button logginButton;

    @FXML
    private ImageView logginImage;

    @FXML
    private Button registerButton;

    @FXML
    private ImageView registerImage;

    @FXML
    private StackPane stackPane;

    @FXML
    public void initialize(){
        logginButton.setOnAction(event->openLoginMenu());//boton login entra a su menu
        registerButton.setOnAction(event->openRegisterMenu());//boton register entra a su menu
        closeButton.setOnAction(event->closeApplication());//boton close sale del programa
    }
    private void openLoginMenu(){
        loadScene("/interfaz/login.fxml");//menu login necesita el cliente y los barberos
    }
    private void openRegisterMenu(){
        loadScene("/interfaz/register.fxml");//menu registro solo usa el repo de cliente
    }
    private void closeApplication(){
        Platform.exit();//sale del programa
    }
    private void loadScene(String fxmlPath){
        try{
            FXMLLoader loader= new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root= loader.load();
            //avanzo a la escena elegida
            Stage stage=(Stage) stackPane.getScene().getWindow();
            stage.setScene(new Scene(root));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
