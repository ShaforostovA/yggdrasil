package com.ntgspiyggdrasil.yggdrasil.payload.response;

import com.ntgspiyggdrasil.yggdrasil.models.Report;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ReportModel {
    public static ReportModel toModel(Report entity) {
        ReportModel model = new ReportModel();
        model.setId(entity.getId());
        model.setReportData(entity.getReportData());
        model.setCanChange(entity.getCanChange());
        model.setDateCreate(entity.getDateCreate());
        model.setDateUpdate(entity.getDateUpdate());
        model.setDateStart(entity.getDateStart());
        model.setDateEnd(entity.getDateEnd());
        model.setReportStructure(ReportStructureModel.toModel(entity.getReportStructure()));
        model.setUser(UserShortModel.toModel(entity.getUser()));
        model.setReportStatus(ReportStatusModel.toModel(entity.getReportStatus()));
        return model;
    }
    private Long id;
    private String reportData;
    private Boolean canChange;
    private Date dateCreate;
    private Date dateUpdate;
    private Date dateStart;
    private Date dateEnd;
    private ReportStructureModel reportStructure;
    private UserShortModel user;
    private ReportStatusModel reportStatus;
}
