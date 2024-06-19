package com.aplicacion.interfaces;

public interface Irepository<T> {
    void add(T obj);
    void delete(Integer id);
    T findId(Integer id);
    void update(T obj);
}
