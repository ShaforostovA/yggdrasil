package com.ntgspiyggdrasil.yggdrasil.services;

import com.ntgspiyggdrasil.yggdrasil.models.Report;
import com.ntgspiyggdrasil.yggdrasil.models.ReportStatus;
import com.ntgspiyggdrasil.yggdrasil.models.ReportStructure;
import com.ntgspiyggdrasil.yggdrasil.models.User;
import com.ntgspiyggdrasil.yggdrasil.payload.request.CreateReportRequest;
import com.ntgspiyggdrasil.yggdrasil.payload.request.ReportCanChange;
import com.ntgspiyggdrasil.yggdrasil.payload.request.UpdateReportRequest;
import com.ntgspiyggdrasil.yggdrasil.payload.request.UpdateReportStatus;
import com.ntgspiyggdrasil.yggdrasil.repository.ReportRepository;
import com.ntgspiyggdrasil.yggdrasil.repository.ReportStatusRepository;
import com.ntgspiyggdrasil.yggdrasil.repository.ReportStructureRepository;
import com.ntgspiyggdrasil.yggdrasil.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private ReportStructureRepository reportStructureRepository;
    @Autowired
    private ReportStatusRepository reportStatusRepository;
    @Autowired
    private UserRepository userRepository;

    public Page<Report> loadAllReports(long userId, String parameter, String sortField, String sortDir, int pageNumber, String statusName, Date minDate, Date maxDate, String reportType) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 15, sort);
        return reportRepository.findAllSort(userId, parameter, statusName, minDate, maxDate, reportType, pageable);
    }

    public Report loadReportById(long reportId) { return  reportRepository.findById(reportId).orElseThrow(); }

    public Report createReport(CreateReportRequest report) {
        ReportStructure reportStructure = reportStructureRepository.findById(report.getReportStructureId()).orElseThrow();
        ReportStatus reportStatus = reportStatusRepository.findById(report.getReportStatusId()).orElseThrow();
        User user = userRepository.findById(report.getUserId()).orElseThrow();

        Report newReport = new Report();
        newReport.setReportData(report.getReportData());
        newReport.setDateStart(report.getDateStart());
        newReport.setDateEnd(report.getDateEnd());
        newReport.setReportStructure(reportStructure);
        newReport.setUser(user);
        newReport.setReportStatus(reportStatus);
        newReport.setDateCreate(new Date());
        newReport.setDateUpdate(new Date());
        newReport.setCanChange(true);

        return reportRepository.save(newReport);
    }

    public Report updateReport(UpdateReportRequest report) {
        //reportRepository.updateReport(report.getReportId(), report.getReportStructureId(), true, report.getDateStart(), report.getDateEnd(), report.getReportData());
        Report reportUpdate = reportRepository.findById(report.getReportId()).orElseThrow();
        ReportStructure reportStructure = reportStructureRepository.findById(report.getReportStructureId()).orElseThrow();
        reportUpdate.setReportStructure(reportStructure);
        reportUpdate.setDateStart(report.getDateStart());
        reportUpdate.setDateEnd(report.getDateEnd());
        reportUpdate.setReportData(report.getReportData());
        reportUpdate.setDateUpdate(new Date());
        //return reportRepository.findById(report.getReportId()).orElseThrow();
        return reportRepository.save(reportUpdate);
    }

    public Report updateReportStatus(UpdateReportStatus updateReportStatus) {
        reportRepository.updateReportStatus(updateReportStatus.getReportId(), updateReportStatus.getStatusId());

        return reportRepository.findById(updateReportStatus.getReportId()).orElseThrow();
    }

    public Report updateReportCanChange(ReportCanChange reportCanChange) {
        reportRepository.updateReportCanChange(reportCanChange.getReportId(), reportCanChange.getCanChange());

        return reportRepository.findById(reportCanChange.getReportId()).orElseThrow();
    }

    public Page<Report> loadAllCurrantReports(long userId, String parameter, String sortField, String sortDir, int pageNumber, String statusName, Date minDate, Date maxDate, String reportType)  {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 15, sort);
        return reportRepository.findCurrantAllSort(userId, parameter, statusName, minDate, maxDate, reportType, pageable);
    }

    public Report findByReportIdUserId(long reportId, long userId) {
        return reportRepository.findByReportIdUserId(reportId, userId).orElseThrow();
    }

    public boolean deleteReport(long reportId) {
        if (reportRepository.findById(reportId).orElseThrow().getReportStatus().getId().equals(3)) {
            return false;
        }
        reportRepository.deleteById(reportId);
        return true;
    }
}
