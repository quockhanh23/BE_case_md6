package com.example.backendmd6.service;

import com.example.backendmd6.model.ProfileUser;
import com.example.backendmd6.model.Recruitment;

public interface RecruitmentService extends GeneralService<Recruitment>{
    Iterable<Recruitment> findAllByJobContaining(String title);
}
