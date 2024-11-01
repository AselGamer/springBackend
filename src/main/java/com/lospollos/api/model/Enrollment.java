package com.lospollos.api.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "enrollments")
public class Enrollment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id", nullable = false)
	private User user;

    @ManyToOne
    @JoinColumn(name="course_id", referencedColumnName = "id", nullable = false)
	private Course course;

    @Column(name = "grade", nullable = true)
	private int grade;

    @Column(name = "grade_date", nullable = true)
	private Date grade_date;

	public Enrollment() {}

	public Enrollment(User user, Course course) {
		this.user = user;
		this.course = course;
	}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

	public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Date getGradeDate() {
        return grade_date;
    }

    public void setGradeDate(Date grade_date) {
        this.grade_date = grade_date;
    }
}
