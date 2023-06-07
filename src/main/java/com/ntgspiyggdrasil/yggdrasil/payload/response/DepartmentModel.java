package com.ntgspiyggdrasil.yggdrasil.payload.response;

import com.ntgspiyggdrasil.yggdrasil.models.Department;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class DepartmentModel {
    public static DepartmentModel toModel(Department entity) {
        DepartmentModel model = new DepartmentModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setShortName(entity.getShortName());
        model.setDescription(entity.getDescription());
        model.setIsActive(entity.getIsActive());
        model.setDateCreate(entity.getDateCreate());
        model.setDateUpdate(entity.getDateUpdate());
        model.setFaculty(FacultyNoUserModel.toModel(entity.getFaculty()));
        if (entity.getUsers() != null) {
            model.setUsers(entity.getUsers().stream().map(UserModel::toModel).collect(Collectors.toList()));
        }
        return model;
    }
    private Long id;
    private String name;
    private String shortName;
    private String description;
    private Boolean isActive;
    private Date dateCreate;
    private Date dateUpdate;
    private FacultyNoUserModel faculty;
    private List<UserModel> users = new ArrayList<>();
}
