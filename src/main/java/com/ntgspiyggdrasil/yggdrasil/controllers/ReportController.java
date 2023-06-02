package com.ntgspiyggdrasil.yggdrasil.controllers;

import com.ntgspiyggdrasil.yggdrasil.models.Document;
import com.ntgspiyggdrasil.yggdrasil.models.ERole;
import com.ntgspiyggdrasil.yggdrasil.models.Report;
import com.ntgspiyggdrasil.yggdrasil.models.ReportStructure;
import com.ntgspiyggdrasil.yggdrasil.payload.request.*;
import com.ntgspiyggdrasil.yggdrasil.payload.response.*;
import com.ntgspiyggdrasil.yggdrasil.security.jwt.JwtUtils;
import com.ntgspiyggdrasil.yggdrasil.services.ReportService;
import com.ntgspiyggdrasil.yggdrasil.services.ReportStructureService;

import com.ntgspiyggdrasil.yggdrasil.services.ReportTypeService;
import com.ntgspiyggdrasil.yggdrasil.services.UserService;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/reports")
@AllArgsConstructor
public class ReportController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private ReportStructureService reportStructureService;
    @Autowired
    private ReportTypeService reportTypeService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserService userService;

    @PostMapping("/all")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN') or hasRole('MODERATOR')")
    public ReportPageInfo getAllReports(@RequestHeader("Authorization") String authorization, @RequestBody SearchRequest request) {
        String headerAuth = authorization;
        String username = jwtUtils.getUserNameFromJwtToken(headerAuth.substring(7,headerAuth.length()));
        UserShortModel user = userService.loadShortUserByUsername(username);

        Page<Report> page;
        ReportPageInfo pageInfo = new ReportPageInfo();

        Date minDate;
        Date maxDate;
        LocalDate date;

        if (request.getMinDate() == "") {
            minDate = new Date(0);
        } else {
            date = LocalDate.parse(request.getMinDate());
            minDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        if (request.getMaxDate() == "") {
            maxDate = new Date();
        } else {
            date = LocalDate.parse(request.getMaxDate());
            maxDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }

        if (user.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_MODERATOR.name()))) {
            page = reportService.loadAllCurrantReports(user.getId(), request.getParameter(), request.getSortField(), request.getSortDir(), request.getCurrentPage(), request.getStatusName(), minDate, maxDate, request.getReportType());
        } else {
            page = reportService.loadAllReports(user.getId(), request.getParameter(), request.getSortField(), request.getSortDir(), request.getCurrentPage(), request.getStatusName(), minDate, maxDate, request.getReportType());
        }

        pageInfo.setCurrentPage(request.getCurrentPage());
        pageInfo.setTotalItems(page.getTotalElements());
        pageInfo.setTotalPages(page.getTotalPages());
        pageInfo.setReports(page.getContent().stream().map(ReportShortModel::toModel).collect(Collectors.toList()));
        pageInfo.setSortField(request.getSortField());
        pageInfo.setSortDir(request.getSortDir());
        pageInfo.setSearchParameter(request.getParameter());

        return pageInfo;
    }
    @GetMapping("/{reportId}")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN') or hasRole('MODERATOR')")
    public ReportModel getReport(@RequestHeader("Authorization") String authorization, @PathVariable("reportId") long reportId) {
        String headerAuth = authorization;
        String username = jwtUtils.getUserNameFromJwtToken(headerAuth.substring(7,headerAuth.length()));
        UserShortModel user = userService.loadShortUserByUsername(username);
        if (user.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_MODERATOR.name()))) {
            return ReportModel.toModel(reportService.findByReportIdUserId(reportId, user.getId()));
        }
        return ReportModel.toModel(reportService.loadReportById(reportId));
    }
    @PostMapping("/create")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN') or hasRole('MODERATOR')")
    public ReportModel createReport(@RequestBody CreateReportRequest reportRequest) {
        return ReportModel.toModel(reportService.createReport(reportRequest));
    }
    @PostMapping("/update")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN') or hasRole('MODERATOR')")
    public ReportModel updateReport(@RequestBody UpdateReportRequest reportRequest) {
        return ReportModel.toModel(reportService.updateReport(reportRequest));
    }
    @PostMapping("/status/update")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN') or hasRole('MODERATOR')")
    public ReportModel updateReportStatus(@RequestBody UpdateReportStatus reportStatusRequest) {
        return ReportModel.toModel(reportService.updateReportStatus(reportStatusRequest));
    }

    @PostMapping("/structures/all")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ReportStructurePageInfo getAllReportStructures(@RequestBody SearchRequest request) {
        Page<ReportStructure> page;
        ReportStructurePageInfo pageInfo = new ReportStructurePageInfo();

        Date minDate;
        Date maxDate;
        LocalDate date;

        if (request.getMinDate() == "") {
            minDate = new Date(0);
        } else {
            date = LocalDate.parse(request.getMinDate());
            minDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        if (request.getMaxDate() == "") {
            maxDate = new Date();
        } else {
            date = LocalDate.parse(request.getMaxDate());
            maxDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }

        page = reportStructureService.loadAllReportStructures(request.getSortField(), request.getSortDir(), request.getCurrentPage(), request.getParameter(), request.getReportTypeName(), request.getIsActive(), minDate, maxDate);

        pageInfo.setCurrentPage(request.getCurrentPage());
        pageInfo.setTotalItems(page.getTotalElements());
        pageInfo.setTotalPages(page.getTotalPages());
        pageInfo.setReportStructureList(page.getContent().stream().map(ReportStructureShortModel::toModel).collect(Collectors.toList()));
        pageInfo.setSortField(request.getSortField());
        pageInfo.setSortDir(request.getSortDir());
        pageInfo.setSearchParameter(request.getParameter());

        return pageInfo;
    }
    @GetMapping("/structures/active/all")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN') or hasRole('MODERATOR')")
    public List<ReportStructureShortModel> getAllPublicReportStructures() {
        return reportStructureService.loadAllActiveReportStructure().stream().map(ReportStructureShortModel::toModel).collect(Collectors.toList());
    }
    @GetMapping("/structures/{reportStructureId}")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN') or hasRole('MODERATOR')")
    public ReportStructureModel getReportStructure(@PathVariable("reportStructureId") long reportStructureId) {
        return ReportStructureModel.toModel(reportStructureService.loadReportStructureById(reportStructureId));
    }
    @PostMapping("/structures/new/all")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public List<ReportStructureModel> findNewReportStructure(@RequestBody ReportStructureOldRequest reportStructure) {
        return reportStructureService.loadAllNewReportStructure(reportStructure.getOldReportStructureId()).stream().map(ReportStructureModel::toModel).collect(Collectors.toList());
    }
    @PostMapping("/structures/create")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ReportStructureModel createReportStructure(@RequestBody ReportStructureCreate reportStructure) {
        return ReportStructureModel.toModel(reportStructureService.createReportStructure(reportStructure));
    }

    @GetMapping("/structures/{id}/check")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public Boolean checkStatusReportStructure(@PathVariable("id") long reportStructureId) {
        return reportStructureService.checkReportStructure(reportStructureId);
    }

    @PostMapping("/structures/update")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ReportStructureModel updateReportStructure(@RequestBody ReportStructureCreate reportStructure) {
        return ReportStructureModel.toModel(reportStructureService.updateReportStructure(reportStructure));
    }

    @PostMapping("/structures/status/update")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ReportStructureModel updateStatusReportStructure(@RequestBody ReportStructureStatusRequest reportStructureStatus) {
        return ReportStructureModel.toModel(reportStructureService.updateStatusReportStructure(reportStructureStatus));
    }


    @GetMapping("/types/all")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN') or hasRole('MODERATOR')")
    public List<ReportTypeModel> allReportTypes() {
        return reportTypeService.loadAllReportTypes().stream().map(ReportTypeModel::toModel).collect(Collectors.toList());
    }
    @PostMapping("/types/check")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public Boolean checkReportType(@RequestBody CreateReportType reportType) {
        return reportTypeService.canCreateReportType(reportType.getName());
    }
    @PostMapping("/types/create")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ReportTypeModel createReportType(@RequestBody CreateReportType reportType) {
        return ReportTypeModel.toModel(reportTypeService.createReportType(reportType.getName()));
    }

    @PostMapping("/types/update/{reportTypeId}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ReportTypeModel updateReportType(@PathVariable("reportTypeId") long reportTypeId, @RequestBody UpdateReportType updateReportType) {
        return ReportTypeModel.toModel(reportTypeService.updateReportType(reportTypeId, updateReportType.getReportTypeName()));
    }

    @PostMapping("/change/update")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN')")
    public ReportModel updateReportChange(ReportCanChange reportCanChange) {
        return ReportModel.toModel(reportService.updateReportCanChange(reportCanChange));
    }

    @DeleteMapping("/delete/{reportId}")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN') or hasRole('MODERATOR')")
    public boolean deleteReport(@PathVariable("reportId") long reportId, @RequestHeader("Authorization") String authorization) {
        String headerAuth = authorization;
        String username = jwtUtils.getUserNameFromJwtToken(headerAuth.substring(7,headerAuth.length()));
        UserShortModel user = userService.loadShortUserByUsername(username);
        System.out.println((!(user.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_ADMIN.name())) || user.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_SUPER_ADMIN.name()))) && reportService.loadReportById(reportId).getUser().getId() != user.getId()) || reportService.loadReportById(reportId).getReportStatus().getId() == 3);
        if ((!(user.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_ADMIN.name())) || user.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_SUPER_ADMIN.name()))) && reportService.loadReportById(reportId).getUser().getId() != user.getId()) || reportService.loadReportById(reportId).getReportStatus().getId() == 3) {
            return false;
        }
        return reportService.deleteReport(reportId);
    }
}
