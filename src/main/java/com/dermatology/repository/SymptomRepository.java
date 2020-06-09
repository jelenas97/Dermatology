package com.dermatology.repository;

import com.dermatology.model.Patient;
import com.dermatology.model.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SymptomRepository extends JpaRepository<Symptom, Long> {
}
