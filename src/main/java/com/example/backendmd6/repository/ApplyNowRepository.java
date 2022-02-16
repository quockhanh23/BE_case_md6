package com.example.backendmd6.repository;

import com.example.backendmd6.model.ApplyNow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplyNowRepository extends JpaRepository<ApplyNow, Long> {
}
