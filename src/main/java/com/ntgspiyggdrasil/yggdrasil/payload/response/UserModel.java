package com.ntgspiyggdrasil.yggdrasil.payload.response;

import com.ntgspiyggdrasil.yggdrasil.models.Role;
import com.ntgspiyggdrasil.yggdrasil.models.User;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class UserModel {
    public static UserModel toModel(User entity) {
        UserModel model = new UserModel();
        model.setId(entity.getId());
        model.setUsername(entity.getUsername());
        model.setPhone(entity.getPhone());
        model.setEmail(entity.getEmail());
        model.setImgUrl(entity.getImgUrl());
        model.setIsActive(entity.getIsActive());
        model.setLastName(entity.getLastName());
        model.setName(entity.getName());
        model.setPatronymic(entity.getPatronymic());
        model.setJobTitle(entity.getJobTitle());
        model.setIsState(entity.getIsState());
        model.setAcademicTitle(entity.getAcademicTitle());
        model.setAcademicDegree(entity.getAcademicDegree());
        model.setOrcid(entity.getOrcid());
        model.setSpinCode(entity.getSpinCode());
        model.setBirthday(entity.getBirthday());
        model.setDateCreate(entity.getDateCreate());
        model.setDateUpdate(entity.getDateUpdate());
        model.setDepartment(new DepartmentResponse(entity.getDepartment()));
        model.setRoles(entity.getRoles());
        return model;
    }
    private Long id;
    private String username;
    private String phone;
    private String email;
    private String imgUrl;
    private Boolean isActive;
    private String lastName;
    private String name;
    private String patronymic;
    private String jobTitle;
    private Boolean isState;
    private String academicTitle;
    private String academicDegree;
    private String orcid;
    private String spinCode;
    private Date birthday;
    private Date dateCreate;
    private Date dateUpdate;
    private DepartmentResponse department;
    private Set<Role> roles = new HashSet<>();
}
