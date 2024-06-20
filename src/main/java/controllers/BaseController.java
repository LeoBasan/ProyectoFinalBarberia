package controllers;

import com.aplicacion.barbero.Barbero;
import com.aplicacion.barbero.BarberoRepository;
import com.aplicacion.cliente.Cliente;
import com.aplicacion.cliente.ClienteRepository;

public class BaseController {//manejo ambos repos en un control
    private ClienteRepository clienteRepository;
    private BarberoRepository barberoRepository;
    private Cliente cliente;
    private Barbero barbero;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Barbero getBarbero() {
        return barbero;
    }

    public void setBarbero(Barbero barbero) {
        this.barbero = barbero;
    }

    public void setClienteRepository(ClienteRepository clienteRepository){
        this.clienteRepository= clienteRepository;
    }
    public ClienteRepository getClienteRepository(){
        return clienteRepository;
    }
    public void setBarberoRepository(BarberoRepository barberoRepository){
        this.barberoRepository= barberoRepository;
    }
    public BarberoRepository getBarberoRepository(){
        return barberoRepository;
    }
}
