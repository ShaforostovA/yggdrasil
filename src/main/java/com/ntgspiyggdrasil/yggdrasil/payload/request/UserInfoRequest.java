package com.ntgspiyggdrasil.yggdrasil.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserInfoRequest {
    @NotBlank
    private Long userId;
}
