package com.genome.dx.wcore.config;


import com.genome.dx.wcore.config.properties.WebCoreProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.ProtocolHandler;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatReactiveWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class WebServerFactoryCustomizerConfig implements WebServerFactoryCustomizer {

    @Autowired
    WebCoreProperties webCoreProperties;

    public void customize(final WebServerFactory factory) {
        final TomcatReactiveWebServerFactory containerFactory = (TomcatReactiveWebServerFactory) factory;

        containerFactory.addConnectorCustomizers(connector -> {
//            AbstractHttp11Protocol protocol = (AbstractHttp11Protocol) connector.getProtocolHandler();
            ProtocolHandler protocolHandler = connector.getProtocolHandler();
            if (protocolHandler instanceof AbstractHttp11Protocol) {
                applyProperties((AbstractHttp11Protocol) protocolHandler);
            }
        });
//        TomcatReactiveWebServerFactory
//        final AccessLogValve accessLogValve = new AccessLogValve();
//        accessLogValve.setPattern("%{yyyy-MM-dd HH:mm:ss}t\t%s\t%r\t%{User-Agent}i\t%{Referer}i\t%a\t%b");
//        accessLogValve.setDirectory(".");
//        accessLogValve.setSuffix(".log");
//        accessLogValve.setCondition("ignoreLogging");
//        containerFactory.addContextValves(accessLogValve);
    }

    private void applyProperties(AbstractHttp11Protocol protocolHandler) {
        if(null!= webCoreProperties.getHttp11Protocol() && null!= webCoreProperties.getHttp11Protocol().getMaxKeepAliveRequests()) {
            protocolHandler.setMaxKeepAliveRequests(webCoreProperties.getHttp11Protocol().getMaxKeepAliveRequests());
        }
        if(null!= webCoreProperties.getHttp11Protocol() && null!= webCoreProperties.getHttp11Protocol().getConnectionTimeout()) {
            protocolHandler.setConnectionTimeout(webCoreProperties.getHttp11Protocol().getConnectionTimeout());
        }
        if(null!= webCoreProperties.getHttp11Protocol() && null!= webCoreProperties.getHttp11Protocol().getKeepAliveTimeout()) {
            protocolHandler.setKeepAliveTimeout(webCoreProperties.getHttp11Protocol().getKeepAliveTimeout());
        }
        if(null!= webCoreProperties.getHttp11Protocol() && null!= webCoreProperties.getHttp11Protocol().getKeepAliveTimeout()) {
            protocolHandler.setKeepAliveTimeout(webCoreProperties.getHttp11Protocol().getKeepAliveTimeout());
        }
        if(null!= webCoreProperties.getHttp11Protocol() && null!= webCoreProperties.getHttp11Protocol().getAcceptorThreadCount()) {
            protocolHandler.setAcceptorThreadCount(webCoreProperties.getHttp11Protocol().getAcceptorThreadCount());
        }
        if(null!= webCoreProperties.getHttp11Protocol() && null!= webCoreProperties.getHttp11Protocol().getTcpNoDelay()) {
            protocolHandler.setTcpNoDelay(webCoreProperties.getHttp11Protocol().getTcpNoDelay());
        }
    }
}
