package com.ntgspiyggdrasil.yggdrasil.payload.request;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ReportExportRequest {
    private List<Long> documentStructures;
    private long facultyId = -1;
    private long departmentId = -1;
    @Temporal(TemporalType.DATE) private Date minDate;
    @Temporal(TemporalType.DATE) private Date maxDate;
}
