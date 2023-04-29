package com.ntgspiyggdrasil.yggdrasil.services;

import com.ntgspiyggdrasil.yggdrasil.models.Department;
import com.ntgspiyggdrasil.yggdrasil.payload.request.DepartmentRequest;
import com.ntgspiyggdrasil.yggdrasil.payload.response.DepartmentModel;
import com.ntgspiyggdrasil.yggdrasil.repository.DepartmentRepository;
import com.ntgspiyggdrasil.yggdrasil.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private FacultyRepository facultyRepository;
    public DepartmentModel loadDepartmentById(long departmentId) {
        return DepartmentModel.toModel(departmentRepository.findById(departmentId));
    }
    public List<Department> loadAllDepartment() {
        return departmentRepository.findAll();
    }
    public Department updateDepartment(DepartmentRequest department) {
        departmentRepository.updateDepartmentDataById(department.getId(), department.getShortName(), department.getName(), department.getDescription(), department.getFacultyId());
        return departmentRepository.findById(department.getId());
    }
    public Department createDepartment(DepartmentRequest department) {
        Department newDepartment = new Department();
        newDepartment.setDateCreate(new Date());
        newDepartment.setDateUpdate(new Date());
        newDepartment.setShortName(department.getShortName());
        newDepartment.setName(department.getName());
        newDepartment.setDescription(department.getDescription());
        newDepartment.setFaculty(facultyRepository.findById(department.getFacultyId()));
        newDepartment.setIsActive(true);
        return departmentRepository.save(newDepartment);
    }
}
