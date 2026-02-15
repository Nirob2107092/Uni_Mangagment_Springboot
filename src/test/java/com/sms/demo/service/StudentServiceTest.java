package com.sms.demo.service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sms.demo.entity.Student;
import com.sms.demo.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private Student testStudent;

    @BeforeEach
    void setUp() {
        testStudent = new Student();
        testStudent.setId(1L);
        testStudent.setRollNumber("STU001");
        testStudent.setName("John Doe");
        testStudent.setEmail("john@example.com");
        testStudent.setPhone("1234567890");
    }

    @Test
    void findById_shouldReturnStudent_whenStudentExists() {
        // Given
        when(studentRepository.findById(1L)).thenReturn(Optional.of(testStudent));

        // When
        Student result = studentService.findById(1L);

        // Then
        assertNotNull(result);
        assertEquals("STU001", result.getRollNumber());
        assertEquals("John Doe", result.getName());
        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    void findById_shouldReturnNull_whenStudentNotExists() {
        // Given
        when(studentRepository.findById(999L)).thenReturn(Optional.empty());

        // When
        Student result = studentService.findById(999L);

        // Then
        assertNull(result);
        verify(studentRepository, times(1)).findById(999L);
    }

    @Test
    void updateByStudent_shouldUpdateOnlyAllowedFields() {
        // Given
        Student updateData = new Student();
        updateData.setRollNumber("CHANGED"); // Student should NOT be able to change this
        updateData.setName("Jane Doe");
        updateData.setEmail("jane@example.com");
        updateData.setPhone("9876543210");

        when(studentRepository.findById(1L)).thenReturn(Optional.of(testStudent));
        when(studentRepository.save(any(Student.class))).thenReturn(testStudent);

        // When
        Student result = studentService.updateByStudent(1L, updateData);

        // Then
        assertNotNull(result);
        assertEquals("STU001", result.getRollNumber()); // Roll number should NOT change
        assertEquals("Jane Doe", result.getName());
        assertEquals("jane@example.com", result.getEmail());
        assertEquals("9876543210", result.getPhone());
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    void updateByTeacher_shouldUpdateAllFields() {
        // Given
        Student updateData = new Student();
        updateData.setRollNumber("STU999"); // Teacher CAN change roll number
        updateData.setName("Teacher Updated");
        updateData.setEmail("updated@example.com");

        when(studentRepository.findById(1L)).thenReturn(Optional.of(testStudent));
        when(studentRepository.save(any(Student.class))).thenReturn(testStudent);

        // When
        Student result = studentService.updateByTeacher(1L, updateData);

        // Then
        assertNotNull(result);
        assertEquals("STU999", result.getRollNumber()); // Roll number SHOULD change
        assertEquals("Teacher Updated", result.getName());
        verify(studentRepository, times(1)).save(any(Student.class));
    }
}
