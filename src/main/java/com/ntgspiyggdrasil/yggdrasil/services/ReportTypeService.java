package com.ntgspiyggdrasil.yggdrasil.services;

import com.ntgspiyggdrasil.yggdrasil.models.ReportType;
import com.ntgspiyggdrasil.yggdrasil.repository.ReportTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportTypeService {
    @Autowired
    private ReportTypeRepository reportTypeRepository;

    public List<ReportType> loadAllReportTypes() { return reportTypeRepository.findAll(); }

    public Boolean canCreateReportType(String reportTypeName) {
        return reportTypeRepository.existsByName(reportTypeName);
    }

    public ReportType createReportType(String reportTypeName) {
        ReportType reportType = new ReportType();
        reportType.setName(reportTypeName);
        return reportTypeRepository.save(reportType);
    }

    public ReportType updateReportType(long reportTypeId, String reportTypeName) {
        ReportType reportType = reportTypeRepository.findById(reportTypeId).orElseThrow();
        reportType.setName(reportTypeName);
        return reportTypeRepository.save(reportType);
    }
}
