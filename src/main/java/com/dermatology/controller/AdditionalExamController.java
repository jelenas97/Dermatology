package com.dermatology.controller;

import com.dermatology.model.AdditionalExam;
import com.dermatology.service.interfaces.AdditionalExamService;
import com.dermatology.service.interfaces.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@RequestMapping(path = "additionalExam")
public class AdditionalExamController {

    @Autowired
    private AdditionalExamService additionalExamService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAdditionalExams() {
        List<AdditionalExam> additionalExams= this.additionalExamService.getAll();
        return new ResponseEntity(additionalExams, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<?> getAdditionalExam(@PathVariable("id") String id) {
        AdditionalExam additionalExam= this.additionalExamService.getById(Long.parseLong(id));
        return new ResponseEntity(additionalExam, HttpStatus.OK);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> addAdditionalExam(@RequestBody AdditionalExam additionalExam) {
        try {
            this.additionalExamService.save(additionalExam);
            return new ResponseEntity(HttpStatus.OK);
        } catch(Exception e)
        {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
