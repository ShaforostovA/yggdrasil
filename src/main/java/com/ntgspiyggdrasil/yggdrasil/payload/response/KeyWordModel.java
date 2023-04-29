package com.ntgspiyggdrasil.yggdrasil.payload.response;

import com.ntgspiyggdrasil.yggdrasil.models.KeyWord;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KeyWordModel {
    public static KeyWordModel toModel(KeyWord entity) {
        KeyWordModel model = new KeyWordModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setUser(UserModel.toModel(entity.getUser()));
        return model;
    }
    private long id;
    private String name;
    private UserModel user;
}
