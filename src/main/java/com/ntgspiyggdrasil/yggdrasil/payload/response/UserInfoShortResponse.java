package com.ntgspiyggdrasil.yggdrasil.payload.response;

import com.ntgspiyggdrasil.yggdrasil.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
public class UserInfoShortResponse {
    public UserInfoShortResponse (User user) {
        id = user.getId();
        lastName = user.getName();
        name = user.getName();
        patronymic = user.getPatronymic();
        imgUrl = user.getImgUrl();
        department = "{id:\"" + user.getDepartment().getId() + "\", name:\""+ user.getDepartment().getShortName() +"\"}";
        dateCreate = user.getDateCreate();
    }
    private long id;
    private String lastName;
    private String name;
    private String patronymic;
    private String imgUrl;
    private String department;
    private Date dateCreate;
}
