package com.example.backendmd6.controller;

import com.example.backendmd6.model.Recruitment;
import com.example.backendmd6.model.Work;
import com.example.backendmd6.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/works")
@CrossOrigin("*")
public class WorkController {
    @Autowired
    WorkService workService;
    @GetMapping("name/{q}")
    public ResponseEntity<Iterable<Work>> search(@PathVariable String q) {
        Iterable<Work> works=workService.findAllByName(q);
        return new ResponseEntity<>(works, HttpStatus.OK);
    }
}
