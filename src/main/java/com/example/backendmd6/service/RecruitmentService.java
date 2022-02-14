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

    Iterable<Recruitment> sortOdd();
}
