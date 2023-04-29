package com.ntgspiyggdrasil.yggdrasil.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "username")
        })
@NoArgsConstructor
public class User {
//    public User(String username, String password, Set<Role> roles, String phone, String email, String imgUrl, String lastName, String name, String patronymic, String jobTitle, Boolean isState, String academicTitle, String academicDegree, String orcid, String spinCode, Department department, Date birthday, Date dateCreate, Date dateUpdate) {
//        this.username = username;
//        this.password = password;
//        this.roles = roles;
//        this.phone = phone;
//        this.email = email;
//        this.imgUrl = imgUrl;
//        this.lastName = lastName;
//        this.name = name;
//        this.patronymic = patronymic;
//        this.jobTitle = jobTitle;
//        this.isState = isState;
//        this.academicTitle = academicTitle;
//        this.academicDegree = academicDegree;
//        this.orcid = orcid;
//        this.spinCode = spinCode;
//        this.department = department;
//        this.birthday = birthday;
//        this.dateCreate = dateCreate;
//        this.dateUpdate = dateUpdate;
//    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "img_url")
    private String imgUrl;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "name")
    private String name;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "job_title")
    private String jobTitle;
    @Column(name = "is_state")
    private Boolean isState;
    @Column(name = "academic_title")
    private String academicTitle;
    @Column(name = "academic_degree")
    private String academicDegree;
    @Column(name = "orcid")
    private String orcid;
    @Column(name = "spin_code")
    private String spinCode;
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
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Document> documents;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Report> reports;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<KeyWord> keyWords;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<DocumentPermission> documentPermissions;
}