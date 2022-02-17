package com.example.backendmd6.repository;

import com.example.backendmd6.model.ApplyNow;
import com.example.backendmd6.model.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplyNowRepository extends JpaRepository<ApplyNow, Long> {
    @Query(value = "select * from apply_now  where recruitment_id = :idRec and filecvid= :idCv",nativeQuery = true)
    Iterable<ApplyNow> findApplyNowByRecruitmentId1(@Param("idRec") Long idRec,@Param("idCv") Long idCv);

    @Query(value = "select * from apply_now  where recruitment_id = :idRec",nativeQuery = true)
    Iterable<ApplyNow> findApplyNowByRecruitmentId(@Param("idRec") Long idRec);
}
