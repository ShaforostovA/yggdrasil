package com.ntgspiyggdrasil.yggdrasil.repository;

import com.ntgspiyggdrasil.yggdrasil.models.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select s from Student s where lower(concat(s.id, ' ', s.lastName, ' ', s.name, ' ', s.patronymic, ' ', s.groupName)) like lower(concat('%', ?1, '%')) and lower(s.department.shortName) like lower(concat('%', ?2, '%')) and (s.isTrained = coalesce(?3, s.isTrained)) and s.dateCreate >= ?4 and s.dateCreate < ?5")
    Page<Student> findAll(String parameter, String departmentName, Boolean isActive, Date minDate, Date maxDate, Pageable pageable);
    @Query("select s from Student s where s.department.faculty.id = ?5 and lower(concat(s.id, ' ', s.lastName, ' ', s.name, ' ', s.patronymic, ' ', s.groupName)) like lower(concat('%', ?1, '%')) and (s.isTrained = coalesce(?2, s.isTrained)) and s.dateCreate >= ?3 and s.dateCreate < ?4")
    Page<Student> findAllByFacultyId(String parameter, Boolean isActive, Date minDate, Date maxDate, long facultyId, Pageable pageable);
    @Query("select s from Student s where s.department.id = ?6 and lower(concat(s.id, ' ', s.lastName, ' ', s.name, ' ', s.patronymic, ' ', s.groupName)) like lower(concat('%', ?1, '%')) and lower(s.department.shortName) like lower(concat('%', ?2, '%')) and (s.isTrained = coalesce(?3, s.isTrained)) and s.dateCreate >= ?4 and s.dateCreate < ?5")
    Page<Student> findAllByDepartmentId(String parameter, String departmentName, Boolean isActive, Date minDate, Date maxDate, long departmentId, Pageable pageable);
    @Query("select s from Student s where s.department.id = ?1")
    List<Student> findAllByDepartment(Long departmentId);
}
