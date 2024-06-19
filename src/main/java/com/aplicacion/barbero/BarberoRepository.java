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
        listaBarberos.add(new Barbero("Leopoldo","Basanta","Leito", "leopoldobasanta@gmail.com", "123456",15));
        listaBarberos.add(new Barbero("Alex","Barrientos","Alekei", "aalexjuliaan@gmail.com", "12345",10));
        listaBarberos.add(new Barbero("Luciano","Dominella","Lucho", "luchodominella@gmail.com", "1234",30));
    }
    public List<Barbero> getListaBarberos(){
        return listaBarberos;
    }
    public Barbero findByEmailAndPassword(String email, String password){
        for(Barbero barbero: listaBarberos){
            if(barbero.getEmail().equals(email)&& barbero.getContrasena().equals(password)){
                return barbero;
            }
        }
        return null;
    }
    @Override
    public void add(Barbero obj) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Barbero findId(Integer id) {
        return null;
    }

    @Override
    public void update(Barbero obj) {

    }
}
