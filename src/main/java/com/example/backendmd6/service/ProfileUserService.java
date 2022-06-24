package com.example.backendmd6.service;

import com.example.backendmd6.model.ProfileUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface ProfileUserService extends UserDetailsService {
    void save(ProfileUser user);

    Iterable<ProfileUser> findAll();

    ProfileUser findByEmail(String username);

    ProfileUser getCurrentUser();

    Optional<ProfileUser> findById(Long id);

    UserDetails loadUserById(Long id);

    boolean checkLogin(ProfileUser user);

    boolean isRegister(ProfileUser user);

    boolean isCorrectConfirmPassword(ProfileUser user);
    void delete(ProfileUser entity);
    Boolean create(ProfileUser profileUser);
}
