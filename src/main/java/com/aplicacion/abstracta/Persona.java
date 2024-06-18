package com.aplicacion.abstracta;

import com.aplicacion.turno.Turno;

import java.util.ArrayList;
import java.util.List;

public abstract class Persona {
        private  Integer id;
        private static int counter = 0;
        private String nombre;
        private String apellido;
        private String usuario;
        private String contrasena;
        private String email;
        private List<Turno> turnos;

    public Persona(String nombre, String apellido, String usuario, String contrasena,String email) {
        this.id = ++counter;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.email = email;
        this.turnos = new ArrayList<>();
    }

    public  Integer getId() {
        return id;
    }

    public  void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
