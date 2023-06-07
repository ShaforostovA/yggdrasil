package com.ntgspiyggdrasil.yggdrasil.payload.response;

import com.ntgspiyggdrasil.yggdrasil.models.Department;
import com.ntgspiyggdrasil.yggdrasil.models.Faculty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class FacultyModel {
    public static FacultyModel toModel(Faculty entity) {
        FacultyModel model = new FacultyModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setShortName(entity.getShortName());
        model.setDescription(entity.getDescription());
        model.setIsActive(entity.getIsActive());
        model.setDateCreate(entity.getDateCreate());
        model.setDateUpdate(entity.getDateUpdate());
        model.setDepartments(entity.getDepartments().stream().map(DepartmentModel::toModel).collect(Collectors.toList()));
        return model;
    }
    private Long id;
    private String name;
    private String shortName;
    private String description;
    private Boolean isActive;
    private Date dateCreate;
    private Date dateUpdate;
    private List<DepartmentModel> departments;
}
