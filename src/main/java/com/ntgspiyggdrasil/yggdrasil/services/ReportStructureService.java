package com.ntgspiyggdrasil.yggdrasil.services;

import com.ntgspiyggdrasil.yggdrasil.models.DocumentStructure;
import com.ntgspiyggdrasil.yggdrasil.models.ReportStructure;
import com.ntgspiyggdrasil.yggdrasil.models.ReportType;
import com.ntgspiyggdrasil.yggdrasil.payload.request.DocumentStructureCreate;
import com.ntgspiyggdrasil.yggdrasil.payload.request.ReportStructureCreate;
import com.ntgspiyggdrasil.yggdrasil.payload.request.ReportStructureStatusRequest;
import com.ntgspiyggdrasil.yggdrasil.payload.response.DocumentStructurePageInfo;
import com.ntgspiyggdrasil.yggdrasil.repository.ReportRepository;
import com.ntgspiyggdrasil.yggdrasil.repository.ReportStructureRepository;
import com.ntgspiyggdrasil.yggdrasil.repository.ReportTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReportStructureService {
    @Autowired
    private ReportStructureRepository reportStructureRepository;
    @Autowired
    private ReportTypeRepository reportTypeRepository;
    @Autowired
    private ReportRepository reportRepository;

    public Page<ReportStructure> loadAllReportStructures(String sortField, String sortDir, int pageNumber, String parameter, String reportTypeName, Boolean isActive, Date minDate, Date maxDate) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 15, sort);

        return reportStructureRepository.findAllSort(parameter, reportTypeName, isActive, minDate, maxDate, pageable);
    }

    public List<ReportStructure> loadAllActiveReportStructure() {
        return reportStructureRepository.findAllByIsActive(true);
    }

    public ReportStructure loadReportStructureById(long reportStructureId) {
        return reportStructureRepository.findById(reportStructureId).orElseThrow();
    }

    public ReportStructure createReportStructure(ReportStructureCreate reportStructure) {
        ReportType reportType = reportTypeRepository.findById(reportStructure.getReportTypeId()).orElseThrow();
        ReportStructure newReportStructure = new ReportStructure();
        newReportStructure.setIsActive(true);
        newReportStructure.setReportType(reportType);
        newReportStructure.setReportStructure(reportStructure.getStructureData());
        newReportStructure.setName(reportStructure.getName());
        newReportStructure.setDescription(reportStructure.getDescription());
        newReportStructure.setDateCreate(new Date());
        newReportStructure.setDateUpdate(new Date());

        return reportStructureRepository.save(newReportStructure);
    }

    public ReportStructure updateReportStructure(ReportStructureCreate reportStructure) {
        if (reportRepository.findAllByReportStructureIdAndStatus(reportStructure.getId(), 3L).size() == 0) {
            ReportStructure reportStructureUpdate = reportStructureRepository.findById(reportStructure.getId()).orElseThrow(); //reportStructureRepository.updateReportStructure(reportStructure.getId(), reportStructure.getName(), reportStructure.getDescription(), reportStructure.getReportTypeId(), reportStructure.getStructureData());
            reportStructureUpdate.setName(reportStructure.getName());
            reportStructureUpdate.setDescription(reportStructure.getDescription());
            reportStructureUpdate.setDateUpdate(new Date());
            reportStructureUpdate.setReportStructure(reportStructure.getStructureData());
            ReportType reportType = reportTypeRepository.findById(reportStructure.getReportTypeId()).orElseThrow();
            reportStructureUpdate.setReportType(reportType);
            //return reportStructureRepository.findById(reportStructure.getId()).orElseThrow();
            return reportStructureRepository.save(reportStructureUpdate);
        }
        ReportType reportType = reportTypeRepository.findById(reportStructure.getReportTypeId()).orElseThrow();
        ReportStructure newReportStructure = new ReportStructure();
        newReportStructure.setIsActive(true);
        newReportStructure.setReportType(reportType);
        newReportStructure.setReportStructure(reportStructure.getStructureData());
        newReportStructure.setName(reportStructure.getName());
        newReportStructure.setDescription(reportStructure.getDescription());
        newReportStructure.setDateCreate(new Date());
        newReportStructure.setDateUpdate(new Date());
        newReportStructure.setOldStructureId(reportStructure.getId());
        ReportStructure readyStructure = reportStructureRepository.save(newReportStructure);
        reportRepository.replacementReportStructure(readyStructure.getOldStructureId(), reportStructure.getId());

        return readyStructure;
    }

    public ReportStructure updateStatusReportStructure(ReportStructureStatusRequest status) {
        reportStructureRepository.updateStatusReportStructure(status.getId(), status.getIsActive());

        reportRepository.revActivateReports(status.getId(), status.getIsActive());

        return reportStructureRepository.findById(status.getId()).orElseThrow();
    }

    public Boolean checkReportStructure(long reportStructureId) {
        return reportStructureRepository.existsById(reportStructureId);
    }

    public List<ReportStructure> loadAllNewReportStructure(long oldReportStructureId) {
        return reportStructureRepository.findAllByOldStructureId(oldReportStructureId);
    }
}