package com.tga.apirest.controller;

import com.tga.apirest.db.model.Movie;
import com.tga.apirest.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tga.apirest.controller.ControllerConstants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(BASE_API_VERSION + MOVIES)
public class MoviesController {

    final private MovieService movieService;

    @GetMapping()
    public List<Movie> getMovies(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "year", required = false) String year) {
        return movieService.getMovies(title, year);
    }

    @PutMapping(FETCH_AND_UPDATE_MOVIES)
    public ResponseEntity fetchMovies(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "page", required = false) Integer page) {
        movieService.fetchMovies(title, page);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}