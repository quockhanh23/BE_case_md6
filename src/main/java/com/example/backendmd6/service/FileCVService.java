package com.example.backendmd6.service;

import com.example.backendmd6.model.FileCV;

import java.util.Optional;

public interface FileCVService extends GeneralService<FileCV> {
    Iterable<FileCV> findByProfileUserId1(Long id);
}
