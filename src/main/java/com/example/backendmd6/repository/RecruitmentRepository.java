package com.example.backendmd6.repository;

import com.example.backendmd6.model.ProfileEnterprise;
import com.example.backendmd6.model.Recruitment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query(value = "select * from recruitment where status_recruitment_id = 2 and 3", nativeQuery = true)
    Page<Recruitment> findAll(Pageable pageable);

    @Query(value = "select * from recruitment where profile_enterprise_id=:id order by id desc ", nativeQuery = true)
    Iterable<Recruitment> findRecruitmentByProfileEnterprise(@Param("id") Long id);

    @Modifying
    @Query(value = "select * from recruitment where address like 'Hà Nội'", nativeQuery = true)
    Iterable<Recruitment> findRecruitmentByAddress1();

    @Modifying
    @Query(value = "select * from recruitment where address like 'Bắc Ninh'", nativeQuery = true)
    Iterable<Recruitment> findRecruitmentByAddress2();

    @Modifying
    @Query(value = "select * from recruitment where address like 'Hà Nam'", nativeQuery = true)
    Iterable<Recruitment> findRecruitmentByAddress3();

    @Modifying
    @Query(value = "select * from recruitment where address like 'Hải Dương'", nativeQuery = true)
    Iterable<Recruitment> findRecruitmentByAddress4();

    @Modifying
    @Query(value = "select * from recruitment where address like 'Hải Phòng'", nativeQuery = true)
    Iterable<Recruitment> findRecruitmentByAddress5();

    @Modifying
    @Query(value = "select * from recruitment where address like 'Hưng Yên'", nativeQuery = true)
    Iterable<Recruitment> findRecruitmentByAddress6();

    @Modifying
    @Query(value = "select * from recruitment where address like 'Nam Định'", nativeQuery = true)
    Iterable<Recruitment> findRecruitmentByAddress7();

    @Modifying
    @Query(value = "select * from recruitment where address like 'Ninh Bình'", nativeQuery = true)
    Iterable<Recruitment> findRecruitmentByAddress8();

    @Modifying
    @Query(value = "select * from recruitment where address like 'Thái Bình'", nativeQuery = true)
    Iterable<Recruitment> findRecruitmentByAddress9();

    @Modifying
    @Query(value = "select * from recruitment where address like 'Vĩnh Phúc'", nativeQuery = true)
    Iterable<Recruitment> findRecruitmentByAddress10();

    @Query(value = "select * from recruitment where status_recruitment_id like 3 order by date_end desc  ", nativeQuery = true)
    Iterable<Recruitment> findRecruitmentByStatusRecruitmentId();
}