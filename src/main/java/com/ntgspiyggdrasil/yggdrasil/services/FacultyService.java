package com.ntgspiyggdrasil.yggdrasil.services;

import com.ntgspiyggdrasil.yggdrasil.models.Faculty;
import com.ntgspiyggdrasil.yggdrasil.payload.request.FacultyRequest;
import com.ntgspiyggdrasil.yggdrasil.payload.response.FacultyModel;
import com.ntgspiyggdrasil.yggdrasil.payload.response.FacultyNoUserModel;
import com.ntgspiyggdrasil.yggdrasil.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    @Autowired
    private FacultyRepository facultyRepository;
    public Faculty loadFacultyById(long id) {
        return facultyRepository.findById(id);
    }
    public List<FacultyNoUserModel> loadAllFaculty() {
        return facultyRepository.findAll().stream().map(FacultyNoUserModel::toModel).collect(Collectors.toList());
    }
    public Faculty updateFaculty(FacultyRequest faculty) {
        facultyRepository.updateFacultyById(faculty.getId(), faculty.getShortName(), faculty.getName(), faculty.getDescription());
        return facultyRepository.findById(faculty.getId());
    }
    public Faculty createFaculty(FacultyRequest facultyData) {
        return facultyRepository.save(new Faculty(facultyData.getName(), facultyData.getShortName(), facultyData.getDescription()));
    }
}
