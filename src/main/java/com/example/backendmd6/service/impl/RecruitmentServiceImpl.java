package com.example.backendmd6.service.impl;

import com.example.backendmd6.model.Recruitment;
import com.example.backendmd6.repository.RecruitmentRepository;
import com.example.backendmd6.service.GeneralService;
import com.example.backendmd6.service.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.GeneratedValue;
import java.util.Optional;

@Service
public class RecruitmentServiceImpl implements RecruitmentService {
    @Autowired
    private RecruitmentRepository recruitmentRepository;

    @Override
    public Iterable<Recruitment> findAll() {
        return recruitmentRepository.findAll();
    }

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
    public Iterable<Recruitment> findAllByJobContaining(String job) {
        return recruitmentRepository.findAllByTitleContaining(job);
    }
}
