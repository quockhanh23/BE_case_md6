package com.example.backendmd6.controller;

import com.example.backendmd6.model.Position;
import com.example.backendmd6.model.Work;
import com.example.backendmd6.service.PositionService;
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
    private WorkService workService;
    @Autowired
    private PositionService positionService;

    @GetMapping("name/{q}")
    public ResponseEntity<Iterable<Work>> search(@PathVariable String q) {
        Iterable<Work> works = workService.findAllByName(q);
        return new ResponseEntity<>(works, HttpStatus.OK);
    }

    @GetMapping("listPosition")
    public ResponseEntity<Iterable<Position>> showAllPosition() {
        Iterable<Position> statusRecruitments = positionService.findAll();
        return new ResponseEntity<>(statusRecruitments, HttpStatus.OK);
    }

    @GetMapping("listWork")
    public ResponseEntity<Iterable<Work>> showAllWork() {
        Iterable<Work> works = workService.findAll();
        return new ResponseEntity<>(works, HttpStatus.OK);
    }
}
