package com.ntgspiyggdrasil.yggdrasil.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;

@Entity
@Table(name = "students")
@NoArgsConstructor
@Data
public class Student {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "name")
    private String name;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "group_name")
    private String groupName;
    @Column(name = "is_trained")
    private Boolean isTrained;
    @Column(name = "year_start")
    private Integer yearStart;
    @Column(name = "year_end")
    private Integer yearEnd;
    @JdbcTypeCode(SqlTypes.DATE)
    @Column(name = "birthday")
    private Date birthday;
    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    @Column(name = "date_create")
    private Date dateCreate;
    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    @Column(name = "date_update")
    private Date dateUpdate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;
}
