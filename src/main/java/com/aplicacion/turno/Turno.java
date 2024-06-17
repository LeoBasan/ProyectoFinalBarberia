package com.aplicacion.turno;

import com.aplicacion.barbero.Barbero;
import com.aplicacion.cliente.Cliente;

import java.time.LocalDateTime;

public class Turno {
    private Integer id;
    private Cliente cliente;
    private Barbero barbero;
    private LocalDateTime date;

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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
