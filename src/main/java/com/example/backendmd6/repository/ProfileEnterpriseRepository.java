package com.example.backendmd6.repository;

import com.example.backendmd6.model.ProfileEnterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileEnterpriseRepository extends JpaRepository<ProfileEnterprise, Long> {
}
