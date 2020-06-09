package com.dermatology.service;

import com.dermatology.model.AdditionalExam;
import com.dermatology.repository.AdditionalExamRepository;
import com.dermatology.service.interfaces.AdditionalExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdditionalExamServiceImpl implements AdditionalExamService {
    @Autowired
    private AdditionalExamRepository additionalExamRepository;

    @Override
    public void save(AdditionalExam exam) {
        this.additionalExamRepository.save(exam);
    }

    @Override
    public AdditionalExam find(Long id) {
        return this.additionalExamRepository.findById(id).orElse(null);
    }

    @Override
    public AdditionalExam findByName(String name) {
        return this.additionalExamRepository.findByName(name);
    }

    @Override
    public List<String> findDistinct() {
        return this.additionalExamRepository.findDistinct();
    }
}
