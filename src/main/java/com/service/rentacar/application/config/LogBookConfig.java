package com.service.rentacar.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.core.*;
import org.zalando.logbook.json.JsonBodyFilters;
import org.zalando.logbook.json.JsonHttpLogFormatter;

import java.util.Set;

import static java.util.Optional.ofNullable;
import static org.zalando.logbook.core.HeaderFilters.authorization;

@Configuration
public class LogBookConfig {

    private static final Set<String> IGNORED_CONTENT_TYPES = Set.of(
            "font/",
            "image/",
            "js/",
            "text/javascript",
            "text/html",
            "text/css"
    );

    private static boolean isIgnoredContentType(String contentType)
    {
        return IGNORED_CONTENT_TYPES.stream()
                .anyMatch(contentType::startsWith);
    }

    @Bean
    public Logbook logbook() {
        return Logbook.builder()
                .correlationId(new DefaultCorrelationId())
                .queryFilter(QueryFilters.replaceQuery("password", "<secret>"))
                .bodyFilter(JsonBodyFilters.replacePrimitiveJsonProperty((text) -> text.equalsIgnoreCase("password"), "<secret>"))
                .responseFilter(ResponseFilters.replaceBody(BodyReplacers.replaceBody(httpResponse -> isIgnoredContentType(ofNullable(httpResponse.getContentType()).orElse("")), "<skipped>")))
                .headerFilter(authorization())
                .sink(new DefaultSink(
                        new JsonHttpLogFormatter(),
                        new DefaultHttpLogWriter()
                ))
                .build();
    }
}
