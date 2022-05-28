package com.dailycodebuffer.service;

import com.dailycodebuffer.entity.Department;
import com.dailycodebuffer.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department department =
                Department.builder()
                        .name("IT")
                        .address("Ahedabad")
                        .code("IT-06")
                        .id(1L)
                        .build();
        Mockito.when(departmentRepository.findDepartmentByNameIgnoreCase("IT")).thenReturn(department);
    }

    @Test
    @DisplayName("Get Data based on Valid Department Name")
    public void whenValidDepartmentName_thenDepartmentShouldBeFound(){
        String departmentName = "IT";
        Department found = departmentService.fetchDepartmentByName(departmentName);
        assertEquals(departmentName, found.getName());
    }
}