package com.ntgspiyggdrasil.yggdrasil.services;

import com.ntgspiyggdrasil.yggdrasil.models.DocumentStructure;
import com.ntgspiyggdrasil.yggdrasil.payload.response.DocumentStructureModel;
import com.ntgspiyggdrasil.yggdrasil.payload.response.DocumentStructuresShortResponse;
import com.ntgspiyggdrasil.yggdrasil.repository.DocumentStructureRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class DocumentStructureService {
    @Autowired
    private DocumentStructureRepository documentStructureRepository;

    public Iterable<DocumentStructure> getDocumentStructures() {
        return documentStructureRepository.findAll();
    }
    public DocumentStructureModel getDocumentStructure(long id) {
        return DocumentStructureModel.toModel(documentStructureRepository.findById(id).orElseThrow());
    }
    public Iterable<DocumentStructuresShortResponse> getDocumentStructuresShort() {
        ArrayList<DocumentStructuresShortResponse> documentStructuresShortResponse = new ArrayList<DocumentStructuresShortResponse>(documentStructureRepository.findAll().size());
        for (DocumentStructure item : documentStructureRepository.findAll()) {
            documentStructuresShortResponse.add(new DocumentStructuresShortResponse(item.getId(), item.getName(), item.getDescription(), item.getDateCreate()));
        }
        Iterable<DocumentStructuresShortResponse> documentStructuresShort = documentStructuresShortResponse;
        return documentStructuresShort;
    }
}
