package main;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root= FXMLLoader.load(getClass().getResource("/interfaz/inicio.fxml"));
        stage.setTitle("Inicio");
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
        /*File archivo= new File("src/main/resources/json/login.json");
        if(archivo.exists()){
            archivo.delete();
        }*/
    }
}