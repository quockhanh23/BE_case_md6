package com.example.backendmd6.repository;

import com.example.backendmd6.model.FileCV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileCVRepository extends JpaRepository<FileCV, Long> {

    @Modifying
    @Query(value = "select * from filecv  where profile_user_id = :idUser", nativeQuery = true)
    Iterable<FileCV> findFileCVByProfileUserId1(@Param("idUser") Long idUser);

    @Modifying
    @Query(value = " select * from enterprise_table where status_enterprise_id like 1", nativeQuery = true)
    Optional<FileCV> findAllByStatusLikeOne();
}
