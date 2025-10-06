package com.tga.apirest.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Represents version information for the application or component.
 */
@Data
@Builder
public class Version {

    /**
     * The version number".
     */
    private String number;

    /**
     * The flavor of the build.
     */
    private String buildFlavor;

    /**
     * The type of build, e.g., "release" or "snapshot".
     */
    private String buildType;

    /**
     * The hash identifier of the build source.
     */
    private String buildHash;
    /**
     * The date when the build was created.
     */
    private String buildDate;

    /**
     * Indicates whether the build is a snapshot (development version).
     */
    private Boolean buildSnapshot;

    /**
     * The Lucene version used.
     */
    private String luceneVersion;

    /**
     * The minimum wire compatibility version.
     */
    private String minimumWireCompatibilityVersion;

    /**
     * The minimum index compatibility version.
     */
    private String minimumIndexCompatibilityVersion;
}
