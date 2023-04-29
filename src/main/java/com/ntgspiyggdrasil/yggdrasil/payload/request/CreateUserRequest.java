package com.ntgspiyggdrasil.yggdrasil.payload.request;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String lastName;
    private String name;
    private String patronymic;
    private Boolean state;
    private long departmentId;
    private String username;
    private String password;
    private String userRole;
}
