package com.ntgspiyggdrasil.yggdrasil.payload.response;

import com.ntgspiyggdrasil.yggdrasil.models.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UserShortModel {
    public static UserShortModel toModel(User entity) {
        UserShortModel model = new UserShortModel();
        model.setId(entity.getId());
        model.setLastName(entity.getLastName());
        model.setName(entity.getName());
        model.setPatronymic(entity.getPatronymic());
        model.setDepartment(DepartmentModel.toModel(entity.getDepartment()));
        model.setActive(entity.getIsActive());
        model.setState(entity.getIsState());
        model.setDateCreate(entity.getDateCreate());
        model.setAuthorities(entity.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList()));
        return model;
    }
    private long id;
    private String lastName;
    private String name;
    private String patronymic;
    private DepartmentModel department;
    private boolean isActive;
    private boolean isState;
    private Date dateCreate;
    private Collection<? extends GrantedAuthority> authorities;
}
