package com.ntgspiyggdrasil.yggdrasil.payload.request;

import lombok.Data;

import java.util.Set;

@Data
public class DocumentCreateRequest {
    private String documentData;
    private long documentStructureId;
    private String username;
    private long documentStatusId;
}
