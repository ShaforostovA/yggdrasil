package com.ntgspiyggdrasil.yggdrasil.services;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;
import com.ntgspiyggdrasil.yggdrasil.models.*;
import com.ntgspiyggdrasil.yggdrasil.payload.request.DatePerRequest;
import com.ntgspiyggdrasil.yggdrasil.payload.request.DocumentCreateRequest;
import com.ntgspiyggdrasil.yggdrasil.payload.response.DocumentShortModel;
import com.ntgspiyggdrasil.yggdrasil.payload.response.StatisticDocumentResponse;
import com.ntgspiyggdrasil.yggdrasil.payload.response.UserShortModel;
import com.ntgspiyggdrasil.yggdrasil.repository.*;
import org.apache.tomcat.util.json.JSONParser;
import org.hibernate.dialect.PostgreSQLJsonbJdbcType;
import org.hibernate.dialect.PostgreSQLPGObjectJdbcType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private DocumentStatusRepository documentStatusRepository;
    @Autowired
    private DocumentTypeRepository documentTypeRepository;
    @Autowired
    private DocumentStructureRepository documentStructureRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private KeyWordRepository keyWordRepository;




    public DocumentShortModel getDocumentById(long documentId) {
        return DocumentShortModel.toModel(documentRepository.findById(documentId).orElseThrow());
    }
    public DocumentShortModel getDocumentByIdAndUserId(long userId, long documentId) {
        return DocumentShortModel.toModel(documentRepository.findByIdAndUserId(userId, documentId).orElseThrow());
    }
    public DocumentShortModel getDocumentByIdAndDepartment(long departmentId, long documentId) {
        return DocumentShortModel.toModel(documentRepository.findByIdAndDepartmentId(departmentId, documentId).orElseThrow());
    }
    public List<DocumentShortModel> getDocuments() {
        return documentRepository.findAllByDocumentForAdmin().stream().map(DocumentShortModel::toModel).collect(Collectors.toList());
    }

    public List<DocumentShortModel> getDocumentsByDepartmentId(long departmentId, long userId) {
        return documentRepository.findAllByUserDepartmentId(departmentId, userId).stream().map(DocumentShortModel::toModel).collect(Collectors.toList());
    }

    public List<DocumentShortModel> getDocumentsByUserId(long userId) {
        return documentRepository.findAllByUserId(userId).stream().map(DocumentShortModel::toModel).collect(Collectors.toList());
    }

    public Document createDocument(DocumentCreateRequest document) {
        DocumentStatus documentStatus = documentStatusRepository.findById(document.getDocumentStatusId()).orElseThrow();
        DocumentStructure documentStructure = documentStructureRepository.findById(document.getDocumentStructureId()).orElseThrow();
        User user = userRepository.findByUsername(document.getUsername()).orElseThrow();

        Document readyDocument = new Document();
        readyDocument.setDocumentData(document.getDocumentData());
        readyDocument.setCanChange(true);
        readyDocument.setDateCreate(new Date());
        readyDocument.setDateUpdate(new Date());
        readyDocument.setDocumentStructure(documentStructure);
        readyDocument.setUser(user);
        readyDocument.setDocumentStatus(documentStatus);

        return documentRepository.save(readyDocument);
    }

    public boolean deleteDocument(long documentId, UserShortModel user) {
        DocumentShortModel document = DocumentShortModel.toModel(documentRepository.findById(documentId).orElseThrow());
        if((document.getUser().getId() == user.getId() || document.getUser().getDepartment().getId() == user.getDepartment().getId()) && document.getDocumentStatus().getId() != 4) {
            documentRepository.deleteById(documentId);
            return true;
        }
        return false;
    }

    public Document updateDocumentStatus(long documentId, long documentStatusId) {
      documentRepository.updateDocumentStatusById(documentId, documentStatusId);
      return documentRepository.findById(documentId).orElseThrow();
    }
    public Document updateDocumentData(long documentId, String documentData) {
//        PostgreSQLJsonbJdbcType jsonbJdbcType = new PostgreSQLJsonbJdbcType();
//        JSONParser parser = new JSONParser(documentData);
//        PostgreSQLPGObjectJdbcType = (PostgreSQLPGObjectJdbcType) JSON.parser;
        documentRepository.updateDocumentDataById(documentId, documentData);
        return documentRepository.findById(documentId).orElseThrow();
    }

    public List<StatisticDocumentResponse> getStatisticsUser(long userId, DatePerRequest datePerRequest) {
        List<DocumentType> documentTypes = documentTypeRepository.findAll();
        List<StatisticDocumentResponse> statisticResponses = new ArrayList<>();
        documentTypes.forEach(documentType -> {
            for (int i = 1; i <= 4; ++i) {
                StatisticDocumentResponse statisticData = new StatisticDocumentResponse();
                List<Document> documents = documentRepository.findCountDocumentUser(userId, i, documentType.getId(), datePerRequest.getMinDate(), datePerRequest.getMaxDate());
                statisticData.setCount(documents.size());
                switch (i) {
                    case 1: statisticData.setStatusName("Черновик");
                        break;
                    case 2: statisticData.setStatusName("Готов");
                        break;
                    case 3: statisticData.setStatusName("Переделать");
                        break;
                    case 4: statisticData.setStatusName("Архив");
                        break;
                }
                statisticData.setTypeName(documentType.getName());
                statisticResponses.add(statisticData);
            }
        });
        return statisticResponses;
    }

    public List<StatisticDocumentResponse> getStatisticsDepartment(long departmentId, DatePerRequest datePerRequest) {
        List<DocumentType> documentTypes = documentTypeRepository.findAll();
        List<StatisticDocumentResponse> statisticResponses = new ArrayList<>();
        documentTypes.forEach(documentType -> {
            for (int i = 1; i <= 4; ++i) {
                StatisticDocumentResponse statisticData = new StatisticDocumentResponse();
                List<Document> documents = documentRepository.findCountDocumentByDepartmentId(departmentId, i, documentType.getId(), datePerRequest.getMinDate(), datePerRequest.getMaxDate());
                statisticData.setCount(documents.size());
                switch (i) {
                    case 1: statisticData.setStatusName("Черновик");
                        break;
                    case 2: statisticData.setStatusName("Готов");
                        break;
                    case 3: statisticData.setStatusName("Переделать");
                        break;
                    case 4: statisticData.setStatusName("Архив");
                        break;
                }
                statisticData.setTypeName(documentType.getName());
                statisticResponses.add(statisticData);
            }
        });
        return statisticResponses;
    }

    public List<StatisticDocumentResponse> getStatisticsFaculty(long facultyId, DatePerRequest datePerRequest) {
        List<DocumentType> documentTypes = documentTypeRepository.findAll();
        List<StatisticDocumentResponse> statisticResponses = new ArrayList<>();
        documentTypes.forEach(documentType -> {
            for (int i = 1; i <= 4; ++i) {
                StatisticDocumentResponse statisticData = new StatisticDocumentResponse();
                List<Document> documents = documentRepository.findCountDocumentByFacultyId(facultyId, i, documentType.getId(), datePerRequest.getMinDate(), datePerRequest.getMaxDate());
                statisticData.setCount(documents.size());
                switch (i) {
                    case 1: statisticData.setStatusName("Черновик");
                        break;
                    case 2: statisticData.setStatusName("Готов");
                        break;
                    case 3: statisticData.setStatusName("Переделать");
                        break;
                    case 4: statisticData.setStatusName("Архив");
                        break;
                }
                statisticData.setTypeName(documentType.getName());
                statisticResponses.add(statisticData);
            }
        });
        return statisticResponses;
    }

    public List<StatisticDocumentResponse> getStatisticsAll(DatePerRequest datePerRequest) {
        List<DocumentType> documentTypes = documentTypeRepository.findAll();
        List<StatisticDocumentResponse> statisticResponses = new ArrayList<>();
        documentTypes.forEach(documentType -> {
            for (int i = 1; i <= 4; ++i) {
                StatisticDocumentResponse statisticData = new StatisticDocumentResponse();
                List<Document> documents = documentRepository.findCountDocumentAll(i, documentType.getId(), datePerRequest.getMinDate(), datePerRequest.getMaxDate());
                statisticData.setCount(documents.size());
                switch (i) {
                    case 1: statisticData.setStatusName("Черновик");
                        break;
                    case 2: statisticData.setStatusName("Готов");
                        break;
                    case 3: statisticData.setStatusName("Переделать");
                        break;
                    case 4: statisticData.setStatusName("Архив");
                        break;
                }
                statisticData.setTypeName(documentType.getName());
                statisticResponses.add(statisticData);
            }
        });
        return statisticResponses;
    }
}
