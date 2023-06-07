package com.ntgspiyggdrasil.yggdrasil.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ntgspiyggdrasil.yggdrasil.models.Faculty;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Faculty findById(long facultyId);
    @Query("select f from Faculty f order by f.id desc")
    List<Faculty> findAll();

    @Query("select f from Faculty f where lower(concat(f.id, ' ', f.name, ' ', f.shortName)) like lower(concat('%',?1,'%')) and (f.isActive = coalesce(?2, f.isActive)) and f.dateCreate >= ?3 and f.dateCreate < ?4")
    Page<Faculty> findAllPaginate(String parameter, Boolean isActive, Date minDate, Date maxDate, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "update faculties set short_name = ?2, name = ?3, description = ?4, date_update = now() where faculties.id = ?1", nativeQuery = true)
    void updateFacultyById(long facultyId, String shortName, String name, String description);

    @Query("select f from Faculty f where f.isActive = true")
    List<Faculty> findAllByActive();
}
