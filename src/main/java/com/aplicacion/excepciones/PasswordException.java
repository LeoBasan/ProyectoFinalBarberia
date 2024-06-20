package com.aplicacion.excepciones;

public class PasswordException extends RuntimeException {
    public PasswordException(String message) {
        super(message);
    }
}
