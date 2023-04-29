package com.ntgspiyggdrasil.yggdrasil.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;
import java.util.Map;

@Data
@Entity
@Table(name = "reports")
@NoArgsConstructor
public class Report {
    public Report(ReportStructure reportStructure, User user, ReportType reportType, ReportStatus reportStatus, String reportData, Boolean canChange, Date dateStart, Date dateEnd, Date dateCreate, Date dateUpdate) {
        this.reportStructure = reportStructure;
        this.user = user;
        this.reportType = reportType;
        this.reportStatus = reportStatus;
        this.reportData = reportData;
        this.canChange = canChange;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.dateCreate = dateCreate;
        this.dateUpdate = dateUpdate;
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "report_structure_id", nullable = false)
    private ReportStructure reportStructure;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "report_type_id", nullable = false)
    private ReportType reportType;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "report_status_id", nullable = false)
    private ReportStatus reportStatus;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "report_data", columnDefinition = "jsonb")
    private String reportData;
    @Column(name = "can_change")
    private Boolean canChange;
    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    @Column(name = "date_create")
    private Date dateCreate;
    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    @Column(name = "date_update")
    private Date dateUpdate;
    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    @Column(name = "date_start")
    private Date dateStart;
    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    @Column(name = "date_end")
    private Date dateEnd;
}