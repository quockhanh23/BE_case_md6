package com.example.backendmd6.service;

import com.example.backendmd6.model.Recruitment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RecruitmentService {

    Iterable<Recruitment> search(String key);

    Iterable<Recruitment> findAll();

    Page<Recruitment> search(String key, Pageable pageable);

    Iterable<Recruitment> search2(String key);

    Iterable<Recruitment> sortNew();

    Iterable<Recruitment> findAllByOrderByDateBeginDesc();

    Iterable<Recruitment> findRecruitmentByProfileEnterprise(Long id);

    Iterable<Recruitment> findRecruitmentByAddress(String q);

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
                                          String name);
}
