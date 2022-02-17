package com.example.backendmd6.service;

import com.example.backendmd6.model.ApplyNow;

public interface ApplyNowService extends GeneralService<ApplyNow>{
    Iterable<ApplyNow> findByRecAndCv(Long idRec,Long idCv);
}
