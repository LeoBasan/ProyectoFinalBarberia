package controllers;

import javafx.scene.layout.AnchorPane;

public abstract class EscenasControlador {
    //Hago visible o no las escenas distintas del menu
    public void mostrarEscena(AnchorPane escena){
        escena.setVisible(true);
    }
    public void ocultarEscena(AnchorPane escena){
        escena.setVisible(false);
    }
}
