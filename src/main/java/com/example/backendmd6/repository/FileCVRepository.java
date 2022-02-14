package com.example.backendmd6.repository;

import com.example.backendmd6.model.FileCV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileCVRepository extends JpaRepository<FileCV, Long> {
}
