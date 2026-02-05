package com.sms.demo.service;

import com.sms.demo.entity.Course;
import com.sms.demo.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public Course update(Long id, Course course) {
        Course existing = findById(id);
        if (existing != null) {
            existing.setName(course.getName());
            existing.setCode(course.getCode());
            existing.setCredits(course.getCredits());
            existing.setDepartment(course.getDepartment());
            existing.setTeacher(course.getTeacher());
            return courseRepository.save(existing);
        }
        return null;
    }

    public void delete(Long id) {
        courseRepository.deleteById(id);
    }
}
