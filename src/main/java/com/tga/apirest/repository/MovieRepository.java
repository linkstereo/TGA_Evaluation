package com.tga.apirest.repository;

import com.tga.apirest.db.model.Movie;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface MovieRepository extends ElasticsearchRepository<Movie, String> {

    List<Movie> getMoviesByTitle(String title);

    List<Movie> getMoviesByYear(int year);

    List<Movie> getMoviesByYearAndTitle(int year, String title);
}
