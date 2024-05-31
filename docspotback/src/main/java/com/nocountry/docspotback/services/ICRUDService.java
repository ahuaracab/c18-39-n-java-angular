package com.nocountry.docspotback.services;

import java.util.List;

public interface ICRUDService <T,ID>{
    T save(T t);
    T update(T t);
    List<T> findAll();
    T findById(ID id);
    void delete(ID id);
    List<T> getAllOrder(int page, int size, String sortDir, String sort);
    // List<T> findAllDeleted();
    // List<T> findAllNotDeleted();
}
