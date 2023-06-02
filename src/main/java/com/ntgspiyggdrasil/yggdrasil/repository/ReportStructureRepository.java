package com.ntgspiyggdrasil.yggdrasil.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ntgspiyggdrasil.yggdrasil.models.ReportStructure;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface ReportStructureRepository extends JpaRepository<ReportStructure, Long> {
//    @Query(value = "select * from reports_structures order by reports_structures.id desc", nativeQuery = true)
//    List<ReportStructure> findAllSort();
    @Query("select rs from ReportStructure rs where lower(concat(rs.id, ' ', rs.name)) like lower(concat('%',?1,'%')) and lower(rs.reportType.name) like lower(concat('%', ?2, '%')) and (rs.isActive = coalesce(?3, rs.isActive)) and rs.dateCreate >= ?4 and rs.dateCreate < ?5")
    Page<ReportStructure> findAllSort(String parameter, String reportTypeName, Boolean isActive, Date minDate, Date maxDate, Pageable pageable);
    @Query(value = "select * from reports_structures where reports_structures.is_active = ?1 order by reports_structures.id desc", nativeQuery = true)
    List<ReportStructure> findAllByIsActive(Boolean isActive);

    Boolean existsById(long reportStructureId);

    @Modifying
    @Transactional
    @Query(value = "update reports_structures set name = ?2, description = ?3, report_type_id = ?4, report_structure = to_json(?5), date_update = now() where reports_structures.id = ?1", nativeQuery = true)
    void updateReportStructure(long reportStructureId, String name, String description, long reportTypeId, String structureData);

    @Modifying
    @Transactional
    @Query(value = "update reports_structures set is_active = ?2, date_update = now() where reports_structures.id = ?1", nativeQuery = true)
    void updateStatusReportStructure(long reportStructureId, Boolean isActive);

    List<ReportStructure> findAllByOldStructureId(long oldStructureId);
}
