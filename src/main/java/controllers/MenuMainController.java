package controllers;

import com.aplicacion.barbero.Barbero;
import com.aplicacion.cliente.Cliente;
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
import java.util.Map;

public class MenuMainController extends BaseController {
    private MenuBarberoController menuBarberoController;
    private MenuTurnosController menuTurnosController;
    private MenuClienteController menuClienteController;

    private Map<Button,AnchorPane> buttonToFormMap= new HashMap<>();
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
    public void setCliente(Cliente cliente){
        super.setCliente(cliente);
        updateUIBasedOnUser();
    }
    public void setBarbero(Barbero barbero){
        super.setBarbero(barbero);
        updateUIBasedOnUser();
    }
    private boolean typeOfUser(){//si viene de cliente retorna true sino false
        return getCliente()!=null;
    }
    private void handleLogout(){
        try{
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/interfaz/login.fxml"));
            Parent root= loader.load();
            BaseController controller= loader.getController();
            controller.setClienteRepository(getClienteRepository());
            controller.setBarberoRepository(getBarberoRepository());

            Stage stage= (Stage) logOutButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}