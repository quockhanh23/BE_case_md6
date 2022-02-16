package com.example.backendmd6.service.impl;

import com.example.backendmd6.model.ApplyNow;
import com.example.backendmd6.repository.ApplyNowRepository;
import com.example.backendmd6.service.ApplyNowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ApplyNowServiceImpl implements ApplyNowService {
    @Autowired
    private ApplyNowRepository applyNowRepository;
    @Override
    public Iterable<ApplyNow> findAll() {
        return applyNowRepository.findAll();
    }

    @Override
    public Optional<ApplyNow> findById(Long id) {
        return applyNowRepository.findById(id);
    }

    @Override
    public void save(ApplyNow applyNow) {
        applyNowRepository.save(applyNow);
    }

    @Override
    public void remove(Long id) {
        applyNowRepository.deleteById(id);
    }
}
