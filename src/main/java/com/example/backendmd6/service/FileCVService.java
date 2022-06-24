package com.example.backendmd6.service;

import com.example.backendmd6.model.FileCV;

public interface FileCVService extends GeneralService<FileCV> {
    Iterable<FileCV> findByProfileUserId1(Long id);
    Iterable<FileCV> findFileCVById(Long id);
}
