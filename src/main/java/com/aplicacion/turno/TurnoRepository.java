package com.aplicacion.turno;

import com.aplicacion.adapter.LocalDateAdapter;
import com.aplicacion.adapter.LocalTimeAdapter;
import com.aplicacion.cliente.Cliente;
import com.aplicacion.cliente.ClienteRepository;
import com.aplicacion.interfaces.IManejoDeTurnos;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TurnoRepository implements IManejoDeTurnos<Turno> {
    private static final String FILE_PATH="src/main/resources/json/turnos.json";
    private Gson gson= new Gson();
    private static TurnoRepository instance;
    private List<Turno> listaTurnos;
    private static int ultimoId=0;

    public static TurnoRepository getInstance(){
        if(instance==null){//si no fue instanciada
            instance= new TurnoRepository();//genero una nueva instancia
        }
        return instance;//sino retorno la que ya existe
    }
    private TurnoRepository() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
                .create();
        listaTurnos = new ArrayList<>();
        loadTurnos();
    }
    private void loadTurnos(){
        try(Reader reader = new FileReader(FILE_PATH)){
            Type listType = new TypeToken<ArrayList<Turno>>(){}.getType();
            listaTurnos = gson.fromJson(reader,listType);
            if (listaTurnos == null){
                listaTurnos= new ArrayList<>();
            }
            for (Turno turno : listaTurnos) {
                int turnoId = Integer.parseInt(turno.getId());
                if (turnoId > ultimoId) {
                    ultimoId = turnoId;
                }
            }
        }catch (FileNotFoundException e){
            listaTurnos = new ArrayList<>();
        } catch (IOException e ){
            e.printStackTrace();
        }
    }
    private void saveTurnos(){
        try(Writer writer = new FileWriter(FILE_PATH)){
            gson.toJson(listaTurnos,writer);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void addTurno(Turno turno) {//Id autoSeteable
        ultimoId++;
        turno.setId(String.valueOf(ultimoId));
        listaTurnos.add(turno);
        saveTurnos();
    }

    @Override
    public void removeTurno(Turno turno) {
        listaTurnos.remove(turno);
        saveTurnos();
    }

    @Override
    public boolean existenceTurno(String dni, LocalDate date, LocalTime time) {
        List<Turno> fechaTurnos=new ArrayList<>();
        for(Turno turno:listaTurnos){
            if(turno.getDate().equals(date)){
                if(turno.getDniBarbero().equals(dni)){
                    if(turno.getTime().equals(time)){
                        return true;
                    }
                }
            }
        }
        return false;
        //Verdadero si lo contiene
    }

    @Override
    public List<Turno> devolverTurnosDni(String dni, byte type) {//type 0 barbero, 1 cliente
        List<Turno> listaDNI= new ArrayList<>();
        for(Turno aux:listaTurnos){
            if(type==0){
                if(aux.getDniBarbero().equals(dni)){
                    listaDNI.add(aux);
                }
            }else{
                if(aux.getDniCliente().equals(dni)){
                    listaDNI.add(aux);
                }
            }
        }
        return listaDNI;
    }
    public List<Turno> devolverTurnosFecha(LocalDate date,String dni){
        List<Turno> fechaTurnos=new ArrayList<>();
        for(Turno turno:listaTurnos){
            if(turno.getDate().equals(date)){
                if(turno.getDniBarbero().equals(dni)){
                    fechaTurnos.add(turno);
                }
            }
        }
        return fechaTurnos;
    }
}
