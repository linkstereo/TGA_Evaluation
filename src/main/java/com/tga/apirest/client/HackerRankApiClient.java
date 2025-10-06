package com.tga.apirest.client;

import com.tga.apirest.client.dto.MovieResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.tga.apirest.client.HackerRankClientConstants.HACKERRANK_HOST;
import static com.tga.apirest.client.HackerRankClientConstants.HACKERRANK_SEARCH_MOVIES;

/**
 * It is the Hackerranck´s client and provides a method to fetch movies form the remote server.
 */
@FeignClient(name = "hackerRankApiClient", url = HACKERRANK_HOST)
public interface HackerRankApiClient {

    String PARAM_TITLE = "Title";
    String PARAM_PAGE = "page";

    @GetMapping(HACKERRANK_SEARCH_MOVIES)
    MovieResponse searchMovies(@RequestParam(PARAM_TITLE) String title, @RequestParam(PARAM_PAGE) int page);
}