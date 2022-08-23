package com.example.back_stage_application.Configuration;


import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;



@Configuration
@EnableElasticsearchRepositories(basePackages = "com.example.back_stage_application.Repository")
@ComponentScan(basePackages ={"com.example.back_stage_application"})

public class Config extends AbstractElasticsearchConfiguration {

    @Value("${elasticsearch.url}")
    public String elasticsearchUrl;

    @Bean
    @Override
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration config = ClientConfiguration.builder()
                .connectedTo(elasticsearchUrl)
                .build();

        return RestClients.create(config).rest();
    }
}


//    @Override
//    @Bean
//    public RestHighLevelClient elasticsearchClient() {
///*        ClientConfiguration clientConfiguration
//                = ClientConfiguration.builder()
//                .connectedTo("localhost:9200")
//                .build();*/
//
//        RestClientBuilder builder = RestClient.builder(new HttpHost("localhost", 9200))
//                //.setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider))
//                .setDefaultHeaders(compatibilityHeaders());
//
//        return new RestHighLevelClient(builder);
//
//        //return RestClients.create(clientConfiguration).rest();
//    }
//
//    @Bean
//    public ElasticsearchOperations elasticsearchTemplate() {
//        return new ElasticsearchRestTemplate(elasticsearchClient());
//    }
//
//    private Header[] compatibilityHeaders() {
//        return new Header[]{new BasicHeader(HttpHeaders.ACCEPT, "application/vnd.elasticsearch+json;compatible-with=7"), new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/vnd.elasticsearch+json;compatible-with=7")};
//    }





