package controllers;

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

public class inicioController {

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
        logginButton.setOnAction(event->openLoginMenu());
        registerButton.setOnAction(event->openRegisterMenu());
        closeButton.setOnAction(event->closeApplication());
    }
    private void openLoginMenu(){
        loadScene("/interfaz/login.fxml");
    }
    private void openRegisterMenu(){
        loadScene("/interfaz/register.fxml");
    }
    private void closeApplication(){
        Platform.exit();
    }
    private void loadScene(String fxmlPath){
        try{
            FXMLLoader loader= new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root= loader.load();
            Stage stage=(Stage) stackPane.getScene().getWindow();
            stage.setScene(new Scene(root));
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
