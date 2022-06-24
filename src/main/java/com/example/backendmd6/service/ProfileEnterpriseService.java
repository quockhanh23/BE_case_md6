package com.example.backendmd6.service;

import com.example.backendmd6.model.ProfileEnterprise;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface ProfileEnterpriseService extends UserDetailsService {
    void save(ProfileEnterprise user);

    Iterable<ProfileEnterprise> findAll();

    ProfileEnterprise findByEmail(String email);

    ProfileEnterprise getCurrentUser();

    Optional<ProfileEnterprise> findById(Long id);

    UserDetails loadUserById(Long id);

    boolean checkLogin(ProfileEnterprise user);

    boolean isRegister(ProfileEnterprise user);

    boolean isCorrectConfirmPassword(ProfileEnterprise user);

    void delete(ProfileEnterprise entity);

    Iterable<ProfileEnterprise> findByNameCompanyContaining(String name);

    Iterable<ProfileEnterprise> findAllByStatusLikeOne();

    Boolean create(ProfileEnterprise profileEnterprise);
}
