package com.example.backendmd6.repository;

import com.example.backendmd6.model.ProfileEnterprise;
import com.example.backendmd6.model.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileEnterpriseRepository extends JpaRepository<ProfileEnterprise, Long> {
    ProfileEnterprise findByEmail(String email);
    void delete(ProfileEnterprise entity);
    Iterable<ProfileEnterprise> findAllByNameCompany(String nameCompany);

}
