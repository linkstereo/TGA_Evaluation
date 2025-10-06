package com.tga.apirest.dto;

import lombok.Builder;
import lombok.Data;

/**
 * It represents the Elasticsearch info
 */
@Data
@Builder
public class ElasticSearchInfo {

    /**
     * The name of the Elasticsearch node.
     */
    private String name;

    /**
     * The name of the Elasticsearch cluster.
     */
    private String clusterName;

    /**
     * The UUID of the Elasticsearch cluster.
     */
    private String clusterUuid;

    /**
     * The version information of Elasticsearch.
     */
    private Version version;

    /**
     * The tagline or slogan of Elasticsearch.
     */
    private String tagline;
}