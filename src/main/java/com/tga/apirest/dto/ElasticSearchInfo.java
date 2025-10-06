package com.tga.apirest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ElasticSearchInfo {

    private String name;
    private String clusterName;
    private String clusterUuid;
    private Version version;
    private String tagline;
}