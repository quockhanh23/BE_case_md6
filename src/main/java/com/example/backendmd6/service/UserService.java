package com.example.backendmd6.service;

import com.example.backendmd6.model.ProfileUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    void save(ProfileUser profileUser);

    Iterable<ProfileUser> findAll();

    ProfileUser findByUsername(String username);

    ProfileUser getCurrentUser();

    Optional<ProfileUser> findById(Long id);

    UserDetails loadUserById(Long id);

    boolean checkLogin(ProfileUser profileUser);

    boolean isRegister(ProfileUser profileUser);

    boolean isCorrectConfirmPassword(ProfileUser profileUser);
    void delete(ProfileUser entity);
}
