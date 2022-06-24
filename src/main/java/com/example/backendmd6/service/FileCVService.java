package com.example.backendmd6.service;

import com.example.backendmd6.model.FileCV;

public interface FileCVService extends GeneralService<FileCV> {
    Iterable<FileCV> findFileCVById(Long id);
}
