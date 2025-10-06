package com.tga.apirest.service;

import com.tga.apirest.client.HackerRankApiClient;
import com.tga.apirest.client.dto.MovieResponse;
import com.tga.apirest.db.model.Movie;
import com.tga.apirest.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Service
@Log4j2
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final HackerRankApiClient hackerRankApiClient;

    public List<Movie> getMovies(String title, String year) {

        log.info("Get Movies, [title: {}, year: {}]", title, year);

        List<Movie> result;

        if (isBlank(title) && isBlank(year)) {
            result = StreamSupport
                    .stream(movieRepository.findAll().spliterator(), false)
                    .toList();
        } else if (!isBlank(title) && isBlank(year)) {
            result = movieRepository.getMoviesByTitle(title);
        } else if (isBlank(title) && !isBlank(year)) {
            int yearInt = Integer.parseInt(year);
            result = movieRepository.getMoviesByYear(yearInt);
        } else {
            int yearInt = Integer.parseInt(year);
            result = movieRepository.getMoviesByYearAndTitle(yearInt, title);
        }

        log.info("Get Movies, total records: {}", result.size());

        return result;
    }

    public void fetchMovies(String substr) {

        log.info("Fetch Movies, [substr: {}]", substr);

        int totalPages = 1;
        int totalRecords = 0;
        MovieResponse page1 = hackerRankApiClient.searchMovies(substr, 1);

        if (page1 != null && page1.getData() != null && !page1.getData().isEmpty()) {
            totalPages = page1.getTotalPages();
            totalRecords = page1.getTotal();
            saveMoviesFromResponse(page1);
        }

        for (int page = 2; page <= totalPages; page++) {
            var pageN = hackerRankApiClient.searchMovies(substr, page);
            saveMoviesFromResponse(pageN);
        }

        log.info("Total pages fetched from remote: {}", totalPages);
        log.info("Total records fetched from remote: {}", totalRecords);
    }

    private void saveMoviesFromResponse(MovieResponse  movieResponse) {
        var movies = mapMovies(movieResponse);
        movieRepository.saveAll(movies);
    }

    private List<Movie> mapMovies(MovieResponse response) {
        return response.getData().stream()
                .map(item -> new Movie(item.getImdbID(), item.getTitle(), item.getYear()))
                .collect(Collectors.toList());
    }
}
