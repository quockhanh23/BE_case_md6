package com.example.backendmd6.controller;

import com.example.backendmd6.model.Recruitment;
import com.example.backendmd6.service.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("api/recruitments")
@CrossOrigin("*")

public class RecruitmentController {
    @Autowired
    private RecruitmentService recruitmentService;

    @GetMapping("")
    public ResponseEntity<Iterable<Recruitment>> showAll() {
        Iterable<Recruitment> recruitments = recruitmentService.findAll();
        return new ResponseEntity<>(recruitments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recruitment> detail(@PathVariable Long id) {
        Optional<Recruitment> recruitment = recruitmentService.findById(id);
        return new ResponseEntity<>(recruitment.get(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Recruitment> create(@RequestBody Recruitment recruitment) {
        recruitmentService.save(recruitment);
        return new ResponseEntity<>(recruitment, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Recruitment> update(@PathVariable Long id, @RequestBody Recruitment recruitment) {
        recruitment.setId(id);
        recruitmentService.save(recruitment);
        return new ResponseEntity<>(recruitment, HttpStatus.OK);
    }
    @GetMapping("name/{q}")
    public ResponseEntity<Iterable<Recruitment>> search(@PathVariable String q) {
        Iterable<Recruitment> recruitments;
        if (Objects.equals(q, "")) {
            recruitments = recruitmentService.findAll();
        } else {
            recruitments = recruitmentService.search(q);
        }
        return new ResponseEntity<>(recruitments, HttpStatus.OK);
    }
    @GetMapping("/sort")
    public ResponseEntity<Iterable<Recruitment>> showAllListOrderByDate() {
        Iterable<Recruitment> recruitments = recruitmentService.findAllByOrderByDateEnd();
        return new ResponseEntity<>(recruitments, HttpStatus.OK);
    }

}
