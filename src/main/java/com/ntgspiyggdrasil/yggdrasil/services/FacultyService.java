package com.ntgspiyggdrasil.yggdrasil.services;

import com.ntgspiyggdrasil.yggdrasil.models.Faculty;
import com.ntgspiyggdrasil.yggdrasil.payload.request.FacultyDocumentsRequest;
import com.ntgspiyggdrasil.yggdrasil.payload.request.FacultyRequest;
import com.ntgspiyggdrasil.yggdrasil.payload.response.FacultyModel;
import com.ntgspiyggdrasil.yggdrasil.payload.response.FacultyNoUserModel;
import com.ntgspiyggdrasil.yggdrasil.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public Page<Faculty> loadAllFacultySearch(String sortField, String sortDir, int pageNumber, String parameter, Boolean isActive, Date minDate, Date maxDate) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 15, sort);
        return facultyRepository.findAllPaginate(parameter, isActive, minDate, maxDate, pageable);
    }
    public Faculty updateFaculty(FacultyRequest faculty) {
        facultyRepository.updateFacultyById(faculty.getId(), faculty.getShortName(), faculty.getName(), faculty.getDescription());
        return facultyRepository.findById(faculty.getId());
    }
    public Faculty createFaculty(FacultyRequest facultyData) {
        return facultyRepository.save(new Faculty(facultyData.getName(), facultyData.getShortName(), facultyData.getDescription()));
    }

    public List<Faculty> loadAllActiveFaculty() {
        return facultyRepository.findAllByActive();
    }
}
