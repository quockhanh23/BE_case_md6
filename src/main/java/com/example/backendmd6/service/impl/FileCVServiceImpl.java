package com.example.backendmd6.service.impl;

import com.example.backendmd6.model.FileCV;
import com.example.backendmd6.repository.FileCVRepository;
import com.example.backendmd6.service.FileCVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FileCVServiceImpl implements FileCVService {

    @Autowired
    private FileCVRepository fileCVRepository;

    @Override
    public Iterable<FileCV> findAll() {
        return fileCVRepository.findAll();
    }

    @Override
    public Optional<FileCV> findById(Long id) {
        return fileCVRepository.findById(id);
    }

    @Override
    public void save(FileCV fileCV) {
        fileCVRepository.save(fileCV);
    }

    @Override
    public void remove(Long id) {
        fileCVRepository.deleteById(id);
    }

    @Override
    public Iterable<FileCV> findFileCVById(Long id) {
        return fileCVRepository.findFileCVById(id);
    }
}
