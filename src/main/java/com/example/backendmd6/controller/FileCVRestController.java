package com.example.backendmd6.controller;

import com.example.backendmd6.model.*;
import com.example.backendmd6.service.FileCVService;
import com.example.backendmd6.service.ProfileUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/findCVByUserId")
    public ResponseEntity<Iterable<FileCV>> findByUserId(Long idUser) {
        Iterable<FileCV> fileCVS = fileCVService.findFileCVById(idUser);
        return new ResponseEntity<>(fileCVS, HttpStatus.OK);
    }
}
