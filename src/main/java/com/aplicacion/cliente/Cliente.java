package com.aplicacion.cliente;

import com.aplicacion.abstracta.Persona;
import com.aplicacion.turno.Turno;

import java.util.List;

public class Cliente extends Persona {

    private String numTelefono;
    private String direccion;

    public Cliente(String dni, String nombre, String apellido, String email, String contrasena, String numTelefono, String direccion) {
        super(dni, nombre, apellido, email, contrasena);
        this.numTelefono = numTelefono;
        this.direccion = direccion;
    }

    //Falta el equals y el hashCode
    public String getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "numTelefono='" + numTelefono + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
