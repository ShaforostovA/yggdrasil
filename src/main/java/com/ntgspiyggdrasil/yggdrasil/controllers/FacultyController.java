package com.ntgspiyggdrasil.yggdrasil.controllers;

import com.ntgspiyggdrasil.yggdrasil.payload.request.FacultyRequest;
import com.ntgspiyggdrasil.yggdrasil.payload.response.FacultyModel;
import com.ntgspiyggdrasil.yggdrasil.payload.response.FacultyNoUserModel;
import com.ntgspiyggdrasil.yggdrasil.services.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/faculty")
public class FacultyController {
    @Autowired
    private FacultyService facultyService;
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN')")
    @GetMapping("/all")
    public List<FacultyNoUserModel> findAllFaculty() {
        return facultyService.loadAllFaculty();
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
