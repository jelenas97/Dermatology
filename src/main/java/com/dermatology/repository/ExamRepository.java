package com.dermatology.repository;

import com.dermatology.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

    @Query("SELECT e from Exam e where e.patient.id = ?1 ")
    List<Exam> getExamForPatient(Long patientId);

}
