package com.ntgspiyggdrasil.yggdrasil.payload.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateStudentStatus {
    private Long studentId;
    private Boolean isTrained;
}
