package com.ntgspiyggdrasil.yggdrasil.controllers;

import com.ntgspiyggdrasil.yggdrasil.models.Department;
import com.ntgspiyggdrasil.yggdrasil.models.Faculty;
import com.ntgspiyggdrasil.yggdrasil.payload.request.DepartmentRequest;
import com.ntgspiyggdrasil.yggdrasil.payload.request.SearchRequest;
import com.ntgspiyggdrasil.yggdrasil.payload.response.DepartmentModel;
import com.ntgspiyggdrasil.yggdrasil.payload.response.DepartmentPageInfo;
import com.ntgspiyggdrasil.yggdrasil.payload.response.FacultyNoUserModel;
import com.ntgspiyggdrasil.yggdrasil.payload.response.FacultyPageInfo;
import com.ntgspiyggdrasil.yggdrasil.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
    @PostMapping("/search/all")
    public DepartmentPageInfo getAllDepartmentSearch(@RequestBody SearchRequest request) {
        Page<Department> page;
        DepartmentPageInfo pageInfo = new DepartmentPageInfo();

        Date minDate;
        Date maxDate;
        LocalDate date;

        if (request.getMinDate() == "") {
            minDate = new Date(0);
        } else {
            date = LocalDate.parse(request.getMinDate());
            minDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        if (request.getMaxDate() == "") {
            maxDate = new Date();
        } else {
            date = LocalDate.parse(request.getMaxDate());
            maxDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        page = departmentService.loadAllDepartmentSearch(request.getSortField(), request.getSortDir(), request.getCurrentPage(), request.getParameter(), request.getFacultyName(), request.getIsActive(), minDate, maxDate);

        pageInfo.setCurrentPage(request.getCurrentPage());
        pageInfo.setTotalItems(page.getTotalElements());
        pageInfo.setTotalPages(page.getTotalPages());
        pageInfo.setDepartmentList(page.getContent().stream().map(DepartmentModel::toModel).collect(Collectors.toList()));
        pageInfo.setSortField(request.getSortField());
        pageInfo.setSortDir(request.getSortDir());
        pageInfo.setSearchParameter(request.getParameter());

        return pageInfo;
    }
    @PostMapping("/update")
    public DepartmentModel updateDepartment(@RequestBody DepartmentRequest department) {
        return DepartmentModel.toModel(departmentService.updateDepartment(department));
    }
    @PostMapping("/create")
    public DepartmentModel createDepartment(@RequestBody DepartmentRequest department) {
        return DepartmentModel.toModel(departmentService.createDepartment(department));
    }

    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN') or hasRole('MODERATOR')")
    @GetMapping("/active/all")
    public List<DepartmentModel> findAllActiveDepartments() {
        return departmentService.findAllActiveDepartment().stream().map(DepartmentModel::toModel).collect(Collectors.toList());
    }
}
