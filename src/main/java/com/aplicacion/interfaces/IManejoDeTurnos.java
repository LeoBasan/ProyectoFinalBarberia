package com.aplicacion.interfaces;

public interface IManejoDeTurnos <T> {
    void addTurno(T t);
    void removeTurno(T t);

    boolean existenceTurno ( T t);

}
