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

import com.lospollos.api.model.Course;
import com.lospollos.api.repository.CourseRepository;
import com.lospollos.api.service.ResponseService;

@RestController
@RequestMapping(path = "/api/courses")
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping(path = "/all")
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @PostMapping(path= "/add")
    public ResponseEntity<String> addCourse(@RequestBody Course course) {
        try {
            courseRepository.save(course);
            return ResponseService.toJsonResponse("Curso guardado correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseService.toJsonResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
