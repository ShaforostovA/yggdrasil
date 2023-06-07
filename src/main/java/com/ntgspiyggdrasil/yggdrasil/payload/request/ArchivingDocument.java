package com.ntgspiyggdrasil.yggdrasil.payload.request;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ArchivingDocument {
    private long documentStructureId;
    private long documentStatusId;
    @Temporal(TemporalType.DATE) private Date minDate;
    @Temporal(TemporalType.DATE) private Date maxDate;
}
