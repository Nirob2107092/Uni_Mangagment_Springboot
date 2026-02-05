package com.sms.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sms.demo.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByRollNumber(String rollNumber);
}
