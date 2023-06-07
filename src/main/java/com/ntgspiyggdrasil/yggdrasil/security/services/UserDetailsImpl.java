package com.ntgspiyggdrasil.yggdrasil.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ntgspiyggdrasil.yggdrasil.models.Department;
import com.ntgspiyggdrasil.yggdrasil.models.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String email;

    private String lastname;

    private String name;

    private String patronymic;

    @JsonIgnore
    private String password;

    private Date birthday;

    private String academicTitle;

    private String academicDegree;

    private String phone;

    private String jobTitle;

    private String orcid;

    private String spinCode;

    private boolean isState;

    private String imgUrl;

    private Department department;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id,
                           String username,
                           String email,
                           String password,
                           String lastname,
                           String name,
                           String patronymic,
                           Date birthday,
                           String academicTitle,
                           String academicDegree,
                           String phone,
                           String jobTitle,
                           String orcid,
                           String spinCode,
                           boolean isState,
                           Department department,
                           String imgUrl,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.lastname = lastname;
        this.name = name;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.academicTitle = academicTitle;
        this.academicDegree = academicDegree;
        this.phone = phone;
        this.jobTitle = jobTitle;
        this.orcid = orcid;
        this.spinCode = spinCode;
        this.isState = isState;
        this.department = department;
        this.imgUrl = imgUrl;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getLastName(),
                user.getName(),
                user.getPatronymic(),
                user.getBirthday(),
                user.getAcademicTitle(),
                user.getAcademicDegree(),
                user.getPhone(),
                user.getJobTitle(),
                user.getOrcid(),
                user.getSpinCode(),
                user.getIsState(),
                user.getDepartment(),
                user.getImgUrl(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }


    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
}