package com.ntgspiyggdrasil.yggdrasil.payload.request;

import lombok.Data;

@Data
public class DepartmentRequest {
    long id;
    String shortName;
    String name;
    String description;
    long facultyId;
}
