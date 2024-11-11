package com.lospollos.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lospollos.api.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query("SELECT c FROM Course c " +
           "JOIN Enrollment e ON e.course.id = c.id " +
           "JOIN User u ON e.user.id = u.id " +
           "WHERE u.email = :email")
    List<Course> findCoursesByUserEmail(@Param("email") String email);
}
