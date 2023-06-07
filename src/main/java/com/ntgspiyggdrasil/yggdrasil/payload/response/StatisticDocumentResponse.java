package com.ntgspiyggdrasil.yggdrasil.payload.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatisticDocumentResponse {
    private long count;
    private String typeName;
    private String statusName;
}
