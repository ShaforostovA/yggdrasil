package com.ntgspiyggdrasil.yggdrasil.controllers;

import com.google.gson.*;
import com.ntgspiyggdrasil.yggdrasil.models.*;
import com.ntgspiyggdrasil.yggdrasil.payload.request.*;
import com.ntgspiyggdrasil.yggdrasil.payload.response.*;
import com.ntgspiyggdrasil.yggdrasil.security.jwt.JwtUtils;
import com.ntgspiyggdrasil.yggdrasil.services.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private DocumentTypeService documentTypeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private FacultyService facultyService;


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
    public DocumentShortModel updateDocumentData(@PathVariable("documentId") long documentId, @RequestBody DocumentDataRequest documentDataRequest) {
        return DocumentShortModel.toModel(documentService.updateDocumentData(documentId, documentDataRequest.getDocumentData()));
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
    @PostMapping("/all")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public DocumentPageInfo getDocuments(@RequestHeader("Authorization") String authorization, @RequestBody SearchRequest request) throws ParseException {
        String headerAuth = authorization;
        String username = jwtUtils.getUserNameFromJwtToken(headerAuth.substring(7,headerAuth.length()));
        UserShortModel user = userService.loadShortUserByUsername(username);
        Page<Document> page;
        DocumentPageInfo pageInfo = new DocumentPageInfo();

        Date minDate;
        Date maxDate;
        LocalDate date;

        if (request.getMinDate() == "") {
            minDate = new Date(0);
        } else {
            date = LocalDate.parse(request.getMinDate());
            minDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        if (request.getMaxDate() == "") {
            maxDate = new Date();
        } else {
            date = LocalDate.parse(request.getMaxDate());
            maxDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }

        if (request.getTypeSearch().equals("common")) {
            if (user.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_ADMIN.name()))) {
                page = documentService.getDocuments(request.getParameter(), request.getSortField(), request.getSortDir(), request.getCurrentPage(), request.getStatusName(), minDate, maxDate, request.getDocumentType());
            } else if (user.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_MODERATOR.name()))) {
                page = documentService.getDocumentsByDepartmentId(user.getDepartment().getId(), user.getId(), request.getParameter(), request.getSortField(), request.getSortDir(), request.getCurrentPage(), request.getStatusName(), minDate, maxDate, request.getDocumentType());
            } else {
                page = documentService.getDocumentsByUserId(user.getId(), request.getParameter(), request.getSortField(), request.getSortDir(), request.getCurrentPage(), request.getStatusName(), minDate, maxDate, request.getDocumentType());
            }
        } else {
            if (user.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_ADMIN.name()))) {
                page = documentService.getDocumentsByKeyWords(request.getParameter(), request.getSortField(), request.getSortDir(), request.getCurrentPage(), request.getStatusName(), minDate, maxDate, request.getDocumentType());
            } else if (user.getAuthorities().contains(new SimpleGrantedAuthority(ERole.ROLE_MODERATOR.name()))) {
                page = documentService.loadDocumentsByDepartmentKeyword(user.getDepartment().getId(), user.getId(), request.getParameter(), request.getSortField(), request.getSortDir(), request.getCurrentPage(), request.getStatusName(), minDate, maxDate, request.getDocumentType());
            } else {
                page = documentService.getDocumentsByUserIdKeyword(user.getId(), request.getParameter(), request.getSortField(), request.getSortDir(), request.getCurrentPage(), request.getStatusName(), minDate, maxDate, request.getDocumentType());
            }
        }

        pageInfo.setCurrentPage(request.getCurrentPage());
        pageInfo.setTotalItems(page.getTotalElements());
        pageInfo.setTotalPages(page.getTotalPages());
        pageInfo.setDocuments(page.getContent().stream().map(DocumentShortModel::toModel).collect(Collectors.toList()));
        pageInfo.setSortField(request.getSortField());
        pageInfo.setSortDir(request.getSortDir());
        pageInfo.setSearchParameter(request.getParameter());
        return pageInfo;
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
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('SUPER_ADMIN') or hasRole('ADMIN')")
    public Iterable<DocumentStructuresShortResponse> getDocumentStructuresShort() {
        return documentStructureService.getPublicDocumentStructuresShort();
    }

    @PostMapping("/structures/short/admin")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public DocumentStructurePageInfo getAdminDocumentStructuresShort(@RequestBody SearchRequest request) {
        Date minDate;
        Date maxDate;
        LocalDate date;

        if (request.getMinDate() == "") {
            minDate = new Date(0);
        } else {
            date = LocalDate.parse(request.getMinDate());
            minDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        if (request.getMaxDate() == "") {
            maxDate = new Date();
        } else {
            date = LocalDate.parse(request.getMaxDate());
            maxDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }

        return documentStructureService.getAdminDocumentStructuresShort(request.getSortField(), request.getSortDir(), request.getCurrentPage(), request.getParameter(), request.getDocumentTypeName(), request.getIsActive(), minDate, maxDate);
    }

    @PostMapping("/structures/new/all")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public List<DocumentStructureModel> findNewDocumentStructure(@RequestBody DocumentStructureOldRequest documentStructure) {
        return documentStructureService.loadAllNewDocumentStructure(documentStructure.getOldDocumentStructureId()).stream().map(DocumentStructureModel::toModel).collect(Collectors.toList());
    }

    @PostMapping("/structures/create")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public DocumentStructureModel createDocumentStructure(@RequestBody DocumentStructureCreate documentStructure) {
        return DocumentStructureModel.toModel(documentStructureService.createDocumentStructure(documentStructure));
    }

    @PostMapping("/structures/update")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public DocumentStructureModel updateDocumentStructure(@RequestBody DocumentStructureCreate documentStructure) {
        return DocumentStructureModel.toModel(documentStructureService.updateDocumentStructure(documentStructure));
    }

    @PostMapping("/structures/status/update")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public DocumentStructureModel updateStatusDocumentStructure(@RequestBody DocumentStructureStatusRequest documentStructureStatus) {
        return DocumentStructureModel.toModel(documentStructureService.updateStatusDocumentStructure(documentStructureStatus));
    }

    @GetMapping("/structures/{id}/check")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public Boolean checkStatusDocumentStructure(@PathVariable("id") long documentStructureId) {
        return documentStructureService.checkDocumentStructure(documentStructureId);
    }

    @PostMapping("/statistic/user/{userId}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<StatisticDocumentResponse> userStatistic(@PathVariable("userId") long userId, @RequestBody DatePerRequest datePerRequest) {
        Date minDate;
        Date maxDate;
        LocalDate date;

        if (datePerRequest.getMinDate() == "") {
            minDate = new Date(0);
        } else {
            date = LocalDate.parse(datePerRequest.getMinDate());
            minDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        if (datePerRequest.getMaxDate() == "") {
            maxDate = new Date();
        } else {
            date = LocalDate.parse(datePerRequest.getMaxDate());
            maxDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
//        if (datePerRequest.getMinDate() == null) {
//            datePerRequest.setMinDate(new Date(0));
//        }
//        if (datePerRequest.getMaxDate() == null) {
//            datePerRequest.setMaxDate(new Date());
//        }
        return documentService.getStatisticsUser(userId, minDate, maxDate);
    }

    @PostMapping("/statistic/department/{departmentId}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<StatisticDocumentResponse> departmentStatistic(@PathVariable("departmentId") long departmentId, @RequestBody DatePerRequest datePerRequest) {
        Date minDate;
        Date maxDate;
        LocalDate date;

        if (datePerRequest.getMinDate() == "") {
            minDate = new Date(0);
        } else {
            date = LocalDate.parse(datePerRequest.getMinDate());
            minDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        if (datePerRequest.getMaxDate() == "") {
            maxDate = new Date();
        } else {
            date = LocalDate.parse(datePerRequest.getMaxDate());
            maxDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        return documentService.getStatisticsDepartment(departmentId, minDate, maxDate);
    }

    @PostMapping("/statistic/faculty/{facultyId}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<StatisticDocumentResponse> facultyStatistic(@PathVariable("facultyId") long facultyId, @RequestBody DatePerRequest datePerRequest) {
        Date minDate;
        Date maxDate;
        LocalDate date;

        if (datePerRequest.getMinDate() == "") {
            minDate = new Date(0);
        } else {
            date = LocalDate.parse(datePerRequest.getMinDate());
            minDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        if (datePerRequest.getMaxDate() == "") {
            maxDate = new Date();
        } else {
            date = LocalDate.parse(datePerRequest.getMaxDate());
            maxDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        return documentService.getStatisticsFaculty(facultyId, minDate, maxDate);
    }

    @PostMapping("/statistic/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<StatisticDocumentResponse> allStatistic(@RequestBody DatePerRequest datePerRequest) {
        Date minDate;
        Date maxDate;
        LocalDate date;

        if (datePerRequest.getMinDate() == "") {
            minDate = new Date(0);
        } else {
            date = LocalDate.parse(datePerRequest.getMinDate());
            minDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        if (datePerRequest.getMaxDate() == "") {
            maxDate = new Date();
        } else {
            date = LocalDate.parse(datePerRequest.getMaxDate());
            maxDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        return documentService.getStatisticsAll(minDate, maxDate);
    }
    @GetMapping("/types/all")
    public List<DocumentTypeModel> allDocumentTypes() {
        return documentTypeService.loadAllDocumentTypes().stream().map(DocumentTypeModel::toModel).collect(Collectors.toList());
    }
    @PostMapping("/types/check")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public Boolean checkDocumentType(@RequestBody CreateDocumentType documentType) {
        return documentTypeService.canCreateDocumentType(documentType.getName());
    }
    @PostMapping("/types/create")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public DocumentTypeModel createDocumentType(@RequestBody CreateDocumentType documentType) {
        return DocumentTypeModel.toModel(documentTypeService.createDocumentType(documentType.getName()));
    }

    @PostMapping("/types/update/{documentTypeId}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public DocumentTypeModel updateDocumentType(@PathVariable("documentTypeId") long documentTypeId, @RequestBody UpdateDocumentType updateDocumentType) {
        return DocumentTypeModel.toModel(documentTypeService.updateDocumentType(documentTypeId, updateDocumentType.getDocumentTypeName()));
    }

    @PostMapping("/status/update/remake")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity updateDocumentStatusRemake(@RequestBody DocumentStructureId documentStructureId) {
        documentService.updateDocumentStatusRemake(documentStructureId.getId());
        return ResponseEntity.ok("success");
    }

    @PostMapping("/faculty/all")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public List<DocumentModel> findAllDocuments(@RequestBody FacultyDocumentsRequest facultyDocuments) {
        return documentService.loadAllDocuments(facultyDocuments).stream().map(DocumentModel::toModel).collect(Collectors.toList());
    }

    @PostMapping("/department/all")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public List<DocumentModel> findAllDocuments(@RequestBody DepartmentDocumentsRequest departmentDocuments) {
        return documentService.loadAllDocuments(departmentDocuments).stream().map(DocumentModel::toModel).collect(Collectors.toList());
    }

    @PostMapping("/archiving")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public Boolean archivingDocuments(@RequestBody ArchivingDocument archivingDocument) {
        return documentService.archivingDocuments(archivingDocument.getDocumentStructureId(), archivingDocument.getDocumentStatusId(), archivingDocument.getMinDate(), archivingDocument.getMaxDate());
    }

    @PostMapping(value = "/export", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN') or hasRole('MODERATOR')")
    @ResponseBody
    public ResponseEntity<byte[]> exportToExcel(@RequestBody ReportExportRequest reportExportData) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        int countPage = 0;
        for (long documentStructureId : reportExportData.getDocumentStructures()) {
            DocumentStructureModel documentStructure = documentStructureService.getDocumentStructure(documentStructureId);
            String obrStr = documentStructure.getName();
            obrStr = obrStr.toUpperCase();
            String [] arrStr = obrStr.split(" ");
            obrStr = "";
            for (String word : arrStr) {
                obrStr += word.charAt(0);;
            }
            XSSFSheet sheet = workbook.createSheet(obrStr);
            Gson g = new Gson();

            DataDocumentStructure dataDocumentStructure = g.fromJson(documentStructure.getStructure(), DataDocumentStructure.class);

            XSSFRow header = sheet.createRow(0);
            header = sheet.createRow(1);

            CellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontHeight(14);
            font.setFontName("Times New Roman");
            style.setFont(font);

            CellStyle style2 = workbook.createCellStyle();
            XSSFFont font2 = workbook.createFont();
            font2.setFontHeight(14);
            font2.setFontName("Times New Roman");
            style2.setFont(font2);

            CellStyle style3 = workbook.createCellStyle();
            XSSFFont font3 = workbook.createFont();
            font3.setFontHeight(11);
            font3.setBold(true);
            font3.setFontName("Times New Roman");
            style3.setFont(font3);

            CellStyle style4 = workbook.createCellStyle();
            XSSFFont font4 = workbook.createFont();
            font4.setFontHeight(11);
            font4.setFontName("Times New Roman");
            style4.setFont(font4);

            int rowCount = 1;
            if (countPage == 0) {
                header.createCell(2).setCellValue("ОТЧЕТ");
                header.getCell(2).setCellStyle(style);

                header = sheet.createRow(2);
                header.createCell(2).setCellValue("кафедры / колледжа_____________________________________________");
                header.getCell(2).setCellStyle(style2);

                DateFormat dataFormatter = new SimpleDateFormat("dd.MM.yyyy");
                String minDate = dataFormatter.format(reportExportData.getMinDate());
                String maxDate = dataFormatter.format(reportExportData.getMaxDate());

                header = sheet.createRow(3);
                header.createCell(2).setCellValue("за период " + minDate + " - " + maxDate);
                header.getCell(2).setCellStyle(style2);

                header = sheet.createRow(5);
                header.createCell(0).setCellValue(documentStructure.getName());
                header.getCell(0).setCellStyle(style);
                //header = sheet.createRow(7);
            } else {
                header.createCell(0).setCellValue(documentStructure.getName());
                header.getCell(0).setCellStyle(style);
                //header = sheet.createRow(3);
            }
            XSSFRow headerTable = sheet.createRow(0);
            if (countPage == 0) {
                headerTable = sheet.createRow(7);
            } else {
                headerTable = sheet.createRow(3);
            }
            headerTable.createCell(0).setCellValue("№ п/п");
            headerTable.getCell(0).setCellStyle(style3);
            for (FieldDocumentStructure field : dataDocumentStructure.getData()) {
                if (field.getName().toCharArray()[field.getName().length()-1] == ':') {
                    headerTable.createCell(rowCount++).setCellValue(field.getName().substring(0, field.getName().length()-1));
                    headerTable.getCell(rowCount-1).setCellStyle(style3);
                } else {
                    headerTable.createCell(rowCount++).setCellValue(field.getName());
                    headerTable.getCell(rowCount-1).setCellStyle(style3);
                }
            }

            List<Document> documents;

            if (reportExportData.getFacultyId() > 0) {
                documents = documentService.getFacultyDocumentsForExport(documentStructureId, reportExportData.getFacultyId(), reportExportData.getMinDate(), reportExportData.getMaxDate());
            } else {
                documents = documentService.getDepartmentDocumentsForExport(documentStructureId, reportExportData.getDepartmentId(), reportExportData.getMinDate(), reportExportData.getMaxDate());
            }
            //rowCount = 1;
            if (countPage == 0) {
                rowCount = 8;
            } else {
                rowCount = 4;
            }

            for (Document document : documents) {

                JsonParser parser = new JsonParser();
                JsonElement jsonElement = parser.parse(document.getDocumentData());
                JsonArray arr = jsonElement.getAsJsonObject().get("userResponse").getAsJsonArray();

                int indexInnerArr = 0;
                int cellCount = 1;

                XSSFRow row = sheet.createRow(rowCount++);
                if (countPage == 0) {
                    row.createCell(0).setCellValue(rowCount - 8);
                    row.getCell(0).setCellStyle(style4);
                } else {
                    row.createCell(0).setCellValue(rowCount - 4);
                    row.getCell(0).setCellStyle(style4);
                }

                for (int i = 0; i < dataDocumentStructure.getData().size(); ++i) {
                    if (!arr.toString().contains(dataDocumentStructure.getData().get(i).getId().toString())) {
                        row.createCell(cellCount++).setCellValue("");
                        row.getCell(cellCount-1).setCellStyle(style4);
                    } else {
//                        System.out.println(dataDocumentStructure.getData().get(i).getType());
                        if (dataDocumentStructure.getData().get(i).getType().equals("selectMultipleImage")) {
                            String userList = "";
                            //System.out.println(arr.get(indexInnerArr).getAsJsonObject().get(dataDocumentStructure.getData().get(i).getId()).getAsJsonArray().get(0).getAsJsonObject().get("name").toString());
                            JsonArray jArr = arr.get(indexInnerArr++).getAsJsonObject().get(dataDocumentStructure.getData().get(i).getId()).getAsJsonArray();
                            for (int j = 0; j < jArr.size(); ++j) {
                                userList += jArr.get(j).getAsJsonObject().get("name").getAsString();
                                if (j + 1 < jArr.size()) {
                                    userList += ", ";
                                }
                            }
                            row.createCell(cellCount++).setCellValue(userList);
                            row.getCell(cellCount-1).setCellStyle(style4);
                        } else if (dataDocumentStructure.getData().get(i).getType().equals("selectMultiple") || dataDocumentStructure.getData().get(i).getType().equals("checkbox")) {
                            String selectList = "";
                            JsonArray jArr = arr.get(indexInnerArr++).getAsJsonObject().get(dataDocumentStructure.getData().get(i).getId()).getAsJsonArray();
                            for (int j = 0; j < jArr.size(); ++j) {
                                selectList += jArr.get(j).getAsString();
                                if (j + 1 < jArr.size()) {
                                    selectList += ", ";
                                }
                            }
                            row.createCell(cellCount++).setCellValue(selectList);
                            row.getCell(cellCount-1).setCellStyle(style4);
                        } else if (dataDocumentStructure.getData().get(i).getType().equals("selectSingleImage")) {
                            row.createCell(cellCount++).setCellValue(arr.get(indexInnerArr++).getAsJsonObject().get(dataDocumentStructure.getData().get(i).getId()).getAsJsonObject().get("name").getAsString());
                            row.getCell(cellCount-1).setCellStyle(style4);
                        } else {
                            row.createCell(cellCount++).setCellValue(arr.get(indexInnerArr++).getAsJsonObject().get(dataDocumentStructure.getData().get(i).getId()).getAsString());
                            row.getCell(cellCount-1).setCellStyle(style4);
                        }
                    }
                }
            }
            for (int i = 0; i < headerTable.getPhysicalNumberOfCells(); i++) {
                sheet.autoSizeColumn(i);
            }
            countPage += 1;
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "employees.xlsx");

        // Возвращаем ответ с документом
        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }

    @PostMapping(value = "/combined/export", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<byte[]> combinedExportToExcel(@RequestBody CombinedReportExportRequest reportExportData) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        int countPage = 0;
        boolean isDepartment = !reportExportData.getDepartmentList().isEmpty();
        List<Long> departmentlist;
        if (isDepartment) {
            departmentlist = reportExportData.getDepartmentList();
        } else {
            departmentlist = reportExportData.getFacultyList();
        }
        for (long departmentId : departmentlist) {
            DepartmentModel department;
            Faculty faculty;
            String depName = "";

            if (isDepartment) {
                department = departmentService.loadDepartmentById(departmentId);
                depName = department.getShortName();
            } else {
                faculty = facultyService.loadFacultyById(departmentId);
                depName = faculty.getShortName();
            }

            for (long documentStructureId : reportExportData.getDocumentStructures()) {
                DocumentStructureModel documentStructure = documentStructureService.getDocumentStructure(documentStructureId);
                String obrStr = documentStructure.getName();
                obrStr = obrStr.toUpperCase();
                String [] arrStr = obrStr.split(" ");
                obrStr = "";
                for (String word : arrStr) {
                    obrStr += word.charAt(0);;
                }
                XSSFSheet sheet = workbook.createSheet(depName + " — " + obrStr);
                Gson g = new Gson();

                DataDocumentStructure dataDocumentStructure = g.fromJson(documentStructure.getStructure(), DataDocumentStructure.class);

                XSSFRow header = sheet.createRow(0);
                header = sheet.createRow(1);

                CellStyle style = workbook.createCellStyle();
                XSSFFont font = workbook.createFont();
                font.setBold(true);
                font.setFontHeight(14);
                font.setFontName("Times New Roman");
                style.setFont(font);

                CellStyle style2 = workbook.createCellStyle();
                XSSFFont font2 = workbook.createFont();
                font2.setFontHeight(14);
                font2.setFontName("Times New Roman");
                style2.setFont(font2);

                CellStyle style3 = workbook.createCellStyle();
                XSSFFont font3 = workbook.createFont();
                font3.setFontHeight(11);
                font3.setBold(true);
                font3.setFontName("Times New Roman");
                style3.setFont(font3);

                CellStyle style4 = workbook.createCellStyle();
                XSSFFont font4 = workbook.createFont();
                font4.setFontHeight(11);
                font4.setFontName("Times New Roman");
                style4.setFont(font4);

                int rowCount = 1;
                if (countPage == 0) {
                    header.createCell(2).setCellValue("ОТЧЕТ");
                    header.getCell(2).setCellStyle(style);

                    header = sheet.createRow(2);
                    header.createCell(2).setCellValue("кафедр / колледжа_____________________________________________");
                    header.getCell(2).setCellStyle(style2);

                    DateFormat dataFormatter = new SimpleDateFormat("dd.MM.yyyy");
                    String minDate = dataFormatter.format(reportExportData.getMinDate());
                    String maxDate = dataFormatter.format(reportExportData.getMaxDate());

                    header = sheet.createRow(3);
                    header.createCell(2).setCellValue("за период " + minDate + " - " + maxDate);
                    header.getCell(2).setCellStyle(style2);

                    header = sheet.createRow(5);
                    header.createCell(0).setCellValue(documentStructure.getName());
                    header.getCell(0).setCellStyle(style);
                    //header = sheet.createRow(7);
                } else {
                    header.createCell(0).setCellValue(documentStructure.getName());
                    header.getCell(0).setCellStyle(style);
                    //header = sheet.createRow(3);
                }
                XSSFRow headerTable = sheet.createRow(0);
                if (countPage == 0) {
                    headerTable = sheet.createRow(7);
                } else {
                    headerTable = sheet.createRow(3);
                }
                headerTable.createCell(0).setCellValue("№ п/п");
                headerTable.getCell(0).setCellStyle(style3);
                for (FieldDocumentStructure field : dataDocumentStructure.getData()) {
                    if (field.getName().toCharArray()[field.getName().length()-1] == ':') {
                        headerTable.createCell(rowCount++).setCellValue(field.getName().substring(0, field.getName().length()-1));
                        headerTable.getCell(rowCount-1).setCellStyle(style3);
                    } else {
                        headerTable.createCell(rowCount++).setCellValue(field.getName());
                        headerTable.getCell(rowCount-1).setCellStyle(style3);
                    }
                }

                List<Document> documents;

                if (reportExportData.getFacultyList().size() > 0) {
                    documents = documentService.getFacultyDocumentsForExport(documentStructureId, departmentId, reportExportData.getMinDate(), reportExportData.getMaxDate());
                } else {
                    documents = documentService.getDepartmentDocumentsForExport(documentStructureId, departmentId, reportExportData.getMinDate(), reportExportData.getMaxDate());
                }
                //rowCount = 1;
                if (countPage == 0) {
                    rowCount = 8;
                } else {
                    rowCount = 4;
                }

                for (Document document : documents) {

                    JsonParser parser = new JsonParser();
                    JsonElement jsonElement = parser.parse(document.getDocumentData());
                    JsonArray arr = jsonElement.getAsJsonObject().get("userResponse").getAsJsonArray();

                    int indexInnerArr = 0;
                    int cellCount = 1;

                    XSSFRow row = sheet.createRow(rowCount++);
                    if (countPage == 0) {
                        row.createCell(0).setCellValue(rowCount - 8);
                        row.getCell(0).setCellStyle(style4);
                    } else {
                        row.createCell(0).setCellValue(rowCount - 4);
                        row.getCell(0).setCellStyle(style4);
                    }

                    for (int i = 0; i < dataDocumentStructure.getData().size(); ++i) {
                        if (!arr.toString().contains(dataDocumentStructure.getData().get(i).getId().toString())) {
                            row.createCell(cellCount++).setCellValue("");
                            row.getCell(cellCount-1).setCellStyle(style4);
                        } else {
//                        System.out.println(dataDocumentStructure.getData().get(i).getType());
                            if (dataDocumentStructure.getData().get(i).getType().equals("selectMultipleImage")) {
                                String userList = "";
                                //System.out.println(arr.get(indexInnerArr).getAsJsonObject().get(dataDocumentStructure.getData().get(i).getId()).getAsJsonArray().get(0).getAsJsonObject().get("name").toString());
                                JsonArray jArr = arr.get(indexInnerArr++).getAsJsonObject().get(dataDocumentStructure.getData().get(i).getId()).getAsJsonArray();
                                for (int j = 0; j < jArr.size(); ++j) {
                                    userList += jArr.get(j).getAsJsonObject().get("name").getAsString();
                                    if (j + 1 < jArr.size()) {
                                        userList += ", ";
                                    }
                                }
                                row.createCell(cellCount++).setCellValue(userList);
                                row.getCell(cellCount-1).setCellStyle(style4);
                            } else if (dataDocumentStructure.getData().get(i).getType().equals("selectMultiple") || dataDocumentStructure.getData().get(i).getType().equals("checkbox")) {
                                String selectList = "";
                                JsonArray jArr = arr.get(indexInnerArr++).getAsJsonObject().get(dataDocumentStructure.getData().get(i).getId()).getAsJsonArray();
                                for (int j = 0; j < jArr.size(); ++j) {
                                    selectList += jArr.get(j).getAsString();
                                    if (j + 1 < jArr.size()) {
                                        selectList += ", ";
                                    }
                                }
                                row.createCell(cellCount++).setCellValue(selectList);
                                row.getCell(cellCount-1).setCellStyle(style4);
                            } else if (dataDocumentStructure.getData().get(i).getType().equals("selectSingleImage")) {
                                row.createCell(cellCount++).setCellValue(arr.get(indexInnerArr++).getAsJsonObject().get(dataDocumentStructure.getData().get(i).getId()).getAsJsonObject().get("name").getAsString());
                                row.getCell(cellCount-1).setCellStyle(style4);
                            } else {
                                row.createCell(cellCount++).setCellValue(arr.get(indexInnerArr++).getAsJsonObject().get(dataDocumentStructure.getData().get(i).getId()).getAsString());
                                row.getCell(cellCount-1).setCellStyle(style4);
                            }
                        }
                    }
                }
                for (int i = 0; i < headerTable.getPhysicalNumberOfCells(); i++) {
                    sheet.autoSizeColumn(i);
                }
                countPage += 1;
            }
        }


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "employees.xlsx");

        // Возвращаем ответ с документом
        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }
}
