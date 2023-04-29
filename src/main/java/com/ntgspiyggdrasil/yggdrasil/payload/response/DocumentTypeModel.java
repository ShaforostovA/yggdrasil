package com.ntgspiyggdrasil.yggdrasil.payload.response;

import com.ntgspiyggdrasil.yggdrasil.models.DocumentType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DocumentTypeModel {
    public static DocumentTypeModel toModel(DocumentType entity) {
        DocumentTypeModel model = new DocumentTypeModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        return model;
    }
    private long id;
    private String name;
}
