package com.aplicacion.excepciones;

public class CampoVacioException extends RuntimeException{
    public CampoVacioException(String message) {
        super(message);
    }
}
