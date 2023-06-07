package com.ntgspiyggdrasil.yggdrasil.payload.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DocumentStructureCreate {
    private long id;
    private String name;
    private String description;
    private long documentTypeId;
    private String structureData;
}
