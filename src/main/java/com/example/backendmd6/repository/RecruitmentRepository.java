package com.example.backendmd6.repository;

import com.example.backendmd6.model.ProfileEnterprise;
import com.example.backendmd6.model.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {
    //    Iterable<Recruitment>findAllByAddressContaining(String title);
    @Query("SELECT r FROM Recruitment r WHERE CONCAT(r.title, ' ', r.address, ' ', r.salary) LIKE %?1%")
    Iterable<Recruitment> search(String key);

    Iterable<Recruitment> findAllByOrderByDateBeginDesc();

    @Query(value = "select * from recruitment order by id desc ", nativeQuery = true)
    Iterable<Recruitment> sortNew();

    @Query(value = "select * from recruitment order by id asc  ", nativeQuery = true)
    Iterable<Recruitment> sortOdd();

    @Query(value = "select * from recruitment where profile_enterprise_id=:id order by id desc ", nativeQuery = true)
    Iterable<Recruitment> findRecruitmentByProfileEnterprise(@Param("id") Long id);

}