package com.dermatology.repository;

import com.dermatology.model.AdditionalExam;
import com.dermatology.model.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalExamRepository extends JpaRepository<AdditionalExam, Long> {
}
