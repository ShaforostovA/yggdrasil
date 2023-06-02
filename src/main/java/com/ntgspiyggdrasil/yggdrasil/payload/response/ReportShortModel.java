package com.ntgspiyggdrasil.yggdrasil.payload.response;

import com.ntgspiyggdrasil.yggdrasil.models.Report;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ReportShortModel {
    public static ReportShortModel toModel(Report entity) {
        ReportShortModel model = new ReportShortModel();
        model.setId(entity.getId());
        model.setCanChange(entity.getCanChange());
        model.setDateCreate(entity.getDateCreate());
        model.setDateUpdate(entity.getDateUpdate());
        model.setDateStart(entity.getDateStart());
        model.setDateEnd(entity.getDateEnd());
        model.setReportStructure(ReportStructureShortModel.toModel(entity.getReportStructure()));
        model.setUser(UserShortModel.toModel(entity.getUser()));
        model.setReportStatus(ReportStatusModel.toModel(entity.getReportStatus()));
        return model;
    }
    private Long id;
    private Boolean canChange;
    private Date dateCreate;
    private Date dateUpdate;
    private Date dateStart;
    private Date dateEnd;
    private ReportStructureShortModel reportStructure;
    private UserShortModel user;
    private ReportStatusModel reportStatus;
}
