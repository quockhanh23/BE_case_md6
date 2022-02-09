package com.example.backendmd6.repository;

import com.example.backendmd6.model.ProfileUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<ProfileUser, Long> {
    ProfileUser findByUsername(String username);
    void delete(ProfileUser entity);
}