package com.aplicacion.turno;

import com.aplicacion.barbero.Barbero;
import com.aplicacion.cliente.Cliente;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class Turno {
    private String id;
    private String dniCliente;
    private String dniBarbero;
    private LocalDate date;
    private LocalTime time;

    public Turno(String dniCliente, String dniBarbero, LocalDate date, LocalTime time) {
        this.dniCliente = dniCliente;
        this.dniBarbero = dniBarbero;
        this.date = date;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public String getDniBarbero() {
        return dniBarbero;
    }

    public void setDniBarbero(String dniBarbero) {
        this.dniBarbero = dniBarbero;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
