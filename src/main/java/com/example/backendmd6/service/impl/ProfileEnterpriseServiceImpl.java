package com.example.backendmd6.service.impl;

import com.example.backendmd6.model.ProfileEnterprise;
import com.example.backendmd6.repository.ProfileEnterpriseRepository;
import com.example.backendmd6.service.ProfileEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class ProfileEnterpriseServiceImpl implements ProfileEnterpriseService {
    @Autowired
    private ProfileEnterpriseRepository profileEnterpriseRepository;

    @Override
    public Iterable<ProfileEnterprise> findAll() {
        return profileEnterpriseRepository.findAll();
    }

    @Override
    public Optional<ProfileEnterprise> findById(Long id) {
        return profileEnterpriseRepository.findById(id);
    }

    @Override
    public void save(ProfileEnterprise enterprise) {
        profileEnterpriseRepository.save(enterprise);
    }

    @Override
    public void remove(Long id) {
        profileEnterpriseRepository.deleteById(id);
    }
}
