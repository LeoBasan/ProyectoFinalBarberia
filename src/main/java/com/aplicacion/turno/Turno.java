package com.aplicacion.turno;

import com.aplicacion.barbero.Barbero;
import com.aplicacion.cliente.Cliente;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Turno {
    private Integer id;
    private Cliente cliente;
    private Barbero barbero;
    private LocalDate date;
    private LocalTime time;

    public Turno(Integer id, Cliente cliente, Barbero barbero, LocalDate  date, LocalTime time) {
        this.id = id;
        this.cliente = cliente;
        this.barbero = barbero;
        this.date = date;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Barbero getBarbero() {
        return barbero;
    }

    public void setBarbero(Barbero barbero) {
        this.barbero = barbero;
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
