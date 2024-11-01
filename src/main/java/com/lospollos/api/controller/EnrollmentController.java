package com.lospollos.api.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lospollos.api.exception.RestFailureException;
import com.lospollos.api.model.Absence;
import com.lospollos.api.model.Enrollment;
import com.lospollos.api.repository.AbsenceRepository;
import com.lospollos.api.repository.CourseRepository;
import com.lospollos.api.repository.EnrollmentRepository;
import com.lospollos.api.repository.UserRepository;
import com.lospollos.api.service.ResponseService;

@RestController
@RequestMapping(path = "/api/enrollments")
public class EnrollmentController {
    @Autowired
    EnrollmentRepository enrollmentRepository;
    @Autowired
    AbsenceRepository absenceRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CourseRepository courseRepository;

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    @GetMapping(path = "/all")
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    @PostMapping(path = "/add")
    public ResponseEntity<String> addEnrollment(@RequestBody Map<String, String> enrollmentRequest) {
        try {

            int user_id = Integer.parseInt(enrollmentRequest.get("user_id"));
            int course_id = Integer.parseInt(enrollmentRequest.get("course_id"));

            Enrollment enrollment = new Enrollment();
            enrollment.setUser(userRepository.findById(user_id)
                    .orElseThrow(() -> new RestFailureException("Error usuario no encontrado")));
            enrollment.setCourse(courseRepository.findById(course_id)
                    .orElseThrow(() -> new RestFailureException("Error curso no encontrado")));
            enrollmentRepository.save(enrollment);
            return ResponseService.toJsonResponse("Matricula guardada correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseService.toJsonResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/absences/{enrollment_id}")
    public List<Absence> getAbsences(@PathVariable int enrollment_id) {
        return absenceRepository.findByEnrollment(enrollmentRepository.findById(enrollment_id)
                .orElseThrow(() -> new RestFailureException("Error marticula no encontrada")));
    }

    @PostMapping(path = "/absences/add")
    public ResponseEntity<String> addAbsence(@RequestBody Map<String, String> absenceRequest) {
        try {
            int enrollment_id = Integer.parseInt(absenceRequest.get("enrollment_id"));

            Absence absence = new Absence();
            absence.setEnrollment(enrollmentRepository.findById(enrollment_id)
                    .orElseThrow(() -> new RestFailureException("Error matricula no encontrada")));

            if (absenceRequest.get("date") != null) {
                Date date = formatter.parse(absenceRequest.get("date"));
                absence.setDate(date);
            }
            absenceRepository.save(absence);
            return ResponseService.toJsonResponse("Falta guardada correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseService.toJsonResponse(e.getMessage(), "error", HttpStatus.OK);
        }
    }
}
