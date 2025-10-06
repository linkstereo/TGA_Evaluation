package com.tga.apirest.repository;

import com.tga.apirest.db.model.Movie;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * It is a retriever object for Movies saved in the Elasticsearch database
 */
public interface MovieRepository extends ElasticsearchRepository<Movie, String> {

    /**
     * Retrieve all movies that contains given string as a tittle
     * @param title
     * @return {@link List}<{@link Movie}>
     */
    List<Movie> getMoviesByTitle(String title);

    /**
     * Retrieve all movies with given year
     * @param year
     * @return {@link List}<{@link Movie}>
     */
    List<Movie> getMoviesByYear(int year);

    /**
     * Retrieve all movies with the combination of year and title
     * @param year
     * @param title
     * @return {@link List}<{@link Movie}>
     */
    List<Movie> getMoviesByYearAndTitle(int year, String title);
}
