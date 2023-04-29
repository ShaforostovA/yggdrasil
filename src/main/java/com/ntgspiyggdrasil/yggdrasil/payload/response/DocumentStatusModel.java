package com.ntgspiyggdrasil.yggdrasil.payload.response;

import com.ntgspiyggdrasil.yggdrasil.models.DocumentStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DocumentStatusModel {
    public static DocumentStatusModel toModel(DocumentStatus entity) {
        DocumentStatusModel model = new DocumentStatusModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        return model;
    }
    private long id;
    private String name;
}
