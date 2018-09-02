package com.company.model.dao;

import java.util.List;

public interface GenericDao<T> extends AutoCloseable{
    void create(T item);
    T findById(int id);
    List<T> findAll();
    void update(T item);
    void delete(T item);
    void close();
}
