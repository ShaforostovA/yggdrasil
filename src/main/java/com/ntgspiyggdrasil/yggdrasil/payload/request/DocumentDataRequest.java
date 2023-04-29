package com.ntgspiyggdrasil.yggdrasil.payload.request;

import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Data
public class DocumentDataRequest {
    private String documentData;
}
