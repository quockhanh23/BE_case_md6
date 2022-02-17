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
    public ResponseEntity<Page<Recruitment>> getAllRecruitment(@PageableDefault(value = 5) Pageable pageable){
        Page<Recruitment> recruitments = recruitmentService.findAll12(pageable);
        return new ResponseEntity<>(recruitments,HttpStatus.OK);
    }

    @GetMapping("listRec")
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
        Long idStatusRec = 2L;
        Optional<StatusRecruitment> statusRecruitment = statusRecruitmentService.findById(idStatusRec);
        recruitment.setStatusRecruitmentId(statusRecruitment.get());
        recruitment.setProfileEnterprise(profileEnterprise.get());
        recruitmentService.save(recruitment);
        return new ResponseEntity<>(recruitment, HttpStatus.CREATED);
    }

    // sửa job2( đang dùng)
    @PutMapping("/edit")
    public ResponseEntity<Recruitment> edit2(@RequestBody Recruitment recruitment,
                                             @RequestParam Long idRecruitment) {
        Recruitment recruitment1 = recruitmentService.findById(idRecruitment).get();
        recruitment.setId(recruitment1.getId());
        recruitment.setDateBegin(recruitment1.getDateBegin());
        recruitment.setStatusRecruitmentId(recruitment1.getStatusRecruitmentId());
        recruitment.setProfileEnterprise(recruitment1.getProfileEnterprise());
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

    @GetMapping("/address")
    public ResponseEntity<Iterable<Recruitment>> address(@RequestParam String q) {
        Iterable<Recruitment> recruitments;
        if (Objects.equals(q, "")) {
            recruitments = recruitmentService.findAll();
        } else {
            recruitments = recruitmentService.findRecruitmentByAddress(q);
        }
        return new ResponseEntity<>(recruitments, HttpStatus.OK);
    }
    // Cụm tìm theo thành phố 1-10
    @GetMapping("/address1")
    public ResponseEntity<Iterable<Recruitment>> address1() {
        Iterable<Recruitment> recruitments = recruitmentService.findRecruitmentByAddress1();
        return new ResponseEntity<>(recruitments, HttpStatus.OK);
    }

    @GetMapping("/address2")
    public ResponseEntity<Iterable<Recruitment>> address2() {
        Iterable<Recruitment> recruitments = recruitmentService.findRecruitmentByAddress2();
        return new ResponseEntity<>(recruitments, HttpStatus.OK);
    }

    @GetMapping("/address3")
    public ResponseEntity<Iterable<Recruitment>> address3() {
        Iterable<Recruitment> recruitments = recruitmentService.findRecruitmentByAddress3();
        return new ResponseEntity<>(recruitments, HttpStatus.OK);
    }

    @GetMapping("/address4")
    public ResponseEntity<Iterable<Recruitment>> address4() {
        Iterable<Recruitment> recruitments = recruitmentService.findRecruitmentByAddress4();
        return new ResponseEntity<>(recruitments, HttpStatus.OK);
    }

    @GetMapping("/address5")
    public ResponseEntity<Iterable<Recruitment>> address5() {
        Iterable<Recruitment> recruitments = recruitmentService.findRecruitmentByAddress5();
        return new ResponseEntity<>(recruitments, HttpStatus.OK);
    }

    @GetMapping("/address6")
    public ResponseEntity<Iterable<Recruitment>> address6() {
        Iterable<Recruitment> recruitments = recruitmentService.findRecruitmentByAddress6();
        return new ResponseEntity<>(recruitments, HttpStatus.OK);
    }

    @GetMapping("/address7")
    public ResponseEntity<Iterable<Recruitment>> address7() {
        Iterable<Recruitment> recruitments = recruitmentService.findRecruitmentByAddress7();
        return new ResponseEntity<>(recruitments, HttpStatus.OK);
    }

    @GetMapping("/address8")
    public ResponseEntity<Iterable<Recruitment>> address8() {
        Iterable<Recruitment> recruitments = recruitmentService.findRecruitmentByAddress8();
        return new ResponseEntity<>(recruitments, HttpStatus.OK);
    }

    @GetMapping("/address9")
    public ResponseEntity<Iterable<Recruitment>> address9() {
        Iterable<Recruitment> recruitments = recruitmentService.findRecruitmentByAddress9();
        return new ResponseEntity<>(recruitments, HttpStatus.OK);
    }

    @GetMapping("/address10")
    public ResponseEntity<Iterable<Recruitment>> address10() {
        Iterable<Recruitment> recruitments = recruitmentService.findRecruitmentByAddress10();
        return new ResponseEntity<>(recruitments, HttpStatus.OK);
    }

    @GetMapping("/findRecruitment")
    public ResponseEntity<Iterable<Recruitment>> findRecruitment(String address, String title, String experience, Long min, Long max, String q) {
        Iterable<Recruitment> recruitments =recruitmentService.findRecruitment(address,title,experience,min,max,q);
        return new ResponseEntity<>(recruitments, HttpStatus.OK);
    }
}
