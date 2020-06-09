package com.dermatology.repository;

import com.dermatology.model.AdditionalExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdditionalExamRepository extends JpaRepository<AdditionalExam, Long> {
    AdditionalExam findByName(String name);

    @Query(value = "select DISTINCT m.name from AdditionalExam m ")
    List<String> findDistinct();
}
