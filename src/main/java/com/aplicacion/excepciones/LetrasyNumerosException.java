package com.aplicacion.excepciones;

public class LetrasyNumerosException extends RuntimeException{  //Excepcion para que el usuario tenga que ingresar letras y numeros en una direccion.

    public LetrasyNumerosException(String message) {
        super(message);
    }
}
