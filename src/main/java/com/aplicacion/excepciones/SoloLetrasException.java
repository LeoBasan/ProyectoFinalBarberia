package com.aplicacion.excepciones;

public class SoloLetrasException extends RuntimeException {
    public SoloLetrasException(String message) {
        super(message);
    }
}
