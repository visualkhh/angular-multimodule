package com.genome.dx.wcore.filter;

import org.jboss.logging.MDC;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.UUID;

public class UniqueIDGenerator {
    private static final String	REQUEST_ID_HEADER_NAME		= "X-Request-ID";
    private static final String	CORRELATION_ID_HEADER_NAME	= "X-Correlation-ID";

    public void generateAndSetMDC(ServerHttpRequest request) {
        MDC.clear();
        String requestId = request.getHeaders().getFirst(REQUEST_ID_HEADER_NAME);
        if (requestId == null)
            requestId = UUID.randomUUID().toString();
        MDC.put(REQUEST_ID_HEADER_NAME, requestId);

        String correlationId = request.getHeaders().getFirst(CORRELATION_ID_HEADER_NAME);
        if (correlationId == null)
            correlationId = UUID.randomUUID().toString();
        MDC.put(CORRELATION_ID_HEADER_NAME, correlationId);
    }
}
