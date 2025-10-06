package com.tga.apirest.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * It represents a movie obtained from the remote server. (HackerRank)
 */
@Data
@AllArgsConstructor
public class RemoteMovie {

    @JsonProperty("Title")
    private String title;

    @JsonProperty("Year")
    private int Year;

    private String imdbID;
}
