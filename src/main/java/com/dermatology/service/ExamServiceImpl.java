package com.dermatology.service;


import com.dermatology.model.Exam;
import com.dermatology.repository.ExamRepository;
import com.dermatology.service.interfaces.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Override
    public List<Exam> findAll() {
        return this.examRepository.findAll();
    }

    @Override
    public Exam find(Long id) {
        return this.examRepository.findById(id).orElse(null);
    }

    @Override
    public Exam save(Exam c) {
        return this.examRepository.save(c);
    }


}
