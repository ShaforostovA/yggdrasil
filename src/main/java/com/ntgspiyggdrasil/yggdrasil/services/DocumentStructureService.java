package com.ntgspiyggdrasil.yggdrasil.services;

import com.ntgspiyggdrasil.yggdrasil.models.DocumentStructure;
import com.ntgspiyggdrasil.yggdrasil.models.DocumentType;
import com.ntgspiyggdrasil.yggdrasil.payload.request.DocumentStructureStatusRequest;
import com.ntgspiyggdrasil.yggdrasil.payload.request.DocumentStructureCreate;
import com.ntgspiyggdrasil.yggdrasil.payload.response.DepartmentModel;
import com.ntgspiyggdrasil.yggdrasil.payload.response.DocumentStructureModel;
import com.ntgspiyggdrasil.yggdrasil.payload.response.DocumentStructurePageInfo;
import com.ntgspiyggdrasil.yggdrasil.payload.response.DocumentStructuresShortResponse;
import com.ntgspiyggdrasil.yggdrasil.repository.DocumentRepository;
import com.ntgspiyggdrasil.yggdrasil.repository.DocumentStructureRepository;
import com.ntgspiyggdrasil.yggdrasil.repository.DocumentTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DocumentStructureService {
    @Autowired
    private DocumentStructureRepository documentStructureRepository;
    @Autowired
    private DocumentTypeRepository documentTypeRepository;
    @Autowired
    private DocumentRepository documentRepository;

    public Iterable<DocumentStructure> getDocumentStructures() {
        return documentStructureRepository.findAllByIsActive(true);
    }
    public DocumentStructureModel getDocumentStructure(long id) {
        return DocumentStructureModel.toModel(documentStructureRepository.findById(id).orElseThrow());
    }
    public DocumentStructurePageInfo getAdminDocumentStructuresShort(String sortField, String sortDir, int pageNumber, String parameter, String documentTypeName, Boolean isActive, Date minDate, Date maxDate) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 15, sort);
        DocumentStructurePageInfo pageInfo = new DocumentStructurePageInfo();

        Page<DocumentStructure> page = documentStructureRepository.findAllSort(parameter, documentTypeName, isActive, minDate, maxDate, pageable);

        ArrayList<DocumentStructuresShortResponse> documentStructuresShortResponse = new ArrayList<DocumentStructuresShortResponse>(page.getSize());
        for (DocumentStructure item : page.getContent()) {
            documentStructuresShortResponse.add(new DocumentStructuresShortResponse(item.getId(), item.getName(), item.getDescription(), item.getIsActive(), item.getDateCreate()));
        }
        Iterable<DocumentStructuresShortResponse> documentStructuresShort = documentStructuresShortResponse;

        pageInfo.setCurrentPage(pageNumber);
        pageInfo.setTotalItems(page.getTotalElements());
        pageInfo.setTotalPages(page.getTotalPages());
        pageInfo.setDocumentStructureList(documentStructuresShort);
        pageInfo.setSortField(sortField);
        pageInfo.setSortDir(sortDir);
        pageInfo.setSearchParameter(parameter);
        return pageInfo;
    }

    public Iterable<DocumentStructuresShortResponse> getPublicDocumentStructuresShort() {
        ArrayList<DocumentStructuresShortResponse> documentStructuresShortResponse = new ArrayList<DocumentStructuresShortResponse>(documentStructureRepository.findAllByIsActive(true).size());
        for (DocumentStructure item : documentStructureRepository.findAllByIsActive(true)) {
            documentStructuresShortResponse.add(new DocumentStructuresShortResponse(item.getId(), item.getName(), item.getDescription(), item.getIsActive(), item.getDateCreate()));
        }
        Iterable<DocumentStructuresShortResponse> documentStructuresShort = documentStructuresShortResponse;
        return documentStructuresShort;
    }

    public DocumentStructure createDocumentStructure(DocumentStructureCreate documentStructure) {
        DocumentType documentType = documentTypeRepository.findById(documentStructure.getDocumentTypeId()).orElseThrow();
        DocumentStructure newDocumentStructure = new DocumentStructure();
        newDocumentStructure.setIsActive(true);
        newDocumentStructure.setDocumentType(documentType);
        newDocumentStructure.setStructure(documentStructure.getStructureData());
        newDocumentStructure.setName(documentStructure.getName());
        newDocumentStructure.setDescription(documentStructure.getDescription());
        newDocumentStructure.setDateCreate(new Date());
        newDocumentStructure.setDateUpdate(new Date());

        return documentStructureRepository.save(newDocumentStructure);
    }

    public DocumentStructure updateDocumentStructure(DocumentStructureCreate documentStructure) {
        if (documentRepository.findAllByDocumentStructureIdAndStatus(documentStructure.getId(), 4L).size() == 0) {
            //documentStructureRepository.updateDocumentStructure(documentStructure.getId(), documentStructure.getName(), documentStructure.getDescription(), documentStructure.getDocumentTypeId(), documentStructure.getStructureData());
            DocumentStructure documentStructureUpdate = documentStructureRepository.findById(documentStructure.getId()).orElseThrow();
            documentStructureUpdate.setName(documentStructure.getName());
            documentStructureUpdate.setDescription(documentStructure.getDescription());
            documentStructureUpdate.setDateUpdate(new Date());
            DocumentType documentType = documentTypeRepository.findById(documentStructure.getDocumentTypeId()).orElseThrow();
            documentStructureUpdate.setDocumentType(documentType);
            documentStructureUpdate.setStructure(documentStructure.getStructureData());
//            return documentStructureRepository.findById(documentStructure.getId()).orElseThrow();
            return documentStructureRepository.save(documentStructureUpdate);
        }
        documentStructureRepository.updateStatusDocumentStructure(documentStructure.getId(), false);
        DocumentType documentType = documentTypeRepository.findById(documentStructure.getDocumentTypeId()).orElseThrow();
        DocumentStructure newDocumentStructure = new DocumentStructure();
        newDocumentStructure.setIsActive(true);
        newDocumentStructure.setDocumentType(documentType);
        newDocumentStructure.setStructure(documentStructure.getStructureData());
        newDocumentStructure.setName(documentStructure.getName());
        newDocumentStructure.setDescription(documentStructure.getDescription());
        newDocumentStructure.setDateCreate(new Date());
        newDocumentStructure.setDateUpdate(new Date());
        newDocumentStructure.setOldStructureId(documentStructure.getId());
        DocumentStructure readyStructure = documentStructureRepository.save(newDocumentStructure);
        documentRepository.replacementDocumentStructure(readyStructure.getOldStructureId(), readyStructure.getId());
        return readyStructure;
    }

    public DocumentStructure updateStatusDocumentStructure(DocumentStructureStatusRequest status) {
        documentStructureRepository.updateStatusDocumentStructure(status.getId(), status.getIsActive());

        documentRepository.revActivateDocuments(status.getId(), status.getIsActive());

        return documentStructureRepository.findById(status.getId()).orElseThrow();
    }

    public Boolean checkDocumentStructure(long documentStructureId) {
        return documentStructureRepository.existsById(documentStructureId);
    }

    public List<DocumentStructure> loadAllNewDocumentStructure(long oldDocumentStructureId) {
        return documentStructureRepository.findAllByOldStructureId(oldDocumentStructureId);
    }
}