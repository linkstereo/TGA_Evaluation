package com.tga.apirest.controller;

public class ControllerConstants {

    /**
     * Version de la API
     */
    public static final String BASE_API_VERSION = "/v1";

    /**
     * Path to obtain elasticsearch database information
     */
    public static final String ELASTICSEARCH_INFO = "/elasticsearch-info";

    /**
     * Path for all services related with Movies
     */
    public static final String MOVIES = "/movies";

    /**
     * Path to fetch from the original server then create or update existing movies in the database.
     */
    public static final String FETCH_AND_UPDATE_MOVIES = "/fetch-and-update";

}
