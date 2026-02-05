package com.sms.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sms.demo.entity.Teacher;
import com.sms.demo.repository.TeacherRepository;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    public Teacher findById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher update(Long id, Teacher teacher) {
        Teacher existing = findById(id);
        if (existing != null) {
            existing.setName(teacher.getName());
            existing.setEmail(teacher.getEmail());
            existing.setPhone(teacher.getPhone());
            existing.setSpecialization(teacher.getSpecialization());
            existing.setDepartment(teacher.getDepartment());
            return teacherRepository.save(existing);
        }
        return null;
    }

    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }
}
