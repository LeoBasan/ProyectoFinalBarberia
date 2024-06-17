package com.aplicacion.barbero;

import com.aplicacion.abstracta.Persona;
import com.aplicacion.turno.Turno;

import java.util.List;

public class Barbero extends Persona {
    private Integer aniosExperiencia;
    public Barbero(String nombre, String apellido, String usuario, String contrasena,Integer aniosExperiencia) {
        super(nombre, apellido, usuario, contrasena);
        this.aniosExperiencia = aniosExperiencia;
    }

    public Integer getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(Integer aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }
}
