package com.lospollos.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lospollos.api.model.Assignment;
import com.lospollos.api.model.Course;

@Repository
public interface AssignmentRespository extends JpaRepository<Assignment, Integer>{
    List<Assignment> findByCourse(Course course);
}
