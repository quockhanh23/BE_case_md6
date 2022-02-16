package com.example.backendmd6.service.impl;

import com.example.backendmd6.model.Recruitment;
import com.example.backendmd6.repository.RecruitmentRepository;
import com.example.backendmd6.service.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecruitmentServiceImpl implements RecruitmentService {

    @Autowired
    private RecruitmentRepository recruitmentRepository;

    @Override
    public Optional<Recruitment> findById(Long id) {
        return recruitmentRepository.findById(id);
    }

    @Override
    public void save(Recruitment recruitment) {
        recruitmentRepository.save(recruitment);
    }

    @Override
    public void remove(Long id) {
        recruitmentRepository.deleteById(id);
    }

//    @Override
//    public Iterable<Recruitment> findAllByCityContaining(String title) {
//        return recruitmentRepository.findAllByAddressContaining(title);
//    }

    @Override
    public Iterable<Recruitment> search(String key) {
        return recruitmentRepository.search(key);
    }

    @Override
    public Iterable<Recruitment> findAll() {
        return recruitmentRepository.findAll();
    }

    @Override
    public Iterable<Recruitment> sortNew() {
        return recruitmentRepository.sortNew();
    }

    @Override
    public Iterable<Recruitment> findAllByOrderByDateBeginDesc() {
        return recruitmentRepository.findAllByOrderByDateBeginDesc();
    }

    @Override
    public Iterable<Recruitment> findRecruitmentByProfileEnterprise(Long id) {
        return recruitmentRepository.findRecruitmentByProfileEnterprise(id);
    }

    @Override
    public Iterable<Recruitment> findRecruitmentByAddress1() {
        return recruitmentRepository.findRecruitmentByAddress1();
    }

    @Override
    public Iterable<Recruitment> findRecruitmentByAddress2() {
        return recruitmentRepository.findRecruitmentByAddress2();
    }

    @Override
    public Iterable<Recruitment> findRecruitmentByAddress3() {
        return recruitmentRepository.findRecruitmentByAddress3();
    }

    @Override
    public Iterable<Recruitment> findRecruitmentByAddress4() {
        return recruitmentRepository.findRecruitmentByAddress4();
    }

    @Override
    public Iterable<Recruitment> findRecruitmentByAddress5() {
        return recruitmentRepository.findRecruitmentByAddress5();
    }

    @Override
    public Iterable<Recruitment> findRecruitmentByAddress6() {
        return recruitmentRepository.findRecruitmentByAddress6();
    }

    @Override
    public Iterable<Recruitment> findRecruitmentByAddress7() {
        return recruitmentRepository.findRecruitmentByAddress7();
    }

    @Override
    public Iterable<Recruitment> findRecruitmentByAddress8() {
        return recruitmentRepository.findRecruitmentByAddress8();
    }

    @Override
    public Iterable<Recruitment> sortOdd() {
        return recruitmentRepository.sortOdd();
    }

    @Override
    public Iterable<Recruitment> findRecruitmentByAddress9() {
        return recruitmentRepository.findRecruitmentByAddress9();
    }

    @Override
    public Iterable<Recruitment> findRecruitmentByAddress10() {
        return recruitmentRepository.findRecruitmentByAddress10();
    }


    @Override
    public Iterable<Recruitment> findRecruitmentByStatusRecruitmentId() {
        return recruitmentRepository.findRecruitmentByStatusRecruitmentId();
    }

    @Override
    public Page<Recruitment> findAll12(Pageable pageable) {
        return recruitmentRepository.findAllPaging(pageable);
    }
}
