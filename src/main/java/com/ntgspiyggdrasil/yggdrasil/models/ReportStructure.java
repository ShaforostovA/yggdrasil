package com.ntgspiyggdrasil.yggdrasil.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;
import java.util.Map;
import java.util.Set;

@Data
@Entity
@Table(name = "reports_structures")
@NoArgsConstructor
public class ReportStructure {
    public ReportStructure(String name, String description, String reportStructure, Long newStructureId, Date dateUpdate, Date dateCreate) {
        this.name = name;
        this.description = description;
        this.reportStructure = reportStructure;
        this.oldStructureId = newStructureId;
        this.dateUpdate = dateUpdate;
        this.dateCreate = dateCreate;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "report_structure", columnDefinition = "jsonb")
    private String reportStructure;
    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    @Column(name = "date_create")
    private Date dateCreate;
    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    @Column(name = "date_update")
    private Date dateUpdate;
    @Column(name = "old_structure_id")
    private Long oldStructureId;
    @Transient
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reportStructure")
    private Set<Report> reports;
}
