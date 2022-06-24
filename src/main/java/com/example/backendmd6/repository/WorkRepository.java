package com.example.backendmd6.repository;

import com.example.backendmd6.model.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkRepository extends JpaRepository<Work,Long> {
    Iterable<Work> findByName(String name);
}
