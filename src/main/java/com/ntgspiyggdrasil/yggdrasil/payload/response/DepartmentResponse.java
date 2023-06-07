package com.ntgspiyggdrasil.yggdrasil.payload.response;

import com.ntgspiyggdrasil.yggdrasil.models.Department;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepartmentResponse {
    public DepartmentResponse(Department department) {
        id = department.getId();
        name = department.getName();
        shortName = department.getShortName();
        description = department.getDescription();
        facultyId = department.getFaculty().getId();
    }
    private long id;
    private String name;
    private String shortName;
    private String description;
    private long facultyId;
}
