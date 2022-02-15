package com.example.backendmd6.service;

import com.example.backendmd6.model.FileCV;
import com.example.backendmd6.model.ProfileEnterprise;

public interface FileCVService extends GeneralService<FileCV> {
    Iterable<FileCV> findFileCVById(Long id);
}
