package com.tga.apirest.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.tga.apirest.dto.ElasticSearchInfo;
import com.tga.apirest.dto.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class ElasticsearchService {

    private final ElasticsearchClient elasticsearchClient;

    @Autowired
    public ElasticsearchService(ElasticsearchClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }

    public ElasticSearchInfo getClusterInfo() throws Exception {
        return Stream.of(elasticsearchClient.info()).map(
                infoResponse ->
                    ElasticSearchInfo.builder()
                            .name(infoResponse.name())
                            .clusterName(infoResponse.clusterName())
                            .clusterUuid(infoResponse.clusterUuid())
                            .version(Version.builder()
                                    .number(infoResponse.version().number())
                                    .buildFlavor(infoResponse.version().buildFlavor())
                                    .buildType(infoResponse.version().buildType())
                                    .buildHash(infoResponse.version().buildHash())
                                    .buildDate(infoResponse.version().buildDate().toString())
                                    .buildSnapshot(infoResponse.version().buildSnapshot())
                                    .luceneVersion(infoResponse.version().luceneVersion())
                                    .minimumWireCompatibilityVersion(infoResponse.version().minimumWireCompatibilityVersion())
                                    .minimumIndexCompatibilityVersion(infoResponse.version().minimumIndexCompatibilityVersion())
                                    .build())
                            .tagline(infoResponse.tagline())
                            .build()).findFirst()
                .get();
    }
}
