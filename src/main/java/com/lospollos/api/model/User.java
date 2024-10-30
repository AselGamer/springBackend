package com.lospollos.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "firstname", length = 100, nullable = false)
    private String firstname;

    @Column(name = "lastname", length = 100, nullable = true)
    private String lastname;

    @Column(name = "is_teacher", length = 100, nullable = false)
    private boolean is_teacher;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    public User() {}

    public User(long id, String firstname, String lastname, boolean is_teacher, String password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.is_teacher = is_teacher;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isTeacher() {
        return is_teacher;
    }

    public void setTeacher(boolean is_teacher) {
        this.is_teacher = is_teacher;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}