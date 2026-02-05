package com.sms.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.demo.entity.Department;
import com.sms.demo.service.DepartmentService;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<Department> getAll() {
        return departmentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getById(@PathVariable Long id) {
        Department department = departmentService.findById(id);
        if (department != null) {
            return ResponseEntity.ok(department);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Department create(@RequestBody Department department) {
        return departmentService.save(department);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> update(@PathVariable Long id, @RequestBody Department department) {
        Department updated = departmentService.update(id, department);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        departmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
