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
@Table(name = "departments")
@NoArgsConstructor
public class Department {

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    private Faculty faculty;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    private List<User> users;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    private List<DocumentPermission> DocumentPermissions;
}