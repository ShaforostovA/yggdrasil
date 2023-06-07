package com.ntgspiyggdrasil.yggdrasil.controllers;

import com.ntgspiyggdrasil.yggdrasil.models.ERole;
import com.ntgspiyggdrasil.yggdrasil.models.Student;
import com.ntgspiyggdrasil.yggdrasil.payload.request.CreateStudentRequest;
import com.ntgspiyggdrasil.yggdrasil.payload.request.SearchRequest;
import com.ntgspiyggdrasil.yggdrasil.payload.request.UpdateStudent;
import com.ntgspiyggdrasil.yggdrasil.payload.request.UpdateStudentStatus;
import com.ntgspiyggdrasil.yggdrasil.payload.response.DepartmentModel;
import com.ntgspiyggdrasil.yggdrasil.payload.response.StudentModel;
import com.ntgspiyggdrasil.yggdrasil.payload.response.StudentPageInfo;
import com.ntgspiyggdrasil.yggdrasil.payload.response.UserShortModel;
import com.ntgspiyggdrasil.yggdrasil.security.jwt.JwtUtils;
import com.ntgspiyggdrasil.yggdrasil.services.StudentService;
import com.ntgspiyggdrasil.yggdrasil.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;

//    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN')")
    @PostMapping("/all")
    public StudentPageInfo getAllStudent(@RequestBody SearchRequest request) {

        Page<Student> page;
        StudentPageInfo pageInfo = new StudentPageInfo();

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

        page = studentService.loadAllStudent(request.getParameter(), request.getSortField(), request.getSortDir(), request.getCurrentPage(), request.getDepartmentName(), request.getIsActive(), minDate, maxDate);

        pageInfo.setCurrentPage(request.getCurrentPage());
        pageInfo.setTotalItems(page.getTotalElements());
        pageInfo.setTotalPages(page.getTotalPages());
        pageInfo.setStudentList(page.getContent().stream().map(StudentModel::toModel).collect(Collectors.toList()));
        pageInfo.setSortField(request.getSortField());
        pageInfo.setSortDir(request.getSortDir());
        pageInfo.setSearchParameter(request.getParameter());

        return pageInfo;
    }

    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN')")
    @PostMapping("/faculty/all")
    public StudentPageInfo getAllFacultyStudent(@RequestBody SearchRequest request) {

        Page<Student> page;
        StudentPageInfo pageInfo = new StudentPageInfo();

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

        page = studentService.loadAllStudentFaculty(request.getParameter(), request.getSortField(), request.getSortDir(), request.getCurrentPage(), request.getFacultyId(), request.getDepartmentName(), request.getIsActive(), minDate, maxDate);

        pageInfo.setCurrentPage(request.getCurrentPage());
        pageInfo.setTotalItems(page.getTotalElements());
        pageInfo.setTotalPages(page.getTotalPages());
        pageInfo.setStudentList(page.getContent().stream().map(StudentModel::toModel).collect(Collectors.toList()));
        pageInfo.setSortField(request.getSortField());
        pageInfo.setSortDir(request.getSortDir());
        pageInfo.setSearchParameter(request.getParameter());

        return pageInfo;
    }

    @GetMapping("/document/all")
    public List<StudentModel> getAllDocumentStudent(@RequestHeader("Authorization") String authorization) {
        String headerAuth = authorization;
//        String username = jwtUtils.getUserNameFromJwtToken(headerAuth.substring(7,headerAuth.length()));
//        UserShortModel user = userService.loadShortUserByUsername(username);
//        if (user.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_ADMIN.name()))) {
//            return studentService.getAllStudents().stream().map(StudentModel::toModel).collect(Collectors.toList());
//        }
//        return studentService.getAllStudentsByDepartmentId(user.getDepartment().getId()).stream().map(StudentModel::toModel).collect(Collectors.toList());
        return studentService.getAllStudents().stream().map(StudentModel::toModel).collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    @PostMapping("/department/all")
    public StudentPageInfo getAllDepartmentStudent(@RequestBody SearchRequest request) {

        Page<Student> page;
        StudentPageInfo pageInfo = new StudentPageInfo();

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

        page = studentService.loadAllStudentFaculty(request.getParameter(), request.getSortField(), request.getSortDir(), request.getCurrentPage(), request.getDepartmentId(), request.getDepartmentName(), request.getIsActive(), minDate, maxDate);

        pageInfo.setCurrentPage(request.getCurrentPage());
        pageInfo.setTotalItems(page.getTotalElements());
        pageInfo.setTotalPages(page.getTotalPages());
        pageInfo.setStudentList(page.getContent().stream().map(StudentModel::toModel).collect(Collectors.toList()));
        pageInfo.setSortField(request.getSortField());
        pageInfo.setSortDir(request.getSortDir());
        pageInfo.setSearchParameter(request.getParameter());

        return pageInfo;
    }

    @PreAuthorize("hasRole('USER') or hasRole('SUPER_ADMIN') or hasRole('MODERATOR')")
    @PostMapping("/create")
    public StudentModel createStudent(@RequestBody CreateStudentRequest request) {
        return StudentModel.toModel(studentService.saveStudent(request.getLastName(), request.getName(), request.getPatronymic(), request.getGroupName(), request.getDepartmentId(), request.getYearStart(), request.getYearEnd(), request.getEmail(), request.getPhone(), request.getBirthday()));
    }

    @GetMapping("/{studentId}")
    public StudentModel findStudent(@PathVariable("studentId") long studentId) {
        return StudentModel.toModel(studentService.findStudent(studentId));
    }

    @PostMapping("/update")
    public StudentModel updateStudent(@RequestBody UpdateStudent updateStudent) {
        return StudentModel.toModel(studentService.updateStudent(updateStudent));
    }

    @PostMapping("/status/update")
    public StudentModel updateStatusStudent(@RequestBody UpdateStudentStatus updateStudentStatus) {
        return StudentModel.toModel(studentService.updateStudentStatus(updateStudentStatus.getStudentId(), updateStudentStatus.getIsTrained()));
    }
}
