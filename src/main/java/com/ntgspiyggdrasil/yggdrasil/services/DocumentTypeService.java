package com.ntgspiyggdrasil.yggdrasil.services;

import com.ntgspiyggdrasil.yggdrasil.models.DocumentType;
import com.ntgspiyggdrasil.yggdrasil.payload.response.DocumentTypeModel;
import com.ntgspiyggdrasil.yggdrasil.repository.DocumentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentTypeService {
    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    public List<DocumentType> loadAllDocumentTypes() {
        return documentTypeRepository.findAll();
    }

    public Boolean canCreateDocumentType(String documentTypeName) {
        return documentTypeRepository.existsByName(documentTypeName);
    }

    public DocumentType createDocumentType(String documentTypeName) {
        DocumentType documentType = new DocumentType();
        documentType.setName(documentTypeName);
        return documentTypeRepository.save(documentType);
    }

    public DocumentType updateDocumentType(long documentTypeId, String documentTypeName) {
        DocumentType documentType = documentTypeRepository.findById(documentTypeId).orElseThrow();
        documentType.setName(documentTypeName);
        return documentTypeRepository.save(documentType);
    }
}
