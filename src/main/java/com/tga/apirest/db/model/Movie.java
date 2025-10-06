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

    @Id
    private String imdbID;
    private String title;
    private int year;

}
