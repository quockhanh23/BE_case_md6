package com.example.backendmd6.service.impl;

import com.example.backendmd6.model.Work;
import com.example.backendmd6.repository.WorkRepository;
import com.example.backendmd6.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkServiceImpl implements WorkService {
    @Autowired
    WorkRepository workRepository;

    @Override
    public Iterable<Work> findAllByName(String name) {
        return workRepository.findByName(name);
    }
}
