package com.tga.apirest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Version {

    private String number;
    private String buildFlavor;
    private String buildType;
    private String buildHash;
    private String buildDate;
    private Boolean buildSnapshot;
    private String luceneVersion;
    private String minimumWireCompatibilityVersion;
    private String minimumIndexCompatibilityVersion;
}
