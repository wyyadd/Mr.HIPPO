package com.hippo.fresh.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//因为jpa和es冲突， 所以要指定扫描位置
@Configuration
@EnableJpaRepositories(basePackages = "com.hippo.fresh.dao")
@EnableElasticsearchRepositories(basePackages = "com.hippo.fresh.search")
@ComponentScan(basePackages = {"com.hippo.fresh"})
public class ElasticsearchClientConfig extends AbstractElasticsearchConfiguration {
    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {

        final ClientConfiguration clientConfiguration =
                ClientConfiguration
                        .builder()
                        .connectedTo("localhost:9200")
                        .build();

        return RestClients
                .create(clientConfiguration)
                .rest();
    }
}