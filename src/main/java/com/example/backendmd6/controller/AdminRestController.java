package com.example.backendmd6.controller;

import com.example.backendmd6.model.ProfileEnterprise;
import com.example.backendmd6.model.Recruitment;
import com.example.backendmd6.model.StatusEnterprise;
import com.example.backendmd6.model.StatusRecruitment;
import com.example.backendmd6.service.ProfileEnterpriseService;
import com.example.backendmd6.service.RecruitmentService;
import com.example.backendmd6.service.StatusEnterpriseService;
import com.example.backendmd6.service.StatusRecruitmentService;
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
    private StatusRecruitmentService statusRecruitmentService;
    @Autowired
    private RecruitmentService recruitmentService;

    // Show tất cả tin tuyển dụng
    @GetMapping("/findRecruitment")
    public ResponseEntity<Iterable<Recruitment>> findAllRecruitment() {
        Iterable<Recruitment> statusRecruitments = recruitmentService.findAll();
        return new ResponseEntity<>(statusRecruitments, HttpStatus.OK);
    }

    // Show 1 tin tuyển dụng
    @GetMapping("findOneRecruitment/{id}")
    public ResponseEntity<Recruitment> findOneRecruitment(@PathVariable Long id) {
        Optional<Recruitment> recruitment = recruitmentService.findById(id);
        if (!recruitment.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(recruitment.get(), HttpStatus.OK);
    }

    // Show tất cả status của tin tuyển dụng
    @GetMapping("/findAllStatusRecruitment")
    public ResponseEntity<Iterable<StatusRecruitment>> findAllStatusRecruitment() {
        Iterable<StatusRecruitment> statusRecruitments = statusRecruitmentService.findAll();
        return new ResponseEntity<>(statusRecruitments, HttpStatus.OK);
    }

    // Show 1 status của tin tuyển dụng
    @GetMapping("findOneStatusRecruitment/{id}")
    public ResponseEntity<StatusRecruitment> findOneStatusRecruitment(@PathVariable Long id) {
        Optional<StatusRecruitment> statusRecruitment = statusRecruitmentService.findById(id);
        if (!statusRecruitment.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(statusRecruitment.get(), HttpStatus.OK);
    }

    // Show tất doanh nghiệp
    @GetMapping("/findAllEnterprise")
    public ResponseEntity<Iterable<ProfileEnterprise>> findAllEnterprise() {
        Iterable<ProfileEnterprise> profileEnterprises = profileEnterpriseService.findAll();
        return new ResponseEntity<>(profileEnterprises, HttpStatus.OK);
    }

    // Show tất status của doanh nghiệp
    @GetMapping("/findAllStatusEnterprise")
    public ResponseEntity<Iterable<StatusEnterprise>> findAllStatusEnterprise() {
        Iterable<StatusEnterprise> statusEnterprises = statusEnterpriseService.findAll();
        return new ResponseEntity<>(statusEnterprises, HttpStatus.OK);
    }

    // Show 1 status của doanh nghiệp
    @GetMapping("findOneStatusEnterprise/{id}")
    public ResponseEntity<StatusEnterprise> findOneStatusEnterpriseById(@PathVariable Long id) {
        Optional<StatusEnterprise> statusEnterprise = statusEnterpriseService.findById(id);
        if (!statusEnterprise.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(statusEnterprise.get(), HttpStatus.OK);
    }

    // Show 1 doanh nghiệp
    @GetMapping("findOneEnterprise/{id}")
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

    // đổi trạng thái doanh nghiệp sang "Công ty được đề xuất"
    @DeleteMapping("/changeVIP/{id}")
    public ResponseEntity<ProfileEnterprise> changeVIP(@PathVariable Long id) {
        Optional<ProfileEnterprise> optionalProfileEnterprise = profileEnterpriseService.findById(id);
        if (!optionalProfileEnterprise.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<StatusEnterprise> statusEnterprise = statusEnterpriseService.findById(4L);
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

    // đổi trạng thái tin đăng tuyển sang ẩn
    @DeleteMapping("/changePrivateRecruitment/{id}")
    public ResponseEntity<Recruitment> changePrivateRecruitment(@PathVariable Long id) {
        Optional<Recruitment> optionalRecruitment = recruitmentService.findById(id);
        if (!optionalRecruitment.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<StatusRecruitment> statusRecruitment = statusRecruitmentService.findById(1L);
        optionalRecruitment.get().setStatusRecruitmentId(statusRecruitment.get());
        recruitmentService.save(optionalRecruitment.get());
        return new ResponseEntity<>(optionalRecruitment.get(), HttpStatus.OK);
    }

    // đổi trạng thái tin đăng tuyển công khai
    @DeleteMapping("/changePublicRecruitment/{id}")
    public ResponseEntity<Recruitment> changePublicRecruitment(@PathVariable Long id) {
        Optional<Recruitment> optionalRecruitment = recruitmentService.findById(id);
        if (!optionalRecruitment.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<StatusRecruitment> statusRecruitment = statusRecruitmentService.findById(2L);
        optionalRecruitment.get().setStatusRecruitmentId(statusRecruitment.get());
        recruitmentService.save(optionalRecruitment.get());
        return new ResponseEntity<>(optionalRecruitment.get(), HttpStatus.OK);
    }

    // đổi trạng thái tin đăng tuyển proposal VIP
    @DeleteMapping("/changeProposalVIPRecruitment/{id}")
    public ResponseEntity<Recruitment> changeProposalVIPRecruitment(@PathVariable Long id) {
        Optional<Recruitment> optionalRecruitment = recruitmentService.findById(id);
        if (!optionalRecruitment.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<StatusRecruitment> statusRecruitment = statusRecruitmentService.findById(3L);
        optionalRecruitment.get().setStatusRecruitmentId(statusRecruitment.get());
        recruitmentService.save(optionalRecruitment.get());
        return new ResponseEntity<>(optionalRecruitment.get(), HttpStatus.OK);
    }

    // Khóa tin đăng tuyển dụng
    @DeleteMapping("/changeLockRecruitment/{id}")
    public ResponseEntity<Recruitment> changeLockRecruitment(@PathVariable Long id) {
        Optional<Recruitment> optionalRecruitment = recruitmentService.findById(id);
        if (!optionalRecruitment.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<StatusRecruitment> statusRecruitment = statusRecruitmentService.findById(4L);
        optionalRecruitment.get().setStatusRecruitmentId(statusRecruitment.get());
        recruitmentService.save(optionalRecruitment.get());
        return new ResponseEntity<>(optionalRecruitment.get(), HttpStatus.OK);
    }
}
