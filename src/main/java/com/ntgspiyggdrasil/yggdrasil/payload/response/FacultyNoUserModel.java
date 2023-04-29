package com.ntgspiyggdrasil.yggdrasil.payload.response;

import com.ntgspiyggdrasil.yggdrasil.models.Faculty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class FacultyNoUserModel {
    public static FacultyNoUserModel toModel(Faculty entity) {
        FacultyNoUserModel model = new FacultyNoUserModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setShortName(entity.getShortName());
        model.setDescription(entity.getDescription());
        model.setIsActive(entity.getIsActive());
        model.setDateCreate(entity.getDateCreate());
        model.setDateUpdate(entity.getDateUpdate());
        return model;
    }
    private Long id;
    private String name;
    private String shortName;
    private String description;
    private Boolean isActive;
    private Date dateCreate;
    private Date dateUpdate;
}
