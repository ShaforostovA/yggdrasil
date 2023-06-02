package com.ntgspiyggdrasil.yggdrasil.payload.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DocumentStructurePageInfo {
    private long currentPage;
    private long totalItems;
    private long totalPages;
    private Iterable<DocumentStructuresShortResponse> documentStructureList;
    private String sortField;
    private String sortDir;
    private String searchParameter;
}
