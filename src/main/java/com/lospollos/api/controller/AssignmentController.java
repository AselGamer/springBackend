package com.lospollos.api.controller;

import java.util.List;

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
import com.lospollos.api.model.Assignment;
import com.lospollos.api.model.Submission;
import com.lospollos.api.repository.AssignmentRespository;
import com.lospollos.api.repository.SubmissionRepository;
import com.lospollos.api.service.ResponseService;

@RestController
@RequestMapping(path = "/api/assignments")
public class AssignmentController {
    @Autowired
    private AssignmentRespository assignmentRespository;
    @Autowired
    private SubmissionRepository submissionRepository;

    @GetMapping(path = "/all")
    public List<Assignment> getAllUsers() {
        return assignmentRespository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Assignment getUserById(@PathVariable int id) {
        return assignmentRespository.findById(id)
                .orElseThrow(() -> new RestFailureException("Error tarea no encontrada"));
    }

    @PostMapping(path = "/add")
    public ResponseEntity<String> addAssignmnent(@RequestBody Assignment assignment) {
        try {
            assignmentRespository.save(assignment);
            return ResponseService.toJsonResponse("Tarea guardada correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseService.toJsonResponse(e.getMessage(), "error", HttpStatus.OK);
        }
    }

    @PostMapping(path = "/submissions/add")
    public ResponseEntity<String> addAssignmnent(@RequestBody Submission submission) {
        try {
            submissionRepository.save(submission);
            return ResponseService.toJsonResponse("Entrega guardada correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseService.toJsonResponse(e.getMessage(), "error", HttpStatus.OK);
        }
    }

    @GetMapping(path = "/submissions/{assignment_id}")
    public List<Submission> getSubmissionsByAssignment(@PathVariable int assignment_id) {
        System.out.println(assignment_id);
        return submissionRepository.findByAssignment(assignmentRespository.findById(assignment_id)
                .orElseThrow(() -> new RestFailureException("Error tarea no encontrada")));
    }

}
