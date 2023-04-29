package com.ntgspiyggdrasil.yggdrasil.payload.response;

import com.ntgspiyggdrasil.yggdrasil.models.Faculty;
import lombok.Data;

import java.util.Date;

@Data
public class FacultyFullResponse {
    public FacultyFullResponse(Faculty faculty) {
        id = faculty.getId();
        name = faculty.getName();
        shortName = faculty.getShortName();
        description = faculty.getDescription();
        isActive = faculty.getIsActive();
        dateCreate = faculty.getDateCreate();
        dateUpdate = faculty.getDateUpdate();
    }
    private Long id;
    private String name;
    private String shortName;
    private String description;
    private Boolean isActive;
    private Date dateCreate;
    private Date dateUpdate;
}
