package com.ntgspiyggdrasil.yggdrasil.controllers;

import com.ntgspiyggdrasil.yggdrasil.payload.request.DepartmentRequest;
import com.ntgspiyggdrasil.yggdrasil.payload.response.DepartmentModel;
import com.ntgspiyggdrasil.yggdrasil.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @GetMapping("/users/list/{departmentId}")
    public DepartmentModel getDepartmentUserList(@PathVariable("departmentId") long departmentId) {
        return departmentService.loadDepartmentById(departmentId);
    }
    @GetMapping("/all")
    public List<DepartmentModel> getAllDepartment() {
        return departmentService.loadAllDepartment().stream().map(DepartmentModel::toModel).collect(Collectors.toList());
    }
    @PostMapping("/update")
    public DepartmentModel updateDepartment(@RequestBody DepartmentRequest department) {
        return DepartmentModel.toModel(departmentService.updateDepartment(department));
    }
    @PostMapping("/create")
    public DepartmentModel createDepartment(@RequestBody DepartmentRequest department) {
        return DepartmentModel.toModel(departmentService.createDepartment(department));
    }
}
