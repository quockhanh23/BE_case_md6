package com.example.backendmd6.repository;

import com.example.backendmd6.model.StatusRecruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRecruitmentRepository extends JpaRepository<StatusRecruitment, Long> {
}
