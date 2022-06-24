package com.example.backendmd6.service;

import com.example.backendmd6.model.Recruitment;

import java.util.List;
import java.util.Optional;

public interface RecruitmentService {
    Iterable<Recruitment> search(String key);

    Iterable<Recruitment> findAll();

    Iterable<Recruitment> sortNew();

    Iterable<Recruitment> findAllByOrderByDateBeginDesc();

    Iterable<Recruitment> findRecruitmentByProfileEnterprise(Long id);

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

    List<Recruitment> findAllPaging(Integer pageNo, Integer pageSize, String sortBy);

    Optional<Recruitment> findById(Long id);

    void save(Recruitment recruitment);

    void remove(Long id);

}
