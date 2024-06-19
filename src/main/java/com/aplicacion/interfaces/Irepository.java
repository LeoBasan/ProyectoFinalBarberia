package com.aplicacion.interfaces;

public interface Irepository<T> {
    void add(T obj);
    void delete(String dni);
    T findId(String dni);
    void update(T obj);
}
