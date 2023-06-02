package com.ntgspiyggdrasil.yggdrasil.payload.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FacultyPageInfo {
    private long currentPage;
    private long totalItems;
    private long totalPages;
    private List<FacultyNoUserModel> facultyList;
    private String sortField;
    private String sortDir;
    private String searchParameter;
}
