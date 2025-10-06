package com.tga.apirest.db.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * It represents the movies fetched from the original source. Once its info is downloaded
 * we save it into our Elasticsearch database
 */
@Document(indexName = "movies")
@Data
@AllArgsConstructor
public class Movie {

    /**
     * The IMDb ID of the movie.
     */
    @Id
    private String imdbID;

    /**
     * The title of the movie.
     */
    private String title;

    /**
     * The release year of the movie.
     */
    private int year;

}
