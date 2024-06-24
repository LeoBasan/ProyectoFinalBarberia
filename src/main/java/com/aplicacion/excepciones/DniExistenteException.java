package com.aplicacion.excepciones;

public class DniExistenteException extends RuntimeException{
    public DniExistenteException(String message) {
        super(message);
    }
}
