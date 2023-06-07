package com.ntgspiyggdrasil.yggdrasil.payload.request;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.util.Date;

@Data
public class DatePerRequest {
//    @Temporal(TemporalType.DATE) private Date minDate;
//    @Temporal(TemporalType.DATE) private Date maxDate;
    private String minDate;
    private String maxDate;
}
