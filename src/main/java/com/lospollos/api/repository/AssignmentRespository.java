package com.lospollos.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lospollos.api.dto.AssignmentDetailDto;
import com.lospollos.api.model.Assignment;

@Repository
public interface AssignmentRespository extends JpaRepository<Assignment, Integer>{
    @Query("SELECT a FROM Assignment a " +
           "WHERE a.hidden = false " +
           "AND a.course.id = :course_id")
    Optional<List<Assignment>> findByCourseNotHidden(@Param("course_id") int course_id);
    @Query("SELECT a FROM Assignment a " +
           "WHERE a.course.id = :course_id")
    Optional<List<Assignment>> findByCourse(@Param("course_id") int course_id);
    @Query("SELECT a FROM Assignment a " +
           "JOIN Submission s ON a.id = s.assignment.id " +
           "WHERE a.id = :id " + 
           "AND s.user.id = :user_id")
    Optional<Assignment> findOneWithSubmission(@Param("id") int id, @Param("user_id") int user_id);
}
