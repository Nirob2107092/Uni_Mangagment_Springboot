package com.sms.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.demo.entity.Course;
import com.sms.demo.service.CourseService;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getAll() {
        return courseService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getById(@PathVariable Long id) {
        Course course = courseService.findById(id);
        if (course != null) {
            return ResponseEntity.ok(course);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasRole('TEACHER')")
    public Course create(@RequestBody Course course) {
        return courseService.save(course);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody Course course) {
        Course updated = courseService.update(id, course);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    // Only teachers can delete courses
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
