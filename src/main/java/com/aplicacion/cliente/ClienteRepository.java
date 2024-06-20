package com.aplicacion.cliente;

import com.aplicacion.interfaces.IManejoDeTurnos;
import com.aplicacion.interfaces.Irepository;
import com.aplicacion.turno.Turno;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class ClienteRepository implements Irepository<Cliente> , IManejoDeTurnos<Turno> {
    private static final String FILE_PATH= "src/main/resources/json/cliente.json";
    private static ClienteRepository instance;//uso esta variable para ver si esta instanciada
    private Map<String,Cliente> mapaCliente = new HashMap<>();
    private Gson gson= new Gson();

    private ClienteRepository() {
        loadCliente();
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

    private void loadCliente(){
        try(Reader reader = new FileReader(FILE_PATH)){
            Type mapType = new TypeToken<Map<String,Cliente>>(){}.getType();
            mapaCliente = gson.fromJson(reader,mapType);
            if (mapaCliente == null){
                mapaCliente= new HashMap<>();
            }
        }catch (FileNotFoundException e){
            mapaCliente = new HashMap<>();
        } catch (IOException e ){
            e.printStackTrace();
        }
    }
    private void saveCliente(){
        try(Writer writer = new FileWriter(FILE_PATH)){
            gson.toJson(mapaCliente,writer);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Cliente findByEmailAndPassword(String mail, String password){//busco por usuario y contraseña
        for(Map.Entry<String, Cliente> entry: mapaCliente.entrySet()){
            if(entry.getValue().getEmail().equals(mail)&& entry.getValue().getContrasena().equals(password)){
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public void add(Cliente obj) {
        if(mapaCliente.containsKey(obj.getDni())){
            ///hacer la excepcion
        }else{
            mapaCliente.put(obj.getDni(),obj);
        }
        saveCliente();
    }

    @Override
    public void delete(String dni) {
        if(mapaCliente.containsKey(dni)){
            mapaCliente.remove(dni);
        }else{
            ///lanzar excepcion de que no existe
        }
        saveCliente();
    }

    @Override
    public Cliente findId(String dni) {
        if(mapaCliente.containsKey(dni)){
            return mapaCliente.get(dni);
        }
        return null;
    }

    @Override
    public void update(Cliente obj) {
        ///tendriamos que ver que quiere cambiar el usuario. Agregar funciones mas tarde.
    }

    @Override
    public void addTurno(Turno turno) { ///verificar anteriormente si el barbero tiene disponible el turno.
        Cliente cliente = this.mapaCliente.get(turno.getCliente().getDni());
        cliente.getTurnos().add(turno);
        saveCliente();
    }

    @Override
    public void removeTurno(Turno turno) {///todas estas funciones las hacemos teniendo en cuenta que estamos
                                        // ubicados en la posicion de memoria del cliente por lo que accedemos rapidamente
           Cliente cliente = this.mapaCliente.get(turno.getCliente().getDni());
           cliente.getTurnos().remove(turno);
           saveCliente();
    }

    @Override
    public boolean existenceTurno(Turno turno) { ///devolvemos false si el turno no esta disponible, y true en caso de que lo este.
        Cliente clienteaux = turno.getCliente();
        if(clienteaux == null){
            return true;
        }
        for(Turno turnoaux : clienteaux.getTurnos()){
            if(turnoaux.getDate().equals(turno.getDate()) && turnoaux.getTime().equals(turno.getTime())){
                return  false;
            }
        }
        return true;
    }

    @Override
    public void viewlistTurno() {

    }
}
