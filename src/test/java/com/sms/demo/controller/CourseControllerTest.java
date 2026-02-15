package com.sms.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.sms.demo.entity.Course;
import com.sms.demo.service.CourseService;

@ExtendWith(MockitoExtension.class)
class CourseControllerTest {

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    private Course testCourse;

    @BeforeEach
    void setUp() {
        testCourse = new Course();
        testCourse.setId(1L);
        testCourse.setCode("CS101");
        testCourse.setName("Introduction to Programming");
        testCourse.setCredits(3);
    }

    @Test
    void getById_shouldReturnCourse_whenCourseExists() {
        // Given
        when(courseService.findById(1L)).thenReturn(testCourse);

        // When
        ResponseEntity<Course> response = courseController.getById(1L);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("CS101", response.getBody().getCode());
        assertEquals("Introduction to Programming", response.getBody().getName());
        verify(courseService, times(1)).findById(1L);
    }

    @Test
    void getById_shouldReturn404_whenCourseNotFound() {
        // Given
        when(courseService.findById(999L)).thenReturn(null);

        // When
        ResponseEntity<Course> response = courseController.getById(999L);

        // Then
        assertNotNull(response);
        assertEquals(404, response.getStatusCode().value());
        assertNull(response.getBody());
        verify(courseService, times(1)).findById(999L);
    }
}
