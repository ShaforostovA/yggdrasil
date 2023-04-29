package com.ntgspiyggdrasil.yggdrasil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ntgspiyggdrasil.yggdrasil.models.Faculty;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Faculty findById(long facultyId);
    @Query("select f from Faculty f order by f.id desc")
    List<Faculty> findAll();

    @Modifying
    @Transactional
    @Query(value = "update faculties set short_name = ?2, name = ?3, description = ?4, date_update = now() where faculties.id = ?1", nativeQuery = true)
    void updateFacultyById(long facultyId, String shortName, String name, String description);
}
