package com.ntgspiyggdrasil.yggdrasil.controllers;

import com.google.gson.Gson;
import com.ntgspiyggdrasil.yggdrasil.models.Document;
import com.ntgspiyggdrasil.yggdrasil.models.DocumentStructure;
import com.ntgspiyggdrasil.yggdrasil.models.ERole;
import com.ntgspiyggdrasil.yggdrasil.payload.request.DatePerRequest;
import com.ntgspiyggdrasil.yggdrasil.payload.request.DocumentCreateRequest;
import com.ntgspiyggdrasil.yggdrasil.payload.request.DocumentDataRequest;
import com.ntgspiyggdrasil.yggdrasil.payload.request.DocumentStatusRequest;
import com.ntgspiyggdrasil.yggdrasil.payload.response.*;
import com.ntgspiyggdrasil.yggdrasil.security.jwt.JwtUtils;
import com.ntgspiyggdrasil.yggdrasil.services.DocumentService;
import com.ntgspiyggdrasil.yggdrasil.services.DocumentStructureService;
import com.ntgspiyggdrasil.yggdrasil.services.UserService;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;
import org.xml.sax.ext.EntityResolver2;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/documents")
public class DocumentController {
    @Autowired
    private DocumentService documentService;
    @Autowired
    private DocumentStructureService documentStructureService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;


    @DeleteMapping("/delete/{documentId}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public boolean deleteDocument(@PathVariable("documentId") long documentId, @RequestHeader("Authorization") String authorization) {
        String headerAuth = authorization;
        String username = jwtUtils.getUserNameFromJwtToken(headerAuth.substring(7,headerAuth.length()));
        UserShortModel user = userService.loadShortUserByUsername(username);
        return documentService.deleteDocument(documentId, user);
    }
    @PostMapping("/create")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public DocumentModel createDocument(@RequestBody DocumentCreateRequest documentCreateRequest) {
        return DocumentModel.toModel(documentService.createDocument(documentCreateRequest));
    }
    @PostMapping("/{documentId}/status/update")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public DocumentModel updateDocumentStatus(@PathVariable("documentId") long documentId, @RequestBody DocumentStatusRequest documentStatus) {
        return DocumentModel.toModel(documentService.updateDocumentStatus(documentId, documentStatus.getDocumentStatusId()));
    }

    @PostMapping("/{documentId}/data/update")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public DocumentModel updateDocumentData(@PathVariable("documentId") long documentId, @RequestBody DocumentDataRequest documentDataRequest) {
        return DocumentModel.toModel(documentService.updateDocumentData(documentId, documentDataRequest.getDocumentData()));
    }

    @GetMapping("/{documentId}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public DocumentShortModel getDocument(@PathVariable("documentId") long documentId, @RequestHeader("Authorization") String authorization) {
        String headerAuth = authorization;
        String username = jwtUtils.getUserNameFromJwtToken(headerAuth.substring(7,headerAuth.length()));
        UserShortModel user = userService.loadShortUserByUsername(username);
        if (user.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_ADMIN.name()))) {
            return documentService.getDocumentById(documentId);
        }
        if (user.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_MODERATOR.name()))) {
            return documentService.getDocumentByIdAndDepartment(user.getDepartment().getId(), documentId);
        }
        return documentService.getDocumentByIdAndUserId(user.getId(), documentId);
    }
    @GetMapping("/all")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<DocumentShortModel> getDocuments(@RequestHeader("Authorization") String authorization) {
        String headerAuth = authorization;
        String username = jwtUtils.getUserNameFromJwtToken(headerAuth.substring(7,headerAuth.length()));
        UserShortModel user = userService.loadShortUserByUsername(username);
        if (user.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_ADMIN.name()))) {
            return documentService.getDocuments();
        }
        if (user.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_MODERATOR.name()))) {
            return documentService.getDocumentsByDepartmentId(user.getDepartment().getId(), user.getId());
        }
        return documentService.getDocumentsByUserId(user.getId());
    }

    @GetMapping("/structures")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('SUPER_ADMIN')")
    public Iterable<DocumentStructure> getDocumentStructures() {
        return documentStructureService.getDocumentStructures();
    }
    @GetMapping("/structures/{structureId}")
    public DocumentStructureModel getUserDepartment(@PathVariable("structureId") long structureId) {
        return documentStructureService.getDocumentStructure(structureId);
    }
    @GetMapping("/structures/short")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('SUPER_ADMIN')")
    public Iterable<DocumentStructuresShortResponse> getDocumentStructuresShort() {
        return documentStructureService.getDocumentStructuresShort();
    }

    @PostMapping("/statistic/user/{userId}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<StatisticDocumentResponse> userStatistic(@PathVariable("userId") long userId, @RequestBody DatePerRequest datePerRequest) {
        if (datePerRequest.getMinDate() == null) {
            datePerRequest.setMinDate(new Date(0));
        }
        if (datePerRequest.getMaxDate() == null) {
            datePerRequest.setMaxDate(new Date());
        }
        return documentService.getStatisticsUser(userId, datePerRequest);
    }

    @PostMapping("/statistic/department/{departmentId}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<StatisticDocumentResponse> departmentStatistic(@PathVariable("departmentId") long departmentId, @RequestBody DatePerRequest datePerRequest) {
        if (datePerRequest.getMinDate() == null) {
            datePerRequest.setMinDate(new Date(0));
        }
        if (datePerRequest.getMaxDate() == null) {
            datePerRequest.setMaxDate(new Date());
        }
        return documentService.getStatisticsDepartment(departmentId, datePerRequest);
    }

    @PostMapping("/statistic/faculty/{facultyId}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<StatisticDocumentResponse> facultyStatistic(@PathVariable("facultyId") long facultyId, @RequestBody DatePerRequest datePerRequest) {
        if (datePerRequest.getMinDate() == null) {
            datePerRequest.setMinDate(new Date(0));
        }
        if (datePerRequest.getMaxDate() == null) {
            datePerRequest.setMaxDate(new Date());
        }
        return documentService.getStatisticsFaculty(facultyId, datePerRequest);
    }

    @PostMapping("/statistic/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<StatisticDocumentResponse> allStatistic(@RequestBody DatePerRequest datePerRequest) {
        if (datePerRequest.getMinDate() == null) {
            datePerRequest.setMinDate(new Date(0));
        }
        if (datePerRequest.getMaxDate() == null) {
            datePerRequest.setMaxDate(new Date());
        }
        return documentService.getStatisticsAll(datePerRequest);
    }
}
