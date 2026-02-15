package com.sms.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.sms.demo.entity.Student;

@SpringBootTest
@Transactional
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void findByRollNumber_shouldReturnStudent_whenRollNumberExists() {
        // Given
        Student student = new Student();
        student.setRollNumber("TEST001");
        student.setName("Test Student");
        student.setEmail("test@example.com");
        studentRepository.save(student);

        // When
        Student found = studentRepository.findByRollNumber("TEST001");

        // Then
        assertNotNull(found);
        assertEquals("TEST001", found.getRollNumber());
        assertEquals("Test Student", found.getName());
    }

    @Test
    void save_shouldPersistStudent() {
        // Given
        Student student = new Student();
        student.setRollNumber("SAVE001");
        student.setName("Save Test");
        student.setEmail("save@test.com");

        // When
        Student saved = studentRepository.save(student);

        // Then
        assertNotNull(saved.getId());
        assertEquals("SAVE001", saved.getRollNumber());
        assertEquals("Save Test", saved.getName());
    }
}
