package com.lospollos.api.controller;

import java.util.List;

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

import com.lospollos.api.dto.AssignmentDetailDto;
import com.lospollos.api.dto.GradeDto;
import com.lospollos.api.exception.RestFailureException;
import com.lospollos.api.model.Assignment;
import com.lospollos.api.model.Submission;
import com.lospollos.api.model.User;
import com.lospollos.api.repository.AssignmentRespository;
import com.lospollos.api.repository.SubmissionRepository;
import com.lospollos.api.repository.UserRepository;
import com.lospollos.api.security.JwtUtil;
import com.lospollos.api.service.AssignmentService;
import com.lospollos.api.service.ResponseService;

@RestController
@RequestMapping(path = "/api/assignments")
public class AssignmentController {
    @Autowired
    private AssignmentRespository assignmentRespository;
    @Autowired
    private SubmissionRepository submissionRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AssignmentService assignmentService;
    
    
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping(path = "/all")
    public List<Assignment> getAllUsers() {
        return assignmentRespository.findAll();
    }

    @GetMapping(path = "/course/{id}")
    public List<Assignment> getByCourseId(@PathVariable int id) {
        return assignmentRespository.findByCourseNotHidden(id)
                .orElseThrow(() -> new RestFailureException("Error tareas no encontrada"));
    }

    @GetMapping(path = "/{id}")
    public Assignment getAssignmentById(@PathVariable int id) {
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
    public ResponseEntity<String> addAssignmnentSubmission(@RequestBody Submission submission, @RequestHeader("Authorization") String authorizationHeader) {
        try {
            String token = authorizationHeader.startsWith("Bearer ") ? authorizationHeader.substring(7) : authorizationHeader;
            User user = userRepository.findByEmail(jwtUtil.getEmailFromToken(token));
            submission.setUser(user);
            submissionRepository.save(submission);
            return ResponseService.toJsonResponse("Entrega guardada correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseService.toJsonResponse(e.getMessage(), "error", HttpStatus.OK);
        }
    }

    @PostMapping(path = "/submissions/grade/{id}")
    public ResponseEntity<String> gradeSubmission(@PathVariable int id, @RequestBody GradeDto gradeDto) {
        try {
            Submission submission = submissionRepository.findById(id)
                .orElseThrow(() -> new RestFailureException("Error entrega no encontrada"));
            submission.setGrade(gradeDto.getGrade());
            submission.setGradedDate(gradeDto.getGradedDate());
            submissionRepository.save(submission);
            return ResponseService.toJsonResponse("Nota guardada correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseService.toJsonResponse(e.getMessage(), "error", HttpStatus.OK);
        }
    }

    @GetMapping(path = "/user/submissions/{id}")
    public AssignmentDetailDto getAssignmentDetail(@PathVariable int id, @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.startsWith("Bearer ") ? authorizationHeader.substring(7) : authorizationHeader;
        User user = userRepository.findByEmail(jwtUtil.getEmailFromToken(token));
        return assignmentService.convertToDTO(assignmentRespository.findOneWithSubmission(id, (int) user.getId())
        .orElseThrow(() -> new RestFailureException("Error tarea o entrega no encontrada")));
    }

    @GetMapping(path = "/user/submissions")
    public List<Assignment> getSubmitted(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.startsWith("Bearer ") ? authorizationHeader.substring(7) : authorizationHeader;
        User user = userRepository.findByEmail(jwtUtil.getEmailFromToken(token));
        return assignmentRespository.findBySubmitted((int) user.getId())
        .orElseThrow(() -> new RestFailureException("Error tarea o entrega no encontrada"));
    }

    @GetMapping(path = "/submissions/{assignment_id}")
    public List<Submission> getSubmissionsByAssignment(@PathVariable int assignment_id) {
        System.out.println(assignment_id);
        return submissionRepository.findByAssignment(assignmentRespository.findById(assignment_id)
                .orElseThrow(() -> new RestFailureException("Error tarea no encontrada")));
    }

}
