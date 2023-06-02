package com.ntgspiyggdrasil.yggdrasil.payload.request;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Data
@NoArgsConstructor
public class CombinedReportExportRequest {
    private List<Long> documentStructures;
    private List<Long> facultyList = new ArrayList<>();
    private List<Long> departmentList = new ArrayList<>();
    @Temporal(TemporalType.DATE) private Date minDate;
    @Temporal(TemporalType.DATE) private Date maxDate;
}
