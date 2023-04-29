package com.ntgspiyggdrasil.yggdrasil.payload.response;

import com.ntgspiyggdrasil.yggdrasil.models.Faculty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FacultyResponse {
    public FacultyResponse(Faculty faculty) {
        id = faculty.getId();
        name = faculty.getName();
        shortName = faculty.getShortName();
        description = faculty.getDescription();
    }
    private long id;
    private String name;
    private String shortName;
    private String description;
}
