package com.ntgspiyggdrasil.yggdrasil.payload.response;

import com.ntgspiyggdrasil.yggdrasil.models.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class UserInfoResponse {
    public UserInfoResponse(long id, String lastname, String name, String patronymic, Date birthday, String academicTitle, String academicDegree, String email, String phone, String jobTitle, String orcid, String spinCode, boolean isState, String imgUrl, long facultyId, long departmentId) {
        this.id = id;
        this.lastname = lastname;
        this.name = name;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.academicTitle = academicTitle;
        this.academicDegree = academicDegree;
        this.email = email;
        this.phone = phone;
        this.jobTitle = jobTitle;
        this.orcid = orcid;
        this.spinCode = spinCode;
        this.isState = isState;
        this.imgUrl = imgUrl;
        this.facultyId = facultyId;
        this.departmentId = departmentId;
    }

    public static UserInfoResponse toModal(User entity) {
        UserInfoResponse model = new UserInfoResponse();
        model.setId(entity.getId());
        model.setLastname(entity.getLastName());
        model.setName(entity.getName());
        model.setPatronymic(entity.getPatronymic());
        model.setBirthday(entity.getBirthday());
        model.setAcademicTitle(entity.getAcademicTitle());
        model.setAcademicDegree(entity.getAcademicDegree());
        model.setEmail(entity.getEmail());
        model.setPhone(entity.getPhone());
        model.setJobTitle(entity.getJobTitle());
        model.setOrcid(entity.getOrcid());
        model.setSpinCode(entity.getSpinCode());
        model.setState(entity.getIsState());
        model.setImgUrl(entity.getImgUrl());
        model.setFacultyId(entity.getDepartment().getFaculty().getId());
        model.setDepartmentId(entity.getDepartment().getId());
        return model;
    }

    private long id;
    private String lastname;
    private String name;
    private String patronymic;
    private Date birthday;
    private String academicTitle;
    private String academicDegree;
    private String email;
    private String phone;
    private String jobTitle;
    private String orcid;
    private String spinCode;
    private boolean isState;
    private String imgUrl;
    private long facultyId;
    private long departmentId;
}
