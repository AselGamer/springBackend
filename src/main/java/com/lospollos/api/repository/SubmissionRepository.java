package com.lospollos.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lospollos.api.model.Assignment;
import com.lospollos.api.model.Submission;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Integer> {
    List<Submission> findByAssignment(Assignment assignment);
}
