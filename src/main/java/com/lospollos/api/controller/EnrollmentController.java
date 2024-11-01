package com.lospollos.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lospollos.api.exception.RestFailureException;
import com.lospollos.api.model.Enrollment;
import com.lospollos.api.repository.CourseRepository;
import com.lospollos.api.repository.EnrollmentRepository;
import com.lospollos.api.repository.UserRepository;
import com.lospollos.api.request.EnrollmentRequest;
import com.lospollos.api.service.ResponseService;

@RestController
@RequestMapping(path = "/api/enrollments")
public class EnrollmentController {
    @Autowired
    EnrollmentRepository enrollmentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CourseRepository courseRepository;

    @GetMapping(path = "/all")
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    @PostMapping(path = "/add")
    public ResponseEntity<String> addEnrollment(@RequestBody EnrollmentRequest enrollmentRequest) {
        try {
            Enrollment enrollment = new Enrollment();
            enrollment.setUser(userRepository.findById(enrollmentRequest.user_id)
                    .orElseThrow(() -> new RestFailureException("Error usuario no encontrado")));
            enrollment.setCourse(courseRepository.findById(enrollmentRequest.course_id)
                    .orElseThrow(() -> new RestFailureException("Error curso no encontrado")));
            enrollmentRepository.save(enrollment);
            return ResponseService.toJsonResponse("Matricula guardada correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseService.toJsonResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
