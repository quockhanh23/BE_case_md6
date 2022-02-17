package com.example.backendmd6.service;

import com.example.backendmd6.model.ProfileUser;
import com.example.backendmd6.model.Recruitment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RecruitmentService {
    //    Iterable<Recruitment> findAllByCityContaining(String title);
    Iterable<Recruitment> search(String key);

    Iterable<Recruitment> findAll();

    Iterable<Recruitment> sortNew();

    Iterable<Recruitment> findAllByOrderByDateBeginDesc();

    Iterable<Recruitment> findRecruitmentByProfileEnterprise(Long id);

    Iterable<Recruitment> findRecruitmentByAddress(String q);

    Iterable<Recruitment> findRecruitmentByAddress1();

    Iterable<Recruitment> findRecruitmentByAddress2();

    Iterable<Recruitment> findRecruitmentByAddress3();

    Iterable<Recruitment> findRecruitmentByAddress4();

    Iterable<Recruitment> findRecruitmentByAddress5();

    Iterable<Recruitment> findRecruitmentByAddress6();

    Iterable<Recruitment> findRecruitmentByAddress7();

    Iterable<Recruitment> findRecruitmentByAddress8();

    Iterable<Recruitment> findRecruitmentByAddress9();

    Iterable<Recruitment> findRecruitmentByAddress10();


    Iterable<Recruitment> sortOdd();

    Iterable<Recruitment> findRecruitmentByStatusRecruitmentId();

    Page<Recruitment> findAll12(Pageable pageable);

    Optional<Recruitment> findById(Long id);

    void save(Recruitment recruitment);

    void remove(Long id);

    Iterable<Recruitment> findRecruitment(String address,
                                           String title,
                                          String experience,
                                          Long min,
                                          Long max,
                                          String q);
}
