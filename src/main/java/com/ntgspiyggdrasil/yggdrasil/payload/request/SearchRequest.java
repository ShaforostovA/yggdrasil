package com.ntgspiyggdrasil.yggdrasil.payload.request;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class SearchRequest {
    private String typeSearch;
    private String parameter;
    private int currentPage;
    private String sortField;
    private String sortDir;
    private String statusName;
    private String minDate;
    private String maxDate;
    private String documentType;
    private String reportType;
    private String departmentName;
    private Boolean isState;
    private String userRole;
    private Boolean isActive;
    private String facultyName;

    private String documentTypeName;

    private String reportTypeName;

    private Long facultyId;
    private Long departmentId;

    //@Temporal(TemporalType.DATE)

}
