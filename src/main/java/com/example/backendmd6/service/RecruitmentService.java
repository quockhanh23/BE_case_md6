package com.example.backendmd6.service;

import com.example.backendmd6.model.ProfileUser;
import com.example.backendmd6.model.Recruitment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RecruitmentService {
    //    Iterable<Recruitment> findAllByCityContaining(String title);
    Iterable<Recruitment> search(String key);

    Iterable<Recruitment> sortNew();

    Iterable<Recruitment> findAllByOrderByDateBeginDesc();

    Iterable<Recruitment> findRecruitmentByProfileEnterprise(Long id);

    Iterable<Recruitment> sortOdd();

    Page<Recruitment> findAll(Pageable pageable);

    Optional<Recruitment> findById(Long id);

    void save(Recruitment recruitment);

    void remove(Long id);

}
