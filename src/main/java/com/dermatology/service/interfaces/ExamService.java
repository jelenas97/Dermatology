package com.dermatology.service.interfaces;

import com.dermatology.model.Exam;
import java.util.List;

public interface ExamService {
    List<Exam> findAll();

    Exam find(Long id);

    Exam save(Exam c);
}
