package com.aplicacion.barbero;

import com.aplicacion.abstracta.Persona;
import com.google.gson.annotations.SerializedName;

public class Barbero extends Persona {

    private Integer aniosExperiencia;
    public Barbero(String dni, String nombre, String apellido, String email, String contrasena, Integer aniosExperiencia) {
        super(dni, nombre, apellido, email, contrasena);
        this.aniosExperiencia = aniosExperiencia;
    }

    public Integer getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(Integer aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }
}
