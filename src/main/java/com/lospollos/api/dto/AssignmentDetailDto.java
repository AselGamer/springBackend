package com.lospollos.api.dto;

import java.util.Date;

import com.lospollos.api.model.Submission;

public class AssignmentDetailDto {
    private String name;
    private String description;
    private Date start_date;
    private Date end_date;
    private Submission submission;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter and Setter for start_date
    public Date getStartDate() {
        return start_date;
    }

    public void setStartDate(Date start_date) {
        this.start_date = start_date;
    }

    // Getter and Setter for end_date
    public Date getEndDate() {
        return end_date;
    }

    public void setEndDate(Date end_date) {
        this.end_date = end_date;
    }

    // Getter and Setter for submission
    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }
}
