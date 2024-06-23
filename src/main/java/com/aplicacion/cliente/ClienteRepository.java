package com.aplicacion.cliente;

import com.aplicacion.adapter.LocalDateAdapter;
import com.aplicacion.adapter.LocalTimeAdapter;
import com.aplicacion.excepciones.DniInvalidoException;
import com.aplicacion.interfaces.IManejoDeTurnos;
import com.aplicacion.interfaces.Irepository;
import com.aplicacion.turno.Turno;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class ClienteRepository implements Irepository<Cliente>{
    private static final String FILE_PATH= "src/main/resources/json/cliente.json";
    private static ClienteRepository instance;//uso esta variable para ver si esta instanciada
    private Map<String,Cliente> mapaCliente;
    private Gson gson;

    private ClienteRepository() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
                .create();
        mapaCliente = new HashMap<>();
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
            throw new DniInvalidoException("DNI no esta disponible. ");
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
            throw  new DniInvalidoException("DNI invalido. ");
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
        this.mapaCliente.put(obj.getDni(),obj);
    }
    /*
    @Override
    public void addTurno(Turno turno) { ///verificar anteriormente si el barbero tiene disponible el turno.
        Cliente cliente = this.mapaCliente.get(turno.getDniCliente());
        cliente.getTurnos().add(turno);
        saveCliente();
    }

    @Override
    public void removeTurno(Turno turno) {///todas estas funciones las hacemos teniendo en cuenta que estamos
                                        // ubicados en la posicion de memoria del cliente por lo que accedemos rapidamente
           Cliente cliente = this.mapaCliente.get(turno.getDniCliente());
           cliente.getTurnos().remove(turno);
           saveCliente();
    }

    @Override
    public boolean existenceTurno(Turno turno) { ///devolvemos false si el turno no esta disponible, y true en caso de que lo este.
        Cliente clienteaux = this.mapaCliente.get(turno.getDniCliente());
        if(clienteaux == null){
            return true;
        }
        for(Turno turnoaux : clienteaux.getTurnos()){
            if(turnoaux.getDate().equals(turno.getDate()) && turnoaux.getTime().equals(turno.getTime())){
                return  false;
            }
        }
        return true;
    }*/
    public boolean existenceEmail( String email){ ///devuelve falso en el caso de que no este disponible ese gmail, True si se puede usar
        for ( Map.Entry<String,Cliente> entrada : this.mapaCliente.entrySet()){
            if(entrada.getValue().getEmail().equals(email)){
                return false;
            }
        }
        return true;
    }
    public boolean existenceDNI(Cliente cliente){ ///devuelve falso en el caso de que el dni ya se encuentre
        // registrado y true en el caso de que nadie lo tenga
        if(mapaCliente.containsKey(cliente.getDni())){
            return false;
        }
        return true;
    }


}
