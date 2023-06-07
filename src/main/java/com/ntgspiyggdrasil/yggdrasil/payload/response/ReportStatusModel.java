package com.ntgspiyggdrasil.yggdrasil.payload.response;

import com.ntgspiyggdrasil.yggdrasil.models.ReportStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReportStatusModel {
    public static ReportStatusModel toModel(ReportStatus entity) {
        ReportStatusModel model = new ReportStatusModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        return model;
    }
    private Long id;
    private String name;
}
