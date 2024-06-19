package com.aplicacion.cliente;

import com.aplicacion.interfaces.Irepository;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class ClienteRepository implements Irepository<Cliente> {
    private static final String FILE_PATH= "src/main/resources/json/cliente.json";
    private static ClienteRepository instance;//uso esta variable para ver si esta instanciada
    private Map<String,Cliente> mapaCliente;
    private Gson gson= new Gson();

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
    public void add(Cliente cliente) {
        if(mapaCliente.containsKey(cliente.getDni())){
            //crear una excepcion personalizada para decir que el usuario ya esta registrado
        }else {
            mapaCliente.put(cliente.getDni(), cliente);
        }
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Cliente findId(Integer id) {
        return null;
    }

    @Override
    public void update(Cliente obj) {

    }
    public Cliente findByEmailAndPassword(String mail, String password){//busco por usuario y contraseña
        for(Map.Entry<String, Cliente> entry: mapaCliente.entrySet()){
            if(entry.getValue().getEmail().equals(mail)&& entry.getValue().getContrasena().equals(password)){
                return entry.getValue();
            }
        }
        return null;
    }
}
