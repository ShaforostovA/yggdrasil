package com.ntgspiyggdrasil.yggdrasil.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@Table(name = "reports_types")
@NoArgsConstructor
public class ReportType {
    public ReportType(String name) {
        this.name = name;
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Transient
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reportType")
    private Set<Report> reports;
}