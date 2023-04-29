package com.ntgspiyggdrasil.yggdrasil.payload.response;

import com.ntgspiyggdrasil.yggdrasil.models.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class DocumentModel {
    public static DocumentModel toModel(Document entity) {
        DocumentModel model = new DocumentModel();
        model.setId(entity.getId());
        model.setDocumentData(entity.getDocumentData());
        model.setCanChange(entity.getCanChange());
        model.setDateCreate(entity.getDateCreate());
        model.setDateUpdate(entity.getDateUpdate());
        model.setUser(UserModel.toModel(entity.getUser()));
        model.setDocumentStatus(DocumentStatusModel.toModel(entity.getDocumentStatus()));
        model.setKeyWords(entity.getKeyWords().stream().map(KeyWordModel::toModel).collect(Collectors.toList()));
        return model;
    }
    private Long id;
    private String documentData;
    private Boolean canChange;
    private Date dateCreate;
    private Date dateUpdate;
    private UserModel user;
    private DocumentStatusModel documentStatus;
    private List<KeyWordModel> keyWords;
}
