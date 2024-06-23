package controllers;

import com.aplicacion.barbero.Barbero;
import com.aplicacion.barbero.BarberoRepository;
import com.aplicacion.cliente.Cliente;
import com.aplicacion.cliente.ClienteRepository;
import com.aplicacion.login.Login;
import com.aplicacion.login.LoginTemporal;
import com.aplicacion.turno.TurnoRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ModPerfilController{
    private BarberoRepository barberoRepository;
    private ClienteRepository clienteRepository;
    private TurnoRepository turnoRepository;
    private LoginTemporal loginTemporal;
    private Cliente cliente;
    private Barbero barbero;

    @FXML
    private AnchorPane anchorPane;
    //Campos modificables
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField adressTextField;
    @FXML
    private TextField phoneTextField;
    //Boton confirmar
    @FXML
    private Button confirmButton;
    //Iconos
    @FXML
    private ImageView icon1;

    @FXML
    private ImageView icon2;

    @FXML
    private ImageView icon3;

    @FXML
    private ImageView icon4;

    @FXML
    public void initialize(){
        cargarRepositorios();
        //System.out.println("Login en ModPerfil: "+loginTemporal.getListLogin().get(0));
        //Login logAUX= loginTemporal.getListLogin().getFirst();
        //String[] aux=typeOfUser(logAUX);
        //System.out.println("String aux: "+aux.toString());
        promptText(loginTemporal.getListLogin().get(0));
    }
    private void cargarRepositorios(){
        barberoRepository= BarberoRepository.getInstance();
        clienteRepository= ClienteRepository.getInstance();
        turnoRepository= TurnoRepository.getInstance();
        loginTemporal= LoginTemporal.getInstance();
    }
    private Object buscarUsuario(String[] userInfo){
        String dni=userInfo[0];
        String userType=userInfo[1];
        if(userType.equals("1")){
            return clienteRepository.findId(dni);
        }
        return barberoRepository.findId(dni);
    }
    private String[] typeOfUser(Login login){
        if(!login.getDniCliente().equals("n")){
            return new String[]{login.getDniCliente(),"1"};
        }
        return new String[]{login.getDniBarbero(),"2"};
    }
    private void promptText(Login login){
        //System.out.println("Logina dentro del prompt: "+login);
        if(!login.getDniCliente().equals("n")){
            //System.out.println("Dentro del clienteDni");
            cliente= clienteRepository.findId(login.getDniCliente());
            //System.out.println("Despues de CLiente Repo"+cliente);
            nameTextField.setPromptText(cliente.getNombre());
            lastNameTextField.setPromptText(cliente.getApellido());
            adressTextField.setPromptText(cliente.getDireccion());
            phoneTextField.setPromptText(cliente.getNumTelefono());
        }else{
            barbero= barberoRepository.findId(login.getDniBarbero());
            nameTextField.setPromptText(barbero.getNombre());
            lastNameTextField.setPromptText(barbero.getApellido());
            adressTextField.setEditable(false);
            phoneTextField.setEditable(false);
        }
    }

}

