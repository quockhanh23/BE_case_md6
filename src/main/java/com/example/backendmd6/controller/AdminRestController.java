package com.example.backendmd6.controller;

import com.example.backendmd6.model.ProfileEnterprise;
import com.example.backendmd6.model.StatusEnterprise;
import com.example.backendmd6.service.ProfileEnterpriseService;
import com.example.backendmd6.service.RecruitmentService;
import com.example.backendmd6.service.StatusEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@PropertySource("classpath:application.properties")
@RequestMapping("/api/admins")
public class AdminRestController {
    @Autowired
    private ProfileEnterpriseService profileEnterpriseService;
    @Autowired
    private StatusEnterpriseService statusEnterpriseService;
    @Autowired
    private RecruitmentService recruitmentService;

    @GetMapping("")
    public ResponseEntity<Iterable<ProfileEnterprise>> findAllEnterprise() {
        Iterable<ProfileEnterprise> profileEnterprises = profileEnterpriseService.findAll();
        return new ResponseEntity<>(profileEnterprises, HttpStatus.OK);
    }

    @GetMapping("/findAllStatus")
    public ResponseEntity<Iterable<StatusEnterprise>> findAllStatus() {
        Iterable<StatusEnterprise> statusEnterprises = statusEnterpriseService.findAll();
        return new ResponseEntity<>(statusEnterprises, HttpStatus.OK);
    }

    @GetMapping("findOneStatus/{id}")
    public ResponseEntity<StatusEnterprise> findStatusEnterpriseById(@PathVariable Long id) {
        Optional<StatusEnterprise> statusEnterprise = statusEnterpriseService.findById(id);
        if (!statusEnterprise.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(statusEnterprise.get(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileEnterprise> findEnterpriseById(@PathVariable Long id) {
        Optional<ProfileEnterprise> profileEnterprise = profileEnterpriseService.findById(id);
        if (!profileEnterprise.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(profileEnterprise.get(), HttpStatus.OK);
    }

    // đổi trạng thái doanh nghiệp sang đã khóa
    @DeleteMapping("/changeLock/{id}")
    public ResponseEntity<ProfileEnterprise> changeLock(@PathVariable Long id) {
        Optional<ProfileEnterprise> optionalProfileEnterprise = profileEnterpriseService.findById(id);
        if (!optionalProfileEnterprise.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<StatusEnterprise> statusEnterprise = statusEnterpriseService.findById(3L);
        optionalProfileEnterprise.get().setStatusEnterpriseId(statusEnterprise.get());
        profileEnterpriseService.save(optionalProfileEnterprise.get());
        return new ResponseEntity<>(optionalProfileEnterprise.get(), HttpStatus.OK);
    }

    // đổi trạng thái doanh nghiệp sang đã kích hoạt
    @DeleteMapping("/changeActive/{id}")
    public ResponseEntity<ProfileEnterprise> changeActive(@PathVariable Long id) {
        Optional<ProfileEnterprise> optionalProfileEnterprise = profileEnterpriseService.findById(id);
        if (!optionalProfileEnterprise.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<StatusEnterprise> statusEnterprise = statusEnterpriseService.findById(2L);
        optionalProfileEnterprise.get().setStatusEnterpriseId(statusEnterprise.get());
        profileEnterpriseService.save(optionalProfileEnterprise.get());
        return new ResponseEntity<>(optionalProfileEnterprise.get(), HttpStatus.OK);
    }
}
