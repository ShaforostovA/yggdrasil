package com.ntgspiyggdrasil.yggdrasil.payload.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class FacultyDocumentsRequest {
    private long documentStructureId;
    private long facultyId;
    private Date minDate;
    private Date maxDate;
}
