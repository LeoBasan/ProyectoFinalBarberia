package com.aplicacion.interfaces;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface IManejoDeTurnos <T> {
    void addTurno(T t);
    void removeTurno(T t);

    boolean existenceTurno (String dni, LocalDate date, LocalTime time);

    List<T> devolverTurnosDni(String dni,byte type);
}
