package com.example.backendmd6.service;

import com.example.backendmd6.model.ApplyNow;
import org.springframework.data.repository.query.Param;

public interface ApplyNowService extends GeneralService<ApplyNow>{
    Iterable<ApplyNow> findByRecAndCv(Long idRec,Long idCv);
    Iterable<ApplyNow> findApplyNowByRecruitmentId(@Param("idRec") Long idRec);
}
