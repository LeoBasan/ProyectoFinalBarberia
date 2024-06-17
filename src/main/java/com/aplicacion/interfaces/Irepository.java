package com.aplicacion.interfaces;

public interface Irepository<T> {
    void agregarTurnos(T obj);
    void eliminarTurnos(Integer id);

    T findId(Integer id);

    void updateTurno(T obj);


}
