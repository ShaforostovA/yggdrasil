package com.ntgspiyggdrasil.yggdrasil.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.*;

@Data
@Entity
@Table(name = "faculties")
@NoArgsConstructor
public class Faculty {
    public Faculty(String name, String shortName, String description) {
        this.name = name;
        this.shortName = shortName;
        this.description = description;
        this.isActive = true;
        this.dateCreate = new Date();
        this.dateUpdate = new Date();
        this.departments = new ArrayList<>();
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    @JdbcTypeCode(SqlTypes.LONGVARCHAR)
    private String name;
    @Column(name = "short_name")
    private String shortName;
    @Column(name = "description")
    @JdbcTypeCode(SqlTypes.LONGVARCHAR)
    private String description;
    @Column(name = "is_active")
    private Boolean isActive;
    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    @Column(name = "date_create")
    private Date dateCreate;
    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    @Column(name = "date_update")
    private Date dateUpdate;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "faculty")
    private List<Department> departments;

}
