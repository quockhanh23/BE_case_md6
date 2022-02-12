package com.example.backendmd6.controller;


import com.example.backendmd6.model.ProfileEnterprise;
import com.example.backendmd6.service.ProfileEnterpriseService;
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

    @GetMapping("")
    public ResponseEntity<Iterable<ProfileEnterprise>> findAllEnterprise() {
        Iterable<ProfileEnterprise> profileEnterprises = profileEnterpriseService.findAll();
        return new ResponseEntity<>(profileEnterprises, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileEnterprise> findEnterpriseById(@PathVariable Long id) {
        Optional<ProfileEnterprise> profileEnterprise = profileEnterpriseService.findById(id);
        return profileEnterprise.map(enterprise ->
                new ResponseEntity<>(enterprise, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

        @GetMapping("/findStatus")
    public ResponseEntity<Iterable<ProfileEnterprise>> findStatusOne() {
        Iterable<ProfileEnterprise> profileEnterprises = profileEnterpriseService.findAllByStatusLikeOne();
        return new ResponseEntity<>(profileEnterprises, HttpStatus.OK);
    }
}
