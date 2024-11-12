package com.lospollos.api.dto;

import java.util.Date;

import org.hibernate.annotations.ColumnDefault;

public class GradeDto {
    private int grade;
    @ColumnDefault("CURRENT_DATE")
    private Date graded_date = new Date();

    public GradeDto(int grade, Date graded_date) {
        this.grade = grade;
        this.graded_date = graded_date != null ? graded_date : new Date();
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
}
