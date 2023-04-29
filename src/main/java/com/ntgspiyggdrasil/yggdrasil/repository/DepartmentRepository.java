package com.ntgspiyggdrasil.yggdrasil.repository;

import com.ntgspiyggdrasil.yggdrasil.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ntgspiyggdrasil.yggdrasil.models.Department;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findById(long id);
    Set<User> findAllById(long id);
    Optional<Department> findByName(String name);
    Optional<Department> findByShortName(String shortName);

    @Modifying
    @Transactional
    @Query(value = "update departments set short_name = ?2, name = ?3, description = ?4, faculty_id = ?5, date_update = now() where departments.id = ?1", nativeQuery = true)
    void updateDepartmentDataById(long departmentId, String shortName, String name, String description, long facultyId);
}
