package com.example.backendmd6.repository;

import com.example.backendmd6.model.ProfileEnterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileEnterpriseRepository extends JpaRepository<ProfileEnterprise, Long> {
    ProfileEnterprise findByEmail(String email);

    void delete(ProfileEnterprise entity);

    Iterable<ProfileEnterprise> findByNameCompanyContaining(String name);

    @Modifying
    @Query(value = " select * from enterprise_table where status_enterprise_id like 1", nativeQuery = true)
    Iterable<ProfileEnterprise> findAllByStatusLikeOne();
}
