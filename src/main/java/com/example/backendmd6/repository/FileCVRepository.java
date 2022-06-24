package com.example.backendmd6.repository;

import com.example.backendmd6.model.FileCV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FileCVRepository extends JpaRepository<FileCV, Long> {
    @Modifying
    @Query(value = "select * from filecv  where profile_user_id=:idUser;", nativeQuery = true)
    Iterable<FileCV> findFileCVById(@Param("idUser") Long id);
}
