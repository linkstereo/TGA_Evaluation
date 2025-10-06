# TGA Evaluation

## HOW INSTALL

### INSTALACION DEL API REST

- Go to the project folder (create it if doesn't exist):
```console
cd <It is the place where you want to clone the project>
```
- Clone project TGA_Evaluation
```console
  git clone https://github.com/linkstereo/TGA_Evaluation
```
- For compiling and testing:
```console
./gradlew build
```
- Now to start up a full docker container with our API Rest 
and dedicated Elasticsearch database
```console
docker-compose up -d
```

## WHAT DOES IT CONTAIN?

### REST API
- INTRODUCTION
  - All requested evaluation points has been developed successfully.
  - There are 3 endpoints:
    - {host:port}/v1/elasticsearch-info
    - {host:port}/v1/movies/
    - {host:port}/v1/movies/fetch-and-update
  - For consuming hackerrank server I decided use feign client in order to have a code clean, reliable and easily scalable.
- ENDPOINTS
  - [GET] /v1/elasticsearch-info
    - I decided to use the elasticsearch info endpoint ({http://elasticsearchhost:9200/}). I thought it could be a good example by consuming from the remote client and check healthy 
  - [GET] /v1/movies
    - I decided to use this path following a simple naming. It can be used without parameters or one or both. The parameter are:
      - title: It filters all movies that contains the title (string)
      - year; It filters all movies that contains the specified year (int)
  - [PUT] /v1/movies/fetch-and-update
    - I decided to use this path to have a name accordingly with its functionality. It retrieves paginated data from the remote server and then saves it in a database.
- LOGGING
  - Logs were added at the beginning and at the ending of the key services.
- ERROR HANDLING
  - Errors are managed by an Advisor object which is configured as we need. In this case, for any Exception we have a clean formated json error with a message
- TESTING 
  - There are 1 integration tests all about fetching and filtering movies. I only mocked the hackerrank server. With Elasticsearch I used a specific test container for these purposes.
- HEALTH
  - Additionally, I configured an Actuator Health check. And therefore all endpoint from that framework:
    - [GET] /actuator/health
    - etc.
- DOCKER
  - The file docker-compose was created carefully in order to avoid timeout errors when spring boot tries to start up and Elasticsearch is still loading
- POSTMAN
  - You can find in the root of the project a postman collection file (ElasticSearchAPI.postman_collection.json) for all API Rest Services

