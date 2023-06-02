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
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_structure_id", referencedColumnName = "id")
    private ReportStructure reportStructure;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_status_id", referencedColumnName = "id")
    private ReportStatus reportStatus;
}