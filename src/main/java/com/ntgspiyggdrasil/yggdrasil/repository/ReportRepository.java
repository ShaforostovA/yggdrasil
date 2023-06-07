package com.ntgspiyggdrasil.yggdrasil.repository;

import com.ntgspiyggdrasil.yggdrasil.models.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ntgspiyggdrasil.yggdrasil.models.Report;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    @Modifying
    @Transactional
    @Query(value = "update reports set can_change = ?2, date_update = now() where reports.report_structure_id = ?1 and reports.report_status_id <> 3", nativeQuery = true)
    void revActivateReports(long reportStructureId, Boolean canChange);

    @Query("select r from Report r where r.reportStructure.id = ?1 and r.reportStatus.id = ?2 order by r.id desc")
    List<Report> findAllByReportStructureIdAndStatus(long reportStructureId, long statusId);

    @Modifying
    @Transactional
    @Query(value = "update reports set report_structure_id = ?2, date_update = now() where reports.report_structure_id = ?1 and reports.report_status_id <> 4", nativeQuery = true)
    void replacementReportStructure(long findReportStructureId, long newReportStructureId);

    @Modifying
    @Transactional
    @Query(value = "update reports set report_structure_id = ?2, can_change = ?3, date_start = ?4, date_end = ?5, report_data = to_json(?6), date_update = now() where reports.id = ?1 and reports.report_status_id <> 4", nativeQuery = true)
    void updateReport(long reportId, long reportStructureId, Boolean canChange, Date dateStart, Date dateEnd, String reportData);

    @Modifying
    @Transactional
    @Query(value = "update reports set report_status_id = ?2, date_update = now() where reports.id = ?1 and reports.report_status_id <> 4", nativeQuery = true)
    void updateReportStatus(long reportId, long reportStatusId);

    @Query("select r from Report r where (r.user.id = ?1 or (r.user.id <> ?1 and r.reportStatus.id <> 1)) and (lower(concat(r.id,' ', r.reportStructure.name)) like lower(concat('%',?2,'%')) or lower(concat(r.user.lastName, ' ', r.user.name, ' ', r.user.patronymic)) like lower(concat('%',?2,'%'))) and lower(r.reportStatus.name) like lower(concat('%',?3,'%')) and r.dateCreate >= ?4 and r.dateCreate < ?5 and lower(r.reportStructure.reportType.name) like lower(concat('%',?6,'%'))")
    Page<Report> findAllSort(long userId, String parameter, String statusName, Date minDate, Date maxDate, String typeName, Pageable pageable);

    @Query("select r from Report r where r.user.id = ?1 and (lower(concat(r.id,' ', r.reportStructure.name)) like lower(concat('%',?2,'%')) or lower(concat(r.user.lastName, ' ', r.user.name, ' ', r.user.patronymic)) like lower(concat('%',?2,'%'))) and lower(r.reportStatus.name) like lower(concat('%',?3,'%')) and r.dateCreate >= ?4 and r.dateCreate < ?5 and lower(r.reportStructure.reportType.name) like lower(concat('%',?6,'%'))")
    Page<Report> findCurrantAllSort(long userId, String parameter, String statusName, Date minDate, Date maxDate, String typeName, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "update reports set can_change = ?2, date_update = now() where reports.id = ?1 and reports.report_status_id <> 4", nativeQuery = true)
    void updateReportCanChange(long reportId, Boolean canChange);

    @Query("select r from Report r where r.id = ?1 and r.user.id = ?2")
    Optional<Report> findByReportIdUserId(long reportId, long userId);

    @Modifying
    @Transactional
    @Query("delete from Report r where r.id = ?1")
    void deleteById(long reportId);
}
