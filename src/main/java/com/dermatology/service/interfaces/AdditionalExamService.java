package com.dermatology.service.interfaces;

import com.dermatology.model.AdditionalExam;
import com.dermatology.repository.AdditionalExamRepository;
import com.dermatology.repository.DiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface AdditionalExamService {


    List<AdditionalExam> getAll();

    AdditionalExam getById(long id);

    void save(AdditionalExam additionalExam);
}
