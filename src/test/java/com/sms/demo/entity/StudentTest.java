package com.sms.demo.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class StudentTest {

    @Test
    void constructor_shouldCreateStudentWithAllFields() {
        // When
        Student student = new Student(1L, "STU001", "John Doe", "john@example.com", "1234567890");

        // Then
        assertEquals(1L, student.getId());
        assertEquals("STU001", student.getRollNumber());
        assertEquals("John Doe", student.getName());
        assertEquals("john@example.com", student.getEmail());
        assertEquals("1234567890", student.getPhone());
    }

    @Test
    void settersAndGetters_shouldWorkCorrectly() {
        // Given
        Student student = new Student();

        // When
        student.setId(2L);
        student.setRollNumber("STU002");
        student.setName("Jane Smith");
        student.setEmail("jane@test.com");
        student.setPhone("9876543210");

        // Then
        assertEquals(2L, student.getId());
        assertEquals("STU002", student.getRollNumber());
        assertEquals("Jane Smith", student.getName());
        assertEquals("jane@test.com", student.getEmail());
        assertEquals("9876543210", student.getPhone());
    }

    @Test
    void defaultConstructor_shouldCreateEmptyStudent() {
        // When
        Student student = new Student();

        // Then
        assertNotNull(student);
        assertNull(student.getId());
        assertNull(student.getRollNumber());
        assertNull(student.getName());
    }
}
