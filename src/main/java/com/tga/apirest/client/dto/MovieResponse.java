package com.tga.apirest.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * It contains the response with a list of movies belonging to a specified page.
 */
@Data
public class MovieResponse {

    private int page;

    @JsonProperty("total_pages")
    private int totalPages;

    private int total;

    private List<RemoteMovie> data;
}
