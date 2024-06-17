package com.aplicacion.cliente;

import com.aplicacion.interfaces.Irepository;

import java.util.HashMap;
import java.util.Map;

public class ClienteRepository implements Irepository<Cliente> {
    private Map<String,Cliente> mapaCliente = new HashMap<>();

    public Map<String, Cliente> getMapaCliente() {
        return mapaCliente;
    }

    @Override
    public void agregarTurnos(Cliente cliente) {
        if(mapaCliente.containsKey(cliente.getUsuario())){
            //crear una excepcion personalizada para decir que el usuario ya esta registrado
        }else {
            mapaCliente.put(cliente.getUsuario(), cliente);
        }
    }

    @Override
    public void eliminarTurnos(Integer id) {

    }

    @Override
    public Cliente findId(Integer id) {
        return null;
    }

    @Override
    public void updateTurno(Cliente obj) {

    }
}
