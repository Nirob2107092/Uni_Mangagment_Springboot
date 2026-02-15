package com.sms.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import org.springframework.http.ResponseEntity;

import com.sms.demo.entity.Department;
import com.sms.demo.service.DepartmentService;

@ExtendWith(MockitoExtension.class)
class DepartmentControllerTest {

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    private Department testDepartment;

    @BeforeEach
    void setUp() {
        testDepartment = new Department();
        testDepartment.setId(1L);
        testDepartment.setName("Computer Science and Engineering");
        testDepartment.setDescription("Department of CSE");
    }

    @Test
    void getById_shouldReturnDepartment_whenDepartmentExists() {
        // Given
        when(departmentService.findById(1L)).thenReturn(testDepartment);

        // When
        ResponseEntity<Department> response = departmentController.getById(1L);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("Computer Science and Engineering", response.getBody().getName());
        assertEquals("Department of CSE", response.getBody().getDescription());
        verify(departmentService, times(1)).findById(1L);
    }

    @Test
    void create_shouldCreateDepartment() {
        // Given
        when(departmentService.save(any(Department.class))).thenReturn(testDepartment);

        // When
        Department result = departmentController.create(testDepartment);

        // Then
        assertNotNull(result);
        assertEquals("Computer Science and Engineering", result.getName());
        assertEquals("Department of CSE", result.getDescription());
        verify(departmentService, times(1)).save(any(Department.class));
    }
}
