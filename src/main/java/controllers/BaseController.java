package controllers;

import com.aplicacion.barbero.Barbero;
import com.aplicacion.cliente.Cliente;

public class BaseController {//maneja cliente o barbero logueado
    protected Cliente cliente;
    protected Barbero barbero;

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setBarbero(Barbero barbero) {
        this.barbero = barbero;
    }

}
