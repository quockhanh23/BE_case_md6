package com.example.backendmd6.service;

import com.example.backendmd6.model.ProfileUser;

import java.util.Optional;

public interface GeneralService<T> {
    Iterable<T> findAll();
    Optional<T> findById(Long id);
    void save(T t);
    void remove(Long id);
}
