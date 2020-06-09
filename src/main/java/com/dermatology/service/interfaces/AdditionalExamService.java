package com.dermatology.service.interfaces;

import com.dermatology.model.AdditionalExam;

import java.util.List;

public interface AdditionalExamService {
    void save(AdditionalExam exam);

    AdditionalExam find(Long id);

    AdditionalExam findByName(String name);

    List<String> findDistinct();
}
