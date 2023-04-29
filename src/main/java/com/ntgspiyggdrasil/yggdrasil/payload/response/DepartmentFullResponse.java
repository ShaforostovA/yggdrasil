package com.ntgspiyggdrasil.yggdrasil.payload.response;

import com.google.gson.Gson;
import com.ntgspiyggdrasil.yggdrasil.models.Department;

import com.ntgspiyggdrasil.yggdrasil.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.Iterator;

@Data
@AllArgsConstructor
public class DepartmentFullResponse {
    public DepartmentFullResponse(Department department) {
        this.id = department.getId();
        this.name = department.getName();
        this.shortName = department.getShortName();
        this.description = department.getDescription();
        this.isActive = department.getIsActive();
        this.dateCreate = department.getDateCreate();
        this.dateUpdate = department.getDateUpdate();
        this.faculty = new Gson().toJson(new FacultyFullResponse(department.getFaculty()));
        Iterator<User> userList = department.getUsers().iterator();
        this.users = "[";
        while (userList.hasNext()) {
            User user = userList.next();
            this.users += new Gson().toJson(new UserInfoShortResponse(user));
            if(userList.hasNext()) {
                this.users += ", ";
            }
        }
        userList = null;
        this.users += "]";
        System.out.println(this.users);
//        this.users = new Gson().toJson(department.getUsers());
    }

    private Long id;
    private String name;
    private String shortName;
    private String description;
    private Boolean isActive;
    private Date dateCreate;
    private Date dateUpdate;
    private String faculty;
    private String users;
}
