package com.lospollos.api.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lospollos.api.exception.RestFailureException;
import com.lospollos.api.model.Absence;
import com.lospollos.api.model.Enrollment;
import com.lospollos.api.model.User;
import com.lospollos.api.repository.AbsenceRepository;
import com.lospollos.api.repository.CourseRepository;
import com.lospollos.api.repository.EnrollmentRepository;
import com.lospollos.api.repository.UserRepository;
import com.lospollos.api.security.JwtUtil;
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

    @Autowired
    private JwtUtil jwtUtil;

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    @GetMapping(path = "/all")
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    @PostMapping(path = "/add")
    public ResponseEntity<String> addEnrollment(@RequestBody Enrollment enrollment) {
        try {
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

    @GetMapping(path = "/absences/mine")
    public List<Absence> getMyAbsences(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.startsWith("Bearer ") ? authorizationHeader.substring(7) : authorizationHeader;
        User user = userRepository.findByEmail(jwtUtil.getEmailFromToken(token));

        return absenceRepository.findByUser((int) user.getId())
            .orElseThrow(() -> new RestFailureException("Usuario no encontrado"));
    }

    @PostMapping(path = "/absences/add")
    public ResponseEntity<String> addAbsence(@RequestBody Absence absence) {
        try {
            absenceRepository.save(absence);
            return ResponseService.toJsonResponse("Falta guardada correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseService.toJsonResponse(e.getMessage(), "error", HttpStatus.OK);
        }
    }
}
