package com.example.backendmd6.controller;

import com.example.backendmd6.model.*;
import com.example.backendmd6.service.ApplyNowService;
import com.example.backendmd6.service.FileCVService;
import com.example.backendmd6.service.ProfileUserService;
import com.example.backendmd6.service.StatusConApplyNowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@PropertySource("classpath:application.properties")
@RequestMapping("/api/cvs")
public class FileCVRestController {
    @Autowired
    private FileCVService fileCVService;

    @Autowired
    private ProfileUserService userService;

    @Autowired
    private ApplyNowService applyNowService;

    @Autowired
    private StatusConApplyNowService statusConApplyNowService;

    // Show tất cả cv hiện có
    @GetMapping("")
    public ResponseEntity<Iterable<FileCV>> findAllCV() {
        Iterable<FileCV> fileCVS = fileCVService.findAll();
        return new ResponseEntity<>(fileCVS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileCV> findOne(@PathVariable Long id) {
        Optional<FileCV> fileCVOptional = fileCVService.findById(id);
        return new ResponseEntity<>(fileCVOptional.get(), HttpStatus.OK);
    }

    // Tạo cv mới theo người đăng
    @PostMapping("/createCV")
    public ResponseEntity<FileCV> createCV(@RequestBody FileCV fileCV, @RequestParam Long idUser) {
        Optional<ProfileUser> profileUser = userService.findById(idUser);
        fileCV.setProfileUserId(profileUser.get());
        fileCVService.save(fileCV);
        return new ResponseEntity<>(fileCV, HttpStatus.CREATED);
    }

    // Sửa cv
    @PutMapping("/editCV/{idCV}")
    public ResponseEntity<FileCV> editCV(@RequestBody FileCV fileCV,
                                         @PathVariable Long idCV) {
        Optional<FileCV> fileCVOptional = fileCVService.findById(idCV);
        fileCV.setId(fileCVOptional.get().getId());
        if (!fileCVOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<ProfileUser> profileUser = userService.findById(fileCVOptional.get().getProfileUserId().getId().longValue());
        fileCV.setProfileUserId(profileUser.get());
        fileCVService.save(fileCV);
        return new ResponseEntity<>(fileCV, HttpStatus.CREATED);
    }

    // Xóa CV
    @DeleteMapping("/{id}")
    public ResponseEntity<FileCV> deleteCV(@PathVariable Long id) {
        Optional<FileCV> fileCVOptional = fileCVService.findById(id);
        if (!fileCVOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        fileCVService.remove(id);
        return new ResponseEntity<>(fileCVOptional.get(), HttpStatus.OK);
    }

    //Gửi hồ sơ để applyNow
    @PostMapping("submitCv")
    public ResponseEntity<ApplyNow> submitCv(@RequestParam Long idUser, @RequestBody Recruitment recruitment) {
        Iterable<FileCV> fileCV = fileCVService.findByProfileUserId1(idUser);
        List<FileCV> list = (List<FileCV>) fileCV;
        FileCV fileCV1 = list.get(0);
        Long idStatusCon = 1L;
        Optional<StatusConfirmOfApplyNow> statusConfirmOfApplyNow = statusConApplyNowService.findById(idStatusCon);
        ApplyNow applyNow = new ApplyNow(fileCV1, recruitment, statusConfirmOfApplyNow.get());
        applyNowService.save(applyNow);
        return new ResponseEntity<>(applyNow, HttpStatus.OK);
    }

    @GetMapping("/findCVByUserId")
    public ResponseEntity<FileCV> findByUserId(@RequestParam Long idUser) {
        Iterable<FileCV> fileCVS = fileCVService.findFileCVById(idUser);
        List<FileCV> list = (List<FileCV>) fileCVS;
        FileCV fileCV = list.get(0);
        return new ResponseEntity<>(fileCV, HttpStatus.OK);
    }

    @GetMapping("/findByRecAndCv")
    public ResponseEntity<List<ApplyNow>> findByRecAndCv(@RequestParam Long idRec, @RequestParam Long idCv) {
        Iterable<ApplyNow> applyNows = applyNowService.findByRecAndCv(idRec, idCv);
        List<ApplyNow> list = (List<ApplyNow>) applyNows;
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/findByRec")
    public ResponseEntity<List<ApplyNow>> findByRecruitmentId(@RequestParam Long idRec) {
        Iterable<ApplyNow> applyNows = applyNowService.findApplyNowByRecruitmentId(idRec);
        List<ApplyNow> list = (List<ApplyNow>) applyNows;
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @DeleteMapping("/setStatus")
    public ResponseEntity<ApplyNow> setStatus(@RequestParam Long idRec, @RequestParam Long idCv) {
        Iterable<ApplyNow> applyNows = applyNowService.findByRecAndCv(idRec, idCv);
        List<ApplyNow> list = (List<ApplyNow>) applyNows;
        Optional<StatusConfirmOfApplyNow> statusConfirmOfApplyNow = statusConApplyNowService.findById(2L);
        list.get(0).setStatusConfirmId(statusConfirmOfApplyNow.get());
        applyNowService.save(list.get(0));
        return new ResponseEntity<>(list.get(0), HttpStatus.OK);
    }
}
