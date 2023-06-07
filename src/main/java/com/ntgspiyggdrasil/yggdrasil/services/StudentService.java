package com.ntgspiyggdrasil.yggdrasil.services;

import com.ntgspiyggdrasil.yggdrasil.models.Department;
import com.ntgspiyggdrasil.yggdrasil.models.Student;
import com.ntgspiyggdrasil.yggdrasil.payload.request.UpdateStudent;
import com.ntgspiyggdrasil.yggdrasil.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private DepartmentService departmentService;

    public Page<Student> loadAllStudent(String parameter, String sortField, String sortDir, int pageNumber, String departmentName, Boolean isActive, Date minDate, Date maxDate) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 15, sort);
        return studentRepository.findAll(parameter, departmentName, isActive, minDate, maxDate, pageable);
    }
    public Page<Student> loadAllStudentFaculty(String parameter, String sortField, String sortDir, int pageNumber, long facultyId, String departmentName, Boolean isActive, Date minDate, Date maxDate) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 15, sort);
        return studentRepository.findAllByFacultyId(parameter, isActive, minDate, maxDate, facultyId, pageable);
    }
    public Page<Student> loadAllStudentDepartment(String parameter, String sortField, String sortDir, int pageNumber, long departmentId, String departmentName, Boolean isActive, Date minDate, Date maxDate) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 15, sort);
        return studentRepository.findAllByDepartmentId(parameter, departmentName, isActive, minDate, maxDate, departmentId, pageable);
    }

    public Student saveStudent(String lastName, String name, String patronymic, String groupName, long departmentId, Integer startYear, Integer endYear, String email, String phone, Date birthday) {
        Student student = new Student();
        Department department = departmentService.loadDepartment(departmentId);
        student.setDepartment(department);
        student.setName(name);
        student.setLastName(lastName);
        student.setPatronymic(patronymic);
        student.setGroupName(groupName);
        student.setYearStart(startYear);
        student.setYearEnd(endYear);
        student.setEmail(email);
        student.setPhone(phone);
        student.setBirthday(birthday);
        student.setDateCreate(new Date());
        student.setDateUpdate(new Date());
        student.setIsTrained(true);
        return studentRepository.save(student);
    }

    public Student findStudent(long studentId) {
        return studentRepository.findById(studentId).orElseThrow();
    }

    public Student updateStudent(UpdateStudent studentData) {
        Student student = studentRepository.findById(studentData.getStudentId()).orElseThrow();
        Department department = departmentService.loadDepartment(studentData.getDepartmentId());
        student.setLastName(studentData.getLastName());
        student.setName(studentData.getName());
        student.setPatronymic(studentData.getPatronymic());
        student.setGroupName(studentData.getGroupName());
        student.setDepartment(department);
        student.setYearStart(studentData.getYearStart());
        student.setYearEnd(studentData.getYearEnd());
        student.setEmail(studentData.getEmail());
        student.setPhone(studentData.getPhone());
        student.setBirthday(studentData.getBirthday());
        student.setDateUpdate(new Date());
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> getAllStudentsByDepartmentId(long departmentId) {
        return studentRepository.findAllByDepartment(departmentId);
    }

    public Student updateStudentStatus(Long studentId, Boolean isTrained) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        student.setIsTrained(isTrained);
        student.setDateUpdate(new Date());
        return studentRepository.save(student);
    }
}
