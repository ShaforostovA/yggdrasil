package com.ntgspiyggdrasil.yggdrasil.payload.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DepartmentPageInfo {
    private long currentPage;
    private long totalItems;
    private long totalPages;
    private List<DepartmentModel> departmentList;
    private String sortField;
    private String sortDir;
    private String searchParameter;
}
