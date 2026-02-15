package com.sms.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.sms.demo.entity.Teacher;
import com.sms.demo.service.TeacherService;

@ExtendWith(MockitoExtension.class)
class TeacherControllerTest {

    @Mock
    private TeacherService teacherService;

    @InjectMocks
    private TeacherController teacherController;

    private Teacher testTeacher;

    @BeforeEach
    void setUp() {
        testTeacher = new Teacher();
        testTeacher.setId(1L);
        testTeacher.setName("Dr. Smith");
        testTeacher.setEmail("smith@university.com");
        testTeacher.setPhone("1234567890");
        testTeacher.setSpecialization("Computer Science");
    }

    @Test
    void getById_shouldReturnTeacher_whenTeacherExists() {
        // Given
        when(teacherService.findById(1L)).thenReturn(testTeacher);

        // When
        ResponseEntity<Teacher> response = teacherController.getById(1L);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("Dr. Smith", response.getBody().getName());
        assertEquals("smith@university.com", response.getBody().getEmail());
        verify(teacherService, times(1)).findById(1L);
    }

    @Test
    void update_shouldUpdateTeacher_whenTeacherExists() {
        // Given
        Teacher updatedTeacher = new Teacher();
        updatedTeacher.setId(1L);
        updatedTeacher.setName("Dr. John Smith");
        updatedTeacher.setEmail("john.smith@university.com");
        updatedTeacher.setSpecialization("Computer Science");

        when(teacherService.update(eq(1L), any(Teacher.class))).thenReturn(updatedTeacher);

        // When
        ResponseEntity<Teacher> response = teacherController.update(1L, updatedTeacher);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("Dr. John Smith", response.getBody().getName());
        assertEquals("john.smith@university.com", response.getBody().getEmail());
        verify(teacherService, times(1)).update(eq(1L), any(Teacher.class));
    }
}
