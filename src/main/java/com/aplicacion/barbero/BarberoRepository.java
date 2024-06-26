package com.aplicacion.barbero;

import com.aplicacion.cliente.Cliente;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.aplicacion.adapter.LocalDateAdapter;
import com.aplicacion.adapter.LocalTimeAdapter;

public class BarberoRepository implements Irepository<Barbero>{
    private static final String FILE_PATH = "src/main/resources/json/barbero.json";
    private  Gson gson;
    private static BarberoRepository instance;
    private Set<Barbero> setBarberos;

    private BarberoRepository(){
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
                .create();
        setBarberos = new HashSet<>();
        loadBarbero();
    }
    public static BarberoRepository getInstance(){
        if(instance==null){
            instance= new BarberoRepository();
        }
        return instance;
    }
   private void loadBarbero(){
        try(Reader reader = new FileReader(FILE_PATH)){
            Type setType = new TypeToken<Set<Barbero>>(){}.getType();
            setBarberos = gson.fromJson(reader,setType);
            if(setBarberos == null){
                setBarberos = new HashSet<>();
            }
        }catch (FileNotFoundException e ){
                setBarberos= new HashSet<>();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void saveBarbero(){
        try(Writer writer = new FileWriter(FILE_PATH)){
            gson.toJson(setBarberos,writer);
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    private void precargarBarberos(){//Este metodo es para probar, pero cargamos una vez el json y listo se borra
        if(setBarberos == null) {
            System.out.println("no funca");
        }
        setBarberos.add(new Barbero("44783789","Leopoldo","Basanta", "leopoldobasanta@gmail.com", "123456",15));
        setBarberos.add(new Barbero("44783654","Alex","Barrientos", "aalexjuliaan@gmail.com", "12345",10));
        setBarberos.add(new Barbero("44783098","Luciano","Dominella", "luchodominella@gmail.com", "1234",30));
        saveBarbero();
    }
    public Set<Barbero> getListaBarberos(){
        return setBarberos;
    }
    public Barbero findByEmailAndPassword(String email, String password){
        for(Barbero barbero: setBarberos){
            if(barbero.getEmail().equals(email)&& barbero.getContrasena().equals(password)){
                return barbero;
            }
        }
        return null;
    }

    @Override
    public void add(Barbero obj) {
        this.setBarberos.add(obj);
        /// en caso de que necesitemos cargar un barbero lo haremos manualmente.
       saveBarbero();
    }

    @Override
    public void delete(String dni) {
        Barbero aux = findId(dni);
        if (aux==null){
            throw new DniInvalidoException("Barbero inexistente. ");
        }else{
            this.setBarberos.remove(aux);
        }
        saveBarbero();
    }

    @Override
    public Barbero findId(String dni) {
        for(Barbero bar : this.setBarberos){
            if(bar.getDni().equals(dni)){
                return  bar;
            }
        }
        return null;
    }
    public boolean findDni(String dni){
        for(Barbero bar: this.setBarberos){
            if(bar.getDni().equals(dni)){
                return true;
            }
        }
        return false;
    }
    @Override
    public void update(Barbero obj) {
        this.setBarberos.remove(obj);
        this.setBarberos.add(obj);
        saveBarbero();
    }
}
