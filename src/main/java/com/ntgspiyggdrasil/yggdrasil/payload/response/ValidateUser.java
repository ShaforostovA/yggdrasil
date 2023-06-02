package com.ntgspiyggdrasil.yggdrasil.payload.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ValidateUser {
    public ValidateUser(Boolean isValid, Boolean isActive) {
        this.isValid = isValid;
        this.isActive = isActive;
    }
    private Boolean isValid;
    private Boolean isActive;
}
