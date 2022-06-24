package com.example.backendmd6.service.impl;

import com.example.backendmd6.model.Recruitment;
import com.example.backendmd6.repository.RecruitmentRepository;
import com.example.backendmd6.service.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    @Override
    public Iterable<Recruitment> findRecruitment(String address, String title, String experience, Long min, Long max, String name) {
        return recruitmentRepository.findRecruitment(address,title,experience,min,max,name);
    }

    @Override
    public Page<Recruitment> search(String key,Pageable pageable) {
        return recruitmentRepository.search(key,pageable);
    }

    @Override
    public Iterable<Recruitment> search2(String key) {
        return recruitmentRepository.search2(key);
    }

    @Override
    public Iterable<Recruitment> search(String key) {
        return null;
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
    public Iterable<Recruitment> findRecruitmentByAddress(String q) {
        return recruitmentRepository.findRecruitmentByAddress(q);
    }

    @Override
    public Iterable<Recruitment> sortOdd() {
        return recruitmentRepository.sortOdd();
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
