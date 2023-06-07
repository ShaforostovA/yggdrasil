package com.ntgspiyggdrasil.yggdrasil.repository;

import com.ntgspiyggdrasil.yggdrasil.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ntgspiyggdrasil.yggdrasil.models.Department;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findById(long id);
    Set<User> findAllById(long id);
    Optional<Department> findByName(String name);
    Optional<Department> findByShortName(String shortName);

    @Query("select d from Department d order by d.id desc")
    List<Department> findAll();

    @Query("select d from Department d where lower(concat(d.id, ' ', d.name, ' ', d.shortName)) like lower(concat('%', ?1, '%')) and lower(d.faculty.shortName) like lower(concat('%', ?2, '%')) and (d.isActive = coalesce(?3, d.isActive)) and d.dateCreate >= ?4 and d.dateCreate < ?5")
    Page<Department> findAllSearch(String parameter, String facultyName, Boolean isActive, Date minDate, Date maxDate, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "update departments set short_name = ?2, name = ?3, description = ?4, faculty_id = ?5, date_update = now() where departments.id = ?1", nativeQuery = true)
    void updateDepartmentDataById(long departmentId, String shortName, String name, String description, long facultyId);

    @Query("select d from Department d where d.isActive = true order by d.id desc")
    List<Department> findAllByActive();
}
