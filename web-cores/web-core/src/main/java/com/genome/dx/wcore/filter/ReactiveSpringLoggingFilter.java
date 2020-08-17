package com.genome.dx.wcore.filter;

import com.genome.dx.wcore.config.properties.WebCoreProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebExchangeDecorator;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
//@Order(-5)
public class ReactiveSpringLoggingFilter implements WebFilter {

    public static String HEADER_REQUEST_MILLIS = "x-server-request-millis";
//    static final Class<ServerHttpRequest> CONTEXT_KEY = ServerHttpRequest.class;
    public static ThreadLocal<ServerHttpRequest> requestLocal = new ThreadLocal();
    public static ThreadLocal<ServerHttpResponse> responseLocal = new ThreadLocal();
    public static ThreadLocal<String> uuidLocal = new ThreadLocal();

    @Autowired
    WebCoreProperties externalCommunicationCoreProperties;
    private UniqueIDGenerator generator = new UniqueIDGenerator();

    public ReactiveSpringLoggingFilter() {
    }

//    public static Mono<ServerHttpRequest> getRequest() {
//        return Mono.subscriberContext()
//                .map(ctx -> ctx.get(CONTEXT_KEY));
//    }
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        requestLocal.set(exchange.getRequest());
        responseLocal.set(exchange.getResponse());
//        if (ignorePatterns != null && exchange.getRequest().getURI().getPath().matches(ignorePatterns)) {
//        generator.generateAndSetMDC(exchange.getRequest());
        String uuid = UUID.randomUUID().toString();
        uuidLocal.set(uuid);
        final long startTime = System.currentTimeMillis();
        exchange.getResponse().getHeaders().add(HEADER_REQUEST_MILLIS, String.valueOf(new Date().getTime()));
        log.debug("Request: uuid={}, method={}, uri={}, headers={}", uuid, exchange.getRequest().getMethod(), exchange.getRequest().getURI(), exchange.getRequest().getHeaders());
        ServerWebExchangeDecorator exchangeDecorator = new ServerWebExchangeDecorator(exchange) {
            @Override
            public ServerHttpRequest getRequest() {
                return new RequestLoggingInterceptor(uuid, super.getRequest());
            }

            @Override
            public ServerHttpResponse getResponse() {
                return new ResponseLoggingInterceptor(uuid, super.getResponse(), startTime);
            }
        };

        return chain.filter(exchangeDecorator)
                .doOnSuccess(aVoid -> {
                    logResponse(uuid, startTime, exchangeDecorator.getResponse());
                })
                .doOnError(throwable -> {
                    logResponse(uuid, startTime, exchangeDecorator.getResponse(), throwable);
                });
    }


    private void logResponse(String uuid, long startTime, ServerHttpResponse response) {
//        final long duration = System.currentTimeMillis() - startTime;
//        log.debug("Response({} ms): uuid={}, status={}, headers={}", duration, uuid, response.getStatusCode(), response.getHeaders());
    }

    private void logResponse(String uuid, long startTime, ServerHttpResponse response, Throwable throwable) {
        final long duration = System.currentTimeMillis() - startTime;
        log.debug("Response({} ms): uuid={}, status={}, headers={}, throwable={}", duration, uuid, 500, response.getHeaders(), throwable.getMessage());
    }
}
