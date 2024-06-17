package controllers;

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

public class registerController {

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

    public void initialize(){
        backButton.setOnAction(event->goBack());
    }

    private void goBack(){
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/prueba/interfaz/inicio.fxml"));
            Parent root= loader.load();
            Stage stage= (Stage) registerAnchorPane.getScene().getWindow();
            stage.setScene(new Scene(root));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
