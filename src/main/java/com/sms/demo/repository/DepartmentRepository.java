package com.sms.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sms.demo.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
