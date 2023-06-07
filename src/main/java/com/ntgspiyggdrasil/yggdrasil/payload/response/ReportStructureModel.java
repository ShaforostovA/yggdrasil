package com.ntgspiyggdrasil.yggdrasil.payload.response;

import com.ntgspiyggdrasil.yggdrasil.models.ReportStructure;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ReportStructureModel {
    public static ReportStructureModel toModel(ReportStructure entity) {
        ReportStructureModel model = new ReportStructureModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setReportStructure(entity.getReportStructure());
        model.setDescription(entity.getDescription());
        model.setDateCreate(entity.getDateCreate());
        model.setDateUpdate(entity.getDateUpdate());
        model.setIsActive(entity.getIsActive());
        model.setOldStructureId(entity.getOldStructureId());
        model.setReportType(ReportTypeModel.toModel(entity.getReportType()));
        return model;
    }
    private Long id;
    private String name;
    private String description;
    private String reportStructure;
    private Date dateCreate;
    private Date dateUpdate;
    private Boolean isActive;
    private ReportTypeModel reportType;
    private Long oldStructureId;
}
