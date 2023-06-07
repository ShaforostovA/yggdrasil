package com.ntgspiyggdrasil.yggdrasil.payload.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class UpdateStudent {
    private Long studentId;
    private String phone;
    private String email;
    private String lastName;
    private String name;
    private String patronymic;
    private String groupName;
    private Integer yearStart;
    private Integer yearEnd;
    private Date birthday;
    private Long departmentId;
}
