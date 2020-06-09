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
    public List<AdditionalExam> getAll() {
        return this.additionalExamRepository.findAll();
    }

    @Override
    public AdditionalExam getById(long id) {
        return this.additionalExamRepository.findById(id).orElse(null);
    }

    @Override
    public void save(AdditionalExam additionalExam) {
        this.additionalExamRepository.save(additionalExam);
    }
}
