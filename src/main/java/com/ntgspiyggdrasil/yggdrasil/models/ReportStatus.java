package com.ntgspiyggdrasil.yggdrasil.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@Table(name = "reports_statuses")
@NoArgsConstructor
public class ReportStatus {
    public ReportStatus(String name) {
        this.name = name;
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Transient
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reportStatus")
    private Set<Report> reports;
}