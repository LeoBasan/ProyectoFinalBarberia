package com.aplicacion.login;

public class Login {
    private String dniCliente;
    private String dniBarbero;

    public Login(String dniCliente, String dniBarbero) {
        this.dniCliente = dniCliente;
        this.dniBarbero = dniBarbero;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public String getDniBarbero() {
        return dniBarbero;
    }

    public void setDniBarbero(String dniBarbero) {
        this.dniBarbero = dniBarbero;
    }

    @Override
    public String toString() {
        return "Login{" +
                "dniCliente='" + dniCliente + '\'' +
                ", dniBarbero='" + dniBarbero + '\'' +
                '}';
    }
}
