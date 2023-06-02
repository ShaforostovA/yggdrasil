package com.ntgspiyggdrasil.yggdrasil.payload.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CreateReportRequest {
    private String reportData;
    private Date dateStart;
    private Date dateEnd;
    private Long reportStructureId;
    private Long userId;
    private Long reportStatusId;
}
