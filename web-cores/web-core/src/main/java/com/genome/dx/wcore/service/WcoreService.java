package com.genome.dx.wcore.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.LoggingCodecSupport;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

@Service
public class WcoreService {

    public WebClient newWebClient() {
        return newWebClient(null);
    }
    public WebClient newWebClient(HttpHeaders defaultHeader) {
        return newWebClientBuilder(defaultHeader).build();
    }

    public WebClient.Builder newWebClientBuilder() {
        return newWebClientBuilder(null);
    }
    //filter https://stackoverflow.com/questions/46154994/how-to-log-spring-5-webclient-call
    //https://stackoverflow.com/questions/46154994/how-to-log-spring-5-webclient-call
    //https://www.baeldung.com/spring-log-webclient-calls
    public WebClient.Builder newWebClientBuilder(HttpHeaders defaultHeader) {
        ObjectMapper objectMapper  = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(
                SerializationFeature.WRITE_DATES_AS_TIMESTAMPS //erializationFeature.WRITE_DATES_AS_TIMESTAMPS = yyyy-mm-dd’T’HH:mm:ssZZ
        );
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(clientDefaultCodecsConfigurer -> {
                    clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper, MediaType.APPLICATION_JSON, MediaType.parseMediaType("application/json-patch+json")));
                    clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper, MediaType.APPLICATION_JSON, MediaType.parseMediaType("application/json-patch+json")));
                })
                .build();
        exchangeStrategies
                .messageWriters().stream()
                .filter(LoggingCodecSupport.class::isInstance)
                .forEach(writer -> ((LoggingCodecSupport) writer).setEnableLoggingRequestDetails(true));
//        HttpClient httpClient = HttpClient.create().wiretap(true);
        HttpClient httpClient = HttpClient.create(ConnectionProvider.newConnection()).wiretap(true);

        ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);
        WebClient.Builder builder = WebClient.builder();
        if(null != defaultHeader && defaultHeader.size() > 0 ) {
            builder.defaultHeaders(headers -> headers.putAll(defaultHeader));
        }

        builder.exchangeStrategies(exchangeStrategies);
        return builder.clientConnector(connector);
    }

}
