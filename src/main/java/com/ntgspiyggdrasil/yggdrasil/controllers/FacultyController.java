package com.ntgspiyggdrasil.yggdrasil.controllers;

import com.ntgspiyggdrasil.yggdrasil.models.Faculty;
import com.ntgspiyggdrasil.yggdrasil.models.User;
import com.ntgspiyggdrasil.yggdrasil.payload.request.FacultyRequest;
import com.ntgspiyggdrasil.yggdrasil.payload.request.SearchRequest;
import com.ntgspiyggdrasil.yggdrasil.payload.response.*;
import com.ntgspiyggdrasil.yggdrasil.services.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
//@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/faculty")
public class FacultyController {
    @Autowired
    private FacultyService facultyService;
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN')")
    @GetMapping("/all")
    public List<FacultyNoUserModel> findAllFaculty() {
        return facultyService.loadAllFaculty();
    }

    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN')")
    @PostMapping("/search/all")
    public FacultyPageInfo findAllFacultySearch(@RequestBody SearchRequest request) {
        Page<Faculty> page;
        FacultyPageInfo pageInfo = new FacultyPageInfo();

        Date minDate;
        Date maxDate;
        LocalDate date;

//        System.out.println(request);

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
        page = facultyService.loadAllFacultySearch(request.getSortField(), request.getSortDir(), request.getCurrentPage(), request.getParameter(), request.getIsActive(), minDate, maxDate);

        pageInfo.setCurrentPage(request.getCurrentPage());
        pageInfo.setTotalItems(page.getTotalElements());
        pageInfo.setTotalPages(page.getTotalPages());
        pageInfo.setFacultyList(page.getContent().stream().map(FacultyNoUserModel::toModel).collect(Collectors.toList()));
        pageInfo.setSortField(request.getSortField());
        pageInfo.setSortDir(request.getSortDir());
        pageInfo.setSearchParameter(request.getParameter());

        return pageInfo;
    }

    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN') or hasRole('MODERATOR')")
    @GetMapping("/active/all")
    public List<FacultyNoUserModel> findAllActiveFaculty() {
        return facultyService.loadAllActiveFaculty().stream().map(FacultyNoUserModel::toModel).collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping("/{facultyId}")
    public FacultyModel findFacultyById(@PathVariable("facultyId") long facultyId) {
        return FacultyModel.toModel(facultyService.loadFacultyById(facultyId));
    }
    @PostMapping("/update")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public FacultyModel updateFaculty(@RequestBody FacultyRequest faculty) {
        return FacultyModel.toModel(facultyService.updateFaculty(faculty));
    }
    @PostMapping("/create")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public FacultyModel createFaculty(@RequestBody FacultyRequest faculty) {
        return FacultyModel.toModel(facultyService.createFaculty(faculty));
    }
}
