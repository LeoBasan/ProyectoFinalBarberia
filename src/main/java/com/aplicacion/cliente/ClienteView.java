package com.aplicacion.cliente;

import java.util.Map;
import java.util.Set;

public class ClienteView {

    public void viewClientes(Map<String,Cliente> ClienteMap){
        System.out.println("Clientes");
        for( Cliente cliente :ClienteMap.values()){
            System.out.println("----------------------------");
            System.out.println("DNI: " + cliente.getDni());
            System.out.println("Nombre y apellido del Cliente: " + cliente.getNombre() + " " + cliente.getApellido());
            System.out.println("Telefono: " + cliente.getNumTelefono());
            System.out.println("Direccion: " + cliente.getDireccion());
            System.out.println("Contraseña: " + cliente.getContrasena());
            System.out.println("Email: " + cliente.getEmail());

            }
        }
    }

