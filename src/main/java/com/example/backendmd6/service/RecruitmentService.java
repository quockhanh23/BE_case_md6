package com.example.backendmd6.service;

import com.example.backendmd6.model.ProfileUser;
import com.example.backendmd6.model.Recruitment;
import org.springframework.data.repository.query.Param;

public interface RecruitmentService extends GeneralService<Recruitment> {
    //    Iterable<Recruitment> findAllByCityContaining(String title);
    Iterable<Recruitment> search(String key);

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


}
