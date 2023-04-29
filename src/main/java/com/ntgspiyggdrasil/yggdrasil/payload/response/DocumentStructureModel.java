package com.ntgspiyggdrasil.yggdrasil.payload.response;

import com.ntgspiyggdrasil.yggdrasil.models.DocumentStructure;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;

@Data
@NoArgsConstructor
public class DocumentStructureModel {
    public static DocumentStructureModel toModel(DocumentStructure entity) {
        DocumentStructureModel model = new DocumentStructureModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setDescription(entity.getDescription());
        model.setStructure(entity.getStructure());
        model.setDateCreate(entity.getDateCreate());
        model.setDateUpdate(entity.getDateUpdate());
        model.setOldStructureId(entity.getOldStructureId());
        model.setDocumentType(DocumentTypeModel.toModel(entity.getDocumentType()));
        return model;
    }
    private Long id;
    private String name;
    private String description;
    private String structure;
    private Date dateCreate;
    private Date dateUpdate;
    private Long oldStructureId;
    private DocumentTypeModel documentType;
}
