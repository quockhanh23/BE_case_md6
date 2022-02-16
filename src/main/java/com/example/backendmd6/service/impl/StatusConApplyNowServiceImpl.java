package com.example.backendmd6.service.impl;

import com.example.backendmd6.model.StatusConfirmOfApplyNow;
import com.example.backendmd6.repository.StatusConApplyNowRepository;
import com.example.backendmd6.service.StatusConApplyNowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatusConApplyNowServiceImpl implements StatusConApplyNowService {

    @Autowired
    private StatusConApplyNowRepository statusConApplyNowRepository;

    @Override
    public Iterable<StatusConfirmOfApplyNow> findAll() {
        return statusConApplyNowRepository.findAll();
    }

    @Override
    public Optional<StatusConfirmOfApplyNow> findById(Long id) {
        return statusConApplyNowRepository.findById(id);
    }

    @Override
    public void save(StatusConfirmOfApplyNow statusConfirmOfApplyNow) {
        statusConApplyNowRepository.save(statusConfirmOfApplyNow);
    }

    @Override
    public void remove(Long id) {
        statusConApplyNowRepository.deleteById(id);
    }
}
