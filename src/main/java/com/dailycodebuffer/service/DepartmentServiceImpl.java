package com.dailycodebuffer.service;

import com.dailycodebuffer.entity.Department;
import com.dailycodebuffer.error.DepartmentNotFoundException;
import com.dailycodebuffer.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if(!department.isPresent()) {
            throw new DepartmentNotFoundException("Department Not Available");
        }
        return department.get();
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) {
        Department depDB = departmentRepository.findById(departmentId).get();

        if(Objects.nonNull(department.getName()) &&
                !"".equalsIgnoreCase(department.getName())){
            depDB.setName(department.getName());
        }

        if(Objects.nonNull(department.getCode()) &&
                !"".equalsIgnoreCase(department.getCode())){
            depDB.setCode(department.getCode());
        }

        if(Objects.nonNull(department.getAddress()) &&
                !"".equalsIgnoreCase(department.getAddress())){
            depDB.setAddress(department.getAddress());
        }

        return departmentRepository.save(depDB);
    }

    @Override
    public Department fetchDepartmentByName(String departmentName) {
        return departmentRepository.findDepartmentByNameIgnoreCase(departmentName);
    }
}
