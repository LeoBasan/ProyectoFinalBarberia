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

public class loginController {

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
        buttonBack.setOnAction(event->goBack());
    }

    private void goBack(){
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/interfaz/inicio.fxml"));
            Parent root= loader.load();
            Stage stage= (Stage) anchorPane.getScene().getWindow();
            stage.setScene(new Scene(root));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
