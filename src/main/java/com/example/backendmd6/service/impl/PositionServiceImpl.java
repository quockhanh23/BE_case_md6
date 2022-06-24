package com.example.backendmd6.service.impl;

import com.example.backendmd6.model.Position;
import com.example.backendmd6.repository.PositionRepository;
import com.example.backendmd6.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionRepository positionRepository;

    @Override
    public Iterable<Position> findAll() {
        return positionRepository.findAll();
    }

    @Override
    public Optional<Position> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Position position) {

    }

    @Override
    public void remove(Long id) {

    }
}
