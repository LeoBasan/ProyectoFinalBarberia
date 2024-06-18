package com.aplicacion.cliente;

import com.aplicacion.interfaces.Irepository;

import java.util.HashMap;
import java.util.Map;

public class ClienteRepository implements Irepository<Cliente> {
    private static ClienteRepository instance;//uso esta variable para ver si esta instanciada
    private Map<String,Cliente> mapaCliente;

    private ClienteRepository() {
        this.mapaCliente = new HashMap<>();
    }
    public static ClienteRepository getInstance(){//Principio singleton, se instancia una sola vez el repo
        if(instance==null){//si no fue instanciada
            instance= new ClienteRepository();//genero una nueva instancia
        }
        return instance;//sino retorno la que ya existe
    }
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
    public Cliente findByUserAndPassword(String user, String password){//busco por usuario y contraseña
        for(Map.Entry<String, Cliente> entry: mapaCliente.entrySet()){
            if(entry.getValue().getUsuario().equals(user)&& entry.getValue().getContrasena().equals(password)){
                return entry.getValue();
            }
        }
        return null;
    }
}
