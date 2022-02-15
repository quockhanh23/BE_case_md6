package com.example.backendmd6.controller;


import com.example.backendmd6.model.ProfileEnterprise;
import com.example.backendmd6.model.ProfileUser;
import com.example.backendmd6.model.Recruitment;
import com.example.backendmd6.service.ProfileEnterpriseService;
import com.example.backendmd6.service.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@PropertySource("classpath:application.properties")
@RequestMapping("/api/profileEnterprises")
public class ProfileEnterpriseController {
    @Autowired
    private ProfileEnterpriseService profileEnterpriseService;
    @Autowired
    private RecruitmentService recruitmentService;

    @GetMapping("")
    public ResponseEntity<Iterable<ProfileEnterprise>> findAllEnterprise() {
        Iterable<ProfileEnterprise> profileEnterprises = profileEnterpriseService.findAll();
        return new ResponseEntity<>(profileEnterprises, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileEnterprise> findEnterpriseById(@PathVariable Long id) {
        Optional<ProfileEnterprise> profileEnterprise = profileEnterpriseService.findById(id);
        return profileEnterprise.map(enterprise -> new ResponseEntity<>(enterprise, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}/myListRecruitment")
    public ResponseEntity<Iterable<Recruitment>> findMyListRecruitment(@PathVariable Long id) {
        Iterable<Recruitment> recruitments = recruitmentService.findRecruitmentByProfileEnterprise(id);
        return new ResponseEntity<>(recruitments, HttpStatus.OK);
    }

    @GetMapping("/findStatus")
    public ResponseEntity<Iterable<ProfileEnterprise>> findStatusOne() {
        Iterable<ProfileEnterprise> profileEnterprises = profileEnterpriseService.findAllByStatusLikeOne();
        return new ResponseEntity<>(profileEnterprises, HttpStatus.OK);
    }

    @PutMapping("/profileEnterprises/{id}")
    public ResponseEntity<ProfileEnterprise> updateProfileEnterprise(
            @PathVariable Long id,@RequestBody ProfileEnterprise profileEnterprise) {
        Optional<ProfileEnterprise> profileEnterpriseOptional = this.profileEnterpriseService.findById(id);
        if (!profileEnterpriseOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        profileEnterprise.setId(profileEnterpriseOptional.get().getId());
        profileEnterprise.setStatusEnterpriseId(profileEnterpriseOptional.get().getStatusEnterpriseId());
        profileEnterprise.setImage(profileEnterpriseOptional.get().getImage());
        profileEnterprise.setEmail(profileEnterpriseOptional.get().getEmail());
        profileEnterprise.setEnabled(profileEnterpriseOptional.get().isEnabled());
        profileEnterprise.setPassword(profileEnterpriseOptional.get().getPassword());
        profileEnterprise.setRoles(profileEnterpriseOptional.get().getRoles());
        profileEnterprise.setConfirmPassword(profileEnterpriseOptional.get().getConfirmPassword());
        profileEnterpriseService.save(profileEnterprise);
        return new ResponseEntity<>(profileEnterprise, HttpStatus.OK);
    }
}
