package com.lospollos.api.model;

import java.util.Date;
import java.util.List;

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

    @OneToMany(mappedBy = "enrollment")
    private List<Absence> absences;

    @Column(name = "grade", nullable = true)
	private int grade;

    @Column(name = "grade_date", nullable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
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

    @JsonSetter("user_id")
    public void setUserById(Long userId) {
        User user = new User();
        user.setId(userId);
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @JsonSetter("course_id")
    public void setCourseById(Long courseId) {
        Course course = new Course();
        course.setId(courseId);
        this.course = course;
    }

    @JsonIgnore
    public List<Absence> getAbsences() {
        return absences;
    }

    @JsonIgnore
    public void setAbsences(List<Absence> absences) {
        this.absences = absences;
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
