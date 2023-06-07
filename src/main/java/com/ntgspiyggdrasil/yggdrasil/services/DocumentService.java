package com.ntgspiyggdrasil.yggdrasil.services;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;
import com.ntgspiyggdrasil.yggdrasil.models.*;
import com.ntgspiyggdrasil.yggdrasil.payload.request.DatePerRequest;
import com.ntgspiyggdrasil.yggdrasil.payload.request.DepartmentDocumentsRequest;
import com.ntgspiyggdrasil.yggdrasil.payload.request.DocumentCreateRequest;
import com.ntgspiyggdrasil.yggdrasil.payload.request.FacultyDocumentsRequest;
import com.ntgspiyggdrasil.yggdrasil.payload.response.DocumentShortModel;
import com.ntgspiyggdrasil.yggdrasil.payload.response.StatisticDocumentResponse;
import com.ntgspiyggdrasil.yggdrasil.payload.response.UserShortModel;
import com.ntgspiyggdrasil.yggdrasil.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.apache.tomcat.util.json.JSONParser;
import org.hibernate.dialect.PostgreSQLJsonbJdbcType;
import org.hibernate.dialect.PostgreSQLPGObjectJdbcType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.print.Doc;
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
    public Page<Document> getDocuments(String parameter, String sortField, String sortDir, int pageNumber, String statusName, Date minDate, Date maxDate, String documentType) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 15, sort);
        return documentRepository.findAllByDocumentForAdmin(parameter, statusName, minDate, maxDate, documentType, pageable);
    }

    public Page<Document> getDocumentsByKeyWords(String keyWords, String sortField, String sortDir, int pageNumber, String statusName, Date minDate, Date maxDate, String documentType) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 15, sort);
        return documentRepository.findAllByDocumentForAdminKeyWord(keyWords, statusName, minDate, maxDate, documentType, pageable);
    }

    public Page<Document> getDocumentsByDepartmentId(long departmentId, long userId, String parameter, String sortField, String sortDir, int pageNumber, String statusName, Date minDate, Date maxDate, String documentType) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 15, sort);

        return documentRepository.findAllByUserDepartmentId(departmentId, userId, parameter, statusName, minDate, maxDate, documentType, pageable);
    }

    public Page<Document> loadDocumentsByDepartmentKeyword(long departmentId, long userId, String keyWords, String sortField, String sortDir, int pageNumber, String statusName, Date minDate, Date maxDate, String documentType) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 15, sort);

        return documentRepository.findAllByDepartmentAndKeyWord(departmentId, userId, keyWords, statusName, minDate, maxDate, documentType, pageable);
    }

    public Page<Document> getDocumentsByUserId(long userId, String parameter, String sortField, String sortDir, int pageNumber, String statusName, Date minDate, Date maxDate, String documentType) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 15, sort);

        return documentRepository.findAllByUserIdPageable(userId, parameter, statusName, minDate, maxDate, documentType, pageable);
    }

    public Page<Document> getDocumentsByUserIdKeyword(long userId, String keyWords, String sortField, String sortDir, int pageNumber, String statusName, Date minDate, Date maxDate, String documentType) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 15, sort);

        return documentRepository.findAllByUserIdPageableKeyWord(userId, keyWords, statusName, minDate, maxDate, documentType, pageable);
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
        Document document = documentRepository.findById(documentId).orElseThrow(() -> new EntityNotFoundException("DocumentEntity not found with id: " + documentId));
//        documentRepository.updateDocumentDataById(documentId, documentData);
        document.setDocumentData(documentData);
        document.setDateUpdate(new Date());
        //return documentRepository.findById(documentId).orElseThrow();
        return documentRepository.save(document);
    }

    public List<StatisticDocumentResponse> getStatisticsUser(long userId, Date minDate, Date maxDate) {
        List<DocumentType> documentTypes = documentTypeRepository.findAll();
        List<StatisticDocumentResponse> statisticResponses = new ArrayList<>();
        documentTypes.forEach(documentType -> {
            for (int i = 1; i <= 4; ++i) {
                StatisticDocumentResponse statisticData = new StatisticDocumentResponse();
                List<Document> documents = documentRepository.findCountDocumentUser(userId, i, documentType.getId(), minDate, maxDate);
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

    public List<StatisticDocumentResponse> getStatisticsDepartment(long departmentId, Date minDate, Date maxDate) {
        List<DocumentType> documentTypes = documentTypeRepository.findAll();
        List<StatisticDocumentResponse> statisticResponses = new ArrayList<>();
        documentTypes.forEach(documentType -> {
            for (int i = 1; i <= 4; ++i) {
                StatisticDocumentResponse statisticData = new StatisticDocumentResponse();
                List<Document> documents = documentRepository.findCountDocumentByDepartmentId(departmentId, i, documentType.getId(), minDate, maxDate);
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

    public List<StatisticDocumentResponse> getStatisticsFaculty(long facultyId, Date minDate, Date maxDate) {
        List<DocumentType> documentTypes = documentTypeRepository.findAll();
        List<StatisticDocumentResponse> statisticResponses = new ArrayList<>();
        documentTypes.forEach(documentType -> {
            for (int i = 1; i <= 4; ++i) {
                StatisticDocumentResponse statisticData = new StatisticDocumentResponse();
                List<Document> documents = documentRepository.findCountDocumentByFacultyId(facultyId, i, documentType.getId(), minDate, maxDate);
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

    public List<StatisticDocumentResponse> getStatisticsAll(Date minDate, Date maxDate) {
        List<DocumentType> documentTypes = documentTypeRepository.findAll();
        List<StatisticDocumentResponse> statisticResponses = new ArrayList<>();
        documentTypes.forEach(documentType -> {
            for (int i = 1; i <= 4; ++i) {
                StatisticDocumentResponse statisticData = new StatisticDocumentResponse();
                List<Document> documents = documentRepository.findCountDocumentAll(i, documentType.getId(), minDate, maxDate);
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

    public void updateDocumentStatusRemake(long documentStructureId) {
        documentRepository.updateDocumentStatusRemake(documentStructureId);
    }

    public List<Document> loadAllDocuments(FacultyDocumentsRequest facultyDocuments) {
        return documentRepository.findDocumentAllByStructureIdFacultyId(facultyDocuments.getDocumentStructureId(), facultyDocuments.getFacultyId(), facultyDocuments.getMinDate(), facultyDocuments.getMaxDate());
    }

    public List<Document> loadAllDocuments(DepartmentDocumentsRequest departmentDocuments) {
        return documentRepository.findDocumentAllByStructureIdDepartmentId(departmentDocuments.getDocumentStructureId(), departmentDocuments.getDepartmentId(), departmentDocuments.getMinDate(), departmentDocuments.getMaxDate());
    }

    public Boolean archivingDocuments(long documentStructureId, long documentStatusId, Date minDate, Date maxDate) {
        documentRepository.archivingDocuments(documentStructureId, documentStatusId, minDate, maxDate);
        return true;
    }

    public List<Document> getFacultyDocumentsForExport(long documentStructureId, long faultyId, Date minDate, Date maxDate) {
        return documentRepository.findAllByFacultyForExport(documentStructureId, faultyId, minDate, maxDate);
    }
    public List<Document> getDepartmentDocumentsForExport(long documentStructureId, long departmentId, Date minDate, Date maxDate) {
        return documentRepository.findAllByDepartmentForExport(documentStructureId, departmentId, minDate, maxDate);
    }
}
