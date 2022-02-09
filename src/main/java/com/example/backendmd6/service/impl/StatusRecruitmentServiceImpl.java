package com.example.backendmd6.service.impl;

import com.example.backendmd6.model.StatusRecruitment;
import com.example.backendmd6.repository.StatusRecruitmentRepository;
import com.example.backendmd6.service.StatusRecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatusRecruitmentServiceImpl implements StatusRecruitmentService {
    @Autowired
    private StatusRecruitmentRepository statusRecruitmentRepository;

    @Override
    public Iterable<StatusRecruitment> findAll() {
        return statusRecruitmentRepository.findAll();
    }

    @Override
    public Optional<StatusRecruitment> findById(Long id) {
        return statusRecruitmentRepository.findById(id);
    }

    @Override
    public void save(StatusRecruitment statusRecruitment) {
        statusRecruitmentRepository.save(statusRecruitment);
    }

    @Override
    public void remove(Long id) {
        statusRecruitmentRepository.deleteById(id);
    }
}
