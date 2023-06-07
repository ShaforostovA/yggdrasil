package com.ntgspiyggdrasil.yggdrasil.payload.request;

import lombok.Data;

@Data
public class FacultyRequest {
    long id;
    String shortName;
    String name;
    String description;
}
