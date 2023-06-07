package com.ntgspiyggdrasil.yggdrasil.services;

import com.google.gson.*;
import com.ntgspiyggdrasil.yggdrasil.models.*;
import com.ntgspiyggdrasil.yggdrasil.payload.request.ReportExportRequest;
import com.ntgspiyggdrasil.yggdrasil.payload.response.DocumentStructureModel;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DocumentExcelExport {
    private final XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private ReportExportRequest reportExportData;

    private final DocumentStructureService documentStructureService;
    private final DocumentService documentService;

    public DocumentExcelExport(ReportExportRequest reportExportData, DocumentStructureService documentStructureService, DocumentService documentService) {
        this.reportExportData = reportExportData;
        this.documentStructureService = documentStructureService;
        this.documentService = documentService;
        workbook = new XSSFWorkbook();

    }

    private void writeHeaderRow(List<FieldDocumentStructure> fieldsDocumentStructure) {
        XSSFRow row = sheet.createRow(0);
        int rowCount = 1;
        row.createCell(0).setCellValue("№ п/п");
        for (FieldDocumentStructure field : fieldsDocumentStructure) {
            row.createCell(rowCount++).setCellValue(field.getName());
        }
    }

    private void writeDateRows(List<Document> documents, List<FieldDocumentStructure> fieldsDocumentStructure) {
        int rowCount = 1;

        for (Document document : documents) {

            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(document.getDocumentData());
            JsonArray arr = jsonElement.getAsJsonObject().get("userResponse").getAsJsonArray();

            int indexInnerArr = 0;
            int cellCount = 1;

            XSSFRow row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(rowCount -1);
//            Cell cell = row.createCell(0);
//            cell.setCellValue(rowCount -1 );
            System.out.println(arr);
            for (int i = 0; i < fieldsDocumentStructure.size(); ++i) {
                if (!arr.toString().contains(fieldsDocumentStructure.get(i).getId().toString())) {
//                    System.out.println(fieldsDocumentStructure.get(i).getId());
//                    System.out.println("");
//                    cell = row.createCell(cellCount++);
//                    cell.setCellValue("");
                    System.out.println(i);
                    row.createCell(cellCount++).setCellValue("");
                } else {
//                    System.out.println(fieldsDocumentStructure.get(i).getId());
//                    System.out.println(arr.get(indexInnerArr++).getAsJsonObject().get(fieldsDocumentStructure.get(i).getId()).toString());
//                    cell = row.createCell(cellCount++);
//                    cell.setCellValue(arr.get(indexInnerArr++).getAsJsonObject().get(fieldsDocumentStructure.get(i).getId()).toString());
                    System.out.println(i);
                    row.createCell(cellCount++).setCellValue(arr.get(indexInnerArr++).getAsJsonObject().get(fieldsDocumentStructure.get(i).getId()).toString());
                }
            }

//            for (int i = 0; i < arr.size(); ++i) {
//                System.out.println(arr.get(i));
//                System.out.println(keysStructure.get(i));
//            }
            //DataDocument dataDocument = g.fromJson(document.getDocumentData(), DataDocument.class);
//            Row row = sheet.createRow(rowCount++);
//            int cellCount = 0;
//            for (FieldDocumentStructure field : fieldsDocumentStructure) {
//                JsonArray findEl = jsonElement.getAsJsonArray().get(0).getAsJsonArray();
//                Cell cell = row.createCell(cellCount++);
//                cell.setCellValue(documentData);
//            }
        }
    }

    public byte[] export() throws IOException {
        for (long documentStructureId : reportExportData.getDocumentStructures()) {
            DocumentStructureModel documentStructure = documentStructureService.getDocumentStructure(documentStructureId);
            sheet = workbook.createSheet(documentStructure.getName());
            Gson g = new Gson();

            DataDocumentStructure dataDocumentStructure = g.fromJson(documentStructure.getStructure(), DataDocumentStructure.class);

            writeHeaderRow(dataDocumentStructure.getData());

            if (reportExportData.getFacultyId() != -1) {
                writeDateRows(documentService.getFacultyDocumentsForExport(documentStructureId, reportExportData.getFacultyId(), reportExportData.getMinDate(), reportExportData.getMaxDate()), dataDocumentStructure.getData());
            } else {
                writeDateRows(documentService.getDepartmentDocumentsForExport(documentStructureId, reportExportData.getDepartmentId(), reportExportData.getMinDate(), reportExportData.getMaxDate()), dataDocumentStructure.getData());
            }
        }

//        ServletOutputStream outputStream = response.getOutputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();
        return outputStream.toByteArray();
        //outputStream.close();
    }
}
