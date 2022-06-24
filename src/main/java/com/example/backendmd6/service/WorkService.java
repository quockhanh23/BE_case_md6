package com.example.backendmd6.service;

public interface WorkService {
    Iterable<com.example.backendmd6.model.Work> findAllByName(String name);

    Iterable<com.example.backendmd6.model.Work> findAll();
}
