package com.ntgspiyggdrasil.yggdrasil.payload.response;

import com.ntgspiyggdrasil.yggdrasil.models.Student;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class StudentModel {
    public static StudentModel toModel(Student entity) {
        StudentModel model = new StudentModel();
        model.setId(entity.getId());
        model.setPhone(entity.getPhone());
        model.setEmail(entity.getEmail());
        model.setLastName(entity.getLastName());
        model.setName(entity.getName());
        model.setPatronymic(entity.getPatronymic());
        model.setGroupName(entity.getGroupName());
        model.setIsTrained(entity.getIsTrained());
        model.setYearStart(entity.getYearStart());
        model.setYearEnd(entity.getYearEnd());
        model.setDateCreate(entity.getDateCreate());
        model.setDateUpdate(entity.getDateUpdate());
        model.setBirthday(entity.getBirthday());
        model.setDepartment(DepartmentModel.toModel(entity.getDepartment()));
        return model;
    }
    private Long id;
    private String phone;
    private String email;
    private String lastName;
    private String name;
    private String patronymic;
    private String groupName;
    private Boolean isTrained;
    private Integer yearStart;
    private Integer yearEnd;
    private Date birthday;
    private Date dateCreate;
    private Date dateUpdate;
    private DepartmentModel department;
}
