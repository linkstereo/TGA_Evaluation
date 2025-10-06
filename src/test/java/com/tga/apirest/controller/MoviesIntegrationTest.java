package com.tga.apirest.controller;

import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import com.tga.apirest.client.HackerRankApiClient;
import com.tga.apirest.client.dto.MovieResponse;
import com.tga.apirest.client.dto.RemoteMovie;
import com.tga.apirest.repository.MovieRepository;
import com.tga.apirest.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(value = MockitoExtension.class)
@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class MoviesIntegrationTest {

    static final String ELASTICSEARCH_IMAGE = "docker.elastic.co/elasticsearch/elasticsearch:8.19.4";
    static final JacksonJsonpMapper JSONP_MAPPER = new JacksonJsonpMapper();

    @Container
    static ElasticsearchContainer elasticsearch = new ElasticsearchContainer(ELASTICSEARCH_IMAGE)
            .withEnv("discovery.type", "single-node")
            .withEnv("xpack.security.enabled", "false");

    private MockMvc mockMvc;

    @Mock
    private HackerRankApiClient hackerRankApiClient;

    @Autowired
    private MovieRepository movieRepository;

    private MovieService movieService;
    private MoviesController moviesController;

    @DynamicPropertySource
    static void elasticsearchProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.elasticsearch.uris", elasticsearch::getHttpHostAddress);
    }

    @BeforeEach
    public void setup() {

        movieService = new MovieService(movieRepository, hackerRankApiClient);
        moviesController = new MoviesController(movieService);
        mockMvc = MockMvcBuilders.standaloneSetup(moviesController).build();

        movieRepository.deleteAll();
    }

    @Test
    public void testIntegrationTestFetchingMoviesAndSavingAndObtainingFromElasticSearch() throws Exception {
        given(hackerRankApiClient.searchMovies(any(), anyInt())).willReturn(createMovieResponse());

        mockMvc.perform(put("/v1/movies/fetch-and-update"))
                .andExpect(status().isCreated());

        //Searching all movies with no params
        mockMvc.perform(get("/v1/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(10))
                .andExpect(jsonPath("$[0].title").value("The Making of 'Waterworld'"));

        //Searching movies that contains 'Waterworld' on its title
        mockMvc.perform(get("/v1/movies?title=Waterworld"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].title").value("The Making of 'Waterworld'"))
                .andExpect(jsonPath("$[1].title").value("Waterworld 4: History of the Islands"));

        //Searching movies released in 2015
        mockMvc.perform(get("/v1/movies?year=2015"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].title").value("Maze Runner: The Scorch Trials"))
                .andExpect(jsonPath("$[1].title").value("Into the Grizzly Maze"));

        //Searching movies that contains 'Waterworld' on its title and released in 2015
        mockMvc.perform(get("/v1/movies?title=Waterworld&year=2015"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(0));

        //Searching movies that contains 'The' word on its title and released in 1967
        mockMvc.perform(get("/v1/movies?title=The&year=1967"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].title").value("The Superman/Aquaman Hour of Adventure"));
    }

    private MovieResponse createMovieResponse() {
        List<RemoteMovie> movies = List.of(
                new RemoteMovie("The Making of 'Waterworld'", 1995, "tt2670548"),
                new RemoteMovie("Waterworld 4: History of the Islands", 1997, "tt0161077"),
                new RemoteMovie("Fighting, Flying and Driving: The Stunts of Spiderman 3", 2007, "tt1132238"),
                new RemoteMovie("The Superman/Aquaman Hour of Adventure", 1967, "tt0231046"),
                new RemoteMovie("Aquaman: The Teen Drama", 2013, "tt4018616"),
                new RemoteMovie("The Antman", 2002, "tt0321249"),
                new RemoteMovie("The Maze Runner", 2014, "tt1790864"),
                new RemoteMovie("Maze Runner: The Scorch Trials", 2015, "tt4046784"),
                new RemoteMovie("Into the Grizzly Maze", 2015, "tt1694021"),
                new RemoteMovie("Hercules in the Maze of the Minotaur", 1994, "tt0110018"));

        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setTotalPages(1);
        movieResponse.setPage(1);
        movieResponse.setTotal(10);
        movieResponse.setData(movies);

        return movieResponse;
    }
}