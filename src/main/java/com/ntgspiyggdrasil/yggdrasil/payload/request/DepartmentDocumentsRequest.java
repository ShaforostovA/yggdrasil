package com.ntgspiyggdrasil.yggdrasil.payload.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class DepartmentDocumentsRequest {
    private long documentStructureId;
    private long departmentId;
    private Date minDate;
    private Date maxDate;
}
