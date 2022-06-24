package com.example.backendmd6.service;


import com.example.backendmd6.model.Role;

public interface RoleService {
    Iterable<Role> findAll();

    void save(Role role);

    Role findByName(String name);
}
