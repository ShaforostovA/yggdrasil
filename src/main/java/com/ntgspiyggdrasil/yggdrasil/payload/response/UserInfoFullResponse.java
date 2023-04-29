package com.ntgspiyggdrasil.yggdrasil.payload.response;

import com.google.gson.Gson;
import com.ntgspiyggdrasil.yggdrasil.models.Department;
import com.ntgspiyggdrasil.yggdrasil.models.Role;
import com.ntgspiyggdrasil.yggdrasil.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserInfoFullResponse {
    public UserInfoFullResponse(User user) {
        id = user.getId();
        username = user.getUsername();
        password = user.getPassword();
        phone = user.getPhone();
        email = user.getEmail();
        imgUrl = user.getImgUrl();
        isActive = user.getIsActive();
        lastName = user.getLastName();
        name = user.getName();
        patronymic = user.getPatronymic();
        jobTitle = user.getJobTitle();
        isState = user.getIsState();
        academicTitle = user.getAcademicTitle();
        academicDegree = user.getAcademicDegree();
        orcid = user.getOrcid();
        spinCode = user.getSpinCode();
        birthday = user.getBirthday();
        dateCreate = user.getDateCreate();
        dateUpdate = user.getDateUpdate();
        department = new Gson().toJson(user.getDepartment());
        roles = user.getRoles();
    }

    private Long id;
    private String username;
    private String password;
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
    private String department;
    private Set<Role> roles = new HashSet<>();
}
