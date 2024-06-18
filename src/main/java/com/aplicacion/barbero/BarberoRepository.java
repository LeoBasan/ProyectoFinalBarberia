package com.aplicacion.barbero;

import com.aplicacion.interfaces.Irepository;

import java.util.ArrayList;
import java.util.List;

public class BarberoRepository implements Irepository<Barbero> {
    private static BarberoRepository instance;
    private List<Barbero> listaBarberos;

    private BarberoRepository(){
        listaBarberos= new ArrayList<>();
        precargarBarberos();
    }
    public static BarberoRepository getInstance(){
        if(instance==null){
            instance= new BarberoRepository();
        }
        return instance;
    }
    private void precargarBarberos(){//Este metodo es para probar, pero cargamos una vez el json y listo se borra
        listaBarberos.add(new Barbero("Leopoldo","Basanta","Leito","123456",15));
        listaBarberos.add(new Barbero("Alex","Barrientos","Alekei","12345",10));
        listaBarberos.add(new Barbero("Luciano","Dominella","Lucho","1234",30));
    }
    public List<Barbero> getListaBarberos(){
        return listaBarberos;
    }
    public Barbero findByUserAndPassword(String user, String password){
        for(Barbero barbero: listaBarberos){
            if(barbero.getUsuario().equals(user)&& barbero.getContrasena().equals(password)){
                return barbero;
            }
        }
        return null;
    }
    @Override
    public void agregarTurnos(Barbero obj) {

    }

    @Override
    public void eliminarTurnos(Integer id) {

    }

    @Override
    public Barbero findId(Integer id) {
        return null;
    }

    @Override
    public void updateTurno(Barbero obj) {

    }
}
