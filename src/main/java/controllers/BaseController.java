package controllers;

import com.aplicacion.barbero.BarberoRepository;
import com.aplicacion.cliente.ClienteRepository;

public class BaseController {//manejo ambos repos en un control
    private ClienteRepository clienteRepository;
    private BarberoRepository barberoRepository;

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
