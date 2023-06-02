package com.ntgspiyggdrasil.yggdrasil.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class DocumentStructuresShortResponse {
    private long id;
    private String name;
    private String description;
    private Boolean isActive;
    private Date dateCreate;
}
