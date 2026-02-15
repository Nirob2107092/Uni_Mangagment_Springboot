package com.sms.demo.controller;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.sms.demo.entity.Student;
import com.sms.demo.service.StudentService;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

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
    void getAll_shouldReturnAllStudents() {
        // Given
        List<Student> students = Arrays.asList(testStudent);
        when(studentService.findAll()).thenReturn(students);

        // When
        List<Student> result = studentController.getAll();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("STU001", result.get(0).getRollNumber());
        assertEquals("John Doe", result.get(0).getName());
        verify(studentService, times(1)).findAll();
    }

    @Test
    void getById_shouldReturnStudent_whenStudentExists() {
        // Given
        when(studentService.findById(1L)).thenReturn(testStudent);

        // When
        ResponseEntity<Student> response = studentController.getById(1L);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("STU001", response.getBody().getRollNumber());
        verify(studentService, times(1)).findById(1L);
    }

    @Test
    void getById_shouldReturn404_whenStudentNotFound() {
        // Given
        when(studentService.findById(999L)).thenReturn(null);

        // When
        ResponseEntity<Student> response = studentController.getById(999L);

        // Then
        assertNotNull(response);
        assertEquals(404, response.getStatusCode().value());
        assertNull(response.getBody());
        verify(studentService, times(1)).findById(999L);
    }

    @Test
    void create_shouldCreateStudent() {
        // Given
        when(studentService.save(any(Student.class))).thenReturn(testStudent);

        // When
        Student result = studentController.create(testStudent);

        // Then
        assertNotNull(result);
        assertEquals("STU001", result.getRollNumber());
        assertEquals("John Doe", result.getName());
        verify(studentService, times(1)).save(any(Student.class));
    }

    @Test
    void updateSelf_shouldUpdateStudent_whenStudentExists() {
        // Given
        Student updatedStudent = new Student();
        updatedStudent.setId(1L);
        updatedStudent.setRollNumber("STU001");
        updatedStudent.setName("Jane Doe");
        updatedStudent.setEmail("jane@example.com");
        
        when(studentService.updateByStudent(eq(1L), any(Student.class))).thenReturn(updatedStudent);

        // When
        ResponseEntity<Student> response = studentController.updateSelf(1L, updatedStudent);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("Jane Doe", response.getBody().getName());
        verify(studentService, times(1)).updateByStudent(eq(1L), any(Student.class));
    }

    @Test
    void updateSelf_shouldReturn404_whenStudentNotFound() {
        // Given
        when(studentService.updateByStudent(eq(999L), any(Student.class))).thenReturn(null);

        // When
        ResponseEntity<Student> response = studentController.updateSelf(999L, testStudent);

        // Then
        assertNotNull(response);
        assertEquals(404, response.getStatusCode().value());
        assertNull(response.getBody());
    }

    @Test
    void update_shouldUpdateStudent_whenStudentExists() {
        // Given
        Student updatedStudent = new Student();
        updatedStudent.setId(1L);
        updatedStudent.setRollNumber("STU002");
        updatedStudent.setName("John Smith");
        
        when(studentService.updateByTeacher(eq(1L), any(Student.class))).thenReturn(updatedStudent);

        // When
        ResponseEntity<Student> response = studentController.update(1L, updatedStudent);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("STU002", response.getBody().getRollNumber());
        assertEquals("John Smith", response.getBody().getName());
        verify(studentService, times(1)).updateByTeacher(eq(1L), any(Student.class));
    }

    @Test
    void update_shouldReturn404_whenStudentNotFound() {
        // Given
        when(studentService.updateByTeacher(eq(999L), any(Student.class))).thenReturn(null);

        // When
        ResponseEntity<Student> response = studentController.update(999L, testStudent);

        // Then
        assertNotNull(response);
        assertEquals(404, response.getStatusCode().value());
        assertNull(response.getBody());
    }

    @Test
    void delete_shouldDeleteStudent() {
        // Given
        doNothing().when(studentService).delete(1L);

        // When
        ResponseEntity<Void> response = studentController.delete(1L);

        // Then
        assertNotNull(response);
        assertEquals(204, response.getStatusCode().value());
        assertNull(response.getBody());
        verify(studentService, times(1)).delete(1L);
    }
}