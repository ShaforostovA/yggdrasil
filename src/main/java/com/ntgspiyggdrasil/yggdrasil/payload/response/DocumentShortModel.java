package com.ntgspiyggdrasil.yggdrasil.payload.response;

import com.ntgspiyggdrasil.yggdrasil.models.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class DocumentShortModel {
    public static DocumentShortModel toModel(Document entity) {
        DocumentShortModel model = new DocumentShortModel();
        model.setId(entity.getId());
        model.setDateCreate(entity.getDateCreate());
        model.setDateUpdate(entity.getDateUpdate());
        model.setDocumentData(entity.getDocumentData());
        model.setCanChange(entity.getCanChange());
        model.setUser(UserShortModel.toModel(entity.getUser()));
        model.setDocumentStatus(DocumentStatusModel.toModel(entity.getDocumentStatus()));
        model.setDocumentStructure(DocumentStructureModel.toModel(entity.getDocumentStructure()));
        if (!entity.getKeyWords().isEmpty() && entity.getKeyWords() != null) {
            model.setKeyWords(entity.getKeyWords().stream().map(KeyWordModel::toModel).collect(Collectors.toList()));
        }
        return model;
    }
    private long id;
    private Date dateCreate;
    private Date dateUpdate;
    private String documentData;
    private boolean canChange;
    private UserShortModel user;
    private DocumentStatusModel documentStatus;
    private DocumentStructureModel documentStructure;
    private List<KeyWordModel> keyWords = new ArrayList<>();
}
