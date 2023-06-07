package com.ntgspiyggdrasil.yggdrasil.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DataDocument {
    private List<String> userResponse;
}
