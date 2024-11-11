package com.lospollos.api.model;

import java.util.Date;
import java.util.List;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "assignments")
public class Assignment {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "description", length = 100, nullable = false)
    private String description;

    @Column(name = "hidden", nullable = false)
    private boolean hidden = true;

    @Column(name = "start_date", nullable = false)
    @ColumnDefault("CURRENT_DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date start_date = new Date();

    @Column(name = "end_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date end_date;

    @ManyToOne
    @JoinColumn(name="course_id", referencedColumnName = "id", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private Course course;

    @OneToMany(mappedBy = "assignment")
    private List<Submission> submissions;

    public Assignment() {}

    public Assignment(String name, String description, boolean hidden, Date end_date) {
        this.name = name;
        this.description = description;
        this.hidden = hidden;
        this.end_date = end_date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    @JsonIgnore
    public Date getStartDate() {
        return start_date;
    }

    public void setStartDate(Date start_date) {
        this.start_date = start_date;
    }

    @JsonIgnore
    public Date getEndDate() {
        return end_date;
    }

    public void setEndDate(Date end_date) {
        this.end_date = end_date;
    }

    @JsonIgnore
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @JsonIgnore
    public List<Submission> getSubmissions() {
        return submissions;
    }

    @JsonSetter("course_id")
    public void setCourseById(Long courseId) {
        Course course = new Course();
        course.setId(courseId);
        this.course = course;
    }

    public String toString() {
        return name + 
        " " + description + 
        " " + end_date.toString() + 
        " " + start_date.toString();
    }
}
