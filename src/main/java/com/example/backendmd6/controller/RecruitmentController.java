package com.example.backendmd6.controller;

import com.example.backendmd6.model.ProfileEnterprise;
import com.example.backendmd6.model.Recruitment;
import com.example.backendmd6.model.StatusRecruitment;
import com.example.backendmd6.service.ProfileEnterpriseService;
import com.example.backendmd6.service.RecruitmentService;
import com.example.backendmd6.service.StatusRecruitmentService;
import javafx.beans.DefaultProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("api/recruitments")
@CrossOrigin("*")

public class RecruitmentController {
    @Autowired
    private RecruitmentService recruitmentService;
    @Autowired
    private ProfileEnterpriseService profileEnterpriseService;
    @Autowired
    private StatusRecruitmentService statusRecruitmentService;

    @GetMapping("paging")
    public ResponseEntity<List<Recruitment>> getAllRecruitment(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<Recruitment> list = recruitmentService.findAllPaging(pageNo, pageSize, sortBy);

        return new ResponseEntity<List<Recruitment>>(list,HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Iterable<Recruitment>> findAll() {
        Iterable<Recruitment> recruitments = recruitmentService.findAll();
        return new ResponseEntity<>(recruitments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recruitment> detail(@PathVariable Long id) {
        Optional<Recruitment> recruitment = recruitmentService.findById(id);
        return new ResponseEntity<>(recruitment.get(), HttpStatus.OK);
    }

    // tạo job mới
    @PostMapping("/create")
    public ResponseEntity<Recruitment> create(@RequestBody Recruitment recruitment, @RequestParam Long idEnterprise) {
        recruitment.setDateBegin(LocalDateTime.now());
        Optional<ProfileEnterprise> profileEnterprise = profileEnterpriseService.findById(idEnterprise);
        Long idStatusRec = 1L;
        Optional<StatusRecruitment> statusRecruitment = statusRecruitmentService.findById(idStatusRec);
        recruitment.setStatusRecruitmentId(statusRecruitment.get());
        recruitment.setProfileEnterprise(profileEnterprise.get());
        recruitmentService.save(recruitment);
        return new ResponseEntity<>(recruitment, HttpStatus.CREATED);
    }

    // sửa job
    @PutMapping("/edit/{idRecruitment}")
    public ResponseEntity<Recruitment> edit(@RequestBody Recruitment recruitment,
                                            @PathVariable Long idRecruitment) {
        Optional<Recruitment> recruitmentOptional = recruitmentService.findById(idRecruitment);
        recruitment.setId(recruitmentOptional.get().getId());
        Optional<ProfileEnterprise> profileEnterprise = profileEnterpriseService.findById
                (recruitmentOptional.get().getProfileEnterprise().getId().longValue());
        recruitment.setProfileEnterprise(profileEnterprise.get());
        Optional<StatusRecruitment> statusRecruitment = statusRecruitmentService.findById(1L);
        recruitment.setStatusRecruitmentId(statusRecruitment.get());
        recruitmentService.save(recruitment);
        return new ResponseEntity<>(recruitment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recruitment> update(@PathVariable Long id, @RequestBody Recruitment recruitment) {
        recruitment.setId(id);
        recruitmentService.save(recruitment);
        return new ResponseEntity<>(recruitment, HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<Iterable<Recruitment>> search(@RequestParam String q) {
        Iterable<Recruitment> recruitments;
        if (Objects.equals(q, "")) {
            recruitments = recruitmentService.findAll();
        } else {
            recruitments = recruitmentService.search(q);
        }
        return new ResponseEntity<>(recruitments, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Recruitment> delete(@PathVariable Long id) {
        recruitmentService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //sắp xếp theo thời gian đăng bài
    @GetMapping("/sort")
    public ResponseEntity<Iterable<Recruitment>> showAllListOrderByDate() {
        Iterable<Recruitment> recruitments = recruitmentService.findAllByOrderByDateBeginDesc();
        return new ResponseEntity<>(recruitments, HttpStatus.OK);
    }

    @GetMapping("nameCompany/{q}")
    public ResponseEntity<Iterable<ProfileEnterprise>> searchName(@PathVariable String q) {
        Iterable<ProfileEnterprise> profileEnterprises = profileEnterpriseService.findByNameCompanyContaining(q);
        return new ResponseEntity<>(profileEnterprises, HttpStatus.OK);
    }

    @GetMapping("sortNewJob")
    public ResponseEntity<Iterable<Recruitment>> sortNewJob() {
        Iterable<Recruitment> recruitments = recruitmentService.sortNew();
        return new ResponseEntity<>(recruitments, HttpStatus.OK);
    }

    @GetMapping("sortOddJob")
    public ResponseEntity<Iterable<Recruitment>> sortOddJob() {
        Iterable<Recruitment> recruitments = recruitmentService.sortOdd();
        return new ResponseEntity<>(recruitments, HttpStatus.OK);
    }

    //Hiện thị danh sách bài viêt VIP sắp xếp theo thời gian
    @GetMapping("/topVIP")
    public ResponseEntity<Iterable<Recruitment>> showListVIPOrderByDate() {
        Iterable<Recruitment> recruitments = recruitmentService.findRecruitmentByStatusRecruitmentId();
        return new ResponseEntity<>(recruitments, HttpStatus.OK);
    }
}
