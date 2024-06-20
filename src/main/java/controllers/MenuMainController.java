package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class MenuMainController extends BaseController {
    //Panel ppal
    @FXML
    private AnchorPane anchorPaneMenuMain;
    //Conjunto panel de arriba y abajo
    @FXML
    private BorderPane borderPaneMenu;
    //Panel de arriba
    @FXML
    private AnchorPane anchorPaneUp;

    @FXML
    private ImageView companyLogo;

    @FXML
    private Label companyName;

    @FXML
    private MenuButton profileButton;

    @FXML
    private ImageView profileButtonImage;

    @FXML
    private MenuItem profileOption1;

    @FXML
    private MenuItem profileOption2;

    @FXML
    private StackPane stackPaneMenu;
    //Panel de abajo
    @FXML
    private AnchorPane anchorPaneDown;
    //Paneles de escenas que voy a manejar abajo
    @FXML
    private AnchorPane menuClientes;

    @FXML
    private AnchorPane menuBarbero;

    @FXML
    private AnchorPane menuTurnos;

    @FXML
    public void initialize(){
        inicScenes();
        handleMainMenu();
    }

    private void handleMainMenu(){
        if(typeOfUser()){
            showMenuBarbero();
        } else {
            showMenuClientes();
        }
    }
    private boolean typeOfUser(){//si viene de cliente retorna true sino false
        return getCliente()==null;
    }
    private void inicScenes(){
        menuClientes.setVisible(false);
        menuBarbero.setVisible(false);
        menuTurnos.setVisible(false);
    }
    private void showMenuClientes(){
        menuBarbero.setVisible(false);
        menuTurnos.setVisible(false);
        menuClientes.setVisible(true);
    }
    private void showMenuBarbero(){
        menuTurnos.setVisible(false);
        menuClientes.setVisible(false);
        menuBarbero.setVisible(true);
    }
    private void showMenuTurnos(){
        menuBarbero.setVisible(false);
        menuClientes.setVisible(false);
        menuTurnos.setVisible(true);
    }
}