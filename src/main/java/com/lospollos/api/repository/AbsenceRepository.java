package com.lospollos.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lospollos.api.model.Absence;
import com.lospollos.api.model.Enrollment;
import com.lospollos.api.model.User;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Integer>{
    List<Absence> findByEnrollment(Enrollment enrollment);

    @Query("SELECT a FROM Absence a " +
           "WHERE a.enrollment.user.id = :user_id")
    Optional<List<Absence>> findByUser(@Param("user_id") int user_id);
}
