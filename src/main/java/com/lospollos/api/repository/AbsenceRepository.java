package com.lospollos.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lospollos.api.model.Absence;
import com.lospollos.api.model.Enrollment;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Integer>{
    List<Absence> findByEnrollment(Enrollment enrollment);
}
