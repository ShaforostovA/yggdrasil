package com.ntgspiyggdrasil.yggdrasil.payload.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class UserUpdateRequest {
    private Long id;
    private String phone;
    private String email;
    private String imgUrl;
    private String lastName;
    private String name;
    private String patronymic;
    private String jobTitle;
    private String academicTitle;
    private String academicDegree;
    private String orcid;
    private String spinCode;
    private Date birthday;
    private Boolean state;
    private long departmentId;
}
