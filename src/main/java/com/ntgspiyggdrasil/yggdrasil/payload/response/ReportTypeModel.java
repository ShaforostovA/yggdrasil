package com.ntgspiyggdrasil.yggdrasil.payload.response;

import com.ntgspiyggdrasil.yggdrasil.models.ReportType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReportTypeModel {
    public static ReportTypeModel toModel(ReportType entity) {
        ReportTypeModel model = new ReportTypeModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        return model;
    }
    private Long id;
    private String name;
}
