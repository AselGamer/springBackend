package com.lospollos.api.model;

import java.util.Date;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "submissions")
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "assignment_id", referencedColumnName = "id", nullable = false)
    private Assignment assignment;

    @Column(name = "send_date", nullable = false)
    @ColumnDefault("CURRENT_DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date send_date = new Date();

    @Column(name = "grade", nullable = true)
    private int grade;

    @Column(name = "graded_date", nullable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date graded_date;

    @Column(name = "attachtment", length = 255, nullable = true)
    private String attachment;

    public Submission() {}

    public Submission(int grade, Date graded_date, String attachment) {
        this.grade = grade;
        this.graded_date = graded_date;
        this.attachment = attachment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    @JsonIgnore
    public void setUser(User user) {
        this.user = user;
    }

    @JsonSetter("user_id")
    public void setUserById(int userId) {
        User user = new User();
        user.setId(userId);
        this.user = user;
    }

    @JsonIgnore
    public Assignment getAssignment() {
        return assignment;
    }

    @JsonIgnore
    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    @JsonSetter("assignment_id")
    public void setAssignmentById(int assignmentId) {
        Assignment assignment = new Assignment();
        assignment.setId(assignmentId);
        this.assignment = assignment;
    }

    public Date getSendDate() {
        return send_date;
    }

    public void setSendDate(Date send_date) {
        this.send_date = send_date;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Date getGradedDate() {
        return graded_date;
    }

    public void setGradedDate(Date graded_date) {
        this.graded_date = graded_date;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachtment) {
        this.attachment = attachtment;
    }
}
