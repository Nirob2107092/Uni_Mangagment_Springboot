package com.sms.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String email;

    private String phone;

    private String specialization;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;

    @JsonIgnore
    @OneToOne(mappedBy = "teacher")
    private User user;

    // Constructors
    public Teacher() {}

    public Teacher(Long id, String name, String email, String phone, String specialization) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.specialization = specialization;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    public List<Course> getCourses() { return courses; }
    public void setCourses(List<Course> courses) { this.courses = courses; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
