package com.lospollos.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lospollos.api.dto.AssignmentDetailDto;
import com.lospollos.api.model.Assignment;
import com.lospollos.api.repository.AssignmentRespository;

@Service
public class AssignmentService {
    @Autowired
    private AssignmentRespository assignmentRespository;

    public AssignmentDetailDto convertToDTO(Assignment assignment) {
        AssignmentDetailDto assignmentDetailDto = new AssignmentDetailDto();
        assignmentDetailDto.setName(assignment.getName());
        assignmentDetailDto.setDescription(assignment.getDescription());
        assignmentDetailDto.setStartDate(assignment.getStartDate());
        assignmentDetailDto.setEndDate(assignment.getEndDate());
        assignmentDetailDto.setSubmission(assignment.getSubmissions().get(0));
        return assignmentDetailDto;
    }
}
