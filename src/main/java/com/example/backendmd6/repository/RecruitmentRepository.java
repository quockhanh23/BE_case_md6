package com.example.backendmd6.repository;

import com.example.backendmd6.model.ProfileEnterprise;
import com.example.backendmd6.model.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {
    //    Iterable<Recruitment>findAllByAddressContaining(String title);
    @Query("SELECT r FROM Recruitment r WHERE CONCAT(r.title, ' ', r.salary, ' ', r.address) LIKE %?1%")
    Iterable<Recruitment> search(String key);

    @Query(value = "select * from Recruitment order by id desc limit 10", nativeQuery = true)
    Iterable<Recruitment> findByOrOrderByDateEndAsc();


    Iterable<Recruitment> findAllByOrderByDateBegin();
}