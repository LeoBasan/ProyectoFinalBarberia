package controllers;

import com.aplicacion.barbero.Barbero;
import com.aplicacion.barbero.BarberoRepository;
import com.aplicacion.cliente.Cliente;
import com.aplicacion.cliente.ClienteRepository;
import com.aplicacion.login.Login;
import com.aplicacion.login.LoginTemporal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuMainController{
    private Map<Button,AnchorPane> buttonToFormMap= new HashMap<>();
    private LoginTemporal loginTemporal;
    //Panel ppal
    @FXML
    private StackPane stackPaneMenu;
    @FXML
    private AnchorPane anchorPaneMenuMain;
    //Conjunto de paneles
    @FXML
    private BorderPane borderPaneMenu;
    //Panel de arriba
    @FXML
    private AnchorPane anchorPaneUp;
    @FXML
    private AnchorPane anchorPaneUp2;
    @FXML
    private ImageView companyLogo;
    @FXML
    private Label companyName;
    //Panel izquierdo
    @FXML
    private AnchorPane leftAnchorPane1;
    @FXML
    private AnchorPane getLeftAnchorPane2;
    @FXML
    private Button historialButton;
    @FXML
    private Button addTurnoButton;
    @FXML
    private Button modifyProfileButton;
    @FXML
    private Button logOutButton;
    //Panel de abajo
    @FXML
    private AnchorPane anchorPaneCenter;
    //Paneles de escenas que voy a manejar abajo
    @FXML
    private AnchorPane menuClientes;

    @FXML
    private AnchorPane menuModPerfil;

    @FXML
    private AnchorPane menuTurnos;


    //------------------------------------------------------------------
    private Button selectedButton;//Almecena el boton seleccionado
    @FXML
    public void initialize(){
        buttonToFormMap.put(historialButton,menuClientes);
        buttonToFormMap.put(addTurnoButton,menuTurnos);
        buttonToFormMap.put(modifyProfileButton,menuModPerfil);

        selectedButton= historialButton;
        switchForm(new ActionEvent(historialButton,null));
        cargarRepositorios();
        updateUIBasedOnUser();

        System.out.println("Menu main Cliente: "+loginTemporal.getListLogin().get(0));
        //listeners para cada boton

        historialButton.setOnAction(this::switchForm);
        addTurnoButton.setOnAction(this::switchForm);
        modifyProfileButton.setOnAction(this::switchForm);
        logOutButton.setOnAction(event -> handleLogout());
    }
    public void switchForm(ActionEvent actionEvent){
        //Obtengo el boton que disparo el evento
        Button clickedButton=(Button) actionEvent.getSource();
        selectedButton= clickedButton;
        //Oculto todos los formularios
        buttonToFormMap.values().forEach(form->form.setVisible(false));
        //Mostrar formulario asociado al boton
        AnchorPane selectedForm= buttonToFormMap.get(clickedButton);
        if(selectedForm!=null){
            selectedForm.setVisible(true);
        }
    }
    private void updateUIBasedOnUser(){//depende en que user se loguea habilito agregar turno o no
        if(!typeOfUser()){
            addTurnoButton.setDisable(true);
        }else {
            addTurnoButton.setDisable(false);
        }
    }
    private void cargarRepositorios(){
        loginTemporal= LoginTemporal.getInstance();
    }
    private boolean typeOfUser(){//si viene de cliente retorna true sino false
        List<Login> aux=loginTemporal.getListLogin();
        if(!aux.get(0).getDniCliente().equals("n")){
            return true;
        }
        return false;
    }
    private void handleLogout(){
        try{
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/interfaz/login.fxml"));
            Parent root= loader.load();
            Stage stage= (Stage) logOutButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}