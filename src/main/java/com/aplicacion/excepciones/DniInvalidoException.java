package com.aplicacion.excepciones;

public class DniInvalidoException extends RuntimeException{
    public DniInvalidoException(String message) {
        super(message);
    }
}
