package com.tga.apirest.errorhandling.model;

import lombok.Builder;
import lombok.Data;

/**
 * It represents the error json message to be thrown to the client
 */
@Data
@Builder
public class ErrorBody {

    /**
     * Error message
     */
    private String mensaje;
}
