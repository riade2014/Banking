package com.sylviane.banking.services;

import java.util.List;

public interface AbstractService<T> {//interface generique
    Integer save(T dto);
    List<T> findAll();
    T findById(Integer id);
    void delete(Integer id);
}
