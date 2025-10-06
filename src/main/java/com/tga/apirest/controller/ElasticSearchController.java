package com.tga.apirest.controller;

import com.tga.apirest.dto.ElasticSearchInfo;
import com.tga.apirest.service.ElasticsearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.tga.apirest.controller.ControllerConstants.BASE_API_VERSION;
import static com.tga.apirest.controller.ControllerConstants.ELASTICSEARCH_INFO;

@RestController
@RequiredArgsConstructor
@RequestMapping(BASE_API_VERSION)
public class ElasticSearchController {

    private final ElasticsearchService elasticSearchService;

    @GetMapping(ELASTICSEARCH_INFO)
    public ElasticSearchInfo getElasticSearchInfo() throws Exception {
        return elasticSearchService.getClusterInfo();
    }
}
