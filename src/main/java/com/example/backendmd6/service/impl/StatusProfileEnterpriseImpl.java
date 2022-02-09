package com.example.backendmd6.service.impl;

import com.example.backendmd6.model.StatusEnterprise;
import com.example.backendmd6.repository.StatusEnterpriseRepository;
import com.example.backendmd6.service.StatusEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatusProfileEnterpriseImpl implements StatusEnterpriseService {
    @Autowired
    private StatusEnterpriseRepository statusEnterpriseRepository;

    @Override
    public Iterable<StatusEnterprise> findAll() {
        return statusEnterpriseRepository.findAll();
    }

    @Override
    public Optional<StatusEnterprise> findById(Long id) {
        return statusEnterpriseRepository.findById(id);
    }

    @Override
    public void save(StatusEnterprise statusEnterprise) {
        statusEnterpriseRepository.save(statusEnterprise);
    }

    @Override
    public void remove(Long id) {

    }
}
